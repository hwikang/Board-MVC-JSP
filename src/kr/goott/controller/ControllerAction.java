package kr.goott.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
		
			// * 모든 접속
@WebServlet("/*.do")
public class ControllerAction extends HttpServlet {
														//키 , 밸류
	HashMap<String,CommandService> mapping = new HashMap<String,CommandService>();
	
    public ControllerAction() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		//get 이든 post든 이거는 무조건 실행
		System.out.println("initialized...");
		//properties 에 urlMapping.properties
		String propertiesFilename= config.getInitParameter("proConfig");
		Properties pro = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propertiesFilename);
			pro.load(fis);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("init method - properties error");
		}
		
		try {
			//properties 의 문자열을 hashmap 에저장
		Set keyList = pro.keySet();
		Iterator keyIterator = keyList.iterator();
		while(keyIterator.hasNext()) {
			String key = (String)keyIterator.next();
			//.home.CommandIndex
			String className = pro.getProperty(key); // 해당 key의 클래스명에 해당하는 문자열
			System.out.println(key+"="+className);
			
			//문자열 객체 만들기
			Class commandClass = Class.forName(className);
			//객체로 생ㅅ어된 클래스를 commandservice로 형변환하ㅕㅇ hashmap 세팅
			CommandService command = (CommandService) commandClass.getDeclaredConstructors()[0].newInstance();
			mapping.put(key,command);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mvcStart(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mvcStart(request,response);
	}
	
	protected void mvcStart(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//System.out.println("beep...beep... mvc has operated");
		//.do 로 접속하면 
		String uri = req.getRequestURI();
		System.out.println(uri); //    /webMVC/*.do
		String contextPath =req.getContextPath();
		System.out.println(contextPath);  // /webMVC
		String commandKey = uri.substring(contextPath.length(),uri.length());
		System.out.println(commandKey);			//경로와 파일명만 (key)
		CommandService service = mapping.get(commandKey); // (커맨드파일을가져옴)
		String viewFile = service.process(req, res);
		
		//view 페이지 이동하기
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewFile); 
		dispatcher.forward(req, res);
	
	}

}
