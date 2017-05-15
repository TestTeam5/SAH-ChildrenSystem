package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private  Map<String, Integer> tagscount0 = new HashMap<>();	// 0 ��������ձ�
	private Map<String, Integer> tagscount1 = new HashMap<>();	// 1 �����Ϸ����б�
	private Map<String, Integer> tagscount2 = new HashMap<>();	// 2 �����Ĵ��ձ�
	
	{
		tagscount0.put("0 0", 0);tagscount0.put("0 1", 0);tagscount0.put("0 2", 0);
		tagscount0.put("1 0", 0);tagscount0.put("1 1", 0);tagscount0.put("1 2", 0);
		tagscount0.put("1 3", 0);tagscount0.put("2 0", 0);tagscount0.put("2 1", 0);
		tagscount0.put("2 2", 0);tagscount0.put("2 3", 0);tagscount0.put("2 4", 0);
		tagscount0.put("2 5", 0);tagscount0.put("2 6", 0);tagscount0.put("2 7", 0);
		tagscount0.put("2 8", 0);tagscount0.put("2 9", 0);tagscount0.put("3 0", 0);
		tagscount0.put("3 1", 0);tagscount0.put("3 2", 0);tagscount0.put("3 3", 0);
		tagscount0.put("3 4", 0);tagscount0.put("3 5", 0);tagscount0.put("3 6", 0);
		tagscount0.put("3 7", 0);tagscount0.put("4 0", 0);tagscount0.put("4 1", 0);
		tagscount0.put("4 2", 0);tagscount0.put("4 3", 0);tagscount0.put("4 4", 0);
		tagscount0.put("5 0", 0);tagscount0.put("5 1", 0);tagscount0.put("5 2", 0);
		tagscount0.put("5 3", 0);tagscount0.put("5 4", 0);tagscount0.put("6 0", 0);
		tagscount0.put("6 1", 0);tagscount0.put("6 2", 0);tagscount0.put("6 3", 0);
		tagscount0.put("6 4", 0);tagscount0.put("7 0", 0);tagscount0.put("7 1", 0);
		tagscount0.put("7 2", 0);tagscount0.put("7 3", 0);tagscount0.put("7 4", 0);
		tagscount0.put("8 0", 0);tagscount0.put("8 1", 0);tagscount0.put("8 2", 0);
		tagscount0.put("8 3", 0);tagscount0.put("8 4", 0);
		
		tagscount1.put("0 0", 0);tagscount1.put("0 1", 0);tagscount1.put("0 2", 0);
		tagscount1.put("1 0", 0);tagscount1.put("1 1", 0);tagscount1.put("1 2", 0);
		tagscount1.put("1 3", 0);tagscount1.put("2 0", 0);tagscount1.put("2 1", 0);
		tagscount1.put("2 2", 0);tagscount1.put("2 3", 0);tagscount1.put("2 4", 0);
		tagscount1.put("2 5", 0);tagscount1.put("2 6", 0);tagscount1.put("2 7", 0);
		tagscount1.put("2 8", 0);tagscount1.put("2 9", 0);tagscount1.put("3 0", 0);
		tagscount1.put("3 1", 0);tagscount1.put("3 2", 0);tagscount1.put("3 3", 0);
		tagscount1.put("3 4", 0);tagscount1.put("3 5", 0);tagscount1.put("3 6", 0);
		tagscount1.put("3 7", 0);tagscount1.put("4 0", 0);tagscount1.put("4 1", 0);
		tagscount1.put("4 2", 0);tagscount1.put("4 3", 0);tagscount1.put("4 4", 0);
		tagscount1.put("5 0", 0);tagscount1.put("5 1", 0);tagscount1.put("5 2", 0);
		tagscount1.put("5 3", 0);tagscount1.put("5 4", 0);tagscount1.put("6 0", 0);
		tagscount1.put("6 1", 0);tagscount1.put("6 2", 0);tagscount1.put("6 3", 0);
		tagscount1.put("6 4", 0);tagscount1.put("7 0", 0);tagscount1.put("7 1", 0);
		tagscount1.put("7 2", 0);tagscount1.put("7 3", 0);tagscount1.put("7 4", 0);
		tagscount1.put("8 0", 0);tagscount1.put("8 1", 0);tagscount1.put("8 2", 0);
		tagscount1.put("8 3", 0);tagscount1.put("8 4", 0);
		
		tagscount2.put("0 0", 0);tagscount2.put("0 1", 0);tagscount2.put("0 2", 0);
		tagscount2.put("1 0", 0);tagscount2.put("1 1", 0);tagscount2.put("1 2", 0);
		tagscount2.put("1 3", 0);tagscount2.put("2 0", 0);tagscount2.put("2 1", 0);
		tagscount2.put("2 2", 0);tagscount2.put("2 3", 0);tagscount2.put("2 4", 0);
		tagscount2.put("2 5", 0);tagscount2.put("2 6", 0);tagscount2.put("2 7", 0);
		tagscount2.put("2 8", 0);tagscount2.put("2 9", 0);tagscount2.put("3 0", 0);
		tagscount2.put("3 1", 0);tagscount2.put("3 2", 0);tagscount2.put("3 3", 0);
		tagscount2.put("3 4", 0);tagscount2.put("3 5", 0);tagscount2.put("3 6", 0);
		tagscount2.put("3 7", 0);tagscount2.put("4 0", 0);tagscount2.put("4 1", 0);
		tagscount2.put("4 2", 0);tagscount2.put("4 3", 0);tagscount2.put("4 4", 0);
		tagscount2.put("5 0", 0);tagscount2.put("5 1", 0);tagscount2.put("5 2", 0);
		tagscount2.put("5 3", 0);tagscount2.put("5 4", 0);tagscount2.put("6 0", 0);
		tagscount2.put("6 1", 0);tagscount2.put("6 2", 0);tagscount2.put("6 3", 0);
		tagscount2.put("6 4", 0);tagscount2.put("7 0", 0);tagscount2.put("7 1", 0);
		tagscount2.put("7 2", 0);tagscount2.put("7 3", 0);tagscount2.put("7 4", 0);
		tagscount2.put("8 0", 0);tagscount2.put("8 1", 0);tagscount2.put("8 2", 0);
		tagscount2.put("8 3", 0);tagscount2.put("8 4", 0);
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
		if(num == 0)
			return tagscount0.get(ntag);
		else if(num == 1)
			return tagscount1.get(ntag);
		else if(num == 2)
			return tagscount2.get(ntag);
		return 0;
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
			minusCount(newsTag.substring(i, i + 3), num);
		}else{
			if(!newsTag.equals("")){
				newsTag = newsTag + "|";
			}
			newTagString = newsTag + ntag;
			this.newslist.get(index).put("TagIts", newTagString);
		}
		addCount(ntag, num);
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
						addCount(tag, 0);
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
						addCount(tag, 1);
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
						addCount(tag, 2);
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
	
	private  void addCount(String ntag, int num){
		if(num == 0){
			int tagCount = this.tagscount0.get(ntag);
			this.tagscount0.put(ntag, ++tagCount);
		}else if(num == 1){
			int tagCount = this.tagscount1.get(ntag);
			this.tagscount1.put(ntag, ++tagCount);
		}else if(num == 2){
			int tagCount = this.tagscount2.get(ntag);
			this.tagscount2.put(ntag, ++tagCount);
		}
	}
	private  void minusCount(String ntag, int num){
		if(num == 0){
			int tagCount = this.tagscount0.get(ntag);
			this.tagscount0.put(ntag, --tagCount);
		}else if(num == 1){
			int tagCount = this.tagscount1.get(ntag);
			this.tagscount1.put(ntag, --tagCount);
		}else if(num == 2){
			int tagCount = this.tagscount2.get(ntag);
			this.tagscount2.put(ntag, --tagCount);
		}
	}
	
	private void addDeletedCount(){
		deletedCount++;
	}
	private void minusDeletedCount(){
		deletedCount--;
	}
	
	
}
	