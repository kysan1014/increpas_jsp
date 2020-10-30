package day04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/day04/rdrtTest.cls")
public class Test01_01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "/WEB-INF/views/day04/Test01.jsp";
		view += "?id=" + request.getParameter("id") + "&pw=" + request.getParameter("pw");
		response.sendRedirect(view);
	}

}
