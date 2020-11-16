package controller.resrc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResourcesController extends HttpServlet{

	private static final long serialVersionUID = -6804409197265631241L;
	private final String RESOURCES_DIR = "/WEB-INF/resources";
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.indexOf('/', 1));
		RequestDispatcher rd = request.getRequestDispatcher(RESOURCES_DIR + path);
		rd.forward(request, response);
	}
}
