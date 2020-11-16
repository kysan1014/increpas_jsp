package controller.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controlable;

public class InfoController implements Controlable{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("isRedirect", null);
		
		String view = "{ \"name\"=\"김의산\" }";
		return view;
	}

}
