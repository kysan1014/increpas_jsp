package com.increpas.cls.service;

import java.util.ArrayList;

import com.increpas.cls.dao.ReBoardDAO;
import com.increpas.cls.vo.ReBoardVO;

public class AddRBDService {

	ReBoardDAO dao;
	public AddRBDService() {
		dao = new ReBoardDAO();
	}
	
	public ArrayList<ReBoardVO> getList() {
		ArrayList<ReBoardVO> list = getIdList();
		for(ReBoardVO vo : list) {
			vo.setBody(vo.getId() + "씨가 글을 씁니다.");
		}
		return list;
	}
	
	public int writeRBD() {
		int cnt = 0;
		ArrayList<ReBoardVO> list = getList();
		for(ReBoardVO vo : list) {
			cnt += dao.addBoard(vo);
		}
		return cnt;
	}
	
	public ArrayList<ReBoardVO> getIdList() {
		/*
		ArrayList<String> list = new ArrayList<String>();
		list.add("euns");
		list.add("joo");
		list.add("euisan");
		list.add("sun");
		list.add("mygusdnd");
		list.add("smkim");
		list.add("jjang");
		list.add("joseph");
		list.add("kys");
		list.add("chan");
		list.add("wook");
		list.add("jieun");
		list.add("jeong");
		list.add("jiwoo");
		list.add("juhyun");
		list.add("park");
		list.add("jang");
		list.add("jinwoo");
		list.add("hh");
		list.add("hong");
		list.add("dooly");
		*/
		
		ArrayList<ReBoardVO> list = dao.getIdList();
		
		return list;
	}
	
}
