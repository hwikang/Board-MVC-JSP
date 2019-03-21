package kr.goott.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandDataEdit implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DataVO vo = new DataVO(Integer.parseInt(req.getParameter("recordNo")));
		DataDAO dao = new DataDAO();
		
		dao.getData(vo);
		req.setAttribute("vo", vo);
		return "edit.jsp";
	}

}
