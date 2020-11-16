package com.increpas.cls.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.vo.MemberVO;

public class MemberInfo implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		String id = req.getParameter("id");
		System.out.println("ID : " + id);
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMemberInfo(id);
//		req.getSession().setAttribute("data", vo);
		req.setAttribute("DATA", vo);

		System.out.println("Member Info : " + vo.toString());
		
		String view = "member/MemberInfo";

		return view;
	}

}
