package day03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import day03.db.ClsJDBC;
import day03.sql.MemberSQL;

public class MemberDao {

	private ClsJDBC jdbc;
	private MemberSQL sql;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;

	public MemberDao() {
		jdbc = new ClsJDBC();
		sql = new MemberSQL();
	}

	public int getLoginCnt(String id, String pw) {
		int cnt = -1;
		try {
			conn = jdbc.getConnection();
			psmt = jdbc.getPreparedStatement(conn, sql.getSQL(sql.SEL_LOGIN_CNT));
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
			jdbc.close(rs);
		}
		if (psmt != null) {
			jdbc.close(psmt);
		}
		if (stmt != null) {
			jdbc.close(stmt);
		}
		if (conn != null) {
			jdbc.close(conn);
		}
	}
}
