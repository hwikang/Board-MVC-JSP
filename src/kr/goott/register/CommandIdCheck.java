package kr.goott.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandIdCheck implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userid = req.getParameter("userid");
		
		RegisterDAO dao = new RegisterDAO();
		int cnt = dao.idCheck(userid);
		
		req.setAttribute("userid",userid);
		req.setAttribute("cnt", cnt); //view ������(idcheck.jsp) ���� ��밡���� getattribute �ؼ�
		return "idCheck.jsp";
	}

}
