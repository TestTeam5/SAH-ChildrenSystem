package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.News;

public class NewsTest6 {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetLocation() {
		news.setLocation("�����ձ�");
		assertEquals("�����ձ�",news.getLocation());
	}

	@Test
	public void testSetLocation() {
	}

}
