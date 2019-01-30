package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.CommentVo;

public class DeleteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long no = Long.parseLong(request.getParameter("no"));
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		
		CommentVo vo = new CommentVo();
		vo.setNo(no);
		new BoardDao().delete(vo);
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=view&group_no="+group_no+"&order_no="+order_no);

	}

}
