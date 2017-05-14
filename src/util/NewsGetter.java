package util;

import model.NewsList;

/*
 * 用于获取新闻页面的25条新闻标题
 * 并记录当前读取到的新闻条数
 */
public class NewsGetter {
	static int index = 0; // 新闻列表最后一条新闻的下一条的下标
	static NewsList newsList = Initializer.newsList;
	static int selectedIndex = 0; // 选中项的新闻下标
	static int selectedMainTag = 0;	//选中的主标签
	static Object[][] newsTitles = { new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" } };

	public static void init(){
		index = -1;
		indexPlus();
	}
	
	// 获取下一页新闻标题
	public static Object[][] getNews() {
		if(index >= newsList.size())
			return newsTitles;
		for (int i = 0; i < 25; i++) {
			if (index >= newsList.size()) {
				newsTitles[i] = new Object[] { "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index) };
			}
			indexPlus();
		}
		return newsTitles;
	}

	// 执行index++操作
	public static void indexPlus() {
		index++;
		if(index < newsList.size()){
			if(newsList.isDeleted(index))
				indexPlus();
		}
	}

	// 执行index--操作
	public static void indexMinus() {
		index--;
		if(index < newsList.getFirstNotDeleted())
			index = newsList.getFirstNotDeleted();
		else if(newsList.isDeleted(index))
			indexMinus();
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
		index = newsList.getNotDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + newsList.size() + 25;
		return getPreviousNews();
	}
	
	// 设置选中的新闻项
	public static void setSelected(int num){
		num = 25 - num;
		int tempIndex = index;
		for(int i = 0; i < num; i++){
			indexMinus();
		}
		selectedIndex = index;
		index = tempIndex;
	}
	
	public static String getSelectedContent(){
		NewsDetailReader newsDetailReader = new NewsDetailReader(newsList.getNewsItem(selectedIndex));
		return newsDetailReader.getContent();
	}
	
	public static Object[][] deleteSelectedNews(){
		for(int i = 0; i < 25; i++){
			indexMinus();
		}
		newsList.delete(selectedIndex);
		return getNews();
	}
	
	public static void setSelectedMainTag(int num){
		selectedMainTag = num;
	}
	public static int getSelectedMainTag(){
		return selectedMainTag;
	}
	
	public static void refactorTags(int subTag){
		String ntag = selectedMainTag + " " + subTag;
		newsList.refactor(selectedIndex, ntag);
	}
}
