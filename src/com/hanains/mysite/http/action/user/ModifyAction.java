package com.hanains.mysite.http.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.UserDao;
import com.hanains.mysite.vo.UserVo;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String no = request.getParameter("no");
		String password =request.getParameter("password"); // 사용자가 수정 시 입력한 현재 암호
		String uptPassword = request.getParameter("uptPassword"); // 바꿀 암호
		
		UserDao dao = new UserDao();
		String chkPassword = dao.getPassword(Long.parseLong(no));
		
		
		if(chkPassword.equals( password ) == true
				&& uptPassword.equals("") == false){
			UserVo vo = new UserVo();
			vo.setNo( Long.parseLong(no) );
			vo.setPassword(uptPassword);
			dao.setPassword(vo);
			HttpUtil.redirect(response, "/mysite/main");
		}else{
			HttpUtil.forwarding(request, response, "/WEB-INF/views/user/updateform_retry.jsp");
			return;
		}
		
	}

}
