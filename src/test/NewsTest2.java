package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.News;

public class NewsTest2 {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		news.delete();
		assertTrue(news.isDeleted());
	}

}
