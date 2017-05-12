package widget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartFactory {
	private ChartPanel pieChartPanel;
	
	public PieChartFactory(DefaultPieDataset dataset,String title){
		JFreeChart pieChart = ChartFactory.createPieChart(title, dataset, true, true, false);
		// 设置标题字体
		pieChart.getTitle().setFont(new Font("微软雅黑", 0, 15));
		// 设置标注字体
		pieChart.getLegend().setItemFont(new Font("微软雅黑", 0, 12));
		
		// 获得图表区域对象  
		PiePlot piePlot = (PiePlot)pieChart.getPlot();
		// 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两 位  
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));   
		// 图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比 例                  
		piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		// 设置图表区域的标签字体  
        piePlot.setLabelFont(new Font("微软雅黑", 0, 14));  
        piePlot.setLabelBackgroundPaint(Color.DARK_GRAY);
        piePlot.setLabelPaint(Color.WHITE);
        piePlot.setLabelShadowPaint(null);
        piePlot.setLabelOutlinePaint(Color.WHITE);
        // 设置图表区域无数据时的默认显示文字  
        piePlot.setNoDataMessage("没有统计数据");
        piePlot.setNoDataMessagePaint(Color.DARK_GRAY);
        piePlot.setNoDataMessageFont(new Font("微软雅黑", 0, 20));
        // 设置图表区域文字与图表区域的间隔距离，0.02表示2%  
        piePlot.setLabelGap(0.02D);
        piePlot.setIgnoreNullValues(true);
        piePlot.setIgnoreZeroValues(true);
        // 设置饼图为圆形
        piePlot.setCircular(true);
        //设定背景透明度（0-1.0之间） 
        piePlot.setBackgroundAlpha(0);
        piePlot.setBackgroundPaint(Color.WHITE); 
        //设定前景透明度（0-1.0之间） 
        piePlot.setForegroundAlpha(1f); 
        //指定 section 轮廓线的颜色 
        piePlot.setBaseSectionOutlinePaint(Color.WHITE); 
        //指定 section 轮廓线的厚度 
        piePlot.setSectionOutlineStroke(new BasicStroke(4)); 
        //指定 section 的色彩
        for(int i = 0; i < 10; i++){
        	if(i % 3 == 0){
        		piePlot.setSectionPaint(i,new Color(220, 220, 220)); 
        	}else if(i % 2 == 0){
        		piePlot.setSectionPaint(i,new Color(140, 140, 140)); 
        	}else{
        		piePlot.setSectionPaint(i,new Color(180, 180, 180)); 
        	}
        }
        piePlot.setShadowPaint(null);
		
        pieChartPanel = new ChartPanel(pieChart, true);
	}
	
	public ChartPanel getPieChartPanel(){
		return pieChartPanel;
	}
}
