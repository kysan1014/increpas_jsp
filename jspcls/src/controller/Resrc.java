package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@WebServlet({"/css/*", "/js/*", "/img/*"})*/
public class Resrc extends HttpServlet {
	/*
	 * css 파일을 요청하는 경우 /css/w3.css /css/cls.css /css/member/member.css
	 * 
	 * js 파일을 요청하는 경우 /js/jquery-3.5.1.min.js /js/member.js
	 */
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		String url = req.getRequestURI();
		// 파일 경로 : /WEB-INF/resources/css/member/member.css

		url = url.substring(url.indexOf("/", 1));

		String view = "/WEB-INF/resources" + url;
		System.out.println(view);

		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
