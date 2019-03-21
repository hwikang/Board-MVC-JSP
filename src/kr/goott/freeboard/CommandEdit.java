package kr.goott.freeboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandEdit implements CommandService{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FreeboardVO vo = new FreeboardVO();
		vo.setNum(Integer.parseInt(req.getParameter("num")));
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo")));
		FreeboardDAO dao =new FreeboardDAO();
		dao.selectRecord(vo);
		req.setAttribute("vo", vo);
		return "edit.jsp";
	}

}
