package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.News;
import model.NewsTags;

public class NewsTest5 {
	private News news = new News();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetTagIts() {
		ArrayList<NewsTags> list = new ArrayList<>();
		NewsTags newsTags = new NewsTags(1,"纯净新闻");
		news.setTagIts(list);
		assertEquals("1,纯净新闻",news.getTagIts());
	}

	@Test
	public void testSetTagIts() {
	}

}
