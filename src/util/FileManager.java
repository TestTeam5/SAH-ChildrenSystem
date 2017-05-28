package util;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * �ϲ���ɺ�ǵ�ˢ�������б�
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
	        // 1.�õ�DOM�������Ĺ���ʵ��
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        // 2.��DOM�������ȡDOM������
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // 3.����XML�ĵ����õ�document����DOM��
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
		case "�����ձ�":
			return guangming;
		case "�Ϸ����б�(ȫ����)":
			return nanfang;
		case "�Ĵ��ձ�(���ֱ�)":
			return sichuan;
		default:
			return null;
		}
	}

}
