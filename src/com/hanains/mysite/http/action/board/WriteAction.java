package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.vo.BoardVo;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		Long lNo = Long.parseLong(no);
		
		BoardVo vo = new BoardVo(title, content, lNo);
		vo.setNo(lNo);
		
		BoardDAO dao = new BoardDAO();
		dao.insert(vo);
		
		HttpUtil.redirect(response, "/mysite/bs");
		
		
	}

}
