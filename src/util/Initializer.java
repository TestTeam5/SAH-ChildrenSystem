package util;

import model.NewsList;

/*
 * ���ڳ�ʼ���������򼰱����޸ġ�
 * �������ʱ��ȡxml�ļ�������������ݴ��News�ࣻ
 * �������ʱ�Ѷ����ŵ��޸ģ���Ҫ�Ǳ�ǩ���޸ĺ����ŵġ�ɾ����������ļ���
 */

public class Initializer {
	
	NewsList guangming, nanfangdaily, sichuan;
	XMLReader guangmingreader, nanfangdailyreader, sichuanreader;
	{
		guangming = new NewsList();
		nanfangdaily = new NewsList();
		sichuan = new NewsList();
		guangmingreader = new XMLReader("resource/file/guangming.xml");
		nanfangdailyreader = new XMLReader("resource/file/nanfangdaily.xml");
		sichuanreader = new XMLReader("resource/file/sichuan.xml");
		guangming.init(guangmingreader);
		nanfangdaily.init(nanfangdailyreader);
		sichuan.init(sichuanreader);
	}
	
	
	public void saveChanges(){
		/*
		 * �����޸ĵ��ļ�����ʵ�֣�
		 */
	}
	
}
