package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;

import util.ConsistencyCheck;
import util.DeletedNewsGetter;
import util.FileManager;
import util.FilePathSelector;
import util.Initializer;
import util.NewsGetter;
import util.StatisticsGetter;
import util.TestNewsGetter;
import widget.FontAwesome;
import widget.NewsScrollPane;
import widget.NewsTable;
import widget.PageSelectButton;
import widget.ShiftButton;
import widget.TabButton;
import widget.TagButtonGroup;

public class MainWindow {

	private JFrame frame;
	// 日志输出
	private static Logger logger = Logger.getLogger(MainWindow.class.getName());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		logger.debug("界面初始化开始");

		logger.debug("数据初始化开始");
		Initializer.initData();
		NewsGetter.init();
		DeletedNewsGetter.init();
		logger.debug("数据初始化完成");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
					e.printStackTrace();
				}
			}
		});
		logger.debug("界面初始化完成");

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
		firstPagePanel.setLayout(new BorderLayout());
		firstPagePanel.setBackground(Color.WHITE);

		// 首页顶部按钮
		JPanel firstTopButtonPanel = new JPanel();
		firstTopButtonPanel.setBackground(Color.WHITE);
		firstTopButtonPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 10));
		firstTopButtonPanel.setLayout(new GridLayout(1, 8, 5, 5));
		firstPagePanel.add(firstTopButtonPanel, BorderLayout.NORTH);

		JButton importButton = new PageSelectButton("导入");
		firstTopButtonPanel.add(importButton);
		JButton testButton = new PageSelectButton("测试");
		firstTopButtonPanel.add(testButton);
		JButton consistencyButton = new PageSelectButton("结果");
		firstTopButtonPanel.add(consistencyButton);
		for (int i = 0; i < 5; i++) {
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			firstTopButtonPanel.add(temp);
		}

		// 首页主体部分
		JPanel firstCardPanel = new JPanel();
		final CardLayout firstCardLayout = new CardLayout();
		firstCardPanel.setLayout(firstCardLayout);
		firstPagePanel.add(firstCardPanel, BorderLayout.CENTER);

		JPanel firstWelcomeInfoPanel = new JPanel();
		firstCardPanel.add("欢迎信息", firstWelcomeInfoPanel);
		JPanel firstTestPanel = new JPanel();
		firstCardPanel.add("测试界面", firstTestPanel);
		JPanel firstConsistencyPanel = new JPanel();
		firstCardPanel.add("检验结果", firstConsistencyPanel);

		// 首页->欢迎信息界面
		firstWelcomeInfoPanel.setLayout(new BorderLayout());
		firstWelcomeInfoPanel.setBackground(Color.WHITE);

		JTextField firstWelcomeInfoText = new JTextField("欢迎使用留守儿童舆情调查系统");
		firstWelcomeInfoText.setHorizontalAlignment(JTextField.CENTER);
		firstWelcomeInfoText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		firstWelcomeInfoText.setFocusable(false);
		firstWelcomeInfoText.setBorder(null);
		firstWelcomeInfoPanel.add(firstWelcomeInfoText);

		// 首页->测试界面
		firstTestPanel.setLayout(new BorderLayout());
		Border firstTestBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		firstTestPanel.setBorder(firstTestBorder);
		firstTestPanel.setBackground(Color.WHITE);

		// **************************************************************************************
		JPanel firstTestMainTagsPanel = new JPanel();

		GridBagLayout firstTestMainTagsGb = new GridBagLayout();
		GridBagConstraints firstTestMainTagsGbc = new GridBagConstraints();
		firstTestMainTagsPanel.setLayout(firstTestMainTagsGb);

		firstTestMainTagsGbc.fill = GridBagConstraints.BOTH;
		firstTestMainTagsGbc.weightx = 1;
		TagButtonGroup firstTestMainTagsGroup = new TagButtonGroup();
		for (int i = 0; i < 8; i++) {
			firstTestMainTagsGroup.add(new JButton(mainTagsText[i]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});
			firstTestMainTagsGb.setConstraints(firstTestMainTagsGroup.get(i), firstTestMainTagsGbc);
			firstTestMainTagsPanel.add(firstTestMainTagsGroup.get(i));
		}
		firstTestMainTagsGbc.gridwidth = GridBagConstraints.REMAINDER;
		firstTestMainTagsGroup.add(new JButton(mainTagsText[8]) {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.WHITE);
				g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
			}
		});
		firstTestMainTagsGb.setConstraints(firstTestMainTagsGroup.get(8), firstTestMainTagsGbc);
		firstTestMainTagsPanel.add(firstTestMainTagsGroup.get(8));

		firstTestPanel.add(firstTestMainTagsPanel, BorderLayout.NORTH);

		// 新闻详细界面->主标签下方的外部容器
		JPanel firstTestMainPanel = new JPanel();
		firstTestMainPanel.setLayout(new BorderLayout());
		firstTestMainPanel.setBackground(Color.WHITE);
		firstTestMainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		firstTestPanel.add(firstTestMainPanel, BorderLayout.CENTER);

		// 新闻详细界面->子标签的CardLayout容器
		JPanel firstTestSubTagsCardPanel = new JPanel();
		final CardLayout firstTestSubTagsCardLayout = new CardLayout();
		firstTestSubTagsCardPanel.setLayout(firstTestSubTagsCardLayout);
		firstTestMainPanel.add(firstTestSubTagsCardPanel, BorderLayout.NORTH);

		// 每个子标签容器
		JPanel[] firstTestSubTagsPanels = new JPanel[9];
		// 每个子标签的按钮组
		TagButtonGroup[] firstTestSubTagsBtnGroup = new TagButtonGroup[9];

		for (int i = 0; i < 9; i++) {
			firstTestSubTagsBtnGroup[i] = new TagButtonGroup();
			firstTestSubTagsBtnGroup[i].setCanReSelected(true);
			firstTestSubTagsPanels[i] = new JPanel();
			firstTestSubTagsPanels[i].setBackground(Color.WHITE);
			GridBagLayout firstTestSubTagsGb = new GridBagLayout();
			GridBagConstraints firstTestSubTagsGbc = new GridBagConstraints();
			firstTestSubTagsPanels[i].setLayout(firstTestSubTagsGb);
			firstTestSubTagsCardPanel.add(Integer.toString(i), firstTestSubTagsPanels[i]);

			firstTestSubTagsGbc.fill = GridBagConstraints.BOTH;
			firstTestSubTagsGbc.weightx = 1;

			for (int j = 0; j < subTagsText[i].length - 1; j++) {
				firstTestSubTagsBtnGroup[i].add(new JButton(subTagsText[i][j]) {
					public void paint(Graphics g) {
						super.paint(g);
						g.setColor(Color.WHITE);
						g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
					}
				});
				firstTestSubTagsGb.setConstraints(firstTestSubTagsBtnGroup[i].get(j), firstTestSubTagsGbc);
				firstTestSubTagsPanels[i].add(firstTestSubTagsBtnGroup[i].get(j));

				// 添加子标签点击事件
				firstTestSubTagsBtnGroup[i].get(j).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int j = 0; j < subTagsText[TestNewsGetter.getSelectedMainTag()].length - 1; j++) {
							if (e.getActionCommand().equals(subTagsText[TestNewsGetter.getSelectedMainTag()][j])) {
								logger.debug("显示新闻界面-点击子标签" + j);
								TestNewsGetter.refactorTags(j);
							}
						}
					}
				});
			}

			firstTestSubTagsGbc.gridwidth = GridBagConstraints.REMAINDER;
			firstTestSubTagsBtnGroup[i].add(new JButton(subTagsText[i][subTagsText[i].length - 1]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});
			firstTestSubTagsBtnGroup[i].get(subTagsText[i].length - 1).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < subTagsText[TestNewsGetter.getSelectedMainTag()].length; j++) {
						if (e.getActionCommand().equals(subTagsText[TestNewsGetter.getSelectedMainTag()][j])) {
							logger.debug("显示新闻界面-点击子标签" + j);
							TestNewsGetter.refactorTags(j);
						}
					}
				}
			});

			firstTestSubTagsGb.setConstraints(firstTestSubTagsBtnGroup[i].get(subTagsText[i].length - 1),
					firstTestSubTagsGbc);
			firstTestSubTagsPanels[i].add(firstTestSubTagsBtnGroup[i].get(subTagsText[i].length - 1));
		}

		// 添加主标签点击事件
		for (int i = 0; i < 9; i++) {
			firstTestMainTagsGroup.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							logger.debug("显示新闻界面-点击主标签" + j);
							firstTestSubTagsCardLayout.show(firstTestSubTagsCardPanel, Integer.toString(j));
							TestNewsGetter.setSelectedMainTag(j);
							int temp = TestNewsGetter.getSelectedSubTag();
							if (temp != -1) {
								firstTestSubTagsBtnGroup[j].select(temp);
							} else {
								firstTestSubTagsBtnGroup[j].clearSelection();
							}
						}
					}
				}
			});
		}

		// 测试界面底部上一条、下一条按钮
		JPanel firstTestShiftPanel = new JPanel();
		firstTestShiftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		firstTestShiftPanel.setBackground(Color.WHITE);
		JButton firstTestLastButton = new JButton("上一条");
		firstTestLastButton.setBackground(Color.DARK_GRAY);
		firstTestLastButton.setForeground(Color.WHITE);
		firstTestLastButton.setFocusPainted(false);
		firstTestLastButton.setBorderPainted(false);
		firstTestShiftPanel.add(firstTestLastButton);
		JPanel firstTestTempPanel = new JPanel();
		firstTestTempPanel.setSize(1, 1);
		firstTestTempPanel.setBackground(Color.WHITE);
		firstTestShiftPanel.add(firstTestTempPanel);
		JButton firstTestNextButton = new JButton("下一条");
		firstTestNextButton.setBackground(Color.DARK_GRAY);
		firstTestNextButton.setForeground(Color.WHITE);
		firstTestNextButton.setFocusPainted(false);
		firstTestNextButton.setBorderPainted(false);
		firstTestShiftPanel.add(firstTestNextButton);
		
		firstTestTempPanel = new JPanel();
		firstTestTempPanel.setSize(1, 1);
		firstTestTempPanel.setBackground(Color.WHITE);
		firstTestShiftPanel.add(firstTestTempPanel);
		
		JTextField numEditBox = new JTextField("1");
		numEditBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		numEditBox.setFocusable(true);
		numEditBox.setColumns(2);
		firstTestShiftPanel.add(numEditBox);
		
		JTextField totalNumText = new JTextField("/10");
		totalNumText.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		totalNumText.setFocusable(false);
		totalNumText.setBorder(null);
		firstTestShiftPanel.add(totalNumText);
		
		firstTestTempPanel = new JPanel();
		firstTestTempPanel.setSize(1, 1);
		firstTestTempPanel.setBackground(Color.WHITE);
		firstTestShiftPanel.add(firstTestTempPanel);
		
		JButton firstTestChangeButton = new JButton("转到");
		firstTestChangeButton.setBackground(Color.DARK_GRAY);
		firstTestChangeButton.setForeground(Color.WHITE);
		firstTestChangeButton.setFocusPainted(false);
		firstTestChangeButton.setBorderPainted(false);
		firstTestShiftPanel.add(firstTestChangeButton);
		
		firstTestMainPanel.add(firstTestShiftPanel, BorderLayout.SOUTH);

		// 添加详细新闻内容版面
		JEditorPane firstTestNewsContentPane = new JEditorPane();
		firstTestNewsContentPane.setBackground(Color.WHITE);
		firstTestNewsContentPane.setContentType("text/html");
		firstTestNewsContentPane.setEditable(false);
		firstTestNewsContentPane.setSelectionColor(new Color(230, 230, 230));
		firstTestNewsContentPane.setText(
				"<html><body><H1>留守儿童与回家父母手拉手</H1><span>2014-01-29</span>&nbsp;&nbsp;<span>光明日报(数字报)</span><br/><span>06,教科新闻</span><P>信息来源於四川新闻网 / Cited from http://www.newssc.org/<P>□川报集团特派记者 王小玲本报记者 黄泽君<P>【基层即景】<P>小区绿意盎然，地面不见一点垃圾，楼道扶梯干乾净净……11月13日，记者走进成都市锦江区双桂路街道五福桥社区江东民居时，不少居民正围在一起聊天。生活环境改善了，有问题能很快得到解决。居民龙先勇觉得，自从社会管理创新以来，日子越过越舒心了。<P>成立院落党支部、鼓励居民成为志愿者服务社区、实行“以院养院”的物管自治机制……社区党委书记姚艳洪介绍，目前五福桥社区一共成立了11个社会组织，注册志愿者多达1500余名，全部由本社区居民组成，在养老、物管、社区自治等方面为居民提供服务。<P>生活满意度越来越高，生活环境越来越好，这是五福桥社区居民近年来最大的感受。大家希望，能有更多的利好政策倾向基层，倾向社区。<P>【会场连线】<P>嘉宾：李向志 十八大代表、德阳市委书记丁爱谱 十八大代表、攀枝花市东区长寿路街道健康路社区党员<P>畅通社区工作人员成长渠道<P>社区党委书记姚艳洪：希望把人、财、物等资源更多地配置到社区，进一步提高社区工作人员的素质和待遇。<P>李向志：社会管理是个系统工程，在实践中要从城乡社区抓起，夯实基层基础，坚持重心下移、服务下延、工作下沉。作为全国社会管理创新试点城市之一，德阳近两年来做了一些探索，我们将社区人员基本报酬、办公经费纳入财政预算，并建立了自然增长机制。同时，加强社区队伍建设，每个社区居委会都配备了5至9名工作人员，并聘请社区综合服务协管员，招录大学生加入社区管理队伍，加大在社区工作人员中优先发展党员、招录公务员、选拔基层领导干部的工作力度，进一步畅通了社区工作人员的成长渠道。建议国家建立社会管理工作投入保障机制，特别是建立面向基层 “费随事转”的转移支付机制，为加强和创新社会管理提供有力保障。<P>整合辖区资源应有长效机制<P>居民黄家发：现在社区每周都会开展活动，这让我们的社区更加和谐，但有时会出现组织经费不足的情况，希望能有更多资金和政策支持。<P>丁爱谱：十八大报告提出要强化企事业单位、人民团体在社会管理和服务中的职责，引导社会组织健康有序发展，发挥群衆参与社会管理的基础作用。作为一名普通的党员，我在20多年的社区服务工作中，深有同感。目前，尽管我们在整合辖区资源、协调居民参与上做了一些尝试，但是仍然缺乏以制度固定下来的资源整合长效机制。如果能够建立一种社区牵头、辖区企业参与、百姓受益的政策或工作导向，使社区与辖区单位的关系能制度化、规范化，社区的各项社会工作就会更容易开展，效果也会好很多。<P>让群衆在社区就能办好事<P>居民胡延碧：我们希望有更多与民生相关的公共服务，能够深入基层，深入社区。<P>李向志：十八大报告提出要改进政府提供公共服务方式，加强基层社会管理和服务体系建设，增强城乡社区服务功能。我们要坚持把服务群衆作为社区建设的核心和关键，不断完善社区服务体系，切实解决民生问题。一方面要完善政务服务平台，将党务服务、劳动就业、社会保障等服务职能从街道（乡镇）便民服务中心向社区延伸，真正实现群衆在社区就能办所有事。另一方面，也要改进我们的社区服务方式，德阳这两年向社区群衆免费发放服务联系卡，建立困难群衆情况登记制度，社区工作人员定期主动上门探访空巢老人、残疾人、留守儿童等，帮助解决实际困难，我认为这些都是行之有效的办法。<P>（本报北京11月13日电） </P></body></html>");

		JScrollPane firstTestContentPanel = new NewsScrollPane(firstTestNewsContentPane);
		firstTestContentPanel.setBackground(Color.WHITE);
		Border firstTestContentBorder = new CompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createLineBorder(Color.BLACK, 1));
		firstTestContentPanel.setBorder(firstTestContentBorder);
		firstTestMainPanel.add(firstTestContentPanel, BorderLayout.CENTER);
		// ***********************************************************************************************************

		// 首页->结果界面
		firstConsistencyPanel.setLayout(new BorderLayout());
		firstConsistencyPanel.setBackground(Color.WHITE);

		JTextField firstConsistencyText = new JTextField("一致性检验结果为：");
		firstConsistencyText.setHorizontalAlignment(JTextField.CENTER);
		firstConsistencyText.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		firstConsistencyText.setFocusable(false);
		firstConsistencyText.setBorder(null);
		firstConsistencyPanel.add(firstConsistencyText);

		// 导入按钮点击事件
		importButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<String> filepaths = FilePathSelector.getFilePaths();
				FileManager.mergeAll(filepaths);
				Initializer.initData();
			}
		});

		// 测试按钮点击事件
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (Initializer.initTestData()) {
					TestNewsGetter.init();
					firstTestNewsContentPane.setText(TestNewsGetter.getSelectedContent());
					firstTestNewsContentPane.setCaretPosition(0);

					totalNumText.setText("/" + TestNewsGetter.getMaxCount());
					numEditBox.setText((TestNewsGetter.getSelectedIndex() + 1) + "");
					
					// 设置主标签默认选中第一个
					firstTestMainTagsGroup.select(0);

					// 设置子标签面板切换到第一个
					firstTestSubTagsCardLayout.show(firstTestSubTagsCardPanel, "0");
					TestNewsGetter.setSelectedMainTag(0);
					int temp = TestNewsGetter.getSelectedSubTag();
					if (temp != -1) {
						firstTestSubTagsBtnGroup[0].select(temp);
					} else {
						firstTestSubTagsBtnGroup[0].clearSelection();
					}

					firstCardLayout.show(firstCardPanel, "测试界面");
				}
			}
		});

		// 结果按钮点击事件
		consistencyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<String> paths = FilePathSelector.getFilePaths();
				if (!paths.isEmpty()) {
					double result = ConsistencyCheck.getConsistencyRate(paths);
					NumberFormat fmt = NumberFormat.getPercentInstance();
					fmt.setMaximumFractionDigits(2);
					firstConsistencyText.setText("一致性检验结果为：" + fmt.format(result));
					firstCardLayout.show(firstCardPanel, "检验结果");
				}
				logger.debug("首页->结果->未选择文件");
			}
		});

		// 首页->测试页面->上一条点击事件
		firstTestLastButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TestNewsGetter.indexMinus();

				firstTestNewsContentPane.setText(TestNewsGetter.getSelectedContent());
				firstTestNewsContentPane.setCaretPosition(0);

				// 设置主标签默认选中第一个
				firstTestMainTagsGroup.select(0);

				// 设置子标签面板切换到第一个
				firstTestSubTagsCardLayout.show(firstTestSubTagsCardPanel, "0");
				TestNewsGetter.setSelectedMainTag(0);
				int temp = TestNewsGetter.getSelectedSubTag();
				if (temp != -1) {
					firstTestSubTagsBtnGroup[0].select(temp);
				} else {
					firstTestSubTagsBtnGroup[0].clearSelection();
				}
				
				numEditBox.setText((TestNewsGetter.getSelectedIndex() + 1) + "");
			}
		});

		// 首页->测试页面->下一条点击事件
		firstTestNextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TestNewsGetter.indexPlus();

				firstTestNewsContentPane.setText(TestNewsGetter.getSelectedContent());
				firstTestNewsContentPane.setCaretPosition(0);

				// 设置主标签默认选中第一个
				firstTestMainTagsGroup.select(0);

				// 设置子标签面板切换到第一个
				firstTestSubTagsCardLayout.show(firstTestSubTagsCardPanel, "0");
				TestNewsGetter.setSelectedMainTag(0);
				int temp = TestNewsGetter.getSelectedSubTag();
				if (temp != -1) {
					firstTestSubTagsBtnGroup[0].select(temp);
				} else {
					firstTestSubTagsBtnGroup[0].clearSelection();
				}
				
				numEditBox.setText((TestNewsGetter.getSelectedIndex() + 1) + "");
			}
		});
		
		// 首页->测试界面->转到点击事件
		firstTestChangeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TestNewsGetter.setIndex(Integer.parseInt(numEditBox.getText()));
				
				firstTestNewsContentPane.setText(TestNewsGetter.getSelectedContent());
				firstTestNewsContentPane.setCaretPosition(0);

				// 设置主标签默认选中第一个
				firstTestMainTagsGroup.select(0);

				// 设置子标签面板切换到第一个
				firstTestSubTagsCardLayout.show(firstTestSubTagsCardPanel, "0");
				TestNewsGetter.setSelectedMainTag(0);
				int temp = TestNewsGetter.getSelectedSubTag();
				if (temp != -1) {
					firstTestSubTagsBtnGroup[0].select(temp);
				} else {
					firstTestSubTagsBtnGroup[0].clearSelection();
				}
				
				numEditBox.setText((TestNewsGetter.getSelectedIndex() + 1) + "");
			}
		});

		// 显示新闻页面
		final CardLayout showNewsCardLayout = new CardLayout();
		showNewsPanel.setLayout(showNewsCardLayout);

		// 显示新闻页面->新闻列表界面
		JPanel showNewsListPanel = new JPanel();
		showNewsListPanel.setLayout(new BorderLayout());
		showNewsPanel.add("新闻列表", showNewsListPanel);

		Object[][] showNewsTableData = NewsGetter.getNews();
		Object[] showNewsColumnTitle = { "标题" };
		DefaultTableModel showNewsModel = new DefaultTableModel(); // 新建一个默认数据模型
		JTable showNewsTable = new NewsTable(showNewsModel);
		showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);

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

		// 切换页码
		JTextField jTextField1 = new JTextField("第");
		jTextField1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		jTextField1.setFocusable(false);
		jTextField1.setBorder(null);
		newsBottomButtons.add(jTextField1);
		JTextField pageEditBox = new JTextField("1");
		pageEditBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		pageEditBox.setFocusable(true);
		pageEditBox.setColumns(2);
		newsBottomButtons.add(pageEditBox);
		JTextField jTextField2 = new JTextField("页");
		jTextField2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		jTextField2.setFocusable(false);
		jTextField2.setBorder(null);
		newsBottomButtons.add(jTextField2);
		JButton newsToPageButton = new PageSelectButton("转到");
		newsBottomButtons.add(newsToPageButton);

		showNewsListPanel.add(newsBottomButtons, BorderLayout.SOUTH);

		// 点击首页事件
		newsFirstButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("显示新闻界面-点击首页");
				NewsGetter.init();
				Object[][] showNewsTableData = NewsGetter.getNews();
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
			}
		});

		// 点击上一页事件
		newsPreviousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("显示新闻界面-点击下一页");
				Object[][] showNewsTableData = NewsGetter.getPreviousNews();
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
			}
		});

		// 点击下一页事件
		newsNextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("显示新闻界面-点击下一页");
				Object[][] showNewsTableData = NewsGetter.getNews();
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
			}
		});

		// 点击尾页事件
		newsLastButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("显示新闻界面-点击尾页");
				Object[][] showNewsTableData = NewsGetter.getLastNews();
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
			}
		});

		// 点击转到事件
		newsToPageButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("显示新闻界面-点击转到");
				int page = Integer.parseInt(pageEditBox.getText());
				Object[][] showNewsTableData = NewsGetter.setPage(page);
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
			}
		});

		// 显示新闻界面->新闻详细内容界面
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
			newsDetailSubTagsBtnGroup[i].setCanReSelected(true);
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

				// 添加子标签点击事件
				newsDetailSubTagsBtnGroup[i].get(j).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int j = 0; j < subTagsText[NewsGetter.getSelectedMainTag()].length - 1; j++) {
							if (e.getActionCommand().equals(subTagsText[NewsGetter.getSelectedMainTag()][j])) {
								logger.debug("显示新闻界面-点击子标签" + j);
								NewsGetter.refactorTags(j);
							}
						}
					}
				});
			}

			newsDetailSubTagsGbc.gridwidth = GridBagConstraints.REMAINDER;
			newsDetailSubTagsBtnGroup[i].add(new JButton(subTagsText[i][subTagsText[i].length - 1]) {
				public void paint(Graphics g) {
					super.paint(g);
					g.setColor(Color.WHITE);
					g.drawLine(getWidth() - 1, 0, getWidth() - 1, getHeight() - 1);
				}
			});
			newsDetailSubTagsBtnGroup[i].get(subTagsText[i].length - 1).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < subTagsText[NewsGetter.getSelectedMainTag()].length; j++) {
						if (e.getActionCommand().equals(subTagsText[NewsGetter.getSelectedMainTag()][j])) {
							logger.debug("显示新闻界面-点击子标签" + j);
							NewsGetter.refactorTags(j);
						}
					}
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
							logger.debug("显示新闻界面-点击主标签" + j);
							newsDetailSubTagsCardLayout.show(newsDetailSubTagsCardPanel, Integer.toString(j));
							NewsGetter.setSelectedMainTag(j);
							int temp = NewsGetter.getSelectedSubTag();
							if (temp != -1) {
								newsDetailSubTagsBtnGroup[j].select(temp);
							} else {
								newsDetailSubTagsBtnGroup[j].clearSelection();
							}
						}
					}
				}
			});
		}

		// 详细新闻界面底部返回按钮、删除按钮及容器
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		backPanel.setBackground(Color.WHITE);
		JButton backButton = new JButton("返回");
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backPanel.add(backButton);
		JPanel tempPanel = new JPanel();
		tempPanel.setSize(1, 1);
		tempPanel.setBackground(Color.WHITE);
		backPanel.add(tempPanel);
		JButton deleteButton = new JButton("删除");
		deleteButton.setBackground(Color.DARK_GRAY);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFocusPainted(false);
		deleteButton.setBorderPainted(false);
		backPanel.add(deleteButton);
		showNewsDetailMainPanel.add(backPanel, BorderLayout.SOUTH);

		// 添加删除按钮的点击事件
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("新闻详细界面-点击删除按钮");
				Object[][] showNewsTableData = NewsGetter.deleteSelectedNews();
				Object[] showNewsColumnTitle = { "标题" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// 设置滚动条滚动到顶部
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if (showNewsScrollBar != null) {
					showNewsScrollBar.setValue(0);
				}
				showNewsCardLayout.show(showNewsPanel, "新闻列表");
			}
		});

		// 添加返回按钮的点击事件
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("新闻详细界面-点击返回按钮");
				showNewsCardLayout.show(showNewsPanel, "新闻列表");
			}
		});

		// 添加详细新闻内容版面
		JEditorPane newsContentPane = new JEditorPane();
		newsContentPane.setBackground(Color.WHITE);
		newsContentPane.setContentType("text/html");
		newsContentPane.setEditable(false);
		newsContentPane.setSelectionColor(new Color(230, 230, 230));
		newsContentPane.setText(
				"<html><body><H1>留守儿童与回家父母手拉手</H1><span>2014-01-29</span>&nbsp;&nbsp;<span>光明日报(数字报)</span><br/><span>06,教科新闻</span><P>信息来源於四川新闻网 / Cited from http://www.newssc.org/<P>□川报集团特派记者 王小玲本报记者 黄泽君<P>【基层即景】<P>小区绿意盎然，地面不见一点垃圾，楼道扶梯干乾净净……11月13日，记者走进成都市锦江区双桂路街道五福桥社区江东民居时，不少居民正围在一起聊天。生活环境改善了，有问题能很快得到解决。居民龙先勇觉得，自从社会管理创新以来，日子越过越舒心了。<P>成立院落党支部、鼓励居民成为志愿者服务社区、实行“以院养院”的物管自治机制……社区党委书记姚艳洪介绍，目前五福桥社区一共成立了11个社会组织，注册志愿者多达1500余名，全部由本社区居民组成，在养老、物管、社区自治等方面为居民提供服务。<P>生活满意度越来越高，生活环境越来越好，这是五福桥社区居民近年来最大的感受。大家希望，能有更多的利好政策倾向基层，倾向社区。<P>【会场连线】<P>嘉宾：李向志 十八大代表、德阳市委书记丁爱谱 十八大代表、攀枝花市东区长寿路街道健康路社区党员<P>畅通社区工作人员成长渠道<P>社区党委书记姚艳洪：希望把人、财、物等资源更多地配置到社区，进一步提高社区工作人员的素质和待遇。<P>李向志：社会管理是个系统工程，在实践中要从城乡社区抓起，夯实基层基础，坚持重心下移、服务下延、工作下沉。作为全国社会管理创新试点城市之一，德阳近两年来做了一些探索，我们将社区人员基本报酬、办公经费纳入财政预算，并建立了自然增长机制。同时，加强社区队伍建设，每个社区居委会都配备了5至9名工作人员，并聘请社区综合服务协管员，招录大学生加入社区管理队伍，加大在社区工作人员中优先发展党员、招录公务员、选拔基层领导干部的工作力度，进一步畅通了社区工作人员的成长渠道。建议国家建立社会管理工作投入保障机制，特别是建立面向基层 “费随事转”的转移支付机制，为加强和创新社会管理提供有力保障。<P>整合辖区资源应有长效机制<P>居民黄家发：现在社区每周都会开展活动，这让我们的社区更加和谐，但有时会出现组织经费不足的情况，希望能有更多资金和政策支持。<P>丁爱谱：十八大报告提出要强化企事业单位、人民团体在社会管理和服务中的职责，引导社会组织健康有序发展，发挥群衆参与社会管理的基础作用。作为一名普通的党员，我在20多年的社区服务工作中，深有同感。目前，尽管我们在整合辖区资源、协调居民参与上做了一些尝试，但是仍然缺乏以制度固定下来的资源整合长效机制。如果能够建立一种社区牵头、辖区企业参与、百姓受益的政策或工作导向，使社区与辖区单位的关系能制度化、规范化，社区的各项社会工作就会更容易开展，效果也会好很多。<P>让群衆在社区就能办好事<P>居民胡延碧：我们希望有更多与民生相关的公共服务，能够深入基层，深入社区。<P>李向志：十八大报告提出要改进政府提供公共服务方式，加强基层社会管理和服务体系建设，增强城乡社区服务功能。我们要坚持把服务群衆作为社区建设的核心和关键，不断完善社区服务体系，切实解决民生问题。一方面要完善政务服务平台，将党务服务、劳动就业、社会保障等服务职能从街道（乡镇）便民服务中心向社区延伸，真正实现群衆在社区就能办所有事。另一方面，也要改进我们的社区服务方式，德阳这两年向社区群衆免费发放服务联系卡，建立困难群衆情况登记制度，社区工作人员定期主动上门探访空巢老人、残疾人、留守儿童等，帮助解决实际困难，我认为这些都是行之有效的办法。<P>（本报北京11月13日电） </P></body></html>");

		JScrollPane newsDetailContentPanel = new NewsScrollPane(newsContentPane);
		newsDetailContentPanel.setBackground(Color.WHITE);
		Border newsDetailContentBorder = new CompoundBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0),
				BorderFactory.createLineBorder(Color.BLACK, 1));
		newsDetailContentPanel.setBorder(newsDetailContentBorder);
		showNewsDetailMainPanel.add(newsDetailContentPanel, BorderLayout.CENTER);

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

		// 统计界面主面板的容器
		gbc.gridwidth = 8;
		JPanel statisticsFigurePanel = new JPanel();
		statisticsFigurePanel.setBackground(Color.WHITE);
		gb.setConstraints(statisticsFigurePanel, gbc);
		statisticsPanel.add(statisticsFigurePanel);

		JPanel statisticsSelectMode = new JPanel();
		statisticsSelectMode.setLayout(new GridLayout(1, 6));
		for (int i = 0; i < 4; i++) {
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			statisticsSelectMode.add(temp);
		}
		TagButtonGroup modeSelectGroup = new TagButtonGroup();
		JButton trendStatistics = new JButton("趋势统计");
		modeSelectGroup.add(trendStatistics);
		statisticsSelectMode.add(trendStatistics);
		JButton tendencyComparison = new JButton("倾向性比较");
		modeSelectGroup.add(tendencyComparison);
		statisticsSelectMode.add(tendencyComparison);

		statisticsFigurePanel.setLayout(new BorderLayout());
		statisticsFigurePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		statisticsFigurePanel.add(statisticsSelectMode, BorderLayout.NORTH);

		// 放置统计图的面板（包括左右按钮）
		JPanel statisticsFigureLayout = new JPanel();
		statisticsFigureLayout.setBackground(Color.WHITE);
		statisticsFigureLayout.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0),
				BorderFactory.createLineBorder(Color.BLACK)));
		statisticsFigurePanel.add(statisticsFigureLayout, BorderLayout.CENTER);

		statisticsFigureLayout.setLayout(new BorderLayout());
		JPanel leftButtonPanel = new JPanel(new GridLayout(3, 1));
		leftButtonPanel.setBackground(Color.WHITE);
		JPanel leftTempPanel = new JPanel();
		leftTempPanel.setBackground(Color.WHITE);
		leftButtonPanel.add(leftTempPanel);
		JButton leftButton = new ShiftButton("fa-chevron-left", 20, "");
		leftButtonPanel.add(leftButton);
		statisticsFigureLayout.add(leftButtonPanel, BorderLayout.WEST);
		JPanel rightButtonPanel = new JPanel(new GridLayout(3, 1));
		rightButtonPanel.setBackground(Color.WHITE);
		JPanel rightTempPanel = new JPanel();
		rightTempPanel.setBackground(Color.WHITE);
		rightButtonPanel.add(rightTempPanel);
		JButton rightButton = new ShiftButton("fa-chevron-right", 20, "");
		rightButtonPanel.add(rightButton);
		statisticsFigureLayout.add(rightButtonPanel, BorderLayout.EAST);

		JPanel statisticsFigureContent = new JPanel();
		final CardLayout figureCardLayout = new CardLayout();
		statisticsFigureContent.setLayout(figureCardLayout);
		statisticsFigureLayout.add(statisticsFigureContent, BorderLayout.CENTER);

		// 趋势统计面板
		JPanel trendStatisticsPanel = new JPanel();
		trendStatisticsPanel.setLayout(new BorderLayout());
		statisticsFigureContent.add("趋势统计", trendStatisticsPanel);

		// 倾向性比较面板
		JPanel tendencyComparisonPanel = new JPanel();
		tendencyComparisonPanel.setLayout(new BorderLayout());
		statisticsFigureContent.add("倾向性比较", tendencyComparisonPanel);

		StatisticsGetter.init();
		trendStatisticsPanel.add(StatisticsGetter.getOldBarChartPanel(), BorderLayout.CENTER);
		tendencyComparisonPanel.add(StatisticsGetter.getOldPieChartPanel(), BorderLayout.CENTER);

		// 设置向左按钮点击事件
		leftButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("统计界面-点击向左切换按钮");
				StatisticsGetter.changePreviousNewspaper();
				if (!StatisticsGetter.isTendencyComparison) {
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				} else {
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getPieChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});

		// 设置向右点击事件
		rightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("统计界面-点击向右切换按钮");
				StatisticsGetter.changeNextNewspaper();
				if (!StatisticsGetter.isTendencyComparison) {
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				} else {
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getPieChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});

		// 趋势统计点击事件
		trendStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("点击趋势统计按钮");
				if (StatisticsGetter.isTendencyComparison) {
					StatisticsGetter.setIsTendencyComparison(false);
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				}
			}
		});

		// 倾向性比较点击事件
		tendencyComparison.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("点击倾向性比较按钮");
				if (!StatisticsGetter.isTendencyComparison) {
					StatisticsGetter.setIsTendencyComparison(true);
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getPieChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});

		// 添加主标签点击事件
		for (int i = 0; i < 9; i++) {
			statisticsMainTags.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							logger.debug("统计界面-点击主标签" + j);
							statisticsSubTagsLayout.show(statisticsSubTags, Integer.toString(j));
							// 清除选中状态
							statisticsSubTagsBtnGroup[j].clearSelection();
							StatisticsGetter.setSelectedMainTag(j);
							if (StatisticsGetter.isTendencyComparison) {
								tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
								tendencyComparisonPanel.add(StatisticsGetter.getPieChartPanel());
								tendencyComparisonPanel.updateUI();
							} else {
								trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
								trendStatisticsPanel.add(StatisticsGetter.getDefaultBarChartPanel());
								trendStatisticsPanel.updateUI();
							}
						}
					}
				}
			});
		}

		// 添加子标签点击事件
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < subTagsText[i].length; j++) {
				statisticsSubTagsBtnGroup[i].get(j).addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int j = 0; j < subTagsText[StatisticsGetter.getSelectedMainTag()].length; j++) {
							if (e.getActionCommand().equals(subTagsText[StatisticsGetter.getSelectedMainTag()][j])) {
								logger.debug("统计界面-点击子标签" + j);
								StatisticsGetter.setSelectedSubTag(j);
								if (!StatisticsGetter.isTendencyComparison) {
									trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
									trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
									trendStatisticsPanel.updateUI();
								}
							}
						}
					}
				});
			}
		}

		// 回收站界面
		recyclePanel.setLayout(new BorderLayout());

		Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
		Object[] recycleNewsColumnTitle = { "标题", "操作" };
		DefaultTableModel recycleNewsModel = new DefaultTableModel(); // 新建一个默认数据模型
		JTable recycleNewsTable = new NewsTable(recycleNewsModel);
		recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
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

		// 表格点击事件
		recycleNewsTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable src = null;
				if (e.getSource() instanceof JTable) {
					src = (JTable) e.getSource();
					if (src.getSelectedColumn() == 1) {
						Object[][] recycleNewsTableData = DeletedNewsGetter.restore(src.getSelectedRow());
						Object[] recycleNewsColumnTitle = { "标题", "操作" };
						recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
						recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
						// 设置滚动条滚动到顶部
						JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
						if (recycleNewsScrollBar != null) {
							recycleNewsScrollBar.setValue(0);
						}
					}
				}
			}
		});

		// 点击首页事件
		recycleNewsFirstButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("删除界面-点击首页按钮");
				DeletedNewsGetter.init();
				Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
				Object[] recycleNewsColumnTitle = { "标题", "操作" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// 设置滚动条滚动到顶部
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if (recycleNewsScrollBar != null) {
					recycleNewsScrollBar.setValue(0);
				}
			}
		});

		// 点击上一页事件
		recycleNewsPreviousButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("删除界面-点击上一页按钮");
				Object[][] recycleNewsTableData = DeletedNewsGetter.getPreviousNews();
				Object[] recycleNewsColumnTitle = { "标题", "操作" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// 设置滚动条滚动到顶部
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if (recycleNewsScrollBar != null) {
					recycleNewsScrollBar.setValue(0);
				}
			}
		});

		// 点击下一页事件
		recycleNewsNextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("删除界面-点击下一页按钮");
				Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
				Object[] recycleNewsColumnTitle = { "标题", "操作" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// 设置滚动条滚动到顶部
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if (recycleNewsScrollBar != null) {
					recycleNewsScrollBar.setValue(0);
				}
			}
		});

		// 点击尾页事件
		recycleNewsLastButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.debug("删除界面-点击尾页按钮");
				Object[][] recycleNewsTableData = DeletedNewsGetter.getLastNews();
				Object[] recycleNewsColumnTitle = { "标题", "操作" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// 设置滚动条滚动到顶部
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if (recycleNewsScrollBar != null) {
					recycleNewsScrollBar.setValue(0);
				}
			}
		});

		// 为tab栏按钮设置点击事件
		ActionListener tabListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "首页":
					logger.debug("点击首页");
					cardLayout.show(pagePanel, "首页");
					firstCardLayout.show(firstCardPanel, "欢迎信息");
					break;
				case "新闻":
					logger.debug("点击新闻");
					cardLayout.show(pagePanel, "新闻");
					showNewsCardLayout.show(showNewsPanel, "新闻列表");
					// showNewsTable.clearSelection();
					NewsGetter.init();
					Object[][] showNewsTableData = NewsGetter.getNews();
					Object[] showNewsColumnTitle = { "标题" };
					showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
					showNewsTable.clearSelection();
					// 设置滚动条滚动到顶部
					JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
					if (showNewsScrollBar != null) {
						showNewsScrollBar.setValue(0);
					}
					pageEditBox.setText(Integer.toString(NewsGetter.getPage()));
					break;
				case "统计":
					logger.debug("点击统计");
					cardLayout.show(pagePanel, "统计");
					// recycleNewsTable.clearSelection();
					statisticsMainTags.select(0);
					// 显示第一个主标签对应子标签
					statisticsSubTagsLayout.show(statisticsSubTags, "0");
					// 清除选中状态
					statisticsSubTagsBtnGroup[0].clearSelection();
					// 设置模式选择按钮选中第一个
					modeSelectGroup.select(0);
					figureCardLayout.show(statisticsFigureContent, "趋势统计");
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					StatisticsGetter.init();
					trendStatisticsPanel.add(StatisticsGetter.getOldBarChartPanel(), BorderLayout.CENTER);
					tendencyComparisonPanel.add(StatisticsGetter.getOldPieChartPanel(), BorderLayout.CENTER);
					break;
				case "回收站":
					logger.debug("点击回收站");
					cardLayout.show(pagePanel, "回收站");
					DeletedNewsGetter.init();
					Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
					Object[] recycleNewsColumnTitle = { "标题", "操作" };
					recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
					recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
					recycleNewsTable.clearSelection();
					// 设置滚动条滚动到顶部
					JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
					if (recycleNewsScrollBar != null) {
						recycleNewsScrollBar.setValue(0);
					}
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

					NewsGetter.setSelected(src.getSelectedRow());
					newsContentPane.setText(NewsGetter.getSelectedContent());

					// 设置主标签默认选中第一个
					newsDetailMainTagsGroup.select(0);

					// 设置子标签面板切换到第一个
					newsDetailSubTagsCardLayout.show(newsDetailSubTagsCardPanel, "0");
					NewsGetter.setSelectedMainTag(0);
					int temp = NewsGetter.getSelectedSubTag();
					if (temp != -1) {
						newsDetailSubTagsBtnGroup[0].select(temp);
					} else {
						newsDetailSubTagsBtnGroup[0].clearSelection();
					}

					newsContentPane.setCaretPosition(0);

					showNewsCardLayout.show(showNewsPanel, "新闻详细内容");
				}
			}
		});

		// 设置 趋势统计 和 倾向性比较 按钮点击事件
		trendStatistics.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				figureCardLayout.show(statisticsFigureContent, "趋势统计");
			}
		});

		tendencyComparison.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				figureCardLayout.show(statisticsFigureContent, "倾向性比较");
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
