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
	static int pageNumber = 0; // �����б�ѡ��ҳ��
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
	
	// ��ȡ��һҳ���ű���
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

	// ִ��index++����
	public static void indexPlus() {
		index++;
		if(index < newsList.size()){
			if(newsList.isDeleted(index, false))
				indexPlus();
		}
	}

	// ִ��index--����
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
	
	// ��ȡ��һҳ���ű���
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

	// ��ȡβҳ���ű���
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
	
	// ��ȡѡ����������ǩ�µ��ӱ�ǩֵ�������ӱ�ǩ����-1
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
