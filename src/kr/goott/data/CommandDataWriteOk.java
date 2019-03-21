package kr.goott.data;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.goott.controller.CommandService;

public class CommandDataWriteOk implements CommandService {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//req에 다있음
		//req를 이용해서 멀티파트를 만든다
		// 필요한 객체 = request객체 , 업로드 경로 , 크기제한, 한글인코딩 , rename
		//절대주소구하기(fileupload까지)
		String path = req.getServletContext().getRealPath("/fileupload");
		//System.out.println(path);
		//업로드 제한 사이즈(BYTE);
		int maxSize =Integer.MAX_VALUE;
		//파일이름 중복된 파일이 있으면 파일이름 뒤에 1~9999까지의 숫자를 붙여서 파일의 rename을 진행
		DefaultFileRenamePolicy pol = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(req,path,maxSize,"UTF-8",pol);
		///
		DataVO vo = new DataVO();
		vo.setUserid(mr.getParameter("userid"));
		vo.setTitle(mr.getParameter("title"));
		//파일명이 저장된 key목록
		//Returns the names of all the uploaded files as an Enumeration of Strings. It returns an empty Enumeration if there are no file input fields on the form.
		Enumeration fileList = mr.getFileNames(); //파일목록
		String newFileName[] = new String[3]; //배열생성
		int idx=0;
		while(fileList.hasMoreElements()) {
			//old file
			String oldFile = (String)fileList.nextElement(); //파일목록에서 하나하나빼서
			System.out.println("oldfile="+oldFile);
			newFileName[idx++] = mr.getFilesystemName(oldFile); //똑같은 이름이있으면 new file
		}
		System.out.println(newFileName[0]+"newFileName[0]");
		System.out.println(newFileName[1]+"newFileName[1]");
		
		vo.setNewFileName(newFileName); //배열생성과 동시에 vo.filename을 setting해줌
		
		//////////////////
		DataDAO dao = new DataDAO();
		int cnt = dao.dataWrite(vo);
		//레코드 추가 실패시 (이미업로드 된 파일삭제)
		if(cnt<=0) {
			for(int i =0;i<newFileName.length;i++) {
				if(newFileName[i]!=null && !newFileName[i].equals("")) {
										//실패한 파일경로,이름
					File f =  new File(path,newFileName[i]);
					f.delete();
				}
			}
		}
		req.setAttribute("cnt", cnt);
		
		
		
		
		
		
		
		
		
		return "writeOk.jsp";
	}

}
