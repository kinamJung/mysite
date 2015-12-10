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

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		BoardDAO dao = new BoardDAO();
		
		List<BoardInfo> list = dao.getListByFaging(1, 5);
		int tempSize = dao.getBoardCount() ;
		int size = (tempSize / 5) + 1;
		if( tempSize % 5 == 0 ){
			size = size-1;
		}
		
		request.setAttribute("size", size);
		request.setAttribute("list", list);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
		

	}

}
