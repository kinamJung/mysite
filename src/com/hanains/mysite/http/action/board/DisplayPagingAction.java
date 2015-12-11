package com.hanains.mysite.http.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.vo.BoardInfo;

public class DisplayPagingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String no = request.getParameter("index");
		int iNo = Integer.parseInt(no);
		
		String size = request.getParameter("size");
		int iSize = Integer.parseInt(size);
		
		
		BoardDAO dao = new BoardDAO();
		List<BoardInfo> list =dao.getListByFaging(iNo, 5);
		
		
		request.setAttribute("size", iSize);
		request.setAttribute("list", list);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
		
	}

}
