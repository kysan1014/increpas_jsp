package com.increpas.cls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.increpas.cls.sql.GBoardSQL;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.GuestBoardVO;

import db.ClsDBCP;

public class GBoardDAO {

	private ClsDBCP db;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	private GBoardSQL gSQL;

	public GBoardDAO() {
		db = new ClsDBCP();
		gSQL = new GBoardSQL();
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

	public int getIdCnt(String id) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.SEL_ID_CNT));
			psmt.setString(1, id);
			rs = psmt.executeQuery();
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

	public int addGBoard(String id, String body) {
		int cnt = -1;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.ADD_GBD));
			psmt.setString(1, id);
			psmt.setString(2, body);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public int addGBoard(ArrayList<HashMap<String, String>> list) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			for (HashMap<String, String> map : list) {
				psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.ADD_GBD));
				psmt.setString(1, map.get("id"));
				psmt.setString(2, map.get("body"));
				System.out.println(map.get("id") + " : " + map.get("body"));
				cnt += psmt.executeUpdate();
				psmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	public ArrayList<GuestBoardVO> getGBoardList() {
		ArrayList<GuestBoardVO> list = new ArrayList<GuestBoardVO>();

		conn = db.getConnection();
		psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.SEL_ALL_GBD));
		try {
			rs = psmt.executeQuery();
			GuestBoardVO vo = null;

			while (rs.next()) {
				vo = new GuestBoardVO();
				vo.setGno(rs.getInt("gno"));
				vo.setId(rs.getString("id"));
				vo.setBody(rs.getString("body"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWtime(rs.getTime("wdate"));
				vo.setAvatar(rs.getString("avatar"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public ArrayList<GuestBoardVO> getGBoardList(PageUtil page) {
		ArrayList<GuestBoardVO> list = new ArrayList<GuestBoardVO>();
	
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.SEL_GBD_ROW));
			psmt.setInt(1, page.getStartCont());
			psmt.setInt(2, page.getEndCont());
			rs = psmt.executeQuery();
			GuestBoardVO vo = null;
			while (rs.next()) {
				vo = new GuestBoardVO();
				vo.setRno(rs.getInt("rno"));
				vo.setGno(rs.getInt("gno"));
				vo.setId(rs.getNString("id"));
				vo.setBody(rs.getString("body"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWtime(rs.getTime("wdate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	public int getTotal() {
		int total = 0;
		
		conn = db.getConnection();
		psmt = db.getPreparedStatement(conn, gSQL.getSQL(gSQL.SEL_GBD_TCNT));
		try {
			rs = psmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return total;
	}

}
