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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardInfo> list = dao.getListByFaging(1, Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
		int tempSize = dao.getBoardCount() ;
		int size = (tempSize / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE) + 1;
		if( tempSize % 5 == 0 ){
			size = size-1;
		}
		
		request.setAttribute("size", size);
		request.setAttribute("list", list);
		request.setAttribute("index", 1);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
		

	}

}
