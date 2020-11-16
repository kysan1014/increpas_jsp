package controller.test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ClsMain;

public class TagTest02 implements ClsMain{
	private final String VIEW = "test/foreach02";

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("윤요셉");
		list.add("박진우");
		list.add("김영선");
		list.add("이지우");
		list.add("서현웅");
		list.add("김의산");
		list.add("권영선");
		list.add("장수진");
		req.setAttribute("LIST", list);
		req.setAttribute("isRedirect", false);
		return VIEW;
	}

}
