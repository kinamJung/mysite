package com.hanains.mysite.http.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao dao = new UserDao();
		UserVo vo = dao.get(email, password);
		
		if(vo == null){
			HttpUtil.forwarding(request, response, "/WEB-INF/views/user/loginform_retry.jsp");
			return; // redirect는 화면은 바뀌지만 코드는 끝나지 않는다 -> 고로 에러 발생할 확률이 크다 따라서 꼭 Return을 시킨다.
		}
		//Success Login
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", vo);
		HttpUtil.redirect(response, "/mysite/main");
		
	}

}
