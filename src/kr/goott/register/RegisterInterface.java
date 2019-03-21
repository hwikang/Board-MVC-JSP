package kr.goott.register;

import java.util.List;

import kr.goott.freeboard.FreeboardReplyVO;

public interface RegisterInterface {
	//회원가입 추상 메소드 
	public int RegisterSave(RegisterVO vo);
	//id중복검사
	public int idCheck(String userid);
	//우편번호 검색
	public List<ZipcodeVO> searchZipcode(String doro);
	//로그인
	public void loginCheck(RegisterVO vo);

	//아이디 찾기
	public void idSearch(RegisterVO vo);
	//회원 수정 -선택
	public RegisterVO getRegSelect(String userid);
	//회원 정보 수정-업데이트
	public int setRegUpdate(RegisterVO vo);
	
	
}
