package util;

import model.NewsList;

/*
 * 用于初始化整个程序及保存修改。
 * 程序加载时读取xml文件，并把相关内容存进News类；
 * 程序结束时把对新闻的修改（主要是标签的修改和新闻的“删除”）存回文件。
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
		 * 保存修改到文件（待实现）
		 */
	}
	
}
