package kr.goott.data;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandDataList implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		DataDAO dao = new DataDAO();
		List<DataVO> list =dao.getDataListAll();
		
		req.setAttribute("list", list);
		return "dataList.jsp";
	}

}
