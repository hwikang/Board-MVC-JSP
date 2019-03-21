package kr.goott.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandEditOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FreeboardVO vo = new FreeboardVO();
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo")));
		vo.setNum(Integer.parseInt(req.getParameter("num")));
		vo.setUserid(req.getParameter("userid"));
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		
		FreeboardDAO dao = new FreeboardDAO();
		int cnt = dao.updateRecord(vo);
		
		req.setAttribute("vo", vo);
		req.setAttribute("cnt", cnt);
		
		return "editOk.jsp";
	}

}
