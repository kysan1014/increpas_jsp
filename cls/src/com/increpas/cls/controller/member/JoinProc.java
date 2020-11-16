package com.increpas.cls.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.vo.MemberVO;

public class JoinProc implements ClsMain{
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", true);
		
		String view = "/cls/guestBoard/guestBoard.cls";
		
		MemberVO vo = new MemberVO();
		vo.setName(req.getParameter("name"));
		vo.setId(req.getParameter("id"));
		vo.setPw(req.getParameter("pw"));
		vo.setMail(req.getParameter("mail"));
		vo.setTel(req.getParameter("tel"));
		vo.setGen(req.getParameter("gen"));
		vo.setAvt(Integer.parseInt(req.getParameter("avt")));
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.addMember(vo);
		
		if (cnt == 0) {
			view = "/cls/member/join.cls";
		} else {
			req.getSession().setAttribute("SID", req.getParameter("id"));
		}
		
		return view;
	}

	
	
}
