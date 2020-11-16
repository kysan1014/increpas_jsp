package com.increpas.cls.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.ReBoardDAO;

public class ReBoardDelProc implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		
		String sno = req.getParameter("bno");
		String spage = req.getParameter("nowPage");
		int bno = 0;
		try {
			bno = Integer.parseInt(sno);
		} catch (Exception e) {}
		
		if (bno != 0) {
			ReBoardDAO dao = new ReBoardDAO();
			int cnt = dao.delReboard(bno);
			if (cnt != 0) {
				System.out.println(bno + "번 글 삭제 완료 ");
			} else {
				System.out.println(bno + "번 글 삭제 실패 ");
			}
		}
		
		String view = "/cls/reBoard/reBoardList.cls?nowPage=" + spage;
		req.setAttribute("isRedirect", true);
		return view;
	}

}
