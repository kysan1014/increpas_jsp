package com.increpas.cls.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.MemberDAO;

public class LoginProc implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", true);
		String view = "/cls/main.cls";
		
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.getLoginCnt(sid, spw);
		
		if (cnt != 1) {
			view = "/cls/member/login.cls";
		} else {
			req.getSession().setAttribute("SID", sid);
		}
		
		return view;
	}

}
