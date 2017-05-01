package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import libs.NewsReader;

public class NewsReaderTest {
	private NewsReader newsreader = new NewsReader("D:/uni/������/�ſ�/��Ŀ/SAH-ChildrenSystem/src/resources/guangming.xml");

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNewsReader() {
	}

	@Test
	public void testGetNewsDetail() {
		assertEquals("<html><body><P>��������1��15�յ���������������Ϥ,ȫ�������ꡰ��������Ҹж����ˡ���ѡ������ڱ�����������ѡ����:Զ��̫��,Ϊ���͹����˺�����ҵ�����ܳ����׵�Ӣ�ۺ���ԱȺ�塪��������ΰ���ѿ���������ʤ;�¶����,Ϊ���Ӯ�þ޴��������й��ﾶ���˶�Ա����;־Ը֧��,��˽���׵Ļ���ũҵ��ѧũҵ���ù���רҵ�о����챾��;��������,Я������Ӥ���á���ѧ12�صĺ��ϻ���ѧԺ��ѧ����ս��;������ѧ,ȫ��Ͷ�������������幤���������ų��Է�;ʸ־����,�����ֶ�ý��оƬ������조�й�о���ı�������΢�������޹�˾���³����к�;�����참,������ħ,��ַ���Ϊ�����ĺ�����ʡ����������Ժ�����Ƿ�ͥ����Ա�����(Ů,������);ִ���޻�,�������Q�����Ȱ���һǧ�������ҹҹ�ĺӱ���̨����ί�ɲ����Ӻ�;���Т��,�����ز�ĸ����ѧ��ȡ�ĺ���ʡ�����й���˰��ֿ�Ա������;��ʰ����,�̿�ѧϰ,��֯�����ض�ͯ����������İ���ʡ�ʶ������������������ѧУ���ȶ�Ա̷����(Ů);���װ���,��������19���¶���ƶ����ͯ���������Ժ����Ա���С� </P></body></html>", newsreader.getNewsDetail("news:23lh^200601161410077(S:193916305)"));
	}
}
