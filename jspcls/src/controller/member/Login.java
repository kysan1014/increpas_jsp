package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.ClsMain;

public class Login implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "member/Login";
		req.setAttribute("isRedirect", false);
		
		HttpSession session = req.getSession();
		String sid = (String) session.getAttribute("SID");
		if (sid == null) {
			req.setAttribute("isRedirect", false);
		} else {
			req.setAttribute("isRedirect", false);
			view = "main";
		}
		
		return view;
	}

}
