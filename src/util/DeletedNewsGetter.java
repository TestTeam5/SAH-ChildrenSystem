package util;

import model.NewsList;

public class DeletedNewsGetter {
	static int index = 0; // 删除列表最后一条新闻的下一条的下标
	static NewsList newsList = Initializer.newsList;
	static Object[][] newsTitles = { new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" },
			new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" },
			new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" },
			new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" },
			new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" }, new Object[] { "", "" },
			new Object[] { "", "" } };
	
	public static void init(){
		index = -1;
		indexPlus();
	}
	
	// 执行index++操作
	public static void indexPlus() {
		index++;
		if(index < newsList.size()){
			if(!newsList.isDeleted(index, true))
				indexPlus();
		}
	}
	
	// 执行index--操作
	public static void indexMinus() {
		index--;
		if(index < newsList.getFirstDeleted()){
			index = newsList.getFirstDeleted();
			if(index == -1){
				index = newsList.size();
			}
		}
		else if(!newsList.isDeleted(index, true)){
			indexMinus();
		}
	}
	
	// 获取下一页新闻标题
	public static Object[][] getNews() {
		if(index >= newsList.size())
			return newsTitles;
		for (int i = 0; i < 25; i++) {
			if (index >= newsList.size()) {
				newsTitles[i] = new Object[] { "", "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index), "<Html><u>还原</u></Html>" };
			}
			indexPlus();
		}
		return newsTitles;
	}
	
	// 获取上一页新闻标题
	public static Object[][] getPreviousNews() {
		for (int i = 0; i < 50; i++) {
			indexMinus();
		}
		return getNews();
	}
	
	// 获取尾页新闻标题
	public static Object[][] getLastNews() {
		index = newsList.getDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + newsList.size() + 25;
		return getPreviousNews();
	}
	
	// 恢复选中列表项的已删除新闻
	public static Object[][] restore(int num){
		num = 25 - num;
		for(int i = 0; i < num; i++){
			indexMinus();
		}
		newsList.restore(index);
		for(int i = 0; i < 25 - num; i++){
			indexMinus();
		}
		
		return getNews();
	}
}
