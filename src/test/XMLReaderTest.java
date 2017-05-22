package test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import util.XMLReader;


public class XMLReaderTest extends TestCase {
    private XMLReader xmlreader;
    
	@BeforeClass
	public void setUp() throws Exception { 
		xmlreader = new XMLReader("resource/file/xuexiao.xml");
	}
	


	@Test
	public void testXMLReader() throws Exception {
	}

	@Test
	public void ReadXml() throws Exception {
		ArrayList<Map<String, String>> list = xmlreader.readXml();
		for(Map<String, String> item : list){
		    assertEquals("�����ꡰ��������Ҹж����ˡ���ѡ����", item.get("Title"));
			assertEquals("2006-01-16", item.get("Date"));
			assertEquals("�����ձ�", item.get("Location"));
			assertEquals("02,�̿����o", item.get("Type"));
			assertEquals("399", item.get("WordCount"));
			assertEquals("news:23lh^200601161410077(S:193916305)", item.get("ID"));
			assertEquals("ȷ��ũ����Ů���ܹ�������", item.get("Title"));
			assertEquals("2006-03-04", item.get("Date"));
			assertEquals("�����ձ�", item.get("Location"));
			assertEquals("06,�����ؿ�", item.get("Type"));
			assertEquals("724", item.get("WordCount"));
			assertEquals("news:12bc^200603041410010(S:193916305)", item.get("ID"));
		}
		
	}

}
