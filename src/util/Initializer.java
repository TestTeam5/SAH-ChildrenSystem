package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.NewsList;
import model.TestNewsList;

/*
 * 用于初始化整个程序及保存修改。
 * 程序加载时读取xml文件，并把相关内容存进News类；
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
