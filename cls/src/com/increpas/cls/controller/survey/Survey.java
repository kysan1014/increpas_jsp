package com.increpas.cls.controller.survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.SurveyDAO;
import com.increpas.cls.util.SessionUtil;
import com.increpas.cls.vo.SurveyVO;

public class Survey implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		new SessionUtil();
		// 세션 검사하고
		SessionUtil.procSession(req, resp);
		
		String view = "survey/Survey";
		req.setAttribute("isRedirect", false);
		
		// 파라미터 꺼내고
		String strno = req.getParameter("sno");
		int sno = 0 ;
		try {
			sno = Integer.parseInt(strno);
			SurveyDAO sDao = new SurveyDAO();
			ArrayList<SurveyVO> list = sDao.getQuestList(sno);
			
			req.setAttribute("LIST", list);
			req.setAttribute("SNO", sno);
		} catch(Exception e) {
			req.setAttribute("isRedirect", true);
			view = "/cls/survey/surveyInfo.cls";
		}
		
		return view;
	}

}
