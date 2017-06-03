package util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileFilter;

public class FilePathSelector {
	public static List<String> getFilePaths() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select xml file");
		jfc.setMultiSelectionEnabled(true);
		jfc.setDragEnabled(true);
		jfc.setFileFilter(new MyFileFilter("xml", "xmlÎÄ¼þ"));
		updateFont(jfc, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		int result = jfc.showDialog(new JLabel(), "Ñ¡Ôñ");
		List<String> filePaths = new ArrayList<>();
		if (result == JFileChooser.APPROVE_OPTION) {
			File[] files = jfc.getSelectedFiles();
			for (File f : files) {
				filePaths.add(f.getAbsolutePath());
			}
		}
		return filePaths;
	}
	
	public static String getFilePath(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Select xml file");
		jfc.setMultiSelectionEnabled(false);
		jfc.setDragEnabled(true);
		jfc.setFileFilter(new MyFileFilter("xml", "xmlÎÄ¼þ"));
		updateFont(jfc, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		int result = jfc.showDialog(new JLabel(), "Ñ¡Ôñ");
		String filePath = null;
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			filePath = file.getAbsolutePath();
		}
		return filePath;
	}

	public static String getFolderPath(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Save xml file to path");
		jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		updateFont(jfc, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		int result = jfc.showDialog(new JLabel(), "±£´æ");
		if (result == JFileChooser.APPROVE_OPTION) {
			File fi = jfc.getSelectedFile();
			return fi.getAbsolutePath();
		}
		return null;
	}
	
	public static String getNewFilePath(String filename) {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Save xml file to path");
		jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
		updateFont(jfc, new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		int result = jfc.showDialog(new JLabel(), "±£´æ");
		if (result == JFileChooser.APPROVE_OPTION) {
			File fi = jfc.getSelectedFile();
			String filePath = fi.getAbsolutePath() + "\\" + filename + ".xml";
			return filePath;
		}
		return null;
	}

	public static class MyFileFilter extends FileFilter {

		private String extension;

		private String description;

		public MyFileFilter(String extension, String description) {
			super();
			this.extension = extension;
			this.description = description;
		}

		public boolean accept(File f) {
			if (f != null) {
				if (f.isDirectory()) {
					return true;
				}
				String extension = getExtension(f);
				if (extension != null && extension.equalsIgnoreCase(this.extension)) {
					return true;
				}
			}
			return false;
		}

		public String getDescription() {
			return description;
		}

		private String getExtension(File f) {
			if (f != null) {
				String filename = f.getName();
				int i = filename.lastIndexOf('.');
				if (i > 0 && i < filename.length() - 1) {
					return filename.substring(i + 1).toLowerCase();
				}
			}
			return null;
		}

	}

	private static void updateFont(Component comp, Font font) {
		comp.setFont(font);
		if (comp instanceof Container) {
			Container c = (Container) comp;
			int n = c.getComponentCount();
			for (int i = 0; i < n; i++) {
				updateFont(c.getComponent(i), font);
			}
		}
	}
}
