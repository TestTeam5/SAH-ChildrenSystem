package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.FontUIResource;

import widget.FontAwesome;
import widget.NewsScrollPane;
import widget.NewsTable;
import widget.PageSelectButton;
import widget.TabButton;
import widget.TagButtonGroup;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		String[] mainTagsText = { "报纸类别", "新闻类型", "报道主题", "新闻来源", "媒介形象", "帮助类新闻种类", "帮助类新闻主体", "表彰对象", "无法在城市读书原因" };
		String[][] subTagsText = { { "中央一级党报", "省委机关报", "都市报" }, { "纯净新闻", "特稿与特写", "评论", "其他" },
				{ "社会帮助", "建议和看法", "表彰", "暴力现象", "性侵猥亵", "儿童犯罪", "意外身亡", "努力上进", "父母生活", "其他" },
				{ "记者", "政府", "企业", "事业单位", "公益团体", "专家学者", "政府领导", "其他" }, { "积极健康", "可怜悲惨", "沐恩幸福", "问题儿童", "其他" },
				{ "一次性帮助", "旅游活动", "免费开放", "资助项目", "其他" }, { "政府部门", "企业", "事业单位", "公益团体", "个人" },
				{ "政府部门", "企业", "事业单位", "公益团体", "个人" }, { "无本地户籍", "学费高", "教学质量", "办学资格", "其他" } };

		InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 12)); // 统一设置字体

		frame = new JFrame("留守儿童舆情调查系统");
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 顶部tab栏
		JPanel tabPanel = new JPanel();
		tabPanel.setLayout(new GridLayout(1, 4, 0, 0));

		TabButton firstPageBtn = new TabButton("fa-home", 26, "首页");
		firstPageBtn.setBackground(new Color(230, 230, 230));
		firstPageBtn.setForeground(Color.BLACK);
		firstPageBtn.setIcon(FontAwesome.getIcon("fa-home", 26, Color.BLACK));
		tabPanel.add(firstPageBtn);
		TabButton showNewsBtn = new TabButton("fa-wpforms", 22, "新闻");
		tabPanel.add(showNewsBtn);
		TabButton statisticsBtn = new TabButton("fa-line-chart", 22, "统计");
		tabPanel.add(statisticsBtn);
		TabButton recycleBtn = new TabButton("fa-trash", 24, "回收站");
		tabPanel.add(recycleBtn);

		frame.getContentPane().add(tabPanel, BorderLayout.NORTH);

		// 具体页面区域
		JPanel pagePanel = new JPanel();

		final CardLayout cardLayout = new CardLayout();
		pagePanel.setLayout(cardLayout);

		JPanel firstPagePanel = new JPanel();
		pagePanel.add("首页", firstPagePanel);
		JPanel showNewsPanel = new JPanel();
		pagePanel.add("新闻", showNewsPanel);
		JPanel statisticsPanel = new JPanel();
		pagePanel.add("统计", statisticsPanel);
		JPanel recyclePanel = new JPanel();
		pagePanel.add("回收站", recyclePanel);

		frame.getContentPane().add(pagePanel, BorderLayout.CENTER);

		// 首页
		JTextField firstWelcomeInfoText = new JTextField("欢迎使用留守儿童舆情调查系统");
		firstPagePanel.add(firstWelcomeInfoText, BorderLayout.CENTER);

		// 显示新闻页面
		final CardLayout showNewsCardLayout = new CardLayout();
		showNewsPanel.setLayout(showNewsCardLayout);

		// 显示新闻页面->新闻列表界面
		JPanel showNewsListPanel = new JPanel();
		showNewsListPanel.setLayout(new BorderLayout());
		showNewsPanel.add("新闻列表", showNewsListPanel);

		Object[][] showNewsTableData = { new Object[] { "青少年“身边最让我感动的人”评选揭晓" }, new Object[] { "确保农民工子女享受国家政策" },
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
		Object[] showNewsColumnTitle = { "标题" };
		JTable showNewsTable = new NewsTable(showNewsTableData, showNewsColumnTitle);

		JScrollPane showNewsScrollPane = new NewsScrollPane(showNewsTable);
		Border showNewsScrollBorder = new CompoundBorder(
				new CompoundBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20),
						BorderFactory.createLineBorder(Color.BLACK, 1)),
				BorderFactory.createEmptyBorder(20, 25, 20, 25));
		showNewsScrollPane.setBorder(showNewsScrollBorder);
		showNewsListPanel.add(showNewsScrollPane, BorderLayout.CENTER);

		JPanel newsBottomButtons = new JPanel();
		JButton newsFirstButton = new PageSelectButton("首页");
		newsBottomButtons.add(newsFirstButton);
		JButton newsPreviousButton = new PageSelectButton("上一页");
		newsBottomButtons.add(newsPreviousButton);
		JButton newsNextButton = new PageSelectButton("下一页");
		newsBottomButtons.add(newsNextButton);
		JButton newsLastButton = new PageSelectButton("尾页");
		newsBottomButtons.add(newsLastButton);
		showNewsListPanel.add(newsBottomButtons, BorderLayout.SOUTH);

		// 显示新闻节目->新闻详细内容界面
		JPanel showNewsDetailPanel = new JPanel();
		showNewsDetailPanel.setLayout(new BorderLayout());

		Border showNewsDetailBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		showNewsDetailPanel.setBorder(showNewsDetailBorder);
		showNewsDetailPanel.setBackground(Color.WHITE);

		showNewsPanel.add("新闻详细内容", showNewsDetailPanel);

		JPanel newsDetailMainTagsPanel = new JPanel();

		GridBagLayout newsDetailMainTagsGb = new GridBagLayout();
		GridBagConstraints newsDetailMainTagsGbc = new GridBagConstraints();
		newsDetailMainTagsPanel.setLayout(newsDetailMainTagsGb);

		newsDetailMainTagsGbc.fill = GridBagConstraints.BOTH;
		newsDetailMainTagsGbc.weightx = 1;
		TagButtonGroup newsDetailMainTagsGroup = new TagButtonGroup();
		for (int i = 0; i < 8; i++) {
			newsDetailMainTagsGroup.add(new JButton(mainTagsText[i]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});
			newsDetailMainTagsGb.setConstraints(newsDetailMainTagsGroup.get(i), newsDetailMainTagsGbc);
			newsDetailMainTagsPanel.add(newsDetailMainTagsGroup.get(i));
		}
		newsDetailMainTagsGbc.gridwidth = GridBagConstraints.REMAINDER;
		newsDetailMainTagsGroup.add(new JButton(mainTagsText[8]) {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.WHITE);
				g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
			}
		});
		newsDetailMainTagsGb.setConstraints(newsDetailMainTagsGroup.get(8), newsDetailMainTagsGbc);
		newsDetailMainTagsPanel.add(newsDetailMainTagsGroup.get(8));

		showNewsDetailPanel.add(newsDetailMainTagsPanel, BorderLayout.NORTH);

		// 新闻详细界面->主标签下方的外部容器
		JPanel showNewsDetailMainPanel = new JPanel();
		showNewsDetailMainPanel.setLayout(new BorderLayout());
		showNewsDetailMainPanel.setBackground(Color.WHITE);
		showNewsDetailMainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		showNewsDetailPanel.add(showNewsDetailMainPanel, BorderLayout.CENTER);

		// 新闻详细界面->子标签的CardLayout容器
		JPanel newsDetailSubTagsCardPanel = new JPanel();
		final CardLayout newsDetailSubTagsCardLayout = new CardLayout();
		newsDetailSubTagsCardPanel.setLayout(newsDetailSubTagsCardLayout);
		showNewsDetailMainPanel.add(newsDetailSubTagsCardPanel, BorderLayout.NORTH);

		// 每个子标签容器
		JPanel[] newsDetailSubTagsPanels = new JPanel[9];
		// 每个子标签的按钮组
		TagButtonGroup[] newsDetailSubTagsBtnGroup = new TagButtonGroup[9];

		for (int i = 0; i < 9; i++) {
			newsDetailSubTagsBtnGroup[i] = new TagButtonGroup();
			newsDetailSubTagsPanels[i] = new JPanel();
			newsDetailSubTagsPanels[i].setBackground(Color.WHITE);
			GridBagLayout newsDetailSubTagsGb = new GridBagLayout();
			GridBagConstraints newsDetailSubTagsGbc = new GridBagConstraints();
			newsDetailSubTagsPanels[i].setLayout(newsDetailSubTagsGb);
			newsDetailSubTagsCardPanel.add(Integer.toString(i), newsDetailSubTagsPanels[i]);

			newsDetailSubTagsGbc.fill = GridBagConstraints.BOTH;
			newsDetailSubTagsGbc.weightx = 1;

			for (int j = 0; j < subTagsText[i].length - 1; j++) {
				newsDetailSubTagsBtnGroup[i].add(new JButton(subTagsText[i][j]) {
					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.WHITE);
						g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
					}
				});
				newsDetailSubTagsGb.setConstraints(newsDetailSubTagsBtnGroup[i].get(j), newsDetailSubTagsGbc);
				newsDetailSubTagsPanels[i].add(newsDetailSubTagsBtnGroup[i].get(j));
			}

			newsDetailSubTagsGbc.gridwidth = GridBagConstraints.REMAINDER;
			newsDetailSubTagsBtnGroup[i].add(new JButton(subTagsText[i][subTagsText[i].length - 1]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});

			newsDetailSubTagsGb.setConstraints(newsDetailSubTagsBtnGroup[i].get(subTagsText[i].length - 1),
					newsDetailSubTagsGbc);
			newsDetailSubTagsPanels[i].add(newsDetailSubTagsBtnGroup[i].get(subTagsText[i].length - 1));
		}

		// 添加主标签点击事件
		for (int i = 0; i < 9; i++) {
			newsDetailMainTagsGroup.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							newsDetailSubTagsCardLayout.show(newsDetailSubTagsCardPanel, Integer.toString(j));
							// 清除选中状态
							newsDetailSubTagsBtnGroup[j].clearSelection();
						}
					}
				}
			});
		}

		// 统计界面
		statisticsPanel.setLayout(new BorderLayout());

		Border statisticsBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		statisticsPanel.setBorder(statisticsBorder);
		statisticsPanel.setBackground(Color.WHITE);

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		statisticsPanel.setLayout(gb);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		TagButtonGroup statisticsMainTags = new TagButtonGroup();
		for (int i = 0; i < 8; i++) {
			statisticsMainTags.add(new JButton(mainTagsText[i]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});
			gb.setConstraints(statisticsMainTags.get(i), gbc);
			statisticsPanel.add(statisticsMainTags.get(i));
		}
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		statisticsMainTags.add(new JButton(mainTagsText[8]) {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.WHITE);
				g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
			}
		});
		gb.setConstraints(statisticsMainTags.get(8), gbc);
		statisticsPanel.add(statisticsMainTags.get(8));

		// 左侧子标签的容器
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		// 存放各个标签的CardLayout容器
		JPanel statisticsSubTags = new JPanel();
		statisticsSubTags.setBackground(Color.WHITE);
		statisticsSubTags.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 1));
		final CardLayout statisticsSubTagsLayout = new CardLayout();
		statisticsSubTags.setLayout(statisticsSubTagsLayout);
		gb.setConstraints(statisticsSubTags, gbc);
		statisticsPanel.add(statisticsSubTags);

		// 每个子标签容器
		JPanel[] statisticsSubTagsPanels = new JPanel[9];
		// 每个子标签的按钮组
		TagButtonGroup[] statisticsSubTagsBtnGroup = new TagButtonGroup[9];
		for (int i = 0; i < 9; i++) {
			statisticsSubTagsBtnGroup[i] = new TagButtonGroup();

			statisticsSubTagsPanels[i] = new JPanel();
			statisticsSubTagsPanels[i].setBackground(Color.WHITE);
			GridBagLayout statisticsSubTagsGb = new GridBagLayout();
			GridBagConstraints statisticsSubTagsGbc = new GridBagConstraints();
			statisticsSubTagsPanels[i].setLayout(statisticsSubTagsGb);
			statisticsSubTags.add(Integer.toString(i), statisticsSubTagsPanels[i]);

			statisticsSubTagsGbc.fill = GridBagConstraints.BOTH;
			statisticsSubTagsGbc.weightx = 1;
			statisticsSubTagsGbc.gridwidth = GridBagConstraints.REMAINDER;

			statisticsSubTagsBtnGroup[i].add(new JButton(subTagsText[i][0]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
				}
			});
			statisticsSubTagsGb.setConstraints(statisticsSubTagsBtnGroup[i].get(0), statisticsSubTagsGbc);
			statisticsSubTagsPanels[i].add(statisticsSubTagsBtnGroup[i].get(0));

			statisticsSubTagsGbc.weightx = 0;
			for (int j = 1; j < subTagsText[i].length; j++) {
				statisticsSubTagsBtnGroup[i].add(new JButton(subTagsText[i][j]) {
					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.WHITE);
						g.drawLine(0, getHeight() - 1, getWidth() - 1, getHeight() - 1);
					}
				});
				statisticsSubTagsGb.setConstraints(statisticsSubTagsBtnGroup[i].get(j), statisticsSubTagsGbc);
				statisticsSubTagsPanels[i].add(statisticsSubTagsBtnGroup[i].get(j));
			}

			statisticsSubTagsGbc.weighty = 1;
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			statisticsSubTagsGb.setConstraints(temp, statisticsSubTagsGbc);
			statisticsSubTagsPanels[i].add(temp);
		}

		// 添加主标签点击事件
		for (int i = 0; i < 9; i++) {
			statisticsMainTags.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							statisticsSubTagsLayout.show(statisticsSubTags, Integer.toString(j));
							// 清除选中状态
							statisticsSubTagsBtnGroup[j].clearSelection();
						}
					}
				}
			});
		}

		// 放置统计图的容器
		gbc.gridwidth = 8;
		JPanel statisticsFigurePanel = new JPanel();
		statisticsFigurePanel.setBackground(Color.WHITE);
		gb.setConstraints(statisticsFigurePanel, gbc);
		statisticsPanel.add(statisticsFigurePanel);

		// 回收站界面
		recyclePanel.setLayout(new BorderLayout());

		Object[][] recycleNewsTableData = { new Object[] { "青少年“身边最让我感动的人”评选揭晓", "还原" },
				new Object[] { "确保农民工子女享受国家政策", "还原" },
				new Object[] { "全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...", "还原" },
				new Object[] { "不能忽视农村留守儿童", "还原" }, new Object[] { "对农村“留守儿童”问题的理性思考", "还原" },
				new Object[] { "好政策播种新希望——记湖北省农村教师资助行动计划", "还原" }, new Object[] { "和谐社会视野下的教育公平", "还原" },
				new Object[] { "农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战", "还原" }, new Object[] { "农村留守儿童教育亟待加强", "还原" },
				new Object[] { "17部委领导慰问在京流动人口子女和农村留守儿童", "还原" }, new Object[] { "江苏“七彩夏日”点亮精彩暑假", "还原" },
				new Object[] { "高校：新农村建设的生力军——华中师大服务新农村侧记", "还原" },
				new Object[] { "中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童", "还原" },
				new Object[] { "加大经费保障提供重点扶持福建基础教育资源向农村倾斜", "还原" },
				new Object[] { "分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”", "还原" }, new Object[] { "大学生耿高鹏为救落水少年英勇献身", "还原" },
				new Object[] { "农村留守儿童心理需关爱", "还原" }, new Object[] { "志愿服务，是永不谢幕的青春之歌——对武汉大学“赵小亭事迹”的观察与啓示", "还原" },
				new Object[] { "老艺人传授“惠洋十音”", "还原" }, new Object[] { "“三进三同”体验国情民生", "还原" },
				new Object[] { "江苏农家书屋实现全覆盖", "还原" }, new Object[] { "开发农村人力资源促进城乡统筹发展", "还原" },
				new Object[] { "全国教书育人楷模候选人简介", "还原" }, new Object[] { "全国教书育人楷模候选人简介（上接6版）", "还原" },
				new Object[] { "城市，孩子的温暖家园——合肥全社会关爱未成年人的故事", "还原" } };
		Object[] recycleNewsColumnTitle = { "标题", "操作" };
		JTable recycleNewsTable = new NewsTable(recycleNewsTableData, recycleNewsColumnTitle);
		recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);

		JScrollPane recycleScrollPane = new NewsScrollPane(recycleNewsTable);
		Border recycleScrollBorder = new CompoundBorder(
				new CompoundBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20),
						BorderFactory.createLineBorder(Color.BLACK, 1)),
				BorderFactory.createEmptyBorder(20, 25, 20, 25));
		recycleScrollPane.setBorder(recycleScrollBorder);
		recyclePanel.add(recycleScrollPane, BorderLayout.CENTER);

		JPanel recycleNewsBottomButtons = new JPanel();
		JButton recycleNewsFirstButton = new PageSelectButton("首页");
		recycleNewsBottomButtons.add(recycleNewsFirstButton);
		JButton recycleNewsPreviousButton = new PageSelectButton("上一页");
		recycleNewsBottomButtons.add(recycleNewsPreviousButton);
		JButton recycleNewsNextButton = new PageSelectButton("下一页");
		recycleNewsBottomButtons.add(recycleNewsNextButton);
		JButton recycleNewsLastButton = new PageSelectButton("尾页");
		recycleNewsBottomButtons.add(recycleNewsLastButton);
		recyclePanel.add(recycleNewsBottomButtons, BorderLayout.SOUTH);

		// 为tab栏按钮设置点击事件
		ActionListener tabListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "首页":
					cardLayout.show(pagePanel, "首页");
					break;
				case "新闻":
					cardLayout.show(pagePanel, "新闻");
					showNewsCardLayout.show(showNewsPanel, "新闻列表");
					showNewsTable.clearSelection();
					break;
				case "统计":
					cardLayout.show(pagePanel, "统计");
					recycleNewsTable.clearSelection();
					statisticsMainTags.select(0);
					// 显示第一个主标签对应子标签
					statisticsSubTagsLayout.show(statisticsSubTags, "0");
					// 清除选中状态
					statisticsSubTagsBtnGroup[0].clearSelection();
					break;
				case "回收站":
					cardLayout.show(pagePanel, "回收站");
					break;
				}
			}
		};
		firstPageBtn.addActionListener(tabListener);
		showNewsBtn.addActionListener(tabListener);
		statisticsBtn.addActionListener(tabListener);
		recycleBtn.addActionListener(tabListener);

		// 操作：显示新闻界面->新闻列表界面，新闻列表点击事件
		showNewsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable src = null;
				if (e.getSource() instanceof JTable) {
					src = (JTable) e.getSource();
					showNewsCardLayout.show(showNewsPanel, "新闻详细内容");
					// txtInfo.setText( sportType + " : " +
					// src.getSelectedIndex() + " : " + src.getSelectedValue() +
					// "\n" );
				}
			}
		});
	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

}
