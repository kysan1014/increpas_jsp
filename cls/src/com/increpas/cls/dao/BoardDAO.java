package com.increpas.cls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.cls.sql.BoardSQL;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.vo.FileVO;

import db.ClsDBCP;

public class BoardDAO {

	private ClsDBCP db;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement psmt;
	private ResultSet rs;
	private BoardSQL bSQL;

	public BoardDAO() {
		db = new ClsDBCP();
		bSQL = new BoardSQL();
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

	public int addBoard(BoardVO vo) {
		int cnt = 0;

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, bSQL.getSQL(bSQL.ADD_BOARD));
			psmt.setString(1, vo.getTitle());
			psmt.setString(2, vo.getBody());
			psmt.setString(3, vo.getId());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	}

	
	// 파일정보 입력 전담 처리함수
	public int addFile(FileVO fVO) {
		int cnt = 0;
		conn = db.getConnection();
		String sql = bSQL.getSQL(bSQL.ADD_FILE);
		psmt = db.getPreparedStatement(conn, sql);
		try {
			// 질의명령 완성
			psmt.setString(1, fVO.getId());
			psmt.setString(2, fVO.getOriname());
			psmt.setString(3, fVO.getSavename());
			psmt.setLong(4, fVO.getLen());
			
			// 질의보내고 결과받고
			cnt = psmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(psmt);
			db.close(conn);
		}
		
		return cnt;
	}
	
	// 파일정보 입력 전담 처리함수2
	public int addFile(ArrayList<FileVO> list) {
		int cnt = 0;
		// 위에서 만든 단일파일 업로드 함수를 재사용하기로 한다.
		for(FileVO fVO : list) {
			cnt += addFile(fVO);
		}
		
		return cnt;
	}
	
	public ArrayList<BoardVO> getBoardList(PageUtil page) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, bSQL.getSQL(bSQL.SEL_BOARD_LIST));
			psmt.setInt(1, page.getStartCont());
			psmt.setInt(2, page.getEndCont());
			rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setBclick(rs.getInt("bclick"));
				vo.setWdate(rs.getDate("bdate"));
				vo.setWtime(rs.getTime("bdate"));
				vo.setId(rs.getString("id"));
				vo.setCnt(rs.getInt("cnt"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	public int getBoardCnt() {
		int cnt = 0;

		try {
			conn = db.getConnection();
			stmt = db.getStatement(conn);
			rs = stmt.executeQuery(bSQL.getSQL(bSQL.SEL_BOARD_CNT));
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

	public ArrayList<String> getIdList() {
		ArrayList<String> list = new ArrayList<String>();

		try {
			conn = db.getConnection();
			stmt = db.getStatement(conn);
			rs = stmt.executeQuery(bSQL.getSQL(bSQL.SEL_MEMB_LIST));
			while (rs.next()) {
				list.add(rs.getString("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
