package day03;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/main.cls")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MAIN_PAGE_PATH = "/WEB-INF/views/main.jsp";

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(MAIN_PAGE_PATH);
		rd.forward(request, response);
	}

}
