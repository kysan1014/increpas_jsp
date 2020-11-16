package controller.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ClsMain;

public class TagTest01 implements ClsMain{
	private final String VIEW = "test/foreach01";

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("isRedirect", false);
		return VIEW;
	}

}
