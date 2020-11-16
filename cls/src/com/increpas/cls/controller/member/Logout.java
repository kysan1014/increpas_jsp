package com.increpas.cls.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.increpas.cls.controller.ClsMain;

public class Logout implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.setAttribute("SID", null);
		String view = "/cls/main.cls";
		req.setAttribute("isRedirect", true);
		return view;
	}

}
