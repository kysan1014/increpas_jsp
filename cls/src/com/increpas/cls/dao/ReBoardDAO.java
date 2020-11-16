package com.increpas.cls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.cls.sql.ReBoardSQL;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.ReBoardVO;

import db.ClsDBCP;

public class ReBoardDAO {

	ClsDBCP db;
	Connection conn;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	ReBoardSQL rSQL;

	public ReBoardDAO() {
		db = new ClsDBCP();
		rSQL = new ReBoardSQL();
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

	public String getAvatarName(String id) {
		String aname = "";
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, rSQL.getSQL(rSQL.SEL_ID_AVT));
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				aname = rs.getString("avatar");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return aname;
	}

	public ArrayList<ReBoardVO> getReplyList(PageUtil page) {
		ArrayList<ReBoardVO> list = new ArrayList<ReBoardVO>();

		conn = db.getConnection();
		psmt = db.getPreparedStatement(conn, rSQL.getSQL(rSQL.SEL_RBD_RNO));
		try {
			psmt.setInt(1, page.getStartCont());
			psmt.setInt(2, page.getEndCont());
			rs = psmt.executeQuery();

			while (rs.next()) {
				ReBoardVO vo = new ReBoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setAno(rs.getInt("ano"));
				vo.setMno(rs.getInt("mno"));
				vo.setId(rs.getString("id"));
				vo.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setUpno(rs.getInt("upno"));
				vo.setStep(rs.getInt("step"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWtime(rs.getTime("wdate"));
				System.out.println(vo.toString());
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public int addBoard(ReBoardVO vo) {
		int cnt = 0;
		conn = db.getConnection();
		String sql = rSQL.getSQL(rSQL.ADD_BOARD);
		psmt = db.getPreparedStatement(conn, sql);
		try {
			// 질의명령 완성하고
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getBody());
			// 질의명령 보내고 결과받고
			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(psmt);
			db.close(conn);
		}
		return cnt;
	}

	public int delReboard(int bno) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, rSQL.getSQL(rSQL.DEL_REBOARD));
			psmt.setInt(1, bno);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public int editReboard(int bno, String body) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, rSQL.getSQL(rSQL.EDIT_REBOARD));
			psmt.setString(1, body);
			psmt.setInt(2, bno);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public int addContent(String id, String body) {
		int cnt = 0;
		conn = db.getConnection();
		String sql = rSQL.getSQL(rSQL.ADD_BOARD);
		psmt = db.getPreparedStatement(conn, sql);
		try {
			System.out.println(psmt);
			// 질의명령 완성하고
			psmt.setString(1, id);
			psmt.setString(2, body);
			// 질의명령 보내고 결과받고
			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(psmt);
			db.close(conn);
		}
		return cnt;
	}

	public int addComment(String id, String body, int upno) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, rSQL.getSQL(rSQL.ADD_REBOARD));
			psmt.setString(1, id);
			psmt.setString(2, body);
			psmt.setInt(3, upno);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public ArrayList<ReBoardVO> getIdList() {
		ArrayList<ReBoardVO> list = new ArrayList<ReBoardVO>();
		conn = db.getConnection();
		String sql = rSQL.getSQL(rSQL.SEL_MEM_ID);
		psmt = db.getPreparedStatement(conn, sql);
		try {
			rs = psmt.executeQuery();
			while (rs.next()) {
				ReBoardVO vo = new ReBoardVO();
				vo.setId(rs.getString("id"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int getCnt() {
		int cnt = 0;
		conn = db.getConnection();
		stmt = db.getStatement(conn);
		try {
			rs = stmt.executeQuery(rSQL.getSQL(rSQL.SEL_RBD_CNT));
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
}
