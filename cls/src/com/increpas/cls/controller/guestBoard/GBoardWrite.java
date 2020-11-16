package com.increpas.cls.controller.guestBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.GBoardDAO;

public class GBoardWrite implements ClsMain{

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", true);
		String view = "/cls/guestBoard/guestBoard.cls";
		
		String id = req.getParameter("id");
		String body = req.getParameter("body");
		System.out.println(id + " : " + body);
		
		GBoardDAO dao = new GBoardDAO();
		int cnt = dao.addGBoard(id, body);
		System.out.println("Inserted Row CNT : " + cnt);
		if (cnt == 0) {
			view = "/cls/member/login.cls";
		}
		
		return view;
	}

}
