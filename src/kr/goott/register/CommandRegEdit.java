package kr.goott.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.goott.controller.CommandService;

public class CommandRegEdit implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//session �� ���̵�� ������ ������ ����̵�
		HttpSession ses = req.getSession();
		String userid = (String)ses.getAttribute("logid");
		RegisterDAO dao = new RegisterDAO();
		System.out.println("user="+userid);
		RegisterVO vo = dao.getRegSelect(userid);
		req.setAttribute("vo", vo);
		
	
		return "registerEdit.jsp";
	}

}
