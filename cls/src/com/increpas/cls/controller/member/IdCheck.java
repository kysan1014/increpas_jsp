package com.increpas.cls.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.MemberDAO;

public class IdCheck implements ClsMain{

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		StringBuffer buff = new StringBuffer();

		String sid = req.getParameter("id");
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.getIdCnt(sid);

		String result = "";
		if (cnt == 0) {
			result = "OK";
		} else {
			result = "NO";
		}
		buff.append("{");
		buff.append("	\"result\":\"" + result + "\"");
		buff.append("}");

		return buff.toString();
	}

}
