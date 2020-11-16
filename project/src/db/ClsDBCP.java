package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*

	커넥션 풀에 있는 커넥션을 이용해서 데이터베이스 작업을 할 유틸리티 클래스
	@author	전은석
	@since	2020.10.30
	@version	v.1.0
	
 */

public class ClsDBCP {

	// 커넥션 풀을 관리할 변수를 준비한다.
	public DataSource ds;

	/*
	 * 이 클래스는 누군가 new 시키면 context.xml에 등록된 자원을 가지고 등록된 자원을 얻어오도록 한다. 이처럼
	 * context.xml에 등록된 자원을 가지고 오는 기법을 JNDI
	 * 
	 * Java Naming and Directory Interface
	 * 
	 */
	public ClsDBCP() {

		try {

			// 1. context.xml 파일에 등록된 자원을 알아낸다.
			InitialContext context = new InitialContext();

			// 2. 그중 필요한 자원을 얻어낸다.
			ds = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
			/*
			 * 찾을 이름 작성 규칙 java:/comp/env/찾을이름 따라서 ==> java:/comp/env/jdbc/TestDB
			 * 
			 * 따라서 이 작업이 완료가 되면 커넥션 풀을 찾았다는 것이 된다.
			 */
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	/*
	 * 필요한 순간 접속을 해야 한다. 물론 접속은 직접하는 것이 아니고 커넥션 풀이 가지고 있는 커넥션을 빌려오는 것이다.
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			// 커넥션은 커넥션 풀을 관리하는 ds(DataResource)에서 빌려온다.
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// Statement 반환해주는 함수
	public Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	// PreparedStatement 반환해주는 함수
	public PreparedStatement getPreparedStatement(Connection conn, String sql) {
		PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return psmt;
	}

	// 사용하지 않는 자원 반환해주는 함수
	public void close(Object o) {
		try {
			if (o instanceof Connection) {
				((Connection) o).close();
			} else if (o instanceof Statement) {
				((Statement) o).close();
			} else if (o instanceof PreparedStatement) {
				((PreparedStatement) o).close();
			} else if (o instanceof ResultSet) {
				((ResultSet) o).close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
