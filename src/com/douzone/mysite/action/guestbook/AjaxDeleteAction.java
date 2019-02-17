package com.douzone.mysite.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mvc.action.Action;
import com.douzone.mysite.repository.GuestbookDao;
import com.douzone.mysite.vo.GuestbookVo;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		GuestbookDao dao = new GuestbookDao();
		int result = dao.delete(vo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result",result);
		
		response.setContentType("application/json; charset=UTF-8"); // ajax함수에서 dataType이 json이라는 것을 알려줌 
		JSONObject jsonObject = JSONObject.fromObject(map);
		response.getWriter().print(jsonObject.toString());

	}

}
