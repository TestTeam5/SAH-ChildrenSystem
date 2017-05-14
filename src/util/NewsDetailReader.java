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
 * 用于读取新闻详细内容
 * 调用 getContent 方法得到详细新闻内容的html字符串
 */
public class NewsDetailReader {
	// 从list中获取的单条新闻数据
	Map<String, String> newsItem;
	
	// 构造函数传入对应新闻项
	public NewsDetailReader(Map<String, String> newsItem)
	{
		 this.newsItem = newsItem;
	}
	
	// 返回新闻的详细内容
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
	
	// 返回新闻的详细内容字段
	public String getNewsDetail() {
		String encodedContent = newsItem.get("EncodedContent");
		// 若返回字段为空，则从网上爬取新闻信息
		if(encodedContent == null){
			return getNewsFromUrl(newsItem.get("TrueUrl"));
		}
		// 若不为空则对获取的EncodedContent字段进行解析
		else{
			return encoded(encodedContent);
		}
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