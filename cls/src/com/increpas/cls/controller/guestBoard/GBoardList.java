package com.increpas.cls.controller.guestBoard;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cls.controller.ClsMain;
import com.increpas.cls.dao.GBoardDAO;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.GuestBoardVO;

public class GBoardList implements ClsMain {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "guestBoard/GBoardList";
		req.setAttribute("isRedirect", false);
		
		// 파라미터 받고
		int nowPage = 1 ;
		try {
			nowPage = Integer.parseInt(req.getParameter("nowPage"));
			System.out.println("Param : " + nowPage);
		}catch(Exception e) {}
		
		// PageUtil 객체 만들고
		GBoardDAO gDao = new GBoardDAO();
		int total = gDao.getTotal();
		PageUtil page = new PageUtil(nowPage, total);
		
		System.out.println("Start Page : " + page.getStartCont() + ", End Page : " + page.getEndCont());
		
		// 데이터베이스에서 데이터 가져오고
		ArrayList<GuestBoardVO> list = gDao.getGBoardList(page);
		System.out.println("### list size : " + list.size());
		int cnt = 0;
		try{
			String sid = (String) req.getSession().getAttribute("SID");
			/*
			// list 활용하는 방법
			for(int i = 0 ; i < list.size(); i++ ) {
				GuestBoardVO gVO = list.get(i);
				String tid = gVO.getId();
				if(sid.equals(tid)) {
					cnt = 1;
				}
			}
			*/
			
			// 질의명령을 한번더 보내서 처리하는 방법
			cnt = gDao.getIdCnt(sid);
		} catch(Exception e) {}
		System.out.println("GBoardList : " + list.toString());
		// 뷰에 데이터 심고
		req.setAttribute("LIST", list);
		req.setAttribute("CNT", cnt);
		req.setAttribute("PAGE", page);
		
		return view;
	}

}
