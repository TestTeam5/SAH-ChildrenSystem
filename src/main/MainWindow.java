package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import widget.MainTagsButton;
import widget.NewsScrollPane;
import widget.NewsTable;
import widget.PageSelectButton;
import widget.TabButton;

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
		
		Object[][] showNewsTableData = {
				new Object[]{"青少年“身边最让我感动的人”评选揭晓"},
				new Object[]{"确保农民工子女享受国家政策"},
				new Object[]{"全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业..."},
				new Object[]{"不能忽视农村留守儿童"},
				new Object[]{"对农村“留守儿童”问题的理性思考"},
				new Object[]{"好政策播种新希望——记湖北省农村教师资助行动计划"},
				new Object[]{"和谐社会视野下的教育公平"},
				new Object[]{"农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战"},
				new Object[]{"农村留守儿童教育亟待加强"},
				new Object[]{"17部委领导慰问在京流动人口子女和农村留守儿童"},
				new Object[]{"江苏“七彩夏日”点亮精彩暑假"},
				new Object[]{"高校：新农村建设的生力军——华中师大服务新农村侧记"},
				new Object[]{"中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童"},
				new Object[]{"加大经费保障提供重点扶持福建基础教育资源向农村倾斜"}, 
				new Object[]{"分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”"},
				new Object[]{"大学生耿高鹏为救落水少年英勇献身"},
				new Object[]{"农村留守儿童心理需关爱"},
				new Object[]{"志愿服务，是永不谢幕的青春之歌——对武汉大学“赵小亭事迹”的观察与啓示"},
				new Object[]{"老艺人传授“惠洋十音”"},
				new Object[]{"“三进三同”体验国情民生"},
				new Object[]{"江苏农家书屋实现全覆盖"},
				new Object[]{"开发农村人力资源促进城乡统筹发展"},
				new Object[]{"全国教书育人楷模候选人简介"},
				new Object[]{"全国教书育人楷模候选人简介（上接6版）"},
				new Object[]{"城市，孩子的温暖家园——合肥全社会关爱未成年人的故事"}
		};
		Object[] showNewsColumnTitle = {"标题"};
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
		showNewsPanel.add("新闻详细内容", showNewsDetailPanel);

		JPanel newsDetailMainTags = new JPanel();
		newsDetailMainTags.setLayout(new GridLayout(1, 9));
		JButton newsDetailPaperType = new JButton("报纸类型");
		newsDetailMainTags.add(newsDetailPaperType);
		JButton newsDetailNewsType = new JButton("新闻类型");
		newsDetailMainTags.add(newsDetailNewsType);
		JButton newsDetailTopic = new JButton("报道主题");
		newsDetailMainTags.add(newsDetailTopic);
		JButton newsDetailSource = new JButton("新闻来源");
		newsDetailMainTags.add(newsDetailSource);
		JButton newsDetailMedia = new JButton("媒介形象");
		newsDetailMainTags.add(newsDetailMedia);
		JButton newsDetailHelpType = new JButton("帮助类新闻种类");
		newsDetailMainTags.add(newsDetailHelpType);
		JButton newsDetailNewsPeople = new JButton("帮助类新闻主体");
		newsDetailMainTags.add(newsDetailNewsPeople);
		JButton newsDetailHonourPeople = new JButton("表彰对象");
		newsDetailMainTags.add(newsDetailHonourPeople);
		JButton newsDetailCantReason = new JButton("无法在城市读书原因");
		newsDetailMainTags.add(newsDetailCantReason);
		showNewsDetailPanel.add(newsDetailMainTags, BorderLayout.NORTH);

		// 统计界面
		statisticsPanel.setLayout(new BorderLayout());

		Border statisticsBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		statisticsPanel.setBorder(statisticsBorder);
		statisticsPanel.setBackground(Color.WHITE);
		
		JPanel mainTags = new JPanel();
		mainTags.setLayout(new GridLayout(1, 9));
		mainTags.setBackground(Color.WHITE);
		JButton paperType = new MainTagsButton("报纸类型");
		mainTags.add(paperType);
		JButton newsType = new MainTagsButton("新闻类型");
		mainTags.add(newsType);
		JButton topic = new MainTagsButton("报道主题");
		mainTags.add(topic);
		JButton source = new MainTagsButton("新闻来源");
		mainTags.add(source);
		JButton media = new MainTagsButton("媒介形象");
		mainTags.add(media);
		JButton helpType = new MainTagsButton("帮助类新闻种类");
		mainTags.add(helpType);
		JButton newsPeople = new MainTagsButton("帮助类新闻主体");
		mainTags.add(newsPeople);
		JButton honourPeople = new MainTagsButton("表彰对象");
		mainTags.add(honourPeople);
		JButton cantReason = new MainTagsButton("无法在城市读书");
		mainTags.add(cantReason);
		statisticsPanel.add(mainTags, BorderLayout.NORTH);

		JPanel subTags = new JPanel();

		// 回收站界面
		recyclePanel.setLayout(new BorderLayout());
		
		Object[][] recycleNewsTableData = {
				new Object[]{"青少年“身边最让我感动的人”评选揭晓", "还原"},
				new Object[]{"确保农民工子女享受国家政策", "还原"},
				new Object[]{"全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...", "还原"},
				new Object[]{"不能忽视农村留守儿童", "还原"},
				new Object[]{"对农村“留守儿童”问题的理性思考", "还原"},
				new Object[]{"好政策播种新希望——记湖北省农村教师资助行动计划", "还原"},
				new Object[]{"和谐社会视野下的教育公平", "还原"},
				new Object[]{"农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战", "还原"},
				new Object[]{"农村留守儿童教育亟待加强", "还原"},
				new Object[]{"17部委领导慰问在京流动人口子女和农村留守儿童", "还原"},
				new Object[]{"江苏“七彩夏日”点亮精彩暑假", "还原"},
				new Object[]{"高校：新农村建设的生力军——华中师大服务新农村侧记", "还原"},
				new Object[]{"中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童", "还原"},
				new Object[]{"加大经费保障提供重点扶持福建基础教育资源向农村倾斜", "还原"}, 
				new Object[]{"分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”", "还原"},
				new Object[]{"大学生耿高鹏为救落水少年英勇献身", "还原"},
				new Object[]{"农村留守儿童心理需关爱", "还原"},
				new Object[]{"志愿服务，是永不谢幕的青春之歌——对武汉大学“赵小亭事迹”的观察与啓示", "还原"},
				new Object[]{"老艺人传授“惠洋十音”", "还原"},
				new Object[]{"“三进三同”体验国情民生", "还原"},
				new Object[]{"江苏农家书屋实现全覆盖", "还原"},
				new Object[]{"开发农村人力资源促进城乡统筹发展", "还原"},
				new Object[]{"全国教书育人楷模候选人简介", "还原"},
				new Object[]{"全国教书育人楷模候选人简介（上接6版）", "还原"},
				new Object[]{"城市，孩子的温暖家园——合肥全社会关爱未成年人的故事", "还原"}
		};
		Object[] recycleNewsColumnTitle = {"标题", "操作"};
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
