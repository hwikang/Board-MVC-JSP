package kr.goott.freeboard;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class FreeboardDAO extends DBConnection implements FreeboardInterface{
	
	@Override
	public int totalRecordNumber(FreeboardVO vo) {
		int totalNum=0;
		try {
			dbConn();
			String sql ="select count(title) from freeboard";
			if(vo.getSearchKey()!=null) {
				sql += " where "+vo.getSearchKey()+" like ?";
				
			}
			pstmt = con.prepareStatement(sql);
			if(vo.getSearchKey()!=null) {
				pstmt.setString(1, "%"+vo.getSearchWord()+"%");
			}
			ResultSet rs = pstmt.executeQuery();
			//System.out.println(sql);
			if(rs.next()) {
				totalNum = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("get Total record number error");
		}finally {
			dbClose();
		}

		return totalNum;
	}

	@Override
	public void setHitCount(int recordNo) {
		try {
			dbConn();
			String sql ="update freeboard set hit = hit+1 where recordno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("hit count error");
		}finally {
			dbClose();
		}
		
	}

	@Override
	public List<FreeboardVO> getBoardList(FreeboardVO vo) {
		List<FreeboardVO> list = new ArrayList<FreeboardVO>();
		try {
			dbConn();
			
			
		//	System.out.println(vo.toString());
			
			String sql = "select * from (select * from (select recordno, userid, title,content, hit, to_char(regdate,'YYYY-MM-DD') from freeboard ";
				if(vo.getSearchKey()!=null) {
					sql+= "where "+vo.getSearchKey()+" like '%"+vo.getSearchWord()+"%' ";
				}
				sql+= "order by recordno desc) ";
				sql+=  "where rownum<=? order by recordno asc) where rownum<=? order by recordno desc";
			//System.out.println(sql);
			pstmt = con.prepareStatement(sql);
			
			
			pstmt.setInt(1,vo.getNum()*vo.getOnePageRecord());		
			
			
			if(vo.getNum()==vo.getTotalPage()) {  //마지막 페이지 일때
				int lastPageRecordNumber = vo.getTotalRecord() % vo.getOnePageRecord();
				pstmt.setInt(2, lastPageRecordNumber);
			}else {
				pstmt.setInt(2, vo.getOnePageRecord());
			}
			
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeboardVO vo1 =new FreeboardVO();
				vo1.setRecordNo(rs.getInt(1));
				vo1.setUserid(rs.getString(2));
				vo1.setTitle(rs.getString(3));
				vo1.setContent(rs.getString(4));
				vo1.setHit(rs.getInt(5));
				vo1.setRegdate(rs.getString(6));
				System.out.println("레코드가 리스트에 들어가는중");
				list.add(vo1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getBoardList error");
		}finally {
			dbClose();
		}
		return list;
	}

	@Override
	public int insertRecord(FreeboardVO vo) {
		int cnt=0;
		try {
			dbConn();
			String sql = "insert into freeboard(recordNo,userid,title,content,ip,hit,regdate)"
					+ " values(boardsq.nextval,?,?,?,?,0,sysdate)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getIp());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("insert record error");
		}finally{
			dbClose();
		}
		return cnt;
	}

	@Override
	public void selectRecord(FreeboardVO vo) {
		try {
			dbConn();
			String sql ="select * from freeboard where recordno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getRecordNo());
			rs= pstmt.executeQuery();
			if(rs.next()) {
				vo.setRecordNo(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setHit(rs.getInt(5));
				vo.setRegdate(rs.getString(7));
				
				setHitCount(vo.getRecordNo());	//조회수 올리기
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("select record error");
		}finally {
			dbClose();
		}
	
	}

	@Override
	public int deleteRecord(FreeboardVO vo) {
		int cnt =0;
		try {
			dbConn();
			String sql ="delete from freeboard where recordno=? and userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,vo.getRecordNo());
			pstmt.setString(2, vo.getUserid());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("delete record error");
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public int updateRecord(FreeboardVO vo) {
		int cnt = 0;
		try {
			dbConn();
			String sql ="update freeboard set title=? , content=? where recordno=? and userid=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setInt(3,vo.getRecordNo());
			pstmt.setString(4,vo.getUserid());
			
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update record error");
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public int insertReply(FreeboardReplyVO vo) {
		int cnt =0;
		try {
			
			dbConn();
			String sql = "insert into freeboardreply values(replysq.nextval,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getRecordNo());
			pstmt.setString(2, vo.getUserid());
			pstmt.setString(3, vo.getReplyContent());
			pstmt.setString(4, vo.getIp());
			
			cnt = pstmt.executeUpdate();
			System.out.println(cnt);
		} catch (Exception e) {
			e.getMessage();
			System.out.println("insert REPLY ERROR");
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	
	public List<FreeboardReplyVO> replySelect(int recordNo) {
		List<FreeboardReplyVO> list = new ArrayList<FreeboardReplyVO>();
		try {
			dbConn();
			String sql = "select replyno,userid,replycontent,replyRegdate from freeboardreply where recordno =? order by replyno desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FreeboardReplyVO vo = new FreeboardReplyVO();
				vo.setReplyNo(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setReplyContent(rs.getString(3));
				vo.setReplyRegdate(rs.getString(4));
				list.add(vo);
			}
		} catch (Exception e) {
				e.getMessage();
				System.out.println("get reply list errror");
		}finally {
			dbClose();
		}
		return list;
	}

	@Override
	public int replyUpdate(FreeboardReplyVO replyVO) {
		int cnt=0;
		try {
			dbConn();
			String sql ="update freeboardreply set replycontent =? where replyno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, replyVO.getReplyContent());
			pstmt.setInt(2, replyVO.getReplyNo());
			
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.getMessage();
			System.out.println("reply update error");
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public int replyDelete(int replyNo) {
		int cnt =0;
		try {
			dbConn();
			String sql = "delete from freeboardReply where replyno=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, replyNo);
			cnt = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.getMessage();
			System.out.println("delete 댓글 error");
		}finally {
			dbClose();
		}
		return cnt;
	}
	
	
	
}
