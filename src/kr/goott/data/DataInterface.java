package kr.goott.data;

import java.util.List;

public interface DataInterface {
	//자료실목록
	public List<DataVO> getDataListAll();
	//글쓰기
	public int dataWrite(DataVO vo);
	//글수정
	public void getData(DataVO vo);
	//글삭제
	public int dataDelete(int recordNo,String path);
	//다운로드횟수
	public void downLoadCount(int recordNo);
	public int dataUpdate(DataVO vo);
}
