package day03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day03/reqBlue.cls")
public class ReqBlue extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "/jspcls/day03/reqRed.cls";
		response.sendRedirect(view);
	}
}
