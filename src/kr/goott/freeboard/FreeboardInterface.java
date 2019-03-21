package kr.goott.freeboard;

import java.util.List;

public interface FreeboardInterface {

	//�ѷ��ڵ� �����ϱ�
	public int totalRecordNumber(FreeboardVO vo) ;
	//��ȸ�� �ø���
	public void setHitCount(int recordNo);
	//���ڵ� ���� 1������ �з�
	public List<FreeboardVO> getBoardList(FreeboardVO vo); 
	//�۵��
	public int insertRecord(FreeboardVO vo);
	//�ۼ���
	public void selectRecord(FreeboardVO vo);
	//�ۻ���
	public int deleteRecord(FreeboardVO vo);
	//�ۼ���
	public int updateRecord(FreeboardVO vo);
	//���
	public int insertReply(FreeboardReplyVO vo);
	//��۸�Ϥ�
	public List<FreeboardReplyVO> replySelect(int recordNo);
	
	//��ۼ���
	public int replyUpdate(FreeboardReplyVO replyVO);
	
	//��ۻ���
	public int replyDelete(int replyNo);
	
}
