package util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// 用于读取新闻详细内容，调用 getNewsDetail 方法即可
public class NewsReader {
	File file=null;
	DocumentBuilder documentBuilder=null;
	DocumentBuilderFactory documentBuilderFactory=null;
	
	// 构造函数传入对应文件的地址
	public NewsReader(String filePath)
	{
		 this.file=new File(filePath);
	}
	
	// 传入新闻ID字符串，返回对应新闻的详细内容
	public String getNewsDetail(String ID){
		String encodedContent = getEncodedContent(ID);
		// 若返回字段为空，则从网上爬取新闻信息
		if(encodedContent == null){
			return getOnlineNews(ID);
		}
		// 若不为空则对获取的EncodedContent字段进行解析
		else{
			return encoded(encodedContent);
		}
	}
	
	// 返回对应ID新闻的EncodedContent字段
	private String getEncodedContent(String ID)
	{
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "EncodedContent");
	}
	
	// 根据新闻ID找到对应的新闻结点
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
	
	
	// 得到对应node结点的对应标签字段
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
	
	// 解析EncodedContent字段，返回解析后的内容
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
	
	// 返回对应ID新闻的TrueUrl字段
	private String getTrueUrl(String ID)
	{
		Node newsNode = getNode(ID);
		return getNodeContent(newsNode, "TrueUrl");
	}
	
	private String getOnlineNews(String ID){
		String trueUrl = getTrueUrl(ID);
		return getNewsFromUrl(trueUrl);
	}
	
	// 从url抓取新闻数据的p标签中的内容
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