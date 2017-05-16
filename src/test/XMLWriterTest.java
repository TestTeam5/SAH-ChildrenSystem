package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import util.XMLReader;
import util.XMLWriter;

/*
 * 先保证XMLReader能通过测试，不然该测试结果无法预测。
 */

public class XMLWriterTest {
	
	private String path = "resource/file/xinwentest.xml";
	private String ID;
    private XMLReader xmlreader = new XMLReader(path);
	private ArrayList<Map<String, String>> list = xmlreader.readXml();

	@Before
	public void setUp() throws Exception {
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
