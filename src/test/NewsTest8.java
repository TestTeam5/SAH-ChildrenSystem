package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.News;

public class NewsTest8 {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTitle() {
		news.setTitle("青少年“身边最让我感动的人”评选揭晓");
		assertEquals("青少年“身边最让我感动的人”评选揭晓",news.getTitle());
	}

	@Test
	public void testSetTitle() {

	}

}