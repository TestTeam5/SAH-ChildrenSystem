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
		news.setTitle("�����ꡰ��������Ҹж����ˡ���ѡ����");
		assertEquals("�����ꡰ��������Ҹж����ˡ���ѡ����",news.getTitle());
	}

	@Test
	public void testSetTitle() {

	}

}