package kr.goott.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
		//db연결 + 종료
	protected Connection con = null;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	public void dbConn() {
		
		try {
			Context ctx1 = new InitialContext();
			//리소스를 찾은 후 리소스를 사용할 수 있도록 객체를 반환해주는 메소드 .lookup()
											// 찾으려는 리소스의 이름
			Context ctx2 = (Context)ctx1.lookup("java:comp/env");
													// 톰캣에서 리소스를 관리하는 가상의 디렉터리
			DataSource ds = (DataSource)ctx2.lookup("jdbc/myoracle");
			
			//커넥션 풀에 준비된 Connection 객체를 빌려오는 메소드
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("db connection error");		
		}
	}
	
	public void dbClose() {
		try {
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(con!=null)con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
