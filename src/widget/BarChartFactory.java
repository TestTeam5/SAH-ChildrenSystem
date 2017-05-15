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
		JFreeChart barChart = ChartFactory.createBarChart(title,"���(X)", "����(Y)", dataset,PlotOrientation.VERTICAL,false,true,false); 
		// ���ñ�������
		barChart.getTitle().setFont(new Font("΢���ź�", 0, 15));
//		// ���ñ�ע����
//		barChart.getLegend().setItemFont(new Font("΢���ź�", 0, 12));
		
		// ���ͼ��������� 
		CategoryPlot categoryPlot = barChart.getCategoryPlot();// ͼ�εĻ��ƽṹ���� 
		//����������������ɫ  
        categoryPlot.setRangeGridlinePaint(new Color(230, 230, 230));  
        //���������������ʴ�  
        categoryPlot.setRangeGridlineStroke(new BasicStroke(1.0f));
        
        //�趨����͸���ȣ�0-1.0֮�䣩 
        categoryPlot.setBackgroundAlpha(0);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        
        // ����ͼ������������ʱ��Ĭ����ʾ����  
        categoryPlot.setNoDataMessage("û��ͳ������");
        categoryPlot.setNoDataMessagePaint(Color.DARK_GRAY);
        categoryPlot.setNoDataMessageFont(new Font("΢���ź�", 0, 20));
        
        //X��  
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();  
        //����X���������  
        domainAxis.setLabelFont(new Font("΢���ź�", 0, 14));  
        //����X������  
        domainAxis.setTickLabelFont(new Font("΢���ź�", 0, 14));  
        //����������ɫ  
        domainAxis.setTickLabelPaint(Color.DARK_GRAY);
        
        //������߾�,ͬ������֮��ľ���  
        //���Ƿ���֮��ľ���,��BAR��BAR֮��ľ����в��  
        //domainAxis.setCategoryMargin(0.2f);  
        //�������£��󣩱߾�,��������ߵľ���  
        domainAxis.setLowerMargin(0.1);  
        //�������£��ң��߾�,���������ұߵľ���  
        domainAxis.setUpperMargin(0.1);
        
        //Y ��  
        ValueAxis rangeAxis = categoryPlot.getRangeAxis();  
        //����Y���������  
        rangeAxis.setLabelFont(new Font("΢���ź�", 0, 14));  
        //����Y������  
        rangeAxis.setTickLabelFont(new Font("΢���ź�", 0, 14));  
        // ������ɫ  
        rangeAxis.setLabelPaint(Color.DARK_GRAY);
        
        //����Bar����ɫ  
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setBarPainter( new StandardBarPainter() );
        renderer.setItemMargin(-0.01);
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);  
        renderer.setSeriesPaint(1, Color.orange);  
        // Bar���������߲��� 
        renderer.setDrawBarOutline(false);  
        // ÿ��BAR֮��ļ��  
        renderer.setItemMargin(0.0f);  
        //ÿ��BAR�������  
        //renderer.setMaximumBarWidth(0.5f); 
        
        return new ChartPanel(barChart, true);
	}
}
