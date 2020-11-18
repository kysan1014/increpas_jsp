package com.increpas.cls.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.service.BoardInitService;

public class InitDB implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/cls/board/boardList.cls";
		req.setAttribute("isRedirect", true);
		
		BoardInitService bservice = new BoardInitService();
		int cnt = bservice.setDB();
		System.out.println(cnt + "개 행 삽입");
		
		return view;
	}
	
}
