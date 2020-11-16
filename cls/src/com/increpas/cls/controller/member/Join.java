package com.increpas.cls.controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.vo.AvatarVO;

public class Join implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		String view = "member/Join";
		
		MemberDAO dao = new MemberDAO();
		ArrayList<AvatarVO> list = dao.getAvtAll();

		for (AvatarVO vo : list) {
			System.out.println(vo.toString());
		}
		
		req.setAttribute("LIST", list);
		
		return view;
	}

}
