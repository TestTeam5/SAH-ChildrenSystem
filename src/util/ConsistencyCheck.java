package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ConsistencyCheck {
	
	private static final String tag = "TagIts";
	private static double consistencyCount, sum;
	private static List<List<Map<String, String>>> uncheckNews;

	public static double getConsistencyRate(List<String> paths){
		List<List<Map<String, String>>> unchecks = new ArrayList<>();
		for(String path: paths){
			int index = path.lastIndexOf("\\");
			String password = null;
			if(index != -1){
				password = path.substring(index + 1, path.length()-4);
			}else{
				password = path.substring(0, path.length()-4);
			}
			XMLReader xmlReader = new XMLReader(path);
			List<Map<String, String>> temp = xmlReader.readXml();
			for(Map<String, String> tempMap: temp){
				if(tempMap.get("TagIts") != null){
					String decryptTagString = AESEncryptor.decrypt(tempMap.get("TagIts"), password);
					if(decryptTagString != null){
						tempMap.put("TagIts", decryptTagString);
					}else{
						return -1;
					}
				}
			}
			unchecks.add(temp);
		}
		int num = unchecks.get(0).size();
		for(List<Map<String, String>> temp: unchecks){
			if(temp.size() != num){
				return -1;
			}
		}
		return consistencyRate(unchecks);
	}
	
	public static double consistencyRate(List<List<Map<String, String>>> unchecks) {
		if(unchecks == null){
			System.out.println("NO FILES");
			return 0;
		}  else{
			uncheckNews = unchecks;
			sum = (double)unchecks.get(0).size();
			consistencyCount = (double)getConsistencyCount();
			return consistencyCount  / sum;
		}
	}
	
	private static int getConsistencyCount(){
		Initializer.wrongLineNum.clear();
		Initializer.resultNewsVec.clear();
		int indexOfNewsList, indexOfNews, count = uncheckNews.get(0).size();
		String tags1, tags2;
		Vector<String> vec;
		for(indexOfNews = 0; indexOfNews < uncheckNews.get(0).size(); indexOfNews++){
			tags1 = uncheckNews.get(0).get(indexOfNews).get(tag);
			
			vec = new Vector<>();
			vec.add(uncheckNews.get(0).get(indexOfNews).get("Title"));
			Initializer.resultNewsVec.add(vec);
			
			for(indexOfNewsList = 1; indexOfNewsList < uncheckNews.size(); indexOfNewsList++){
				tags2 = uncheckNews.get(indexOfNewsList).get(indexOfNews).get(tag);
				if(!inOrder(tags1).equals(inOrder(tags2))){
					count--;
					
					Initializer.wrongLineNum.add(indexOfNews);
					
					break;
				}
			}
		}
		return count;
	}
	
	private static String inOrder(String tags){
		if(tags != null){
			String[] orderTagsArray = tags.split("|");
			Arrays.sort(orderTagsArray);
			StringBuffer orderTags = new StringBuffer();
			for(String tag : orderTagsArray){
				orderTags.append(tag);
				orderTags.append("|");
			}
			orderTags.deleteCharAt(orderTags.length() - 1);
			return orderTags.toString();
		}else{
			return "";
		}
	}

}
