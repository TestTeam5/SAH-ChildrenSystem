package model;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;

import util.AESEncryptor;
import util.FileCopy;
import util.FilePathSelector;
import util.XMLReader;
import util.XMLWriter;

public class TestNewsList {
	private ArrayList<Map<String, String>> newslist = new ArrayList<>();
	private String password = null;
	private String filepath = null;

	public void init() {
		String filepath = FilePathSelector.getFilePath();
		this.password = JOptionPane.showInputDialog("请输入秘钥：");
		FileCopy.copyFile(filepath, this.password);
		int index = filepath.lastIndexOf('\\');
		this.filepath = filepath.substring(0, index) + "\\" + password + ".xml";

		XMLReader xmlReader = new XMLReader(this.filepath);
		this.newslist = xmlReader.readXml();
	}

	public String getTitle(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("Title");
	}

	public String getDate(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("Date");
	}

	public String getDetail(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("EncodedContent");
	}

	public String getUrl(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("TrueUrl");
	}

	public String getID(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("ID");
	}

	public String getTagIts(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		String tagIts = this.newslist.get(index).get("TagIts");
		if (tagIts != null) {
			tagIts = AESEncryptor.decrypt(tagIts, this.password);
		}
		return tagIts;
	}

	public String getLocation(int index) {
		if (index >= newslist.size()) {
			return null;
		}
		return this.newslist.get(index).get("Location");
	}

	public int size() {
		return newslist.size();
	}

	// 获取对应新闻项
	public Map<String, String> getNewsItem(int index) {
		return newslist.get(index);
	}

	public int getSelectedSubTag(int index, int selectedMainTag) {
		String newsTag = this.newslist.get(index).get("TagIts");
		if (newsTag == null)
			return -1;
		newsTag = AESEncryptor.decrypt(newsTag, this.password);
		String mainTagString = selectedMainTag + " ";
		int i = newsTag.indexOf(mainTagString);
		if (i == -1) {
			return -1;
		} else {
			return Integer.parseInt(newsTag.substring(i + 2, i + 3));
		}
	}

	// 若有同类标签,如果子标签相同则删除，子标签不同则替换，否则添加
	public void refactor(int index, String ntag) {
		String newsTag = this.newslist.get(index).get("TagIts");
		if (newsTag == null) {
			newsTag = "";
		} else {
			newsTag = AESEncryptor.decrypt(newsTag, this.password);
		}
		String type = ntag.substring(0, 2);
		int i = newsTag.indexOf(type);
		int j = newsTag.indexOf(ntag);
		String newTagString = "";
		if (j != -1) {
			if (j == 0) {
				if (newsTag.length() > 3) {
					newTagString = newsTag.substring(4);
				}
			} else {
				newTagString = newsTag.substring(0, j - 1) + newsTag.substring(j + 3);
			}
		} else {
			if (i != -1) {
				newTagString = newsTag.substring(0, i) + ntag + newsTag.substring(i + 3);
			} else {
				if (!newsTag.equals("")) {
					newsTag = newsTag + "|";
				}
				newTagString = newsTag + ntag;
			}
		}
		this.newslist.get(index).put("TagIts", AESEncryptor.encrypt(newTagString, this.password));
		XMLWriter.write(filepath, newslist.get(index).get("ID"), "TagIts", newTagString);
	}

	public boolean hasTag(int index) {
		String newsTag = null;
		String[] mainTags = { "0 ", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 " };
		newsTag = this.newslist.get(index).get("TagIts");
		if (newsTag == null) {
			newsTag = "";
		} else {
			newsTag = AESEncryptor.decrypt(newsTag, this.password);
		}
		for (int j = 0; j < 9; j++) {
			if (newsTag.indexOf(mainTags[j]) == -1) {
				return false;
			}
		}

		return true;
	}
	
	
}
