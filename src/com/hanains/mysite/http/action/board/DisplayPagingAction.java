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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class DisplayPagingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String no = request.getParameter("index");
		int iNo = Integer.parseInt(no);		
		String keyword = request.getParameter("search");
		
		
		BoardDAO dao = new BoardDAO();
		List<BoardInfo> list = null;
		
		//Paging 처리
		int paging = -1;
		int totCount = 0;
		if(keyword.equals("")){
			System.out.println("[info]DisplayPaginAction-equal");
			list =dao.getListByFaging(iNo, Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE);
			totCount = dao.getBoardCount();
			paging = (totCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE)+1;
		
			
		}else{
			System.out.println("[info]DisplayPaginAction-NotEqual");
			list = dao.getListByFaging(iNo, Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE, keyword);
			totCount = dao.getBoardCount(keyword);
			paging = (totCount / Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE)+1;
			
		}
	
		// 한 페이지에 보이는 게시글 수
		if (totCount % Common.SHOW_BOARD_WRITHING_COUNT_ON_PAGE == 0) {
			paging = paging - 1;
		} 
		
		System.out.println("dao count : "+ dao.getBoardCount()+"index: " + iNo + ",pagingSize: " +paging+ " keyword:" +keyword +", list크기:"+list.size() );
		request.setAttribute("index", iNo );
		request.setAttribute("search", keyword);
		request.setAttribute("size", paging);
		request.setAttribute("list", list);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/list.jsp");
		
	}

}
