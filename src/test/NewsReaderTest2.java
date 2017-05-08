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
		assertEquals("<html><body><P>本报北京1月15日电记者李海秀从团中央获悉,全国青少年“身边最让我感动的人”评选活动近日在北京揭晓。入选者有:远徵太空,为共和国载人航天事业作出杰出贡献的英雄航天员群体———杨利伟、费俊龙、聂海胜;勇夺金牌,为祖国赢得巨大荣誉的中国田径队运动员刘翔;志愿支教,无私奉献的华中农业大学农业经济管理专业研究生徐本禹;历尽艰辛,携带“弃婴妹妹”求学12载的湖南怀化学院大学生洪战辉;济困助学,全心投身公益活动的深圳市义工联艺术团团长丛飞;矢志创新,在数字多媒体芯片领域打造“中国芯”的北京中星微电子有限公司董事长邓中翰;秉公办案,抗争病魔,坚持法律为民服务的黑龙江省宁安市人民法院东京城法庭审判员金桂兰(女,朝鲜族);执着无悔,在生死綫上拯救爱妻一千多个日日夜夜的河北邢台团市委干部胡子宏;恪守孝道,背着重病母亲求学进取的河南省开封市国家税务局科员张尚昀;捡拾垃圾,刻苦学习,组织“留守儿童”互相帮助的安徽省肥东县六家畈镇养正中心学校少先队员谭海美(女);奉献爱心,精心培养19个孤儿和贫困儿童的辽宁歌剧院演奏员金辅中。 </P></body></html>", newsreader1.getNewsDetail("news:23lh^200601161410077(S:193916305)"));
	}

	@Test
	public void testGetDate() {
		assertEquals("2010-12-30",newsreader2.getDate("news:06cj^201012305333859(S:193625388)"));
	}

	@Test
	public void testGetLocation() {
		assertEquals("四川日报(数字报)",newsreader3.getLocation("news:15jl^201512293153338(S:196124888)"));
	}

	@Test
	public void testGetIsDeleted() {
		assertEquals("True",newsreader1.getIsDeleted("news:1781^200603121410028(S:193916305)"));
	}

	@Test
	public void testGetTitle() {
		assertEquals("关爱留守儿童宝马车主捐赠图书1.4万余册",newsreader2.getTitle("news:06cj^201012305333859(S:193625388)"));
	}

	@Test
	public void testGetNewsTags() {
	}

}
