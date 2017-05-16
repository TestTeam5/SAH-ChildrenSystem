package util;

import model.NewsList;

/*
 * 用于初始化整个程序及保存修改。
 * 程序加载时读取xml文件，并把相关内容存进News类；
 */

public class Initializer {
	
	public static NewsList newsList;
	
	public static void initData() {
		newsList = new NewsList();
		newsList.init();
	}
	
}
