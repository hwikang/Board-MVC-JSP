package kr.goott.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandDelOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int recordNo = Integer.parseInt(req.getParameter("recordNo"));
		DataDAO dao = new DataDAO();
		String path = req.getServletContext().getRealPath("/fileupload");
		int cnt = dao.dataDelete(recordNo, path);
		req.setAttribute("cnt", cnt);
		req.setAttribute("recordNo", recordNo);
		return "dataDel.jsp";
	}

}
