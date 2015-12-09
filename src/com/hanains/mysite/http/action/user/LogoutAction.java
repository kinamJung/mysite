package com.hanains.mysite.http.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if( session == null ) // 잘못된 접근
		{
			HttpUtil.redirect(response, "/mysite/main");
			return;
		}
		
		//Process Logout
		session.removeAttribute("authUser");
		session.invalidate(); // 세션 재발급
		HttpUtil.redirect(response, "/mysite/main");
		
	}

}
