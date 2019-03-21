package kr.goott.freeboard;

import java.util.List;

public interface FreeboardInterface {

	//총레코드 수구하기
	public int totalRecordNumber(FreeboardVO vo) ;
	//조회수 올리기
	public void setHitCount(int recordNo);
	//레코드 선택 1페이지 분량
	public List<FreeboardVO> getBoardList(FreeboardVO vo); 
	//글등록
	public int insertRecord(FreeboardVO vo);
	//글선택
	public void selectRecord(FreeboardVO vo);
	//글삭제
	public int deleteRecord(FreeboardVO vo);
	//글수정
	public int updateRecord(FreeboardVO vo);
	//댓글
	public int insertReply(FreeboardReplyVO vo);
	//댓글목록ㄷ
	public List<FreeboardReplyVO> replySelect(int recordNo);
	
	//댓글수정
	public int replyUpdate(FreeboardReplyVO replyVO);
	
	//댓글삭제
	public int replyDelete(int replyNo);
	
}
