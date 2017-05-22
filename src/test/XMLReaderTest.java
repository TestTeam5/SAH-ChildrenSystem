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
		    assertEquals("青少年“身边最让我感动的人”评选揭晓", item.get("Title"));
			assertEquals("2006-01-16", item.get("Date"));
			assertEquals("光明日报", item.get("Location"));
			assertEquals("02,教科文衞", item.get("Type"));
			assertEquals("399", item.get("WordCount"));
			assertEquals("news:23lh^200601161410077(S:193916305)", item.get("ID"));
			assertEquals("确保农民工子女享受国家政策", item.get("Title"));
			assertEquals("2006-03-04", item.get("Date"));
			assertEquals("光明日报", item.get("Location"));
			assertEquals("06,两会特刊", item.get("Type"));
			assertEquals("724", item.get("WordCount"));
			assertEquals("news:12bc^200603041410010(S:193916305)", item.get("ID"));
		}
		
	}

}
