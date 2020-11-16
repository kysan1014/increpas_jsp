package com.increpas.cls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.increpas.cls.sql.SurveySQL;
import com.increpas.cls.vo.SurveyVO;

import db.ClsDBCP;

public class SurveyDAO {
	ClsDBCP db;
	Connection conn;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;
	SurveySQL sSQL;

	public SurveyDAO() {
		db = new ClsDBCP();
		sSQL = new SurveySQL();
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

	public ArrayList<SurveyVO> getSIList(String id) {
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, sSQL.getSQL(sSQL.SEL_CURR_LIST));
			psmt.setNString(1, id);
			rs = psmt.executeQuery();

			while (rs.next()) {
				SurveyVO sVO = new SurveyVO();
				sVO.setSno(rs.getInt("sno"));
				sVO.setSbody(rs.getString("sbody"));
				sVO.setCnt(rs.getInt("cnt"));
				list.add(sVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	// 진행중인 설문문항 조회 전담 처리함수
	public ArrayList<SurveyVO> getQuestList(int sno) {
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();

		conn = db.getConnection();
		String sql = sSQL.getSQL(sSQL.SEL_QUEST_LIST);
		psmt = db.getPreparedStatement(conn, sql);
		try {
			psmt.setInt(1, sno);
			rs = psmt.executeQuery();
			while (rs.next()) {
				SurveyVO sVO = new SurveyVO();
				sVO.setSbody(rs.getString("sbody"));
				sVO.setSno(rs.getInt("sno"));
				sVO.setQno(rs.getInt("qno"));
				sVO.setUpno(rs.getInt("upno"));
				sVO.setQbody(rs.getString("qbody"));

				list.add(sVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(psmt);
			db.close(conn);
		}
		return list;
	}

	// 설문응답 데이터베이스 작업 전담 처리함수
	public int addAnswer(ArrayList<SurveyVO> list) {
		int cnt = 0;
		conn = db.getConnection();
		String sql = sSQL.getSQL(sSQL.ADD_ANSWER);

		try {
			for (int i = 0; i < list.size(); i++) {
				psmt = db.getPreparedStatement(conn, sql);
				psmt.setString(1, list.get(i).getId());
				psmt.setInt(2, list.get(i).getQno());

				cnt += psmt.executeUpdate();
				db.close(psmt);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	public ArrayList<SurveyVO> getResult(int sno) {
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();

		try {
			conn = db.getConnection();
			psmt = db.getPreparedStatement(conn, sSQL.getSQL(sSQL.SEL_ANSWER_RESULT));
			psmt.setInt(1, sno);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				SurveyVO sVO = new SurveyVO();
				sVO.setSbody(rs.getString("sbody"));
				sVO.setQno(rs.getInt("qno"));
				sVO.setQbody(rs.getString("qbody"));
				sVO.setSno(rs.getInt("sno"));
				sVO.setUpno(rs.getInt("upno"));
				sVO.setCnt(rs.getInt("cnt"));
				sVO.setPer(rs.getDouble("per"));
				
				list.add(sVO);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

}
