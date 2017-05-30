package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import util.ConsistencyCheck;
import util.XMLReader;

public class ConsistencyCheckTest extends TestCase {
	
	private final String path1 = "resource/file/uncheck1.xml", path2 = "resource/file/uncheck2.xml",
			tags1 = "0 0|1 1", tags2 = "0 1", tags3 = "1 1|0 0",
			tagName = "TagIts";
	private List<Map<String, String>> uncheck1, uncheck2;
	private List<List<Map<String, String>>> unchecks;
	private XMLReader uncheck1Reader, uncheck2Reader;

	@Before
	public void setUp() throws Exception {
		uncheck1Reader = new XMLReader(path1);
		uncheck1 = uncheck1Reader.readXml();
		uncheck2Reader = new XMLReader(path2);
		uncheck2 = uncheck2Reader.readXml();
		for(int i = 0; i < uncheck2.size(); i++){
			uncheck2.get(i).put(tagName, tags1);
		}
		unchecks = new ArrayList<>();
		unchecks.add(uncheck1);
		unchecks.add(uncheck2);
	}

	@Test
	public void testConsistencyRate() {
		for(int i = 0; i < uncheck1.size(); i++){
			unchecks.get(0).get(i).put(tagName, tags1);
		}
		assertEquals(1d, ConsistencyCheck.consistencyRate(unchecks));
		unchecks.get(0).get(0).put(tagName, tags2);
		assertEquals(4d / 5d, ConsistencyCheck.consistencyRate(unchecks));
		for(int i = 0; i < uncheck1.size(); i++){
			unchecks.get(0).get(i).put(tagName, tags2);
		}
		assertEquals(0d, ConsistencyCheck.consistencyRate(unchecks));
		for(int i = 0; i < uncheck1.size(); i++){
			unchecks.get(0).get(i).put(tagName, tags3);
		}
		assertEquals(1d, ConsistencyCheck.consistencyRate(unchecks));
	}

}
