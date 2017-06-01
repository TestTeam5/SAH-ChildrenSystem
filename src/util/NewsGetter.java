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
	static int pageNumber = 0; // 新闻列表选中页码
	static Object[][] newsTitles = { new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" } };

	public static void init(){
		pageNumber = 0;
		index = -1;
		indexPlus();
	}
	
	// 获取下一页新闻标题
	public static Object[][] getNews() {
		pagePlus();
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
			if(newsList.isDeleted(index, false))
				indexPlus();
		}
	}

	// 执行index--操作
	public static void indexMinus() {
		index--;
		if(index < newsList.getFirstNotDeleted()){
			index = newsList.getFirstNotDeleted();
			if(index == -1){
				index = newsList.size();
			}
		}
		else if(newsList.isDeleted(index, false)){
			indexMinus();
		}
	}

	private static void pagePlus() {
		pageNumber++;
		if(pageNumber > getMaxPage()){
			pageNumber = getMaxPage();
		}
	}
	
	private static void pageMinus(){
		pageNumber--;
		if(pageNumber < 1){
			pageNumber = 1;
		}
	}
	
	// 获取上一页新闻标题
	public static Object[][] getPreviousNews() {
		for (int i = 0; i < 50; i++) {
			indexMinus();
		}
		boolean shouldMinusAgain = false;
		if(pageNumber >= getMaxPage()){
			shouldMinusAgain = false;
		}else{
			shouldMinusAgain = true;
		}
		Object[][] temp = getNews();
		if(shouldMinusAgain){
			pageMinus();
		}
		pageMinus();
		return temp;
	}

	// 获取尾页新闻标题
	public static Object[][] getLastNews() {
		index = newsList.getNotDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + newsList.size() + 25;
		Object[][] temp = getPreviousNews();
		pageNumber = getMaxPage();
		return temp;
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
	
	// 获取选中新闻主标签下的子标签值，若无子标签返回-1
	public static int getSelectedSubTag(){
		return newsList.getSelectedSubTag(selectedIndex, selectedMainTag);
	}
	
	public static String getSelectedContent(){
		NewsDetailReader newsDetailReader = new NewsDetailReader(newsList.getNewsItem(selectedIndex));
		return newsDetailReader.getContent();
	}
	
	public static Object[][] deleteSelectedNews(){
		for(int i = 0; i < 25; i++){
			indexMinus();
		}
		index--;
		newsList.delete(selectedIndex);
		indexPlus();
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
	
	public static int getPage(){
		return pageNumber;
	}
	
	public static int getMaxPage(){
		return newsList.getNotDeletedCount()/25 + 1;
	}
	
	public static Object[][] setPage(int page){
		if(page < 0){
			init();
			return getNews();
		}else if(page > getMaxPage()){
			return getLastNews();
		}else if(page > pageNumber){
			int temp = pageNumber;
			for(int i = 0; i < page - temp - 1; i++){
				getNews();
			}
			return getNews();
		}else if(page < pageNumber){
			int temp = pageNumber;
			for(int i = 0; i < temp - page - 1; i++){
				getPreviousNews();
			}
			return getPreviousNews();
		}else{
			getPreviousNews();
			return getNews();
		}
	}
}
