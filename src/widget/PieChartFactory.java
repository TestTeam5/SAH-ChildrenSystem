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
		// ���ñ�������
		pieChart.getTitle().setFont(new Font("΢���ź�", 0, 15));
		// ���ñ�ע����
		pieChart.getLegend().setItemFont(new Font("΢���ź�", 0, 12));
		
		// ���ͼ���������  
		PiePlot piePlot = (PiePlot)pieChart.getPlot();
		// ͼƬ����ʾ�ٷֱ�:�Զ��巽ʽ��{0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ���� ,С������� λ  
		piePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));   
		// ͼ����ʾ�ٷֱ�:�Զ��巽ʽ�� {0} ��ʾѡ� {1} ��ʾ��ֵ�� {2} ��ʾ��ռ�� ��                  
		piePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		// ����ͼ������ı�ǩ����  
        piePlot.setLabelFont(new Font("΢���ź�", 0, 14));  
        piePlot.setLabelBackgroundPaint(Color.DARK_GRAY);
        piePlot.setLabelPaint(Color.WHITE);
        piePlot.setLabelShadowPaint(null);
        piePlot.setLabelOutlinePaint(Color.WHITE);
        // ����ͼ������������ʱ��Ĭ����ʾ����  
        piePlot.setNoDataMessage("û��ͳ������");
        piePlot.setNoDataMessagePaint(Color.DARK_GRAY);
        piePlot.setNoDataMessageFont(new Font("΢���ź�", 0, 20));
        // ����ͼ������������ͼ������ļ�����룬0.02��ʾ2%  
        piePlot.setLabelGap(0.02D);
        piePlot.setIgnoreNullValues(true);
        piePlot.setIgnoreZeroValues(true);
        // ���ñ�ͼΪԲ��
        piePlot.setCircular(true);
        //�趨����͸���ȣ�0-1.0֮�䣩 
        piePlot.setBackgroundAlpha(0);
        piePlot.setBackgroundPaint(Color.WHITE); 
        //�趨ǰ��͸���ȣ�0-1.0֮�䣩 
        piePlot.setForegroundAlpha(1f); 
        //ָ�� section �����ߵ���ɫ 
        piePlot.setBaseSectionOutlinePaint(Color.WHITE); 
        //ָ�� section �����ߵĺ�� 
        piePlot.setSectionOutlineStroke(new BasicStroke(4)); 
        //ָ�� section ��ɫ��
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
