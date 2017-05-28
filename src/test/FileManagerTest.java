package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import util.FileManager;
import util.XMLReader;
import util.XMLWriter;

/*
 * 测试前报纸XMLReader, XMLWriter能正确运行
 */

public class FileManagerTest extends TestCase {

	private String oldTag, newTag, ID, oldpath, newpath;
    private XMLReader xmlreader;
	private ArrayList<Map<String, String>> list;
	private List<String> pathList;
	
	@Before
	public void setUp() throws Exception {
		oldpath = "resource/file/guangming.xml";
	    xmlreader = new XMLReader(oldpath);
		list = xmlreader.readXml();
		ID = list.get(0).get("ID");
		oldTag = list.get(0).get("TagIts");
		newTag = "0 0|1 1|2 2|3 3";
		newpath = "resource/file/testmerge.xml";
		pathList = new ArrayList<String>();
		pathList.add(newpath);
	}

	@Test
	public void testMergeAll() {
		XMLWriter.write(oldpath, ID, "TagIts", "4 0");
		XMLWriter.write(newpath, ID, "TagIts", newTag);
		FileManager.mergeAll(pathList);
		list = new XMLReader(oldpath).readXml();
		assertEquals(newTag, list.get(0).get("TagIts"));
		XMLWriter.write(oldpath, ID, "TagIts", oldTag);
	}

}
