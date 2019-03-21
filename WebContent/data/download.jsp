<%@page import="kr.goott.data.DataDAO"%>
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	String filename = request.getParameter("filename");
	
	//파일의 절대 경로
	String path = request.getServletContext().getRealPath("/fileupload");
	
	//지금부터 전송하는 것은 파일다운로드임을 셋팅
	response.setContentType("application/x-msdownload");
	
	//한글파일명 인코딩하기 (인터넷 익스플로러인지 확인)
	boolean ie =request.getHeader("user-agent").indexOf("MSIE")>0;
	
	String convFilename = "";
	if(ie){//익스플로러일때
		convFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20"); // \\+ 를 %20으로 바꾼다. %20은 공백을 뜻한다. 공백있는 파일도 다운받기위해
	
	}else{//익스플로러가 아닐때 
		convFilename = new String(filename.getBytes("UTF-8"), "8859_1");
	}
	
	//인코딩된 파일명을 response에 셋팅
	response.setHeader("content-Disposition", "attachment;filename=\""+convFilename+"\";");
	
	//파일 내보내기 
	File f = new File(path, filename);
	int filesize = (int)f.length();
	if(filesize>0 && f.isFile()){
		BufferedInputStream is = new BufferedInputStream(new FileInputStream(f));	
		byte data[] = new byte[(int)filesize];
		is.read(data);
		
		//쓰기
		BufferedOutputStream os = new BufferedOutputStream(response.getOutputStream());
		os.write(data, 0, filesize);
		
		os.close();
		is.close();
	}
	//다운로드 횟수 증가
	int recordNo =Integer.parseInt(request.getParameter("recordNo"));
	DataDAO dao = new DataDAO();
	dao.downLoadCount(recordNo);
	%>
