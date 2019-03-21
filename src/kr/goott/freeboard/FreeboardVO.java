package kr.goott.freeboard;

public class FreeboardVO {
	private int recordNo;
	private String userid;
	private String title;
	private String content;
	private int hit;
	private String ip;
	private String regdate;
	
	//paging변수
	private int num=1;		//현재페이지
	private int totalRecord=0;	//총 레코드 수
	private int totalPage=0;	//총 페이지수
	private int onePageRecord=5; //한페이지당 레코드수
	private int pageNumCount =5; //페이징에 표시되는 페이지 수
	private int startPage = 1; //1,6,11
	
	//검색어 검색키
	private String searchKey="";
	private String searchWord="";
	
	public FreeboardVO(){}
	public String toString() {
		String str="";
		str+= "recordNo=" + recordNo +"\n" ;
		str+= "userid=" + userid +"\n";
		str+= "title=" + title +"\n";
		str+= "content=" + content +"\n";
		str+= "hit=" + hit +"\n";
		str+= "ip=" + ip +"\n";
		str+= "regdate=" + regdate +"\n";
		str+= "num=" + num +"\n";
		str+= "totalRecord=" + totalRecord +"\n";
		str+= "totalPage=" + totalPage +"\n";
		str+= "OnePageRecord=" + onePageRecord +"\n";
		str+= "PageNumCount=" + pageNumCount +"\n";
		str+= "searchKey=" + searchKey +"\n";
		str+= "searchWord=" + searchWord  +"\n";
		str+= "startPage=" +startPage;
		System.out.println(str);
		return str;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
		//시작 페이지 num pageNumCount
		//현재 페이지 / 페이지수 
		if(num%pageNumCount==0) {
			startPage= ((num/pageNumCount)-1)*5+1;
		}else {
			startPage= (num/pageNumCount)*5+1 ;
		}
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil(totalRecord/(double)onePageRecord);
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	public int getPageNumCount() {
		return pageNumCount;
	}
	public void setPageNumCount(int pageNumCount) {
		this.pageNumCount = pageNumCount;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	
}
