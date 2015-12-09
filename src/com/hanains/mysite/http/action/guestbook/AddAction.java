package com.hanains.mysite.http.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.GuestBookDAO;
import com.hanains.mysite.vo.GuestBookVo;

public class AddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		 
		//Get Parameter
	 	String name = request.getParameter("name");
	 	String password =request.getParameter("pass");
	 	String content = request.getParameter("content");
	 
	 	GuestBookVo vo = new GuestBookVo(name, password, content);
	 	GuestBookDAO dao = new GuestBookDAO();
	 	
	 	//insert VO
	 	dao.insert(vo);
	 	
	 	//Redirect
		HttpUtil.redirect(response, "/mysite/gs");		
	}
	
}
