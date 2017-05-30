package util;

import model.TestNewsList;

public class TestNewsGetter {
	static int index = 0; // 当前查看新闻的下标
	static TestNewsList testNewsList = Initializer.testNewsList;
	
	public static void init(){
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
}
