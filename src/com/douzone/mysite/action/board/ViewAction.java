package com.douzone.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		
		BoardDao dao = new BoardDao();
		BoardVo vo = dao.get(group_no, order_no);
		vo.setGroup_no(group_no);
		vo.setOrder_no(order_no);
		vo.setDepth(depth);
		
		request.setAttribute("authuser", session.getAttribute("authuser"));
		request.setAttribute("vo", vo);
		
		WebUtils.forward(request, response, "/WEB-INF/views/board/view.jsp");

	}

}
