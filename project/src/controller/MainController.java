package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController implements Controlable {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("isRedirect", false);
		
		String view = "main";
		return view;
	}

}
