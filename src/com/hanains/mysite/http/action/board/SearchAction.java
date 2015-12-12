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

		int pagingIndex = 1; // 현재 페이지를 가리킬 변수
		List<BoardInfo> list = null;
		
		String keyword = request.getParameter("search");
		
		// Query
		BoardDAO dao = new BoardDAO();
		
		// 한 페이지에 보이는 게시글 수
		int onePagingShowBoard = -1;
		int boaodCount = 0; // 필터링된 board 총 개수
		if(keyword.equals("")){
			System.out.println("[info]SearchAction...equals");
			list = dao.getListByFaging(pagingIndex,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
			boaodCount = dao.getBoardCount() ;
			onePagingShowBoard = ( boaodCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) +1;
		}else
		{
			list = dao.getListByFaging(pagingIndex,
					Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE, keyword);
			boaodCount = dao.getBoardCount(keyword) ;
			onePagingShowBoard = (boaodCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) +1;
		}

		
		if (boaodCount % Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE == 0) {
			onePagingShowBoard = onePagingShowBoard - 1;
		} 
		
		System.out.println("[info]boardCount: "+boaodCount+", search: "+keyword+ ", list: " + list.size() +
							", onePagingShowBoard: "+ onePagingShowBoard +", index: "+pagingIndex);
		request.setAttribute("search", keyword);
		request.setAttribute("list", list);
		request.setAttribute("size", onePagingShowBoard);
		request.setAttribute("index", pagingIndex);

		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");

	}

}
