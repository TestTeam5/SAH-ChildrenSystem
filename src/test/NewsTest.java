package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.News;

public class NewsTest {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetDate() {
		news.setDate("news:23lh^200601161410077(S:193916305)");
		assertEquals("news:23lh^200601161410077(S:193916305)",news.getDate());
	}

	@Test
	public void testSetDate() {
		
	}

}