package kr.goott.freeboard;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.goott.controller.CommandService;

public class CommandList implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		FreeboardVO vo = new FreeboardVO();
		String numStr = req.getParameter("num");
		if(numStr!=null) {
			vo.setNum(Integer.parseInt(numStr)); //현재 페이지 입력
		}else {
			vo.setNum(1);
		}
		
		FreeboardDAO dao = new FreeboardDAO();
		
		
		//검색어 검색키
		vo.setSearchWord(req.getParameter("searchWord"));
		if(vo.getSearchWord()==null || vo.getSearchWord().equals("")) {
			vo.setSearchKey(null);
		}else {
			vo.setSearchKey(req.getParameter("searchKey"));
		}
		//총 레코드 수
		
		vo.setTotalRecord(dao.totalRecordNumber(vo)); 
		System.out.println(vo.toString());
		List<FreeboardVO> list = dao.getBoardList(vo);
		//System.out.println("---------------"+list.size());
		req.setAttribute("vo", vo); //페이지 정보
		req.setAttribute("list", list); //검색결과 레코드의 정보
		return "list.jsp";
	}
	
}
