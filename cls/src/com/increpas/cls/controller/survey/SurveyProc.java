package com.increpas.cls.controller.survey;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.SurveyDAO;
import com.increpas.cls.util.SessionUtil;
import com.increpas.cls.vo.SurveyVO;

public class SurveyProc implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/cls/survey/surveyResult.cls";
		req.setAttribute("isRedirect", true);

		// 세션 검사하고 아이디 꺼내고
		String sid = SessionUtil.procSession(req, resp);
		// 카운트 변수
		int cnt = 0;

		// 파라미터 받고
		Enumeration<String> en = req.getParameterNames();
		ArrayList<SurveyVO> list = new ArrayList<SurveyVO>();
		try {
			while (en.hasMoreElements()) {
				String key = en.nextElement();
				if(key.equals("sno")) {
					continue;
				};
				String sno = req.getParameter(key);
				int qno = Integer.parseInt(sno);

				SurveyVO sVO = new SurveyVO();
				sVO.setId(sid);
				sVO.setQno(qno);

				list.add(sVO);
			}
		} catch (Exception e) {
		}

		SurveyDAO sDao = new SurveyDAO();
		cnt = sDao.addAnswer(list);
		req.setAttribute("VIEW", view);
		req.setAttribute("SNO", req.getParameter("sno"));

		if (cnt != list.size()) {
			view = "/cls/survey/surveyInfo.cls";
			req.setAttribute("VIEW", view);
		}

		return view;
	}

}
