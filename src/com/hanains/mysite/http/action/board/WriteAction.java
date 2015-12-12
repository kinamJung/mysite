package com.hanains.mysite.http.action.board;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDAO;
import com.hanains.mysite.util.Common;
import com.hanains.mysite.vo.BoardVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		Long lNo = Long.parseLong(no);

		// insert txt data
		BoardVo vo = new BoardVo(title, content, lNo);
		vo.setNo(lNo);

		BoardDAO dao = new BoardDAO();
		dao.insert(vo);

		// file upload
		ServletContext context = request.getServletContext();
		String uploadFilePath = context.getRealPath(Common.FILE_SAVE_PATH);

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
										Common.UPLOAD_FILE_SIZE_LIMIT,
										Common.ENC_TYPE, new DefaultFileRenamePolicy());

			String fileName = multi.getFilesystemName(Common.FILE_SAVE_PATH);
			System.out.println("[info] file name : " + fileName);
			if (fileName == null) {
				System.out.println("[error]파일 업로드 실패");
			}

		} catch (Exception e) {
			System.out.println("[error] 파일 업로드 실패");
		}

		HttpUtil.redirect(response, "/mysite/bs");

	}
}
