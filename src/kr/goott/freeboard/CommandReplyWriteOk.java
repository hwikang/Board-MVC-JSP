package kr.goott.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.goott.controller.CommandService;

public class CommandReplyWriteOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		FreeboardReplyVO vo = new FreeboardReplyVO();
		req.setCharacterEncoding("UTF-8");
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo"))); //view.jsp에서
		vo.setIp(req.getRemoteAddr());
		vo.setReplyContent(req.getParameter("replyContent"));
		HttpSession session =  req.getSession();
		vo.setUserid((String)session.getAttribute("logid"));
		
		FreeboardDAO dao = new FreeboardDAO();
		int cnt = dao.insertReply(vo);
		
		FreeboardVO vo2= new FreeboardVO();
		vo2.setNum(Integer.parseInt(req.getParameter("num")));
		vo2.setRecordNo(vo.getRecordNo());
		dao.selectRecord(vo2);
		
		req.setAttribute("vo", vo2);
		//댓글목록
		List<FreeboardReplyVO> list = dao.replySelect(vo.getRecordNo());
		req.setAttribute("list", list);
		
		
		
		return "view.jsp";
	}

}
