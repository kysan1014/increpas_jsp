package com.increpas.cls.controller.guestBoard;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.GBoardDAO;

public class AddGBD implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", true);
		String view = "/cls/guestBoard/guestBoard.cls";
		
		GBoardDAO dao = new GBoardDAO();
		int cnt = dao.addGBoard(getList());

		return view;
	}

	public ArrayList<HashMap<String, String>> getList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("id", "euns");
		map1.put("body", "인사말을 등록하세요!");
		list.add(map1);
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("id", "joo");
		map2.put("body", "내가 일등이다!!");
		list.add(map2);
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("id", "jiwoo");
		map3.put("body", "제발 지우지 마세요.");
		list.add(map3);
		HashMap<String, String> map4 = new HashMap<String, String>();
		map4.put("id", "jjang");
		map4.put("body", "오늘 가입했습니다.");
		list.add(map4);
		HashMap<String, String> map5 = new HashMap<String, String>();
		map5.put("id", "park");
		map5.put("body", "인사말을 등록하세요!");
		list.add(map5);
		
		return list;
	}
}
