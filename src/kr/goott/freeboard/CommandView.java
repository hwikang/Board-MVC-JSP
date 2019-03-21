package kr.goott.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandView implements CommandService{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FreeboardVO vo =new FreeboardVO();
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo")));
		vo.setNum(Integer.parseInt(req.getParameter("num")));
		System.out.println("commandview ½ÇÇà");
		FreeboardDAO dao = new FreeboardDAO();
		dao.selectRecord(vo);
		req.setAttribute("vo", vo);
		
		List<FreeboardReplyVO> list = dao.replySelect(vo.getRecordNo());req.setAttribute("list", list);
		req.setAttribute("list", list);
		return "view.jsp";
	}

}
