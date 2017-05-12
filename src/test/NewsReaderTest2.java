package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.NewsReader;

public class NewsReaderTest2 {
	private NewsReader newsreader1 = new NewsReader("resource/file/guangming.xml");
	private NewsReader newsreader2 = new NewsReader("resource/file/nanfangdaily.xml");
	private NewsReader newsreader3 = new NewsReader("resource/file/sichuan.xml");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNewsReader() {
	}

	@Test
	public void testGetNewsDetail() {
		assertEquals("<html><body><P>��������1��15�յ���������������Ϥ,ȫ�������ꡰ��������Ҹж����ˡ���ѡ������ڱ�����������ѡ����:Զ��̫��,Ϊ���͹����˺�����ҵ�����ܳ����׵�Ӣ�ۺ���ԱȺ�塪��������ΰ���ѿ���������ʤ;�¶����,Ϊ���Ӯ�þ޴��������й��ﾶ���˶�Ա����;־Ը֧��,��˽���׵Ļ���ũҵ��ѧũҵ���ù���רҵ�о����챾��;��������,Я������Ӥ���á���ѧ12�صĺ��ϻ���ѧԺ��ѧ����ս��;������ѧ,ȫ��Ͷ�������������幤���������ų��Է�;ʸ־����,�����ֶ�ý��оƬ������조�й�о���ı�������΢�������޹�˾���³����к�;�����참,������ħ,��ַ���Ϊ�����ĺ�����ʡ����������Ժ�����Ƿ�ͥ����Ա�����(Ů,������);ִ���޻�,�������Q�����Ȱ���һǧ�������ҹҹ�ĺӱ���̨����ί�ɲ����Ӻ�;���Т��,�����ز�ĸ����ѧ��ȡ�ĺ���ʡ�����й���˰��ֿ�Ա������;��ʰ����,�̿�ѧϰ,��֯�����ض�ͯ����������İ���ʡ�ʶ������������������ѧУ���ȶ�Ա̷����(Ů);���װ���,��������19���¶���ƶ����ͯ���������Ժ����Ա���С� </P></body></html>", newsreader1.getNewsDetail("news:23lh^200601161410077(S:193916305)"));
	}

	@Test
	public void testGetDate() {
		assertEquals("2010-12-30",newsreader2.getDate("news:06cj^201012305333859(S:193625388)"));
	}

	@Test
	public void testGetLocation() {
		assertEquals("�Ĵ��ձ�(���ֱ�)",newsreader3.getLocation("news:15jl^201512293153338(S:196124888)"));
	}

	@Test
	public void testGetIsDeleted() {
		assertEquals("True",newsreader1.getIsDeleted("news:1781^200603121410028(S:193916305)"));
	}

	@Test
	public void testGetTitle() {
		assertEquals("�ذ����ض�ͯ����������ͼ��1.4�����",newsreader2.getTitle("news:06cj^201012305333859(S:193625388)"));
	}

	@Test
	public void testGetNewsTags() {
	}

}
