package com.increpas.cls.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;

public class Board implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "board/BoardList";
		req.setAttribute("isRedirect", false);
		
		int nowPage = 1;
		try {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
			System.out.println("nowPage = " + nowPage);
		} catch (Exception e) {}
		
		BoardDAO dao = new BoardDAO();
		int totalCount = dao.getBoardCnt();
		PageUtil page = new PageUtil(nowPage, totalCount);
		ArrayList<BoardVO> list = dao.getBoardList(page);
		
		System.out.print("BoardVO List : ");
		for (BoardVO vo : list) {
			System.out.print(vo.getBno() + " ");
		}
		System.out.println();
		
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		return view;
	}

}

