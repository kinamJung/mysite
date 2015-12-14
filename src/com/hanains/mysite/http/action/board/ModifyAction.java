package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.vo.BoardVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		String memberNo = request.getParameter("memberNo");
		Long lNo = Long.parseLong(no);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo(lNo);
		vo.setMemberNo(Long.parseLong(memberNo));
		
		BoardDAO dao = new BoardDAO();
		dao.updateBoard(vo);
		
		request.setAttribute("boardVo", vo);
		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/view.jsp");
		
	}

}
