package com.increpas.cls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.cls.sql.MemberSQL;
import com.increpas.cls.vo.AvatarVO;
import com.increpas.cls.vo.MemberVO;

import db.ClsDBCP;

public class MemberDAO {

//	private ClsJDBC db;
	private ClsDBCP db;
	private MemberSQL mSQL;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	public MemberDAO() {
//		db = new ClsJDBC();
		db = new ClsDBCP();
		mSQL = new MemberSQL();
	}

	public int getLoginCnt(String id, String pw) {
		int cnt = -1;
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.SEL_LOGIN_CNT));
			psmt.setString(1, id);
			psmt.setString(2, pw);
			rs = psmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();		}
		return cnt;
	}
	
	public int getIdCnt(String id) {
		int cnt = -1;
		
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.SEL_ID_CNT));
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}
	
	public ArrayList<AvatarVO> getAvtAll() {
		ArrayList<AvatarVO> list = new ArrayList<AvatarVO>();
		AvatarVO vo = null;
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.SEL_AVT_ALL));
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new AvatarVO();
				vo.setAno(rs.getInt("ANO"));
				vo.setSavename(rs.getString("SNAME"));
				vo.setGen(rs.getString("GEN"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	public void close() {
		if (rs != null) {
			db.close(rs);
		}
		if (psmt != null) {
			db.close(psmt);
		}
		if (stmt != null) {
			db.close(stmt);
		}
		if (conn != null) {
			db.close(conn);
		}
	}

	public int addMember(MemberVO vo) {
		int cnt = -1;
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.ADD_MEMB));
			System.out.println("MemberVO : " + vo.toString());
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getName());
			psmt.setString(4, vo.getMail());
			psmt.setString(5, vo.getGen());
			psmt.setInt(6, vo.getAvt());
			cnt = psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return cnt;
	}

	public MemberVO getMemberInfo(String id) {
		MemberVO vo = null;
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.SEL_MEMB_INFO));
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setMno(rs.getInt("mno"));
				vo.setId(id);
				vo.setName(rs.getString("name"));
				vo.setMail(rs.getString("mail"));
				vo.setGen(rs.getString("gen"));
				vo.setJoinDate(rs.getDate("joindate"));
				vo.setJoinTime(rs.getTime("joindate"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setAvt(rs.getInt("avt"));
				System.out.println("MemberDAO Query Result : " + vo.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	public int editMember(String sid, String mail, int avt) {
		int cnt = 0 ;
		conn = db.getConnection();
		psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.EDIT_MEMB));
		try {
			// 4. 질의명령 완성하고
			psmt.setString(1, mail);
			psmt.setInt(2, avt);
			psmt.setString(3, sid);
			// 5. 질의명령 보내고 결과받고
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		// 6. 결과 반환해주고
		return cnt;
	}

	public int delMemb(int mno, String spw) {
		int cnt = 0 ;
		// con
		conn = db.getConnection();

		// pstmt
		psmt = db.getPreparedStatement(conn, mSQL.getSQL(mSQL.DEL_MEMB));
		try{
			// 질의명령 완성하고
			psmt.setInt(1, mno);
			psmt.setString(2, spw);
			// 질의명령 보내고 결과받고
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(psmt);
			db.close(conn);
		}
		// 결과 반환해주고
		return cnt;
	}
}
