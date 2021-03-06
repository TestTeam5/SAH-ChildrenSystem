package test;

import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.TestCase;
import model.NewsList;

public class NewsListTest extends TestCase {

	NewsList newslist;
	
	@BeforeClass
	public void setUp() throws Exception {
		newslist = new NewsList();
		newslist.init();
	}

	@Test
	public void testInit() {	}

	@Test
	public void testGetTitle() {
		assertEquals("青少年“身边最让我感动的人”评选揭晓", newslist.getTitle(0));
	}

	@Test
	public void testGetDate() {
		assertEquals("2006-01-16", newslist.getDate(0));
	}

	@Test
	public void testGetDetail() {
		assertEquals("PABoAHQAbQBsAD4APABiAG8AZAB5AD4APABQAD4ALGelYhdTrE4xAAhnMQA1AOVlNXWwiwWATmd3bcB5zk7iVi1OLlm3g4lgLABoUf1WUpcRXHReHCCrjrmPAGepixFiH2GoUoR2uk4dIMSLCZA7bahS0Y/lZShXF1OsTu1jU2YCMGVRCZAFgAlnOgDcj7VfKll6eiwAOk5xUYxU/VZ9j7pOKoIpWYtOGk5cT/pRcGf6USGNLnOEdvGCxJYqgilZWFSkf1NPFCAUIBQgaGcpUh9PATA5jcpPmZ8BMEKAd23cgDsAx1I6WdGRTHIsADpOVnn9VmKNl1/oXSdZY4OJioR2LU79VjB1hF8fltCPqFJYVBhS1H87ANdfP2EvZVllLADgZcF5SVkuc4R2TlMtTpxRGk4nWWZbnFEaTs9+Tm2hewZ0E04aThR4dnofdZBfLGe5eTsAhlM9XHCCm48sADpkJl4cIANfdFq5WblZHSBCbGZbMQAyAH2PhHZWbldTAGAWU2ZbYpYnWWZbH3UqbRhiiY87AE5t8FapUmZbLABoUcNflWKrjmxRynY7bahShHbxbTNXAl5JTuVdVIB6gi9n4lbiVn+VG07emDsA4nfXXxtSsGUsAChXcGVXWxpZklpTT6+CR3KGmN9XU2IgkBwgLU79Vq+CHSCEdhdTrE4tTh9mrl81dVBbCWdQlmxR+FNjhItOf5WTkC1O8H87AMl5bFGeUkhoLACXYolOxXVUmywAWlcBY9Vsi186ThFsDWehUoR20Z6Zn19sAXeBW4lbAl66ThFs1WxilhxOrE7OV9VsrV6hWyRSWFTRkUJocFEoAHNZLAAdZ5ycz2UpADsAZ2JAd+BllGAsAChXH3V7a6t9Ck7vYlFlMXK7WQBOQ1MaWSpO5WXlZRxZHFmEdrNsF1OikPBT4lYCXtRZcl7okOGAUFuPWzsAamCIW11bU5AsAMyAQHfNkcV1zWuyTkJsZlvbj9ZThHazbFdTAXcAXwFcAl79VrZbDnqhUkBc0XlYVCBfGlwAZjsAYWP+YoNXPlcsADtS5oJmW2BOLADEfsd+HCBZdYhbP1Hleh0gkk74di5eqVKEdolbvV8Bd6WAHE6/U21RtltIdUeVe1Fjay1Ow19mWyFoEVxIUR+WWFQtjHdtjn8oAHNZKQA7AElZLnMxcsNfLAC+fMNf+Vd7UTEAOQAqTmRbP1GMVCuN8FY/UeV6hHa9j4FbTGtnUmKWFG9PWVhU0ZGFjy1OAjAgADwALwBQAD4APAAvAGIAbwBkAHkAPgA8AC8AaAB0AG0AbAA+AA==", newslist.getDetail(0));
	}

	@Test
	public void testGetUrl() {
		assertEquals(null, newslist.getUrl(0));
	}

	@Test
	public void testGetID() {
		assertEquals("news:23lh^200601161410077(S:193916305)", newslist.getID(0));
	}

	@Test
	public void testGetTagIts() {
		String TagIts = newslist.getTagIts(0);
		assertEquals(TagIts, newslist.getTagIts(0));
	}

	@Test
	public void testGetLocation() {
		assertEquals("光明日报", newslist.getLocation(0));
	}

	@Test
	public void testIsDeleted() {
		boolean isDeleted = newslist.isDeleted(0, false);
		assertEquals(isDeleted, newslist.isDeleted(0, false));
	}

	@Test
	public void testDelete() {
		newslist.delete(0);
		assertEquals(true, newslist.isDeleted(0, false));
	}

	@Test
	public void testRestore() {
		newslist.restore(0);
		assertEquals(false, newslist.isDeleted(0, true));
	}

	@Test
	public void testSize() {	
		assertEquals(3811, newslist.size());
	}

	@Test
	public void testGetDeletedCount() {
		int count = newslist.getDeletedCount();
		newslist.delete(0);
		assertEquals(++count, newslist.getDeletedCount());
		newslist.restore(0);
		assertEquals(--count, newslist.getDeletedCount());
	}

	@Test
	public void testGetNotDeletedCount() {
		int count = newslist.getNotDeletedCount();
		newslist.delete(0);
		assertEquals(--count, newslist.getNotDeletedCount());
		newslist.restore(0);
		assertEquals(++count, newslist.getNotDeletedCount());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, newslist.isEmpty());
	}

	@Test
	public void testGetFirstNotDeleted() {
		assertEquals(1, newslist.getFirstNotDeleted());
	}

	@Test
	public void testGetFirstDeleted() {
		assertEquals(0, newslist.getFirstDeleted());
	}

	@Test
	public void testGetNewsItem() {	}

	@Test
	public void testRefactor() {
		if (newslist.getTagIts(0).contains("0 0")){
			newslist.refactor(0, "0 1");
			assertTrue(newslist.getTagIts(0).contains("0 1"));
			assertFalse(newslist.getTagIts(0).contains("0 0"));
		} else{
			newslist.refactor(0, "0 0");
			assertTrue(newslist.getTagIts(0).contains("0 0"));
		}
	}

	@Test	//前提是refactor函数能正常工作
	public void testGetCount() {
		int originCount = newslist.getCount("0 0", 0);
		newslist.refactor(0, "0 0");
		assertEquals(++originCount, newslist.getCount("0 0", 0));
	}

	@Test
	public void testGetTagCountMap() {	}

	@Test
	public void testGetSelectedSubTag() {	}

}
