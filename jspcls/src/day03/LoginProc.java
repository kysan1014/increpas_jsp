package day03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import day03.dao.MemberDao;

@WebServlet("/day03/loginProc.cls")
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mainView = "/jspcls/pre/main.cls";
		String loginView = "/jspcls/day03/login.cls";
		String sid = null;
		HttpSession session = request.getSession();
		try {
			sid = (String) session.getAttribute("sid");

			if (sid != null) {
				response.sendRedirect(mainView);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		sid = request.getParameter("id");
		String spw = request.getParameter("pw");

		System.out.println(sid + " : " + spw);

		MemberDao dao = new MemberDao();
		int cnt = dao.getLoginCnt(sid, spw);
		System.out.println(cnt);

		if (cnt == 1) {
			session.setAttribute("sid", sid);
			session.setAttribute("spw", spw);
			response.sendRedirect(mainView);
		} else if (cnt != 1) {
			response.sendRedirect(loginView);
		}

	}

}
