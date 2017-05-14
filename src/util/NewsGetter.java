package util;

import model.NewsList;

/*
 * 用于获取新闻页面的25条新闻标题
 * 并记录当前读取到的新闻条数
 */
public class NewsGetter {
	static int index = 0; // 新闻列表最后一条新闻的下一条的下标
	public static int newspaper = 0; // 下一条新闻所属报纸类型标记
	static NewsList newsList = Initializer.guangming;
	static Object[][] newsTitles = { new Object[] { "青少年“身边最让我感动的人”评选揭晓" }, new Object[] { "确保农民工子女享受国家政策" },
			new Object[] { "全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业..." }, new Object[] { "不能忽视农村留守儿童" },
			new Object[] { "对农村“留守儿童”问题的理性思考" }, new Object[] { "好政策播种新希望——记湖北省农村教师资助行动计划" },
			new Object[] { "和谐社会视野下的教育公平" }, new Object[] { "农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战" },
			new Object[] { "农村留守儿童教育亟待加强" }, new Object[] { "17部委领导慰问在京流动人口子女和农村留守儿童" },
			new Object[] { "江苏“七彩夏日”点亮精彩暑假" }, new Object[] { "高校：新农村建设的生力军——华中师大服务新农村侧记" },
			new Object[] { "中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童" }, new Object[] { "加大经费保障提供重点扶持福建基础教育资源向农村倾斜" },
			new Object[] { "分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”" }, new Object[] { "大学生耿高鹏为救落水少年英勇献身" },
			new Object[] { "农村留守儿童心理需关爱" }, new Object[] { "志愿服务，是永不谢幕的青春之歌——对武汉大学“赵小亭事迹”的观察与啓示" },
			new Object[] { "老艺人传授“惠洋十音”" }, new Object[] { "“三进三同”体验国情民生" }, new Object[] { "江苏农家书屋实现全覆盖" },
			new Object[] { "开发农村人力资源促进城乡统筹发展" }, new Object[] { "全国教书育人楷模候选人简介" },
			new Object[] { "全国教书育人楷模候选人简介（上接6版）" }, new Object[] { "城市，孩子的温暖家园——合肥全社会关爱未成年人的故事" } };

	public static void init(){
		if(!Initializer.guangming.isEmpty()){
			index = 0;
			newspaper = 0;
			newsList = Initializer.guangming;
		}else if(!Initializer.nanfangdaily.isEmpty()){
			index = 0;
			newspaper = 1;
			newsList = Initializer.nanfangdaily;
		}else if(!Initializer.sichuan.isEmpty()){
			index = 0;
			newspaper = 2;
			newsList = Initializer.sichuan;
		}else{
			index = 0;
			newspaper = 3;
			newsList = null;
		}
	}
	
	// 获取下一页新闻标题
	public static Object[][] getNews() {
		for (int i = 0; i < 25; i++) {
			if (newsList == null) {
				newsTitles[i] = new Object[] { "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index) };
			}
			index++;
			if (newsList != null) {
				if (index >= newsList.size()) {
					index = 0;
					if (newspaper == 0) {
						if (!Initializer.nanfangdaily.isEmpty()) {
							newsList = Initializer.nanfangdaily;
							index = 0;
							newspaper = 1;
						} else if (!Initializer.sichuan.isEmpty()) {
							newsList = Initializer.sichuan;
							index = 0;
							newspaper = 2;
						} else {
							newsList = null;
							index = 0;
							newspaper = 3;
						}
					} else if (newspaper == 1) {
						if(!Initializer.sichuan.isEmpty()){
							newsList = Initializer.sichuan;
							index = 0;
							newspaper = 2;
						}else{
							newsList = null;
							index = 0;
							newspaper = 3;
						}
					}else if (newspaper == 2){
						newsList = null;
						index = 0;
						newspaper = 3;
					}
				}
			}
		}
		return newsTitles;
	}
	
	// 获取上一页新闻标题
	public static Object[][] getPreviousNews(){
		int sum = 0;
		if(index>=50){
			index -= 50;
		}else{
			if(newspaper >= 3){
				sum = sum + Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size();
			}else if(newspaper == 2){
				sum = sum + Initializer.guangming.size() + Initializer.nanfangdaily.size();
			}else if(newspaper == 1){
				sum = sum + Initializer.guangming.size();
			}
			sum = sum + index - 50;
			if(sum >= Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size()){
				index = sum - Initializer.guangming.size() - Initializer.nanfangdaily.size() - Initializer.sichuan.size();
				newspaper = 3;
				newsList = null;
			}else if(sum >= Initializer.guangming.size() + Initializer.nanfangdaily.size()){
				index = sum - Initializer.guangming.size() - Initializer.nanfangdaily.size();
				newspaper = 2;
				newsList = Initializer.sichuan;
			}else if(sum >= Initializer.guangming.size()){
				index = sum - Initializer.guangming.size();
				newspaper = 1;
				newsList = Initializer.nanfangdaily;
			}else if(sum >= 0){
				index = sum;
				newspaper = 0;
				newsList = Initializer.guangming;
			}else{
				init();
			}
		}
		return getNews();
	}
	
	// 获取尾页新闻标题
	public static Object[][] getLastNews(){
		index = Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size();
		index = index % 25;
		if(index != 0){
			index = 25 - index;
		}
		index = index + 25;
		newspaper = 3;
		newsList = null;
		return getPreviousNews();
	}
}
