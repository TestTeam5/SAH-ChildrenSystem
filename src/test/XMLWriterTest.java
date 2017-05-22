package test;

import java.util.ArrayList;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import util.XMLReader;
import util.XMLWriter;

/*
 * 先保证XMLReader能通过测试，不然该测试结果无法预测。
 */

public class XMLWriterTest extends TestCase{
	
	private String path;
	private String ID;
    private XMLReader xmlreader;
	private ArrayList<Map<String, String>> list;

	@BeforeClass
	public void setUp() throws Exception {
		path = "resource/file/xinwentest.xml";
	    xmlreader = new XMLReader(path);
		list = xmlreader.readXml();
	}

	@Test
	public void testWrite() {
		ID = list.get(0).get("ID");
		XMLWriter.write(path, ID, "TagIts", "0 0|1 1");
		list = new XMLReader(path).readXml();
		assertEquals("0 0|1 1", list.get(0).get("TagIts"));
		XMLWriter.write(path, ID, "TagIts", "0 1|1 0");
		list = new XMLReader(path).readXml();
		assertEquals("0 1|1 0", list.get(0).get("TagIts"));
		XMLWriter.write(path, ID, "IsDeleted", "true");
		list = new XMLReader(path).readXml();
		assertEquals("true", list.get(0).get("IsDeleted"));
		XMLWriter.write(path, ID, "IsDeleted", "false");
		list = new XMLReader(path).readXml();
		assertEquals("false", list.get(0).get("IsDeleted"));
	}

}
