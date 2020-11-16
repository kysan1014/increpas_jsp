package com.increpas.cls.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.dao.ReBoardDAO;

public class MainController implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		String view = "main";
		
		int cnt = new ReBoardDAO().getCnt();
		req.setAttribute("RCNT", cnt);
		
		return view;
	}

}
