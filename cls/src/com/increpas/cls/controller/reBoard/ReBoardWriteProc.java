package com.increpas.cls.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.ReBoardDAO;

public class ReBoardWriteProc implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {

		String sid = req.getParameter("id");
		System.out.println("댓글 작성한 아이디 : " + sid);
		String body = req.getParameter("body");

		ReBoardDAO dao = new ReBoardDAO();
		int cnt = dao.addContent(sid, body);
		String str = " 댓글게시판 게시글 등록 완료 ";
		if (cnt == 0) {
			str = " 댓글 게시판 글 등록 실패";
		}
		System.out.println(str);

		String view = "/cls/reBoard/reBoardList.cls";
		req.setAttribute("isRedirect", true);
		return view;
	}

}