package com.douzone.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mvc.util.WebUtils;
import com.douzone.mysite.repository.BoardDao;
import com.douzone.mysite.vo.BoardVo;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		int group_no = Integer.parseInt(request.getParameter("group_no"));
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		Long user_no = Long.parseLong(request.getParameter("user_no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		//System.out.println(group_no+":"+order_no+":"+depth+":"+user_no);
		order_no = order_no + 1;
		depth = depth + 1;
		//System.out.println(group_no+":"+order_no+":"+depth+":"+user_no);
		
		BoardVo vo = new BoardVo();
		vo.setGroup_no(group_no);
		vo.setDepth(depth);
		vo.setOrder_no(order_no);
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUser_no(user_no);
		
		new BoardDao().updateInsert(vo);
		
		
		WebUtils.redirect(request, response, request.getContextPath()+"/board?a=select");

	}

}
