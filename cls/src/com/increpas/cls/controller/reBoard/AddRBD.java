package com.increpas.cls.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.service.AddRBDService;

public class AddRBD implements ClsMain {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/cls/reBoard/reBoardList.cls";
		req.setAttribute("isRedirect", true);

		AddRBDService service = new AddRBDService();
		
		int cnt = service.writeRBD();
		System.out.println("글 입력 수 : " + cnt);
		
		return view;
	}
}
