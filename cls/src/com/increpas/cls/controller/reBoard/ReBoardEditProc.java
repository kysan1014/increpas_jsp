package com.increpas.cls.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.ReBoardDAO;

public class ReBoardEditProc implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", true);
		String view = "";
		
		String sid = "";
		try {
			sid = (String) req.getSession().getAttribute("SID");
		} catch(Exception e) {
			view = "/cls/member/login.cls";
			return view;
		}
		
		int bno = 0;
		String body = "";
		String spage = "1";
		try {
			bno = Integer.parseInt(req.getParameter("bno"));
			body = req.getParameter("body");
			spage = req.getParameter("nowPage");
		} catch (Exception e) {}

		ReBoardDAO dao = new ReBoardDAO();
		int cnt = dao.editReboard(bno, body);
		
		view = "/cls/reBoard/reBoardList.cls?nowPage=" + spage;

		return view;
	}

}
