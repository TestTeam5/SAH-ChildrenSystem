package util;

import model.NewsList;
import model.TestNewsList;

/*
 * ���ڳ�ʼ���������򼰱����޸ġ�
 * �������ʱ��ȡxml�ļ�������������ݴ��News�ࣻ
 */

public class Initializer {
	
	public static NewsList newsList;
	public static TestNewsList testNewsList;
	
	public static void initData() {
		newsList = new NewsList();
		newsList.init();
	}
	
	public static boolean initTestData() {
		testNewsList = new TestNewsList();
		return testNewsList.init();
	}
	
}
