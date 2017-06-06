package widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import util.Initializer;

public class NewsTable extends JTable {
	private Color selectionColor = new Color(207, 228, 249);// 行选择颜色
	private Color evenRowColor = new Color(240, 240, 240);
	private Color oddRowColor = new Color(255, 255, 255);// 偶数行颜色
	private Color gridColor = new Color(236, 233, 216);// 网格颜色
	private int rowHeight = 30;// 行高度
	
	private boolean hasRedRow = false;

	public NewsTable(Object[][] tableData, Object[] columnTitle) {
		super(tableData, columnTitle);
		this.setGridColor(gridColor);
		this.setRowHeight(rowHeight);
		// 隐藏表格标题
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setPreferredSize(new Dimension(0, 0));
		this.getTableHeader().setDefaultRenderer(headerRenderer);
		
		this.setRowHeight(25);
		this.setShowGrid(false);
	}
	
	public NewsTable(Object[][] tableData, Object[] columnTitle, boolean hasRedRow){
		this(tableData, columnTitle);
		this.hasRedRow = hasRedRow;
	}
	
	public NewsTable(TableModel model) {
		super(model);
		this.setGridColor(gridColor);
		this.setRowHeight(rowHeight);
		// 隐藏表格标题
		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setPreferredSize(new Dimension(0, 0));
		this.getTableHeader().setDefaultRenderer(headerRenderer);
		
		this.setRowHeight(25);
		this.setShowGrid(false);
	}
	
	public NewsTable(TableModel model, boolean hasRedRow){
		this(model);
		this.hasRedRow = hasRedRow;
	}

	@Override
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	public TableCellRenderer getCellRenderer(int row, int column) {
		if(hasRedRow){
			return new MyCellRenderer3();
		}
		if(column == 1){
			return new MyCellRenderer2();
		}
		return new MyCellRenderer();
	}

	class MyCellRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			this.setColor(cell, table, isSelected, hasFocus, row, column);
			return cell;
		}

		/*
		 * 设置颜色
		 */
		private void setColor(Component component, JTable table, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				component.setBackground(selectionColor);
				setBorder(null);// 去掉边
			} else {
				if (row % 2 == 0) {
					component.setBackground(evenRowColor);
				} else {
					component.setBackground(oddRowColor);
				}
			}
//			if(row == 2){
//				component.setBackground(new Color(240, 80, 80));
//				component.setForeground(Color.WHITE);
//			}
		}
	}
	
	class MyCellRenderer2 extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			this.setColor(cell, table, isSelected, hasFocus, row, column);
			return cell;
		}

		/*
		 * 设置颜色
		 */
		private void setColor(Component component, JTable table, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				component.setForeground(Color.BLUE);
				component.setBackground(selectionColor);
				setBorder(null);// 去掉边
			} else {
				component.setForeground(Color.RED);
				if (row % 2 == 0) {
					component.setBackground(evenRowColor);
				} else {
					component.setBackground(oddRowColor);
				}
			}
		}
	}
	
	class MyCellRenderer3 extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			this.setColor(cell, table, isSelected, hasFocus, row, column);
			return cell;
		}

		/*
		 * 设置颜色
		 */
		private void setColor(Component component, JTable table, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				component.setBackground(selectionColor);
				setBorder(null);// 去掉边
			} else {
				if (row % 2 == 0) {
					component.setBackground(evenRowColor);
				} else {
					component.setBackground(oddRowColor);
				}
			}
			if(Initializer.wrongLineNum.contains(row)){
				component.setBackground(new Color(240, 80, 80));
				component.setForeground(Color.WHITE);
			}
		}
	}
}
