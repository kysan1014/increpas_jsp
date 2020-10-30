package day02;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day02/Test01.cls")
public class Test01 extends HttpServlet {

	private static final long serialVersionUID = -4014180119863309981L;

	public void service(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/WEB-INF/views/day02/Test01.jsp";
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			rd.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
