package util;

import model.NewsList;

/*
 * ���ڳ�ʼ���������򼰱����޸ġ�
 * �������ʱ��ȡxml�ļ�������������ݴ��News�ࣻ
 * �������ʱ�Ѷ����ŵ��޸ģ���Ҫ�Ǳ�ǩ���޸ĺ����ŵġ�ɾ����������ļ���
 */

public class Initializer {
	
	public static NewsList guangming, nanfangdaily, sichuan;
	
	public static void initData() {
		guangming = new NewsList();
		nanfangdaily = new NewsList();
		sichuan = new NewsList();
		XMLReader guangmingreader = new XMLReader("resource/file/guangming.xml");
		XMLReader nanfangdailyreader = new XMLReader("resource/file/nanfangdaily.xml");
		XMLReader sichuanreader = new XMLReader("resource/file/sichuan.xml");
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
