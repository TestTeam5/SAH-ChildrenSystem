package util;

import model.TestNewsList;

public class TestNewsGetter {
	static int index = 0; // 当前查看新闻的下标
	static TestNewsList testNewsList;
	static int selectedMainTag = 0;	//选中的主标签
	
	public static void init(){
		testNewsList = Initializer.testNewsList;
		index = 0;
	}
	
	public static void indexPlus(){
		if(!(index >= testNewsList.size() - 1)){
			index++;
		}
	}
	
	public static void indexMinus(){
		if(!(index <= 0)){
			index--;
		}
	}
	
	public static int getMaxCount(){
		return testNewsList.size();
	}
	
	public static int getSelectedIndex(){
		return index;
	}
	
	public static void setIndex(int num){
		if(num >= getMaxCount()){
			index = getMaxCount() - 1;
		}else if(num < 0){
			index = 0;
		}else{
			index = num;
		}
	}
	
	public static String getSelectedContent(){
		NewsDetailReader newsDetailReader = new NewsDetailReader(testNewsList.getNewsItem(index));
		return newsDetailReader.getContent();
	}
	
	public static int getFirstNotTag(){
		for(int i = 0; i < testNewsList.size(); i++){
			if(!testNewsList.hasTag(i)){
				return i;
			}
		}
		return -1;
	}
	
	public static int getPreviousNotTag(){
		int temp = index;
		indexMinus();
		while(testNewsList.hasTag(index) && index>0){
			indexMinus();
		}
		int previousIndex = index;
		index = temp;
		return previousIndex;
	}
	
	public static int getNextNotTag(){
		int temp = index;
		indexPlus();
		while(testNewsList.hasTag(index) && index<testNewsList.size()-1){
			indexPlus();
		}
		int nextIndex = index;
		index = temp;
		return nextIndex;
	}
	
	// 获取选中新闻主标签下的子标签值，若无子标签返回-1
	public static int getSelectedSubTag(){
		return testNewsList.getSelectedSubTag(index, selectedMainTag);
	}
	
	public static void setSelectedMainTag(int num){
		selectedMainTag = num;
	}
	public static int getSelectedMainTag(){
		return selectedMainTag;
	}
	
	public static void refactorTags(int subTag){
		String ntag = selectedMainTag + " " + subTag;
		testNewsList.refactor(index, ntag);
	}
}
