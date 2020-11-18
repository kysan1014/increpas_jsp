package com.increpas.cls.service;

import java.util.ArrayList;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.vo.BoardVO;

public class BoardInitService {

	BoardDAO dao;
	
	public BoardInitService() {
		dao = new BoardDAO();
	}
	
	public int setDB( ) {
		int cnt = 0;
		
		ArrayList<String> list = dao.getIdList();
		
		for (int i = 0; i < list.size(); i++) {
			BoardVO vo = new BoardVO();
			vo.setId(list.get(i));
			vo.setTitle("Title" + (i + 1));
			vo.setBody("Body" + (i + 1));
			cnt += dao.addBoard(vo);
		}
		
		return cnt;
	}
	
}
