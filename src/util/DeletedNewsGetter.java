package util;

import model.NewsList;

public class DeletedNewsGetter {
	static int index = 0; // ɾ���б����һ�����ŵ���һ�����±�
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
	
	// ִ��index++����
	public static void indexPlus() {
		index++;
		if(index < newsList.size()){
			if(!newsList.isDeleted(index, true))
				indexPlus();
		}
	}
	
	// ִ��index--����
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
	
	// ��ȡ��һҳ���ű���
	public static Object[][] getNews() {
		if(index >= newsList.size())
			return newsTitles;
		for (int i = 0; i < 25; i++) {
			if (index >= newsList.size()) {
				newsTitles[i] = new Object[] { "", "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index), "<Html><u>��ԭ</u></Html>" };
			}
			indexPlus();
		}
		return newsTitles;
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
		index = newsList.getDeletedCount();
		index = index % 25;
		if (index != 0) {
			index = 25 - index;
		}
		index = index + newsList.size() + 25;
		return getPreviousNews();
	}
	
	// �ָ�ѡ���б������ɾ������
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
