/**
 * 
 */
package kr.goott.register;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

/**
 * @author goott-1-13
 *
 */
public class CommandZipSearch implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String doro = req.getParameter("doro");
		
		RegisterDAO dao = new RegisterDAO();
		List<ZipcodeVO> list = dao.searchZipcode(doro);
		
		req.setAttribute("list", list);
		req.setAttribute("size", list.size());
		
		//view 파일명 리턴
		return "zipSearch.jsp";
	}

	

}
