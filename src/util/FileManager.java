package util;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * 合并完成后记得刷新新闻列表
 */

public class FileManager {
	
	private static String guangming = "resource/file/guangming.xml";
	private static String nanfang =  "resource/file/nanfangdaily.xml";
	private static String sichuan = "resource/file/sichuan.xml";
	
	public static void mergeAll(List<String> pathes){
		for(String path : pathes){
			merge(path);
		}
	}
	
	private static void merge(String path){
		Document oldDoc, newDoc;
		newDoc = getDOMTree(path);
		if(newDoc.equals(null)){
			System.out.println("ERROR WHEN CREATING DOMTREE, PLEASE CHECK THE FILE PATH: " + path);
		} else{
			String oldPath = getOldPath(newDoc);
			oldDoc = getDOMTree(oldPath);
			NodeList oldTagIts, newTagIts;
			oldTagIts = oldDoc.getElementsByTagName("TagIts");
			newTagIts = newDoc.getElementsByTagName("TagIts");
			Node oldTagNode, newTagNode;
			for(int i = 0; i < newTagIts.getLength(); i++){
				newTagNode = newTagIts.item(i);
				oldTagNode = oldTagIts.item(i);
				if(newTagNode.hasChildNodes()){
					String newTag = newTagNode.getFirstChild().getNodeValue();
					if(oldTagNode.hasChildNodes() && !newTag.equals(oldTagNode.getFirstChild().getNodeValue())){
						oldTagNode.getFirstChild().setNodeValue(newTag);
					} else{
						oldTagNode.appendChild(oldDoc.createTextNode(newTag));
					}
				}
			}
			XMLWriter.writeFile(oldDoc, oldPath);
		}
	}
	
	private static Document getDOMTree(String path){
		try{
	        // 1.得到DOM解析器的工厂实例
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        // 2.从DOM工厂里获取DOM解析器
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // 3.解析XML文档，得到document，即DOM树
	        Document doc = db.parse(path);
	        return doc;
		} catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
	}
	
	private static String getOldPath(Document newDoc){
		NodeList locationList = newDoc.getElementsByTagName("Location");
		Node location = locationList.item(0);
		switch(location.getFirstChild().getNodeValue()){
		case "光明日报":
			return guangming;
		case "南方都市报(全国版)":
			return nanfang;
		case "四川日报(数字报)":
			return sichuan;
		default:
			return null;
		}
	}

}
