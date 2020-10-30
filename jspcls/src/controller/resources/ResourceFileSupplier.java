package controller.resources;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = {"/css/*", "/js/*", "/img/*"}
		)
public class ResourceFileSupplier extends HttpServlet {

	private static final long serialVersionUID = 6903099272507722349L;
	private final String RESOURCE_DIR = "/WEB-INF/resources";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String spath = uri.substring(uri.indexOf('/', 1));

		RequestDispatcher rd = request.getRequestDispatcher(RESOURCE_DIR + spath);
		rd.forward(request, response);

	}
}
