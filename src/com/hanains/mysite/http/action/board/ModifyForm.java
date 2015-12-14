package com.hanains.mysite.http.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.vo.BoardVo;

public class ModifyForm implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String no = request.getParameter("no");
		String memberNo = request.getParameter("memberNo");
		if (no == null) {
			HttpUtil.redirect(response, "/mysite/bs");
			return;
		}
		Long lNo = Long.parseLong(no);

		// Get Board Info
		BoardDAO dao = new BoardDAO();
		BoardVo vo = dao.getBoardVo(lNo);
		vo.setMemberNo(Long.parseLong(memberNo));
		/*// Update view Count
		dao.updateViewCount(lNo);*/
		request.setAttribute("boardVo", vo);

		HttpUtil.forwarding(request, response, "/WEB-INF/views/board/modify.jsp");

	}

}
