package day03.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsJDBC {
	private final String CLASSPATH = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "hello";
	private String pw = "hello";

	public ClsJDBC(){
		try {
			Class.forName(CLASSPATH);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ClsJDBC(String url){
		try {
			Class.forName(CLASSPATH);
			this.url = url;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Connection getConnection(String id, String pw) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public PreparedStatement getPreparedStatement(Connection conn, String sql){
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return psmt;
	}

	public void close(Object obj) {
		try {
			if (obj instanceof ResultSet) {
				((ResultSet) obj).close();
			} else if (obj instanceof Statement) {
				((Statement) obj).close();
			} else if (obj instanceof PreparedStatement) {
				((PreparedStatement) obj).close();
			} else if (obj instanceof Connection) {
				((Connection) obj).close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
