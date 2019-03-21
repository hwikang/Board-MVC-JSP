package kr.goott.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandReplyEditOk implements CommandService{

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		FreeboardReplyVO vo = new FreeboardReplyVO();
		//���۹�ȣ ��������ȣ �亯��ȣ ��������
		vo.setRecordNo(Integer.parseInt(req.getParameter("recordNo")));
		
		int num = Integer.parseInt(req.getParameter("num"));
		vo.setReplyNo(Integer.parseInt(req.getParameter("replyNo")));
		vo.setReplyContent(req.getParameter("replyContent"));
		FreeboardDAO dao = new FreeboardDAO();
		int cnt  = dao.replyUpdate(vo);
		
		//��������
		FreeboardVO vo2 = new FreeboardVO();
		//��ۿ��ִ� ���۹�ȣ
		vo2.setRecordNo(vo.getRecordNo());
		//��������ȣ
		vo2.setNum(num);
		dao.selectRecord(vo2);
		//��۸��
		List<FreeboardReplyVO> list = dao.replySelect(vo2.getRecordNo());
		
		req.setAttribute("vo", vo2);
		req.setAttribute("list", list);
		return "view.jsp";
	}
	
}
