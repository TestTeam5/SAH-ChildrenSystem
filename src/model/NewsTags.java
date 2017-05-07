package model;

import java.util.Map;

public class NewsTags {
	
	private static Map<NewsTags, Integer> count;
	public static int getCount(NewsTags ntag){
		return count.get(ntag);
	}
	public static void addCount(NewsTags ntag){
		if(count.containsKey(ntag)){
			int tagCount = count.get(ntag);
			count.put(ntag, ++tagCount);
		} else{
			count.put(ntag, 1);
		}
	}
	public static void minusCount(NewsTags ntag){
		int tagCount = count.get(ntag);
		count.put(ntag, --tagCount);
	}

	protected int type;	//���ձ�����ļ��ķ����׼�ֳ�1~10ʮ�ࡣ
	protected String tag;
	
	public NewsTags(int type, String tag){
		this.type = type;
		this.tag = tag;
	}
	
}
