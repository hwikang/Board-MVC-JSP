package kr.goott.register;

import java.util.List;

import kr.goott.freeboard.FreeboardReplyVO;

public interface RegisterInterface {
	//ȸ������ �߻� �޼ҵ� 
	public int RegisterSave(RegisterVO vo);
	//id�ߺ��˻�
	public int idCheck(String userid);
	//�����ȣ �˻�
	public List<ZipcodeVO> searchZipcode(String doro);
	//�α���
	public void loginCheck(RegisterVO vo);

	//���̵� ã��
	public void idSearch(RegisterVO vo);
	//ȸ�� ���� -����
	public RegisterVO getRegSelect(String userid);
	//ȸ�� ���� ����-������Ʈ
	public int setRegUpdate(RegisterVO vo);
	
	
}
