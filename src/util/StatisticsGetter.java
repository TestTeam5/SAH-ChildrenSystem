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
	
	static int newspaper = 0;	// 0��������ձ���1�����Ϸ����б���2�����Ĵ��ձ�
	public static boolean isTendencyComparison = false;	// false��������ͳ�ƣ�true���������ԱȽ�
	static NewsList newsList = Initializer.newsList;
	static int selectedMainTag = 0;	//ѡ�е�����ǩ
	static int selectedSubTag = -1;
	static final String[] titles = {"�����ձ�", "�Ϸ����б�(ȫ����)", "�Ĵ��ձ�(���ֱ�)"};
	static final String[][] allTags = {{"0 0", "0 1", "0 2"},
			{"1 0", "1 1", "1 2", "1 3"}, 
			{"2 0", "2 1", "2 2", "2 3", "2 4", "2 5", "2 6", "2 7", "2 8", "2 9"},
			{"3 0", "3 1", "3 2", "3 3", "3 4", "3 5", "3 6", "3 7"}, 
			{"4 0", "4 1", "4 2", "4 3", "4 4"}, 
			{"5 0", "5 1", "5 2", "5 3", "5 4"}, 
			{"6 0", "6 1", "6 2", "6 3", "6 4"}, 
			{"7 0", "7 1", "7 2", "7 3", "7 4"},
			{"8 0", "8 1", "8 2", "8 3", "8 4"}};
	static final String[][] subTagsText = { { "����һ������", "ʡί���ر�", "���б�" }, { "��������", "�ظ�����д", "����", "����" },
			{ "������", "����Ϳ���", "����", "��������", "�������", "��ͯ����", "��������", "Ŭ���Ͻ�", "��ĸ����", "����" },
			{ "����", "����", "��ҵ", "��ҵ��λ", "��������", "ר��ѧ��", "�����쵼", "����" }, { "��������", "��������", "����Ҹ�", "�����ͯ", "����" },
			{ "һ���԰���", "���λ", "��ѿ���", "������Ŀ", "����" }, { "��������", "��ҵ", "��ҵ��λ", "��������", "����" },
			{ "��������", "��ҵ", "��ҵ��λ", "��������", "����" }, { "�ޱ��ػ���", "ѧ�Ѹ�", "��ѧ����", "��ѧ�ʸ�", "����" } };
	//��������  
    static DefaultCategoryDataset barDataset = new DefaultCategoryDataset();	// ����ͳ�Ƶ�����ͼ����
    static DefaultPieDataset pieDataset = new DefaultPieDataset();  // �����ԱȽϵı�ͼ����
	
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
			barDataset.addValue(entry.getValue(), "ĳ��ֽ", entry.getKey()+"��");
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
