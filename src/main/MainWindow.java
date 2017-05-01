package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

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
		frame = new JFrame("留守儿童舆情调查系统");
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 顶部tab栏
		JPanel tabPanel = new JPanel();
		tabPanel.setLayout(new GridLayout(1, 4, 0, 0));

		JButton firstPageBtn = new JButton("首页");
		tabPanel.add(firstPageBtn);
		JButton showNewsBtn = new JButton("新闻");
		tabPanel.add(showNewsBtn);
		JButton statisticsBtn = new JButton("统计");
		tabPanel.add(statisticsBtn);
		JButton recycleBtn = new JButton("回收站");
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
		showNewsPanel.setLayout(new BorderLayout());
		
		String[] news = new String[]{
				"青少年“身边最让我感动的人”评选揭晓",
				"确保农民工子女享受国家政策",
				"全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...",
				"不能忽视农村留守儿童",
				"对农村“留守儿童”问题的理性思考",
				"好政策播种新希望——记湖北省农村教师资助行动计划",
				"和谐社会视野下的教育公平",
				"农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战",
				"农村留守儿童教育亟待加强",
				"17部委领导慰问在京流动人口子女和农村留守儿童",
				"江苏“七彩夏日”点亮精彩暑假",
				"高校：新农村建设的生力军——华中师大服务新农村侧记",
				"中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童",
				"加大经费保障提供重点扶持福建基础教育资源向农村倾斜",
				"分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”"};
		JList newsList = new JList();
		newsList.setListData(news);
		//newsList.setAlignmentY((float)0.5);
		showNewsPanel.add(newsList, BorderLayout.CENTER);
		
		JPanel newsBottomButtons = new JPanel();
		JButton newsFirstButton = new JButton("首页");
		newsBottomButtons.add(newsFirstButton);
		JButton newsPreviousButton = new JButton("上一页");
		newsBottomButtons.add(newsPreviousButton);
		JButton newsNextButton = new JButton("下一页");
		newsBottomButtons.add(newsNextButton);
		JButton newsLastButton = new JButton("尾页");
		newsBottomButtons.add(newsLastButton);
		showNewsPanel.add(newsBottomButtons, BorderLayout.SOUTH);

		// 统计界面
		statisticsPanel.setLayout(new BorderLayout());
		
		JPanel mainTags = new JPanel();
		mainTags.setLayout(new GridLayout(1, 9));
		JButton paperType = new JButton("报纸类型");
		mainTags.add(paperType);
		JButton newsType = new JButton("新闻类型");
		mainTags.add(newsType);
		JButton topic = new JButton("报道主题");
		mainTags.add(topic);
		JButton source = new JButton("新闻来源");
		mainTags.add(source);
		JButton media = new JButton("媒介形象");
		mainTags.add(media);
		JButton helpType = new JButton("帮助类新闻种类");
		mainTags.add(helpType);
		JButton newsPeople = new JButton("帮助类新闻主体");
		mainTags.add(newsPeople);
		JButton honourPeople = new JButton("表彰对象");
		mainTags.add(honourPeople);
		JButton cantReason = new JButton("无法在城市读书原因");
		mainTags.add(cantReason);
		statisticsPanel.add(mainTags, BorderLayout.NORTH);
		
		JPanel subTags = new JPanel();
		
		// 回收站界面
		recyclePanel.setLayout(new BorderLayout());
		
		String[] recycleNews = new String[]{
				"青少年“身边最让我感动的人”评选揭晓",
				"确保农民工子女享受国家政策",
				"全国政协十届四次会议举行第三次全体会议贾庆林出席12位委员围绕科教文衞等社会事业...",
				"不能忽视农村留守儿童",
				"对农村“留守儿童”问题的理性思考",
				"好政策播种新希望——记湖北省农村教师资助行动计划",
				"和谐社会视野下的教育公平",
				"农村学生辍学问题受关注周济坦言新形势下义务教育工作面临新挑战",
				"农村留守儿童教育亟待加强",
				"17部委领导慰问在京流动人口子女和农村留守儿童",
				"江苏“七彩夏日”点亮精彩暑假",
				"高校：新农村建设的生力军——华中师大服务新农村侧记",
				"中国人民公安大学治安系教授王太元：就地解决还是增加留守儿童",
				"加大经费保障提供重点扶持福建基础教育资源向农村倾斜",
				"分成十个课题组走访八十三个村安徽大学百名学生撰写“新农村档案”"};
		JList recycleNewsList = new JList();
		recycleNewsList.setListData(recycleNews);
		recyclePanel.add(recycleNewsList, BorderLayout.CENTER);
		
		JPanel recycleNewsBottomButtons = new JPanel();
		JButton recycleNewsFirstButton = new JButton("首页");
		recycleNewsBottomButtons.add(recycleNewsFirstButton);
		JButton recycleN = new JButton("上一页");
		recycleNewsBottomButtons.add(newsPreviousButton);
		JButton recycleNewsNextButton = new JButton("下一页");
		recycleNewsBottomButtons.add(recycleNewsNextButton);
		JButton recycleNewsLastButton = new JButton("尾页");
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
					break;
				case "统计":
					cardLayout.show(pagePanel, "统计");
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

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
