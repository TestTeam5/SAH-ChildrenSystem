package util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.NewsTags;

/*
 * ���ڶ�ȡ���Ÿ������ݣ�
 * ���� getNewsDetail ������ȡ��ϸ���ݣ�
 * getNewsTags��ȡ��ǩ��
 * getTitle��ȡ����;
 * getDate��ȡ����;
 * getLocation��ȡLocation;
 * getIsDeleted��ȡ�Ƿ�ɾ����ʶ;
 */
public class NewsReader {
	File file=null;
	DocumentBuilder documentBuilder=null;
	DocumentBuilderFactory documentBuilderFactory=null;
	
	// ���캯�������Ӧ�ļ��ĵ�ַ
	public NewsReader(String filePath)
	{
		 this.file=new File(filePath);
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵ���ϸ����
	public String getNewsDetail(String ID){
		String encodedContent = getEncodedContent(ID);
		// �������ֶ�Ϊ�գ����������ȡ������Ϣ
		if(encodedContent == null){
			return getOnlineNews(ID);
		}
		// ����Ϊ����Ի�ȡ��EncodedContent�ֶν��н���
		else{
			return encoded(encodedContent);
		}
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵ�����
	public String getDate(String ID){
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "Date");
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵ�location
	public String getLocation(String ID){
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "Location");
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵ�ɾ����ʶ
	public String getIsDeleted(String ID){
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "IsDeleted");
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵı���
	public String getTitle(String ID){
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "Title");
	}
	
	// ��������ID�ַ��������ض�Ӧ���ŵ����ű�ǩ
	//��ǩ�ֶθ�ʽΪ��int String|int String|......��
	public ArrayList<NewsTags> getNewsTags(String ID){
		Node newsNode = getNode(ID);
		String tagString = getNodeContent(newsNode, "TagIts");
		ArrayList<NewsTags> newsTagsList = new ArrayList<NewsTags>();
		for(String tagIt: tagString.split("\\|")){
			int index = tagIt.indexOf(' ');
			int type = Integer.parseInt(tagIt.substring(0, index));
			String tag = tagIt.substring(index + 1);
			NewsTags newsTag = new NewsTags(type, tag);
			newsTagsList.add(newsTag);
			NewsTags.addCount(newsTag);
		}
		return newsTagsList;
	}
	
	// ���ض�ӦID���ŵ�EncodedContent�ֶ�
	private String getEncodedContent(String ID)
	{
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "EncodedContent");
	}
	
	// ��������ID�ҵ���Ӧ�����Ž��
	private Node getNode(String ID){
		try{
			documentBuilderFactory=DocumentBuilderFactory.newInstance();
			documentBuilder=documentBuilderFactory.newDocumentBuilder();
			Document document=documentBuilder.parse(file);
			Element element=null;
			 
			 element=document.getDocumentElement();
			 NodeList newsNList=element.getChildNodes();
			 for(int i=0;i<newsNList.getLength();i++)
			 {
				Node newsNode=newsNList.item(i);
				if(Node.ELEMENT_NODE==newsNode.getNodeType()&&newsNode.getNodeName().equals("NewsData"))
				{
					
					NodeList nChildList=newsNode.getChildNodes();
					for(int j=0;j<nChildList.getLength();j++)
					{
						if(Node.ELEMENT_NODE==nChildList.item(j).getNodeType())
						{
							Node childNode= nChildList.item(j);
							if (null==childNode.getFirstChild() || !childNode.getNodeName().equals("ID")) {
								continue;
							}
							if(childNode.getFirstChild().getNodeValue().equals(ID)){
								return newsNode;
							}
							
						}
					}
				}
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// �õ���Ӧnode���Ķ�Ӧ��ǩ�ֶ�
	private String getNodeContent(Node newsNode, String tagName){
		NodeList nChildList=newsNode.getChildNodes();
		for(int j=0;j<nChildList.getLength();j++)
		{
			if(Node.ELEMENT_NODE==nChildList.item(j).getNodeType())
			{
				Node childNode= nChildList.item(j);
				if (null==childNode.getFirstChild() || !childNode.getNodeName().equals(tagName)) {
					continue;
				}
				
				return childNode.getFirstChild().getNodeValue();
			}
		}
		
		return null;
	}
	
	// ����EncodedContent�ֶΣ����ؽ����������
	private String encoded(String encodedContent){
		try {
			byte[] toEncodeAsBytes = encodedContent.getBytes();
			byte[] encodeBytes = Base64.decodeBase64(toEncodeAsBytes);
			return new String(encodeBytes, "utf-16le");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	// ���ض�ӦID���ŵ�TrueUrl�ֶ�
	private String getTrueUrl(String ID)
	{
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "TrueUrl");
	}
	
	private String getOnlineNews(String ID){
		String trueUrl = getTrueUrl(ID);
		return getNewsFromUrl(trueUrl);
	}
	
	// ��urlץȡ�������ݵ�p��ǩ�е�����
	private String getNewsFromUrl(String trueUrl){
		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(trueUrl).get();
			Elements pData = doc.getElementsByTag("p");
			StringBuilder newsContent = new StringBuilder("<html><body>");
			for(org.jsoup.nodes.Element element: pData){
				newsContent.append("\n<p>"+element.text()+"</p>");
			}
			newsContent.append("</body></html>");
			return newsContent.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}