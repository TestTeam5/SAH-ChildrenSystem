package util;

import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import model.NewsList;
import widget.BarChartFactory;
import widget.PieChartFactory;

public class StatisticsGetter {

	static ChartPanel barChartPanel = null;
	static ChartPanel pieChartPanel = null;
	
	static int newspaper = 0;	// 0代表光明日报，1代表南方都市报，2代表四川日报
	public static boolean isTendencyComparison = false;	// false代表趋势统计，true代表倾向性比较
	static NewsList newsList = Initializer.newsList;
	static int selectedMainTag = 0;	//选中的主标签
	static int selectedSubTag = -1;
	static final String[] titles = {"光明日报", "南方都市报(全国版)", "四川日报(数字报)"};
	static final String[][] allTags = {{"0 0", "0 1", "0 2"},
			{"1 0", "1 1", "1 2", "1 3"}, 
			{"2 0", "2 1", "2 2", "2 3", "2 4", "2 5", "2 6", "2 7", "2 8", "2 9"},
			{"3 0", "3 1", "3 2", "3 3", "3 4", "3 5", "3 6", "3 7"}, 
			{"4 0", "4 1", "4 2", "4 3", "4 4"}, 
			{"5 0", "5 1", "5 2", "5 3", "5 4"}, 
			{"6 0", "6 1", "6 2", "6 3", "6 4"}, 
			{"7 0", "7 1", "7 2", "7 3", "7 4"},
			{"8 0", "8 1", "8 2", "8 3", "8 4"}};
	static final String[][] subTagsText = { { "中央一级党报", "省委机关报", "都市报" }, { "纯净新闻", "特稿与特写", "评论", "其他" },
			{ "社会帮助", "建议和看法", "表彰", "暴力现象", "性侵猥亵", "儿童犯罪", "意外身亡", "努力上进", "父母生活", "其他" },
			{ "记者", "政府", "企业", "事业单位", "公益团体", "专家学者", "政府领导", "其他" }, { "积极健康", "可怜悲惨", "沐恩幸福", "问题儿童", "其他" },
			{ "一次性帮助", "旅游活动", "免费开放", "资助项目", "其他" }, { "政府部门", "企业", "事业单位", "公益团体", "个人" },
			{ "政府部门", "企业", "事业单位", "公益团体", "个人" }, { "无本地户籍", "学费高", "教学质量", "办学资格", "其他" } };
	//创建数据  
    static DefaultCategoryDataset barDataset = new DefaultCategoryDataset();	// 趋势统计的条形图数据
    static DefaultPieDataset pieDataset = new DefaultPieDataset();  // 倾向性比较的饼图数据
	
	public static void init(){
		selectedMainTag = 0;
		selectedSubTag = -1;
		newspaper = 0;
		isTendencyComparison = false;
		barChartPanel = BarChartFactory.getBarChartPanel(new DefaultCategoryDataset(), getTitle());
		pieChartPanel = PieChartFactory.getPieChartPanel(getPieData(), getTitle());
	}
	
	public static void setSelectedMainTag(int num){
		selectedMainTag = num;
	}
	
	public static int getSelectedMainTag(){
		return selectedMainTag;
	}
	
	public static void setSelectedSubTag(int num){
		selectedSubTag = num;
	}
	
	public static void setIsTendencyComparison(boolean option){
		isTendencyComparison = option;
	}
	
	public static void changeNextNewspaper(){
		newspaper++;
		if(newspaper >= 3)
			newspaper = 0;
	}
	
	public static void changePreviousNewspaper(){
		newspaper--;
		if(newspaper < 0){
			newspaper = 2;
		}
	}
	
	public static ChartPanel getOldBarChartPanel(){
		return barChartPanel;
	}
	
	public static ChartPanel getDefaultBarChartPanel(){
		barChartPanel = BarChartFactory.getBarChartPanel(new DefaultCategoryDataset(), getTitle());
		return barChartPanel;
	}
	
	public static ChartPanel getBarChartPanel(){
		if(selectedSubTag == -1){
			barChartPanel = BarChartFactory.getBarChartPanel(new DefaultCategoryDataset(), getTitle());
		}else{
			barChartPanel = BarChartFactory.getBarChartPanel(getBarData(selectedSubTag), getTitle());
		}
		return barChartPanel;
	}
	
	public static ChartPanel getOldPieChartPanel(){
		return pieChartPanel;
	}
	
	public static ChartPanel getPieChartPanel(){
		pieChartPanel = PieChartFactory.getPieChartPanel(getPieData(), getTitle());
		return pieChartPanel;
	}
	
	private static DefaultCategoryDataset getBarData(int num){
		barDataset.clear();
		Map<String, Integer> dataMap = newsList.getTagCountMap(selectedMainTag + " " + num, newspaper);
		for(Entry<String, Integer> entry: dataMap.entrySet()){
			barDataset.addValue(entry.getValue(), "某报纸", entry.getKey()+"年");
		}
		return barDataset;
	}
	
	private static DefaultPieDataset getPieData(){
		pieDataset.clear();
		int i = 0;
		for(String s: subTagsText[selectedMainTag]){
			pieDataset.setValue(s, newsList.getCount(selectedMainTag + " " + i, newspaper));
			i++;
		}
		return pieDataset;
	}
	
	private static String getTitle(){
		return titles[newspaper];
	}
	
}
