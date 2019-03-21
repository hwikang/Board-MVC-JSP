package kr.goott.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandDataView implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 글내용보기
		DataVO vo = new DataVO(Integer.parseInt(req.getParameter("recordNo")));  //DataVO에 매개변수 받는 생성자메소드가 없으므로 만들어준다.
		DataDAO dao = new DataDAO();
		dao.getData(vo);
		req.setAttribute("vo", vo);
		return "view.jsp";
	}

}
