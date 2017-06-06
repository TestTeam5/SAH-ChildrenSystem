package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.NewsList;
import model.TestNewsList;

/*
 * ���ڳ�ʼ���������򼰱����޸ġ�
 * �������ʱ��ȡxml�ļ�������������ݴ��News�ࣻ
 */

public class Initializer {
	
	public static NewsList newsList;
	public static TestNewsList testNewsList;
	
	public static List<Integer> wrongLineNum = new ArrayList<>();
	public static Vector<Vector<String>> resultNewsVec = new Vector<>();
	
	public static void initData() {
		newsList = new NewsList();
		newsList.init();
	}
	
	public static boolean initTestData() {
		testNewsList = new TestNewsList();
		return testNewsList.init();
	}
	
}
