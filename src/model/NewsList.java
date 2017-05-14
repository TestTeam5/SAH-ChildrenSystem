package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.XMLReader;

/*��ǩ���x y���ձ�
 *   \	x		0							1					2					3
 * y	��ֽ���			��������	��������	������Դ...
 * 0	����һ������
 * 1	ʡί���ر�
 * 2	...
 */

public class NewsList {

	private ArrayList<Map<String, String>> newslist = new ArrayList<>();
	private  Map<String, Integer> tagscount = new HashMap<>();
	
	{
		tagscount.put("0 0", 0);tagscount.put("0 1", 0);tagscount.put("0 2", 0);
		tagscount.put("1 0", 0);tagscount.put("1 1", 0);tagscount.put("1 2", 0);
		tagscount.put("1 3", 0);tagscount.put("2 0", 0);tagscount.put("2 1", 0);
		tagscount.put("2 2", 0);tagscount.put("2 3", 0);tagscount.put("2 4", 0);
		tagscount.put("2 5", 0);tagscount.put("2 6", 0);tagscount.put("2 7", 0);
		tagscount.put("2 8", 0);tagscount.put("2 9", 0);tagscount.put("3 0", 0);
		tagscount.put("3 1", 0);tagscount.put("3 2", 0);tagscount.put("3 3", 0);
		tagscount.put("3 4", 0);tagscount.put("3 5", 0);tagscount.put("3 6", 0);
		tagscount.put("3 7", 0);tagscount.put("4 0", 0);tagscount.put("4 1", 0);
		tagscount.put("4 2", 0);tagscount.put("4 3", 0);tagscount.put("4 4", 0);
		tagscount.put("5 0", 0);tagscount.put("5 1", 0);tagscount.put("5 2", 0);
		tagscount.put("5 3", 0);tagscount.put("5 4", 0);tagscount.put("6 0", 0);
		tagscount.put("6 1", 0);tagscount.put("6 2", 0);tagscount.put("6 3", 0);
		tagscount.put("6 4", 0);tagscount.put("7 0", 0);tagscount.put("7 1", 0);
		tagscount.put("7 2", 0);tagscount.put("7 3", 0);tagscount.put("7 4", 0);
		tagscount.put("8 0", 0);tagscount.put("8 1", 0);tagscount.put("8 2", 0);
		tagscount.put("8 3", 0);tagscount.put("8 4", 0);
	};
	
	private int deletedCount = 0;	// ͳ�Ʊ�ɾ����������
	
	
	/*
	 * ������ز���
	 */
	public void init(XMLReader xmlReader){
		this.newslist = xmlReader.readXml();
		count();
	}
	public String getTitle(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("Title");
	}
	public String getDate(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("Date");
	}
	public String getDetail(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("EncodedContent");
	}
	public String getUrl(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("TrueUrl");
	}
	public String getID(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("ID");
	}
	
	//��ñ�ǩ�ķ�������ȶ
	public String getTagIts(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("TagIts");
	}
	public String getLocation(int index){
		if(index >= newslist.size()){
			return null;
		}
		return this.newslist.get(index).get("Location");
	}
	
	public boolean isDeleted(int index){
		if(index >= newslist.size()){
			return false;
		}
		if(this.newslist.get(index).get("IsDeleted") == null){
			return false;
		}
		return (this.newslist.get(index).get("IsDeleted").equals("true")) ? true : false;
	}
	
	public void delete(int index){
		if(index >= newslist.size()){
			return;
		}
		this.newslist.get(index).put("IsDeleted", "true");
	}
	public void restore(int index){
		if(index >= newslist.size()){
			return;
		}
		this.newslist.get(index).put("IsDeleted", "false");
	}
	
	
	public int size(){
		return newslist.size();
	}
	
	// ��ȡ��ɾ����������
	public int getDeletedCount(){
		return deletedCount;
	}
	
	// ��ȡδ��ɾ����������
	public int getNotDeletedCount(){
		return size()-getDeletedCount();
	}
	
	public boolean isEmpty(){
		if(newslist.size() == 0){
			return true;
		}
		return false;
	}
	
	// ��ȡ��һ��δ��ɾ���������±�
	public int getFirstNotDeleted(){
		for(int i = 0; i < size(); i++){
			if(!isDeleted(i)){
				return i;
			}
		}
		return -1;
	}
	
	// ��ȡ��Ӧ������
	public Map<String, String> getNewsItem(int index){
		return newslist.get(index);
	}
	
	
	/*
	 * ��ǩ��ز���
	 */
	public  int getCount(String ntag){
		return tagscount.get(ntag);
	}
	//����ͬ���ǩ���滻���������
	public void refactor(int index, String ntag){
		String NewTag = this.newslist.get(index).get("TagIts");
		String type = NewTag.substring(0, 2);
		int i = NewTag.indexOf(type);
		if(i != -1){
			this.newslist.get(index).put("TagIts", NewTag.substring(0, i) + ntag + NewTag.substring(i + 3));
			minusCount(NewTag.substring(i, i + 3));
		}else{
			this.newslist.get(index).put("TagIts", NewTag + "\\|" + ntag);
		}
		addCount(ntag);
	}
	private void count(){
		String TagIts;
		String IsDeleted;
		for(Map<String, String> news : this.newslist){
			// ͳ�Ʊ�ǩ����
			TagIts = news.get("TagIts");
			if(TagIts != null){
				for(String tag : TagIts.split("\\|")){
					addCount(tag);
				}
			}
			// ͳ����ɾ����������
			IsDeleted = news.get("IsDeleted");
			if(IsDeleted == "true"){
				addDeletedCount();
			}
		}
	}
	
	private  void addCount(String ntag){
		int tagCount = this.tagscount.get(ntag);
		this.tagscount.put(ntag, ++tagCount);
	}
	private  void minusCount(String ntag){
		int tagCount = this.tagscount.get(ntag);
		this.tagscount.put(ntag, --tagCount);
	}
	
	private void addDeletedCount(){
		deletedCount++;
	}
	private void minusDeletedCount(){
		deletedCount--;
	}
	
	
}
	