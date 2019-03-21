package kr.goott.freeboard;

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
			Context ctx2 = (Context)ctx1.lookup("java:comp/env");
			DataSource ds = (DataSource)ctx2.lookup("jdbc/myoracle");
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
