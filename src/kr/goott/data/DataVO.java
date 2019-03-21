package kr.goott.data;


import kr.goott.util.DBConnection;

public class DataVO {
	private int recordNo;
	private String userid;
	private String title;
	private String filename;
	private String filename2;
	private String regdate;
	private int downCount;
	private String[] newFileName;
	private String path;
	private String delfile;
	private String delfile2;
	

	public DataVO() {
		
	}
	public DataVO(int recordNo) {
		this.recordNo = recordNo;
	}

	public int getRecordNo() {
		return recordNo;
	}

	public void setRecordNo(int recordNo) {
		this.recordNo = recordNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename2() {
		return filename2;
	}

	public void setFilename2(String filename2) {
		this.filename2 = filename2;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getDownCount() {
		return downCount;
	}
	
	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}
	public String[] getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String[] newFileName) {
		this.newFileName = newFileName;
		this.filename = newFileName[0];
		this.filename2 = newFileName[1];
	}
	public String getDelfile() {
		return delfile;
	}
	public void setDelfile(String delfile) {
		this.delfile = delfile;
	}
	public String getDelfile2() {
		return delfile2;
	}
	public void setDelfile2(String delfile2) {
		this.delfile2 = delfile2;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	
}