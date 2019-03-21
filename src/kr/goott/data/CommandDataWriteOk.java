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
		//req�� ������
		//req�� �̿��ؼ� ��Ƽ��Ʈ�� �����
		// �ʿ��� ��ü = request��ü , ���ε� ��� , ũ������, �ѱ����ڵ� , rename
		//�����ּұ��ϱ�(fileupload����)
		String path = req.getServletContext().getRealPath("/fileupload");
		//System.out.println(path);
		//���ε� ���� ������(BYTE);
		int maxSize =Integer.MAX_VALUE;
		//�����̸� �ߺ��� ������ ������ �����̸� �ڿ� 1~9999������ ���ڸ� �ٿ��� ������ rename�� ����
		DefaultFileRenamePolicy pol = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(req,path,maxSize,"UTF-8",pol);
		///
		DataVO vo = new DataVO();
		vo.setUserid(mr.getParameter("userid"));
		vo.setTitle(mr.getParameter("title"));
		//���ϸ��� ����� key���
		//Returns the names of all the uploaded files as an Enumeration of Strings. It returns an empty Enumeration if there are no file input fields on the form.
		Enumeration fileList = mr.getFileNames(); //���ϸ��
		String newFileName[] = new String[3]; //�迭����
		int idx=0;
		while(fileList.hasMoreElements()) {
			//old file
			String oldFile = (String)fileList.nextElement(); //���ϸ�Ͽ��� �ϳ��ϳ�����
			System.out.println("oldfile="+oldFile);
			newFileName[idx++] = mr.getFilesystemName(oldFile); //�Ȱ��� �̸��������� new file
		}
		System.out.println(newFileName[0]+"newFileName[0]");
		System.out.println(newFileName[1]+"newFileName[1]");
		
		vo.setNewFileName(newFileName); //�迭������ ���ÿ� vo.filename�� setting����
		
		//////////////////
		DataDAO dao = new DataDAO();
		int cnt = dao.dataWrite(vo);
		//���ڵ� �߰� ���н� (�̹̾��ε� �� ���ϻ���)
		if(cnt<=0) {
			for(int i =0;i<newFileName.length;i++) {
				if(newFileName[i]!=null && !newFileName[i].equals("")) {
										//������ ���ϰ��,�̸�
					File f =  new File(path,newFileName[i]);
					f.delete();
				}
			}
		}
		req.setAttribute("cnt", cnt);
		
		
		
		
		
		
		
		
		
		return "writeOk.jsp";
	}

}
