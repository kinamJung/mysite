package com.hanains.mysite.http.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardInfo;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int pagingIndex = 1; //현재 페이지를 가리킬 변수
		String keyword = request.getParameter("kwd");
		//Query 
		BoardDAO dao = new BoardDAO();
		List<BoardInfo> list = dao.getListByFaging(pagingIndex, Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE, keyword);
		
		//한 페이지에 보이는 게시글 수		
		int paging = ( list.size() / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE + 1) ;
		if(paging % 5 == 0){
			paging = paging-1;
		}
		
		
		
		request.setAttribute("list", list);
		request.setAttribute("size", paging);
		request.setAttribute("index", pagingIndex);
		
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
		
	}

}
