package kr.goott.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.goott.controller.CommandService;

public class CommandDelOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FreeboardVO vo = new FreeboardVO();
		vo.setNum(Integer.parseInt(req.getParameter("num")));
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo")));
		HttpSession session =req.getSession();
		
		vo.setUserid((String)session.getAttribute("logid"));
		FreeboardDAO dao = new FreeboardDAO();
		int cnt =dao.deleteRecord(vo);
		
		req.setAttribute("vo", vo);
		req.setAttribute("cnt", cnt);
		return "delOk.jsp";
	}

}
