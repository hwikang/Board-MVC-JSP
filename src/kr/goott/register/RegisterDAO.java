package kr.goott.register;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.goott.freeboard.DBConnection;
import kr.goott.freeboard.FreeboardReplyVO;

public class RegisterDAO extends DBConnection implements RegisterInterface {

	@Override
	public int RegisterSave(RegisterVO vo) {
		int cnt =0;		
		
		try {
			 dbConn();
																		//문자를 date로 바꿈
			String sql = "insert into register values(regSq.nextVal,?,?,?,to_date(?,'YYYY-MM-DD'),?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpwd());
			pstmt.setString(3, vo.getUsername());
			pstmt.setString(4, vo.getBirthday());
			pstmt.setString(5, vo.getTel());
			pstmt.setString(6, vo.getHobbyStr());
			pstmt.setString(7, vo.getZipcode());
			pstmt.setString(8, vo.getAddr());
			pstmt.setString(9, vo.getAddrDetail());
			
			cnt = pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 에러");
		}finally {
			dbClose();
		}
		return cnt;
	}
	

	@Override
	public int idCheck(String userid) {
		int cnt=0;
		try {
			dbConn();
			String sql ="select count(userid) from register where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs =pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(userid);
				cnt = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("id check error");
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public List<ZipcodeVO> searchZipcode(String doro) {
		List<ZipcodeVO> list = new ArrayList<ZipcodeVO>();
		try {
			String sql ="select * from zipcodeTbl where doro=?";
			dbConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, doro);
			rs=pstmt.executeQuery();
			System.out.println(doro);
			while(rs.next()) {
				ZipcodeVO vo = new ZipcodeVO();
				
				vo.setZipcode(rs.getString(1));
				vo.setSido(rs.getString(2));
				vo.setSigungu(rs.getString(3));
				vo.setUm(rs.getString(4));
				vo.setDoro(rs.getString(5));
				vo.setbNum(rs.getInt(6));			
				vo.setbName(rs.getString(7));				
				vo.setDong(rs.getString(8));				
				vo.setLiName(rs.getString(9));				
				vo.setNum1(rs.getInt(10));
				vo.setNum2(rs.getInt(11));
				list.add(vo);
								
			}
			
		} catch (Exception e) {
			System.out.println("error 발생");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return list;
	}

	@Override
	public void loginCheck(RegisterVO vo) {
		try {
			dbConn();
			String sql = "select username from register where userid=? and userpwd=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getUserpwd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setUsername(rs.getString(1));
				vo.setLoginStatus("Y");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("login check error");
		}finally {
			dbClose();
		}
		
	}

	@Override
	public void idSearch(RegisterVO vo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RegisterVO getRegSelect(String userid) {
		RegisterVO vo = new RegisterVO();
		try {
			dbConn();
			String sql = "select * from register where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs= pstmt.executeQuery();
			if(rs.next()) {
				vo.setRegno(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setUsername(rs.getString(4));
				vo.setBirthday(rs.getString(5));
				vo.setTel(rs.getString(6));
				vo.setHobbyStr(rs.getString(7));
				vo.setZipcode(rs.getString(8));
				vo.setAddr(rs.getString(9));
				vo.setAddrDetail(rs.getString(10));
				
			}
		} catch (Exception e) {
			e.getMessage();
			System.out.println("getRecord error");
		}finally {
			dbClose();
		}
		return vo;
	}


	@Override
	public int setRegUpdate(RegisterVO vo) {
		int cnt =0;
		try {
			dbConn();
			String sql ="update register set userpwd=? ,birthday=? ,tel=? ,hobby=? ,zipcode=? ,addr=? ,adddetail=?  where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserpwd());
			pstmt.setString(2, vo.getBirthday());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getHobbyStr());
			pstmt.setString(5, vo.getZipcode());
			pstmt.setString(6, vo.getAddr());
			pstmt.setString(7, vo.getAddrDetail());
			pstmt.setString(8, vo.getUserid());		
			
			
			cnt =pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("회원정보수정 에러");
			e.getMessage();
		}finally {
			dbClose();
		}
		return cnt;
	}

}
