package day03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import day03.sql.MemberSQL;
import db.ClsDBCP;

public class MemberDao {

//	private ClsJDBC db;
	private ClsDBCP db;
	private MemberSQL sql;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	public MemberDao() {
//		db = new ClsJDBC();
		db = new ClsDBCP();
		sql = new MemberSQL();
	}

	public int getLoginCnt(String id, String pw) {
		int cnt = -1;
		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, sql.getSQL(sql.SEL_LOGIN_CNT));
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
}
