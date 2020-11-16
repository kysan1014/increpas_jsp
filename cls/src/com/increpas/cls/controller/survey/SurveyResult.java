package com.increpas.cls.controller.survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.SurveyDAO;
import com.increpas.cls.vo.SurveyVO;

public class SurveyResult implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "survey/SurveyResult";
		req.setAttribute("isRedirect", false);
		String tno = req.getParameter("sno");
		int sno = 0;
		try {
			sno = Integer.parseInt(tno);
		} catch (Exception e) {
			view = "survey/SurveyRedirect";
			String rview = "/cls/survey/surveyInfo.cls";
			req.setAttribute("VIEW", rview);
		}
		
		SurveyDAO dao = new SurveyDAO();
		ArrayList<SurveyVO> list = dao.getResult(sno);
		
		req.setAttribute("LIST", list);
		
		return view;
	}

}
