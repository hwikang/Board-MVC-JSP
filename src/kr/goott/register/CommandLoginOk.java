package kr.goott.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.goott.controller.CommandService;

public class CommandLoginOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RegisterVO vo = new RegisterVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setUserpwd(req.getParameter("userpwd"));
		RegisterDAO dao = new RegisterDAO();
		dao.loginCheck(vo);
		req.setAttribute("vo", vo);
	
		if(vo.getLoginStatus().equals("Y")) {
			//로그인성공시필요한 데이터 기록
			
			HttpSession ses = req.getSession();
			ses.setAttribute("logid", vo.getUserid());
			ses.setAttribute("logname", vo.getUsername());
			ses.setAttribute("logStatus", vo.getLoginStatus());
		}
		
		return "loginOk.jsp";
	}

}
