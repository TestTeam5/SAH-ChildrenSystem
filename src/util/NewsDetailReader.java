package util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * ���ڶ�ȡ������ϸ����
 * ���� getContent �����õ���ϸ�������ݵ�html�ַ���
 */
public class NewsDetailReader {
	// ��list�л�ȡ�ĵ�����������
	Map<String, String> newsItem;
	
	// ���캯�������Ӧ������
	public NewsDetailReader(Map<String, String> newsItem)
	{
		 this.newsItem = newsItem;
	}
	
	// �������ŵ���ϸ����
	public String getContent() {
		StringBuilder content = new StringBuilder("<html><body><H1>");
		
		content.append(newsItem.get("Title")+"</H1><span>");
		content.append(newsItem.get("Date")+"</span>&nbsp;&nbsp;<span>");
		content.append(newsItem.get("Location")+"</span><br/><span>");
		content.append(newsItem.get("Type")+"</span>");
		String s = getNewsDetail();
		content.append(s.substring(12));
		
		return content.toString();
	}
	
	// �������ŵ���ϸ�����ֶ�
	public String getNewsDetail() {
		String encodedContent = newsItem.get("EncodedContent");
		// �������ֶ�Ϊ�գ����������ȡ������Ϣ
		if(encodedContent == null){
			return getNewsFromUrl(newsItem.get("TrueUrl"));
		}
		// ����Ϊ����Ի�ȡ��EncodedContent�ֶν��н���
		else{
			return encoded(encodedContent);
		}
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