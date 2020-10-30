package controller.form;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form/login.cls")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String view = "/WEB-INF/views/member/Login.jsp";
    	
    	RequestDispatcher rd = request.getRequestDispatcher(view);
    	rd.forward(request, response);
    }

}
