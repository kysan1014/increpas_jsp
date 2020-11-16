package com.increpas.cls.controller.kakao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;

public class KakaoTest implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		
		String view = "kakao/KakaoTest";
		
		return view;
	}

}
