package dispatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Controlable;

public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String VIEW_DIR = "/WEB-INF/views/";	
	private final String VIEW_SUFFIX = ".jsp";
	private HashMap<String, Controlable> map;

	
	@Override
	public void init() throws ServletException {
		map = new HashMap<String, Controlable>();
		
		String path = this.getClass().getResource("").getPath();
		String propPath = path + "../resources/properties.properties";
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(propPath);
			prop.load(fin);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Iterator<Object> iter = prop.keySet().iterator();
		while(iter.hasNext()) {
			String key = (String) iter.next();
			String classPath = (String) prop.get(key);
			
			Controlable cont;
			try {
				cont = (Controlable) Class.forName(classPath).newInstance();
//				System.out.println(cont);
				map.put(key, cont);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("================ Servlet Initiated ================");
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String key = uri.substring(uri.indexOf('/', 1));

//		System.out.println(map.get(key));
		String view = map.get(key).execute(request, response);
		
		Boolean isRedirect = null;
		try {
			isRedirect = (Boolean) request.getAttribute("isRedirect");
		} catch (NullPointerException ne) { }
		if (isRedirect == null) {
			PrintWriter out = response.getWriter();
			out.print(view);
		} else if (isRedirect) {
			response.sendRedirect(VIEW_DIR + view + VIEW_SUFFIX);
		} else if (!isRedirect) {
			RequestDispatcher rd = request.getRequestDispatcher(VIEW_DIR + view + VIEW_SUFFIX);
			rd.forward(request, response);
		}
		
	}

}
