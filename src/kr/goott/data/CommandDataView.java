package kr.goott.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandDataView implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// �۳��뺸��
		DataVO vo = new DataVO(Integer.parseInt(req.getParameter("recordNo")));  //DataVO�� �Ű����� �޴� �����ڸ޼ҵ尡 �����Ƿ� ������ش�.
		DataDAO dao = new DataDAO();
		dao.getData(vo);
		req.setAttribute("vo", vo);
		return "view.jsp";
	}

}
