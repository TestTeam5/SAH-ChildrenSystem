package util;

import model.NewsList;

/*
 * ���ڳ�ʼ���������򼰱����޸ġ�
 * �������ʱ��ȡxml�ļ�������������ݴ��News�ࣻ
 * �������ʱ�Ѷ����ŵ��޸ģ���Ҫ�Ǳ�ǩ���޸ĺ����ŵġ�ɾ����������ļ���
 */

public class Initializer {
	
	public static NewsList newsList;
	
	public static void initData() {
		newsList = new NewsList();
		newsList.init();
	}
	
}
