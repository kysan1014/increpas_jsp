package com.increpas.cls.dispatch;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ClsMain;

public class ClsDispatch extends HttpServlet {

	private static final long serialVersionUID = -2954609600034660599L;
	private HashMap<String, ClsMain> map;

	@Override
	public void init(ServletConfig config) throws ServletException {
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {

			String spath = this.getClass().getResource("").getPath();
//			System.out.println("### dispatch spath : " + spath);

			fin = new FileInputStream(spath + "../resources/clsProperties.properties");
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

		map = new HashMap<String, ClsMain>();

		Iterator<Object> iter = prop.keySet().iterator();

		while (iter.hasNext()) {
			String key = (String) iter.next();
			String classPath = (String) prop.get(key);

			Class<?> temp;
			try {
				temp = Class.forName(classPath);
				ClsMain cls = (ClsMain) temp.newInstance();
//				System.out.println(key);
				map.put(key, cls);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String prefix = "/WEB-INF/views/";
		String suffix = ".jsp";
		
		String fullURI = request.getRequestURI();
		System.out.println("### dispatch full : " + fullURI);
		String realPath = fullURI.substring(fullURI.indexOf('/', 1));

		ClsMain cls = map.get(realPath);
		System.out.println("### dispatch realpath : " + realPath);
		Boolean bool = null;

		/*
		 * bool == null : 비동기 통신 처리 bool == false : forward bool == true : redirect
		 */

		String view = cls.exec(request, response);

		try {
			bool = (Boolean) request.getAttribute("isRedirect");
		} catch (NullPointerException ne) {
		}

		if (bool == null) {
			PrintWriter pw = response.getWriter();
			pw.print(view);
		} else if (bool) {
			response.sendRedirect(view);
		} else if (!bool) {
			RequestDispatcher rd = request.getRequestDispatcher(prefix + view + suffix);
			rd.forward(request, response);
		}

	}

}
