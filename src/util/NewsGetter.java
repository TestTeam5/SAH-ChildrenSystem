package util;

import model.NewsList;

/*
 * ���ڻ�ȡ����ҳ���25�����ű���
 * ����¼��ǰ��ȡ������������
 */
public class NewsGetter {
	static int index = 0; // �����б����һ�����ŵ���һ�����±�
	static NewsList newsList = Initializer.newsList;
	static int selectedIndex = 0; // ѡ����������±�
	static int selectedMainTag = 0;	//ѡ�е�����ǩ
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
	
	// ��ȡ��һҳ���ű���
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

	// ִ��index++����
	public static void indexPlus() {
		index++;
		if(index < newsList.size()){
			if(newsList.isDeleted(index))
				indexPlus();
		}
	}

	// ִ��index--����
	public static void indexMinus() {
		index--;
		if(index < newsList.getFirstNotDeleted())
			index = newsList.getFirstNotDeleted();
		else if(newsList.isDeleted(index))
			indexMinus();
	}

	// ��ȡ��һҳ���ű���
	public static Object[][] getPreviousNews() {
		for (int i = 0; i < 50; i++) {
			indexMinus();
		}
		return getNews();
	}

	// ��ȡβҳ���ű���
	public static Object[][] getLastNews() {
		index = newsList.getNotDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + newsList.size() + 25;
		return getPreviousNews();
	}
	
	// ����ѡ�е�������
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
