package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import util.XMLReader;
import util.XMLWriter;

/*��ǩ���x y���ձ�
 *   \	x		0							1					2					3
 * y	��ֽ���			��������	��������	������Դ...
 * 0	����һ������
 * 1	ʡί���ر�
 * 2	...
 */

public class NewsList {

	final String[] paths = {"resource/file/guangming.xml", "resource/file/nanfangdaily.xml", "resource/file/sichuan.xml"};
	
	private ArrayList<Map<String, String>> newslist = new ArrayList<>();
	private Map<String, Map<String, Integer> > tagscount0 = new HashMap<>();	// 0 ��������ձ�
	private Map<String, Map<String, Integer> > tagscount1 = new HashMap<>();	// 1 �����Ϸ����б�
	private Map<String, Map<String, Integer> > tagscount2 = new HashMap<>();	// 2 �����Ĵ��ձ�
	
	private final String[] allTags = {"0 0", "0 1", "0 2",
			"1 0", "1 1", "1 2", "1 3", 
			"2 0", "2 1", "2 2", "2 3", "2 4", "2 5", "2 6", "2 7", "2 8", "2 9",
			"3 0", "3 1", "3 2", "3 3", "3 4", "3 5", "3 6", "3 7", 
			"4 0", "4 1", "4 2", "4 3", "4 4", 
			"5 0", "5 1", "5 2", "5 3", "5 4", 
			"6 0", "6 1", "6 2", "6 3", "6 4", 
			"7 0", "7 1", "7 2", "7 3", "7 4",
			"8 0", "8 1", "8 2", "8 3", "8 4"};
	
	{
		for(String s: allTags){
			tagscount0.put(s, new HashMap<String, Integer>());
			tagscount1.put(s, new HashMap<String, Integer>());
			tagscount2.put(s, new HashMap<String, Integer>());
		}
	};
	
	private int deletedCount = 0;	// ͳ�Ʊ�ɾ����������
	
	
	/*
	 * ������ز���
	 */
	public void init(){
		XMLReader guangmingreader = new XMLReader(paths[0]);
		XMLReader nanfangdailyreader = new XMLReader(paths[1]);
		XMLReader sichuanreader = new XMLReader(paths[2]);
		this.newslist = guangmingreader.readXml();
		this.newslist.addAll(nanfangdailyreader.readXml());
		this.newslist.addAll(sichuanreader.readXml());
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
	
	// �����Ƿ�ɾ��, ���������ޣ�����defaultBoolֵ
	public boolean isDeleted(int index, boolean defaultBool){
		if(index >= newslist.size()){
			return defaultBool;
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
		int num = 0;
		if(newslist.get(index).get("Location").equals("�����ձ�"))
			num = 0;
		else if(newslist.get(index).get("Location").equals("�Ϸ����б�(ȫ����)"))
			num = 1;
		else if(newslist.get(index).get("Location").equals("�Ĵ��ձ�(���ֱ�)"))
			num = 2;
		XMLWriter.write(paths[num], newslist.get(index).get("ID"), "IsDeleted", "true");
	}
	public void restore(int index){
		if(index >= newslist.size()){
			return;
		}
		this.newslist.get(index).put("IsDeleted", "false");
		int num = 0;
		if(newslist.get(index).get("Location").equals("�����ձ�"))
			num = 0;
		else if(newslist.get(index).get("Location").equals("�Ϸ����б�(ȫ����)"))
			num = 1;
		else if(newslist.get(index).get("Location").equals("�Ĵ��ձ�(���ֱ�)"))
			num = 2;
		XMLWriter.write(paths[num], newslist.get(index).get("ID"), "IsDeleted", "false");
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
			if(!isDeleted(i, false)){
				return i;
			}
		}
		return -1;
	}
	
	// ��ȡ��һ����ɾ���������±�
	public int getFirstDeleted(){
		for(int i = 0; i < size(); i++){
			if(isDeleted(i, true)){
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
	public int getCount(String ntag, int num){
		int count = 0;
		Map<String, Integer> temp = null;
		if(num == 0)
			temp = tagscount0.get(ntag);
		else if(num == 1)
			temp = tagscount1.get(ntag);
		else if(num == 2)
			temp = tagscount2.get(ntag);
		for(Integer value: temp.values()){
			count += value;
		}
		return count;
	}
	
	public Map<String, Integer> getTagCountMap(String ntag, int num){
		if(num == 0)
			return tagscount0.get(ntag);
		else if(num == 1)
			return tagscount1.get(ntag);
		else if(num == 2)
			return tagscount2.get(ntag);
		return null;
	}
	
	public int getSelectedSubTag(int index, int selectedMainTag){
		String newsTag = this.newslist.get(index).get("TagIts");
		if(newsTag == null)
			return -1;
		String mainTagString = selectedMainTag + " ";
		int i = newsTag.indexOf(mainTagString);
		if(i == -1){
			return -1;
		}else{
			return Integer.parseInt(newsTag.substring(i+2, i+3));
		}
	}
	
	//����ͬ���ǩ���滻���������
	public void refactor(int index, String ntag){
		int num = 0;
		if(newslist.get(index).get("Location").equals("�����ձ�"))
			num = 0;
		else if(newslist.get(index).get("Location").equals("�Ϸ����б�(ȫ����)"))
			num = 1;
		else if(newslist.get(index).get("Location").equals("�Ĵ��ձ�(���ֱ�)"))
			num = 2;
		String newsTag = this.newslist.get(index).get("TagIts");
		if(newsTag == null)
			newsTag = "";
		String type = ntag.substring(0, 2);
		int i = newsTag.indexOf(type);
		String newTagString = "";
		if(i != -1){
			newTagString = newsTag.substring(0, i) + ntag + newsTag.substring(i + 3);
			this.newslist.get(index).put("TagIts", newTagString);
			minusCount(newsTag.substring(i, i + 3), num, this.newslist.get(index).get("Date").substring(0, 4));
		}else{
			if(!newsTag.equals("")){
				newsTag = newsTag + "|";
			}
			newTagString = newsTag + ntag;
			this.newslist.get(index).put("TagIts", newTagString);
		}
		addCount(ntag, num, this.newslist.get(index).get("Date").substring(0, 4));
		XMLWriter.write(paths[num], newslist.get(index).get("ID"), "TagIts", newTagString);
	}
	private void count(){
		String TagIts;
		String IsDeleted;
		for(Map<String, String> news : this.newslist){
			if(news.get("Location").equals("�����ձ�")){
				// ͳ�Ʊ�ǩ����
				TagIts = news.get("TagIts");
				if(TagIts != null){
					for(String tag : TagIts.split("\\|")){
						addCount(tag, 0, news.get("Date").substring(0, 4));
					}
				}
				// ͳ����ɾ����������
				IsDeleted = news.get("IsDeleted");
				if(IsDeleted != null){
					if(IsDeleted.equals("true")){
						addDeletedCount();
					}
				}
			}else if(news.get("Location").equals("�Ϸ����б�(ȫ����)")){
				// ͳ�Ʊ�ǩ����
				TagIts = news.get("TagIts");
				if(TagIts != null){
					for(String tag : TagIts.split("\\|")){
						addCount(tag, 1, news.get("Date").substring(0, 4));
					}
				}
				// ͳ����ɾ����������
				IsDeleted = news.get("IsDeleted");
				if(IsDeleted != null){
					if(IsDeleted.equals("true")){
						addDeletedCount();
					}
				}
			}else if(news.get("Location").equals("�Ĵ��ձ�(���ֱ�)")){
				// ͳ�Ʊ�ǩ����
				TagIts = news.get("TagIts");
				if(TagIts != null){
					for(String tag : TagIts.split("\\|")){
						addCount(tag, 2, news.get("Date").substring(0, 4));
					}
				}
				// ͳ����ɾ����������
				IsDeleted = news.get("IsDeleted");
				if(IsDeleted != null){
					if(IsDeleted.equals("true")){
						addDeletedCount();
					}
				}
			}
		}
	}
	
	private  void addCount(String ntag, int num, String year){
		Map<String, Integer> temp = null;
		if(num == 0){
			temp = this.tagscount0.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, ++tagCount);
		}else if(num == 1){
			temp = this.tagscount1.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, ++tagCount);
		}else if(num == 2){
			temp = this.tagscount2.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, ++tagCount);
		}
	}
	private  void minusCount(String ntag, int num, String year){
		Map<String, Integer> temp = null;
		if(num == 0){
			temp = this.tagscount0.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, --tagCount);
		}else if(num == 1){
			temp = this.tagscount1.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, --tagCount);
		}else if(num == 2){
			temp = this.tagscount2.get(ntag);
			int tagCount = 0;
			if(temp.containsKey(year)){
				tagCount = temp.get(year);
			}
			temp.put(year, --tagCount);
		}
	}
	
	private void addDeletedCount(){
		deletedCount++;
	}
	private void minusDeletedCount(){
		deletedCount--;
	}
	
	
}
	