package kr.goott.data;

import java.util.List;

public interface DataInterface {
	//�ڷ�Ǹ��
	public List<DataVO> getDataListAll();
	//�۾���
	public int dataWrite(DataVO vo);
	//�ۼ���
	public void getData(DataVO vo);
	//�ۻ���
	public int dataDelete(int recordNo,String path);
	//�ٿ�ε�Ƚ��
	public void downLoadCount(int recordNo);
	public int dataUpdate(DataVO vo);
}
