package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.News;

public class NewsTest4 {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetID() {
		news.setID("2006-01-16");
		assertEquals("2006-01-16",news.getID());
	}

	@Test
	public void testSetID() {
	}

}