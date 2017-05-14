package util;

import model.NewsList;

/*
 * 用于获取新闻页面的25条新闻标题
 * 并记录当前读取到的新闻条数
 */
public class NewsGetter {
	static int index = -1; // 新闻列表最后一条新闻的下一条的下标
	public static int newspaper = 0; // 下一条新闻所属报纸类型标记
	static NewsList newsList = Initializer.guangming;
	static Object[][] newsTitles = { new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" }, new Object[] { "" },
			new Object[] { "" } };
	
	static int selectedIndex = 0;
	static int selectedNewspaper = 0;
	static NewsList selectedNewsList = Initializer.guangming;

	public static void init() {
		if (!Initializer.guangming.isEmpty()) {
			newspaper = 0;
			newsList = Initializer.guangming;
			index = -1;
			indexPlus();
		} else if (!Initializer.nanfangdaily.isEmpty()) {
			newspaper = 1;
			newsList = Initializer.nanfangdaily;
			index = -1;
			indexPlus();
		} else if (!Initializer.sichuan.isEmpty()) {
			newspaper = 2;
			newsList = Initializer.sichuan;
			index = -1;
			indexPlus();
		} else {
			newspaper = 3;
			newsList = null;
			index = -1;
			indexPlus();
		}
	}

	// 获取下一页新闻标题
	public static Object[][] getNews() {
		for (int i = 0; i < 25; i++) {
			if (newsList == null) {
				newsTitles[i] = new Object[] { "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index) };
			}
			indexPlus();
		}
		return newsTitles;
	}

	// 执行index++操作并根据index值更新newspaper和newsList
	public static void indexPlus() {
		index++;
		if (newsList != null) {
			if (index >= newsList.size()) {
				index = 0;
				if (newspaper == 0) {
					if (!Initializer.nanfangdaily.isEmpty()) {
						newsList = Initializer.nanfangdaily;
						index = 0;
						newspaper = 1;
					} else if (!Initializer.sichuan.isEmpty()) {
						newsList = Initializer.sichuan;
						index = 0;
						newspaper = 2;
					} else {
						newsList = null;
						index = 0;
						newspaper = 3;
					}
				} else if (newspaper == 1) {
					if (!Initializer.sichuan.isEmpty()) {
						newsList = Initializer.sichuan;
						index = 0;
						newspaper = 2;
					} else {
						newsList = null;
						index = 0;
						newspaper = 3;
					}
				} else if (newspaper == 2) {
					newsList = null;
					index = 0;
					newspaper = 3;
				}
			}
		}
		if (newsList != null) {
			if (newsList.isDeleted(index)) {
				indexPlus();
			}
		}
	}

	// 执行index--操作并根据index值更新newspaper和newsList
	public static void indexMinus() {
		index--;
		if (newspaper == 3 && index < 0) {
			if (Initializer.sichuan.getNotDeletedCount() != 0) {
				index = Initializer.sichuan.size() - 1;
				newspaper = 2;
				newsList = Initializer.sichuan;
			} else if (Initializer.nanfangdaily.getNotDeletedCount() != 0) {
				index = Initializer.nanfangdaily.size() - 1;
				newspaper = 1;
				newsList = Initializer.nanfangdaily;
			} else if (Initializer.guangming.getNotDeletedCount() != 0) {
				index = Initializer.guangming.size() - 1;
				newspaper = 0;
				newsList = Initializer.guangming;
			} else {
				index = 0;
			}
		}
		if (newspaper < 3) {
			if (index < newsList.getFirstNotDeleted()) {
				if (newspaper == 2) {
					if (Initializer.nanfangdaily.getNotDeletedCount() != 0) {
						index = Initializer.nanfangdaily.size() - 1;
						newspaper = 1;
						newsList = Initializer.nanfangdaily;
					} else if (Initializer.guangming.getNotDeletedCount() != 0) {
						index = Initializer.guangming.size() - 1;
						newspaper = 0;
						newsList = Initializer.guangming;
					} else {
						index = newsList.getFirstNotDeleted();
					}
				} else if (newspaper == 1) {
					if (Initializer.guangming.getNotDeletedCount() != 0) {
						index = Initializer.guangming.size() - 1;
						newspaper = 0;
						newsList = Initializer.guangming;
					} else {
						index = newsList.getFirstNotDeleted();
					}
				} else if (newspaper == 0) {
					index = newsList.getFirstNotDeleted();
				}
			}
		}
		if (index == -1) {
			newsList = null;
			newspaper = 3;
			index = 0;
		}
		if (newsList != null) {
			if (!(newspaper == 0 && index < newsList.getFirstNotDeleted())) {
				if (newsList.isDeleted(index)) {
					indexMinus();
				}
			}
		}
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
		index = Initializer.guangming.getNotDeletedCount() + Initializer.nanfangdaily.getNotDeletedCount()
				+ Initializer.sichuan.getNotDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + 25;
		newspaper = 3;
		newsList = null;
		return getPreviousNews();
	}
	
	// 设置选中的新闻项
	public static void setSelected(int num){
		num = 25 - num;
		int tempIndex = index;
		int tempNewspaper = newspaper;
		NewsList tempNewsList = newsList;
		for(int i = 0; i < num; i++){
			indexMinus();
		}
		selectedIndex = index;
		selectedNewspaper = newspaper;
		selectedNewsList = newsList;
		index = tempIndex;
		newspaper = tempNewspaper;
		newsList = tempNewsList;
	}
	
	public static String getSelectedContent(){
		NewsDetailReader newsDetailReader = new NewsDetailReader(selectedNewsList.getNewsItem(selectedIndex));
		return newsDetailReader.getContent();
	}
}
