package com.hanains.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.action.Action;
import com.hanains.http.action.ActionFactory;
import com.hanains.mysite.http.action.board.BoardActionFactory;
import com.hanains.mysite.http.action.guestbook.GuestBookActionFactory;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/bs")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		ActionFactory actionFactory = new BoardActionFactory();
		String actionName = request.getParameter("a");
		
		Action action = actionFactory.getAction(actionName);

		action.execute(request, response);

	}
	
	

}
