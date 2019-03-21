package kr.goott.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandWriteOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//게시판 등록
		req.setCharacterEncoding("UTF-8");
		FreeboardVO vo = new FreeboardVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setTitle(req.getParameter("title"));
		vo.setContent(req.getParameter("content"));
		vo.setIp(req.getRemoteAddr());
		
		FreeboardDAO dao =new FreeboardDAO();
		int cnt = dao.insertRecord(vo);
		req.setAttribute("cnt", cnt);
		System.out.println("cnt = "+cnt);
		return "writeOk.jsp";
	}

}
