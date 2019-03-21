package kr.goott.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandRegFormOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 회원가입
		req.setCharacterEncoding("UTF-8");
		RegisterVO vo = new RegisterVO();
		vo.setUserid(req.getParameter("userid"));
		vo.setUsername(req.getParameter("username"));
		vo.setUserpwd(req.getParameter("userpwd"));
		vo.setBirthday(req.getParameter("birthday"));
		vo.setTel(req.getParameter("tel"));
		vo.setHobby(req.getParameterValues("hobby"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddr(req.getParameter("addr"));
		vo.setAddrDetail(req.getParameter("addrDetail"));
		
		RegisterDAO dao = new RegisterDAO();
		int cnt = dao.RegisterSave(vo);
		req.setAttribute("cnt", cnt);
		
		return "registerResult.jsp";
	}

}
