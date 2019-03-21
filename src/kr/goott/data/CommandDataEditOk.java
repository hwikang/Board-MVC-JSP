package kr.goott.data;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.goott.controller.CommandService;

public class CommandDataEditOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//�ۼ���
		DataVO vo  = new DataVO();
		vo.setPath(req.getServletContext().getRealPath("/fileupload"));
		int maxSize =Integer.MAX_VALUE;
		DefaultFileRenamePolicy pol = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(req,vo.getPath(),maxSize,"UTF-8",pol);
		
		vo.setRecordNo(Integer.parseInt(mr.getParameter("recordNo")));
		vo.setTitle(mr.getParameter("title"));
		
		//��������
		vo.setDelfile(mr.getParameter("delfile"));
		vo.setDelfile2(mr.getParameter("delfile2"));
		//���ε������
		String newFilename[]= new String[2];	
		Enumeration fileList = mr.getFileNames();
		int i =0;
		while(fileList.hasMoreElements()) {
			String file = (String) fileList.nextElement(); //���ϸ�
			newFilename[i++]=mr.getFilesystemName(file);
		
		}
		vo.setNewFileName(newFilename);
		
		//����,�߰��� ������Ʈ
		DataDAO dao = new DataDAO();
		int cnt = dao.dataUpdate(vo);
		req.setAttribute("vo", vo);
		req.setAttribute("cnt",cnt);
		return "editOk.jsp";
	}

}
