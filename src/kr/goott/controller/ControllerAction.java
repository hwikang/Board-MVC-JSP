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
		
			// * ��� ����
@WebServlet("/*.do")
public class ControllerAction extends HttpServlet {
														//Ű , ���
	HashMap<String,CommandService> mapping = new HashMap<String,CommandService>();
	
    public ControllerAction() {
        super();
    }
	public void init(ServletConfig config) throws ServletException {
		//get �̵� post�� �̰Ŵ� ������ ����
		System.out.println("initialized...");
		//properties �� urlMapping.properties
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
			//properties �� ���ڿ��� hashmap ������
		Set keyList = pro.keySet();
		Iterator keyIterator = keyList.iterator();
		while(keyIterator.hasNext()) {
			String key = (String)keyIterator.next();
			//.home.CommandIndex
			String className = pro.getProperty(key); // �ش� key�� Ŭ������ �ش��ϴ� ���ڿ�
			System.out.println(key+"="+className);
			
			//���ڿ� ��ü �����
			Class commandClass = Class.forName(className);
			//��ü�� ������� Ŭ������ commandservice�� ����ȯ�ϤŤ� hashmap ����
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
		//.do �� �����ϸ� 
		String uri = req.getRequestURI();
		System.out.println(uri); //    /webMVC/*.do
		String contextPath =req.getContextPath();
		System.out.println(contextPath);  // /webMVC
		String commandKey = uri.substring(contextPath.length(),uri.length());
		System.out.println(commandKey);			//��ο� ���ϸ� (key)
		CommandService service = mapping.get(commandKey); // (Ŀ�ǵ�������������)
		String viewFile = service.process(req, res);
		
		//view ������ �̵��ϱ�
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewFile); 
		dispatcher.forward(req, res);
	
	}

}
