package kr.goott.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import kr.goott.util.DBConnection;

public class DataDAO extends DBConnection implements DataInterface {

	@Override
	public List<DataVO> getDataListAll() {
		List<DataVO> list = new ArrayList<DataVO>();
		try {
			dbConn();
			String sql= "select recordNo, userid, title, filename, filename2, downCount, regdate from data "
					+ "order by recordNo desc";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				DataVO vo = new DataVO();
				vo.setRecordNo(rs.getInt(1));
				vo.setUserid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setFilename(rs.getString(4));
				vo.setFilename2(rs.getString(5));
				vo.setDownCount(rs.getInt(6));
				vo.setRegdate(rs.getString(7));
				list.add(vo);
			}
		}catch(Exception e) {
			System.out.println(""+e.getMessage());
			
		}finally {
			dbClose();
		}
		return list;
	}

	@Override
	public int dataWrite(DataVO vo) {
		int cnt = 0 ;
		try {
			dbConn();
			String sql="insert into data (recordNo, userid, title, filename, filename2, regdate) "
					+ "values(boardSq.nextVal,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			System.out.println(vo.getUserid());
			System.out.println(vo.getTitle());
			System.out.println(vo.getFilename());
			System.out.println(vo.getFilename2());
			
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getFilename());
			pstmt.setString(4, vo.getFilename2());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("자료실글올리기 에러"+e.getMessage());
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public void getData(DataVO vo) {
		// 글선택
		try {
			dbConn();
			String sql = "select recordNo, userid, title, filename, filename2, downCount, regdate from data where "
					+ "recordNo=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getRecordNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setUserid(rs.getString(2));
				vo.setTitle(rs.getString(3));
				vo.setFilename(rs.getString(4));
				vo.setFilename2(rs.getString(5));
				vo.setDownCount(rs.getShort(6));
				vo.setRegdate(rs.getString(7));
			}
		}catch(Exception e) {
			System.out.println(""+e.getMessage());
		}finally {
			dbClose();
		}
		
	}

	@Override
	public int dataDelete(int recordNo, String path) {
		int cnt=0;
		try {
			dbConn();
			String sql="select filename, filename2 from data where recordNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			rs = pstmt.executeQuery();
			
			sql ="delete from data where recordNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			
			cnt = pstmt.executeUpdate();
			if(cnt>0) {
				if(rs.next()) {
					for(int i=1;i<=2;i++) {
						String del= rs.getString(i);
						if(del!=null && !del.equals("")) {
							File f = new File(path,del);
							f.delete();
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			System.out.println("삭제에러");
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return cnt;
	}

	@Override
	public void downLoadCount(int recordNo) {
		// 다운로드 횟수증가
		try {
			dbConn();
			String sql = "update data set downCount = downCount+1 where recordNo=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("다운로드증가 에러"+e.getMessage());
		}finally {
			dbClose();
		}
	}

	@Override
	public int dataUpdate(DataVO vo) {
		//수정전 현재 파일의 목록
		String fileList[] = getFileList(vo.getRecordNo());
		//db에 저장할 목록찾기
		String dbFileList[]=new String[2];
		int idx=0;
		//filelist null이면 ""으로 바꾸기
		for(int i=0;i<fileList.length;i++) {
			if(fileList[i]==null) {
				fileList[i]="";
			}
		}
		
		//삭제목록에 있는가
		for(int i =0; i<fileList.length;i++) {
			System.out.println("filelist="+fileList[i]);
			System.out.println("delfile="+vo.getDelfile());
			System.out.println("delfile2="+vo.getDelfile2());
			if(!(fileList[i].equals(vo.getDelfile())) && !(fileList[i].equals(vo.getDelfile2())) &&!(fileList[i].equals("")) ) {		
					//삭제목록에 없으면 추가
				System.out.println(fileList[i]);
				dbFileList[idx++] = fileList[i];
			}
		}

		
		//만약 두개가 다 들어갔으면(삭제목록에 아무것도없으면) idx는 2임 
		//삭제가된경우
		
		System.out.println("vo.file1"+vo.getFilename());
		System.out.println("vo.file2"+vo.getFilename2());
		
		System.out.println("idx="+idx);
		if(idx<2) {
			if(vo.getFilename()!=null&&!(vo.getFilename().equals(""))) {
				dbFileList[idx++]=vo.getFilename();
			}
			if(vo.getFilename2()!=null&&!vo.getFilename2().equals("")) {
				dbFileList[idx++]=vo.getFilename2();		
			}
		}
		System.out.println("idx="+idx);
		System.out.println("db0="+dbFileList[0]);
		System.out.println("db1="+dbFileList[1]);
		System.out.println("TITLE="+vo.getTitle());
		System.out.println("RECORDNO="+vo.getRecordNo());
		
		int cnt=0;
		try {
			dbConn();
			String sql = "update data set title=? ,filename=?,filename2=? where recordno=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, dbFileList[0]);
			pstmt.setString(3, dbFileList[1]);		
			pstmt.setInt(4, vo.getRecordNo());
			
			cnt = pstmt.executeUpdate();
			System.out.println("vo.delfile="+vo.getDelfile());
			System.out.println("vo.delfile2="+vo.getDelfile2());
			
			//파일삭제
			if(vo.getDelfile()!=null&& !(vo.getDelfile().equals(""))) {
				File f = new File(vo.getPath(),vo.getDelfile());
				System.out.println("PATH="+vo.getPath());
				f.delete();
			}
			if(vo.getDelfile2()!=null&& !(vo.getDelfile2().equals(""))) {
				File f = new File(vo.getPath(),vo.getDelfile2());
				f.delete();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update Filelist error");
		}finally {
			dbClose();
		}
		
		return cnt;
	}
	
	//수정전 원래 저장된 파이 목록
	public String[] getFileList(int recordNo) {
		String fileList[] = new String[2];
		try {
			dbConn();
			String sql = "select filename,filename2 from data where recordno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, recordNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				fileList[0]=rs.getString(1);
				fileList[1]=rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("getFilelist error");
		}finally {
			dbClose();
		}
		return fileList;
	}
	
}
