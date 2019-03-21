package kr.goott.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnection {
		//db���� + ����
	protected Connection con = null;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	public void dbConn() {
		
		try {
			Context ctx1 = new InitialContext();
			//���ҽ��� ã�� �� ���ҽ��� ����� �� �ֵ��� ��ü�� ��ȯ���ִ� �޼ҵ� .lookup()
											// ã������ ���ҽ��� �̸�
			Context ctx2 = (Context)ctx1.lookup("java:comp/env");
													// ��Ĺ���� ���ҽ��� �����ϴ� ������ ���͸�
			DataSource ds = (DataSource)ctx2.lookup("jdbc/myoracle");
			
			//Ŀ�ؼ� Ǯ�� �غ�� Connection ��ü�� �������� �޼ҵ�
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
