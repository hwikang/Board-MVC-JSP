package kr.goott.register;

public class RegisterVO {
	private int regno;
	private String userid;
	private String userpwd;
	private String username;
	private String birthday;
	private String tel;
	private String hobby[];
	private String hobbyStr;//
	private String zipcode;
	private String addr;
	private String addrDetail;
	private String regdate;
	//로그인여부(Y/N)
	private String loginStatus = "N";
	
	//생성자
	public RegisterVO() {
		
	}
	public RegisterVO(String userid, String userpwd,String username, String birthday, String tel, 
						String hobby[], String zipcode, String addr, String addrDetail) {
		
		this.userid = userid;
		this.userpwd = userpwd;
		this.username = username;
		this.birthday = birthday;
		this.tel = tel;
		this.hobby = hobby;
		this.zipcode = zipcode;
		this.addr = addr;
		this.addrDetail = addrDetail;
	}

	public String toString() {
		String data = "regnum="+regno+"\n";
			   data += "userid="+userid+"\n";
			   data += "userpwd="+userpwd+"\n";
			   data += "username="+username+"\n";
			   data += "birthday="+birthday+"\n";
			   data += "tel="+tel+"\n";
			   data += "hobbyStr="+hobbyStr+"\n";
			   data += "zipcode="+zipcode+"\n";
			   data += "addr="+addr+"\n";
			   data += "addrDetail="+addrDetail+"\n";
			   data += "regdate="+regdate+"\n";
		return data;
	}
	
	//getter/setter
	public int getRegno() {
		return regno;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
		//배열 hobby의 값을 문자열로 만들어 리턴
		hobbyStr ="";
		for(int i=0; i<hobby.length; i++) {
			hobbyStr += hobby[i]+"/";
		}
	}

	public String getHobbyStr() {
		return hobbyStr;
	}

	public void setHobbyStr(String hobbyStr) {
		this.hobbyStr = hobbyStr;
		//hobbyStr의 값을 배열에 추가
		hobby = hobbyStr.split("/");
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public String getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}
	
}
