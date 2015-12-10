package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String no = request.getParameter("no");
		Long lNo = Long.parseLong(no); 
		
		BoardDAO dao = new BoardDAO();
		dao.deleteBoard(lNo);
		
		HttpUtil.redirect(response, "/mysite/bs");
		
	}

}
