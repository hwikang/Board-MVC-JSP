package kr.goott.freeboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandReplyDeleteOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int replyNo = Integer.parseInt(req.getParameter("replyNo"));
		int recordNo = Integer.parseInt(req.getParameter("recordNo"));
		int num = Integer.parseInt(req.getParameter("num"));
		
		
		
		System.out.println("delete ing....");
		FreeboardDAO dao = new FreeboardDAO();
		int cnt = dao.replyDelete(replyNo);
		FreeboardVO vo = new FreeboardVO();
		vo.setRecordNo(recordNo);
		vo.setNum(num);
		dao.selectRecord(vo);
		List<FreeboardReplyVO> list = dao.replySelect(recordNo);
		
		req.setAttribute("list", list);
		req.setAttribute("vo", vo);
		
		return "view.jsp";
	}

}
