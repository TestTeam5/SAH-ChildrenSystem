package widget;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartFactory {
	
	public static ChartPanel getBarChartPanel(DefaultCategoryDataset dataset, String title){
		JFreeChart barChart = ChartFactory.createBarChart(title,"类别(X)", "数量(Y)", dataset,PlotOrientation.VERTICAL,false,true,false); 
		// 设置标题字体
		barChart.getTitle().setFont(new Font("微软雅黑", 0, 15));
//		// 设置标注字体
//		barChart.getLegend().setItemFont(new Font("微软雅黑", 0, 12));
		
		// 获得图表区域对象 
		CategoryPlot categoryPlot = barChart.getCategoryPlot();// 图形的绘制结构对象 
		//数据轴网格线条颜色  
        categoryPlot.setRangeGridlinePaint(new Color(230, 230, 230));  
        //数据轴网格线条笔触  
        categoryPlot.setRangeGridlineStroke(new BasicStroke(1.0f));
        
        //设定背景透明度（0-1.0之间） 
        categoryPlot.setBackgroundAlpha(0);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        
        // 设置图表区域无数据时的默认显示文字  
        categoryPlot.setNoDataMessage("没有统计数据");
        categoryPlot.setNoDataMessagePaint(Color.DARK_GRAY);
        categoryPlot.setNoDataMessageFont(new Font("微软雅黑", 0, 20));
        
        //X轴  
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();  
        //设置X轴标题字体  
        domainAxis.setLabelFont(new Font("微软雅黑", 0, 14));  
        //设置X轴字体  
        domainAxis.setTickLabelFont(new Font("微软雅黑", 0, 14));  
        //设置字体颜色  
        domainAxis.setTickLabelPaint(Color.DARK_GRAY);
        
        //分类轴边距,同种类型之间的距离  
        //这是分类之间的距离,和BAR与BAR之间的距离有差别  
        //domainAxis.setCategoryMargin(0.2f);  
        //分类轴下（左）边距,就是离左边的距离  
        domainAxis.setLowerMargin(0.1);  
        //分类轴下（右）边距,就是离最右边的距离  
        domainAxis.setUpperMargin(0.1);
        
        //Y 轴  
        ValueAxis rangeAxis = categoryPlot.getRangeAxis();  
        //设置Y轴标题字体  
        rangeAxis.setLabelFont(new Font("微软雅黑", 0, 14));  
        //设置Y轴字体  
        rangeAxis.setTickLabelFont(new Font("微软雅黑", 0, 14));  
        // 字体颜色  
        rangeAxis.setLabelPaint(Color.DARK_GRAY);
        
        //设置Bar的颜色  
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setBarPainter( new StandardBarPainter() );
        renderer.setItemMargin(-0.01);
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);  
        renderer.setSeriesPaint(1, Color.orange);  
        // Bar的外轮廓线不画 
        renderer.setDrawBarOutline(false);  
        // 每个BAR之间的间隔  
        renderer.setItemMargin(0.0f);  
        //每个BAR的最大宽度  
        //renderer.setMaximumBarWidth(0.5f); 
        
        return new ChartPanel(barChart, true);
	}
}
