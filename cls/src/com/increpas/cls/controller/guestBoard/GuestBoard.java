package com.increpas.cls.controller.guestBoard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.GBoardDAO;
import com.increpas.cls.vo.GuestBoardVO;

public class GuestBoard implements ClsMain{

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		
		GBoardDAO dao = new GBoardDAO();
		ArrayList<GuestBoardVO> list = dao.getGBoardList();
		req.setAttribute("LIST", list);
		
		String sid = "+";
		int cnt = 0;
		try {
			sid = (String) req.getSession().getAttribute("SID");
			if(!sid.equals("+")) {
				cnt = dao.getIdCnt(sid);
			}
		} catch(Exception e) {}
		System.out.println(sid);
		
		req.setAttribute("CNT", cnt);
		
		String view = "guestBoard/GuestBoard";
		return view;
	}

}
