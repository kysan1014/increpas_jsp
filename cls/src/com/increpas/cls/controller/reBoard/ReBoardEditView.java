package com.increpas.cls.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;

public class ReBoardEditView implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
			
		String body = req.getParameter("body").replace("\r\n", "<br>");
		req.setAttribute("BODY", body);
		
		String view = "reBoard/ReBoardEdit";
		return view;
	}

}
