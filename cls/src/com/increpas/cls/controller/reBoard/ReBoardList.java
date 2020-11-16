package com.increpas.cls.controller.reBoard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.ReBoardDAO;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.ReBoardVO;

public class ReBoardList implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "reBoard/ReBoardList";
		req.setAttribute("isRedirect", false);
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
		} catch (Exception e) {}
		
		ReBoardDAO dao = new ReBoardDAO();
		
		int total = dao.getCnt();
		
		PageUtil page = new PageUtil(nowPage, total, 10, 3);
		ArrayList<ReBoardVO> list = dao.getReplyList(page);
		
		String avatar = "noimage.jpg";
		String sid = "";
		try {
			sid = (String) req.getSession().getAttribute("SID");
		} catch (Exception e) {}
		avatar = dao.getAvatarName(sid);

		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		req.setAttribute("AVTIMG", avatar);
		
		return view;
	}

	
	
}
