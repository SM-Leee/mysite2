package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		String user_no_string = request.getParameter("user_no");
		Long user_no = Long.parseLong(user_no_string);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(contents);
		vo.setUser_no(user_no);
		
		new BoardDao().insert(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=select");

	}

}
