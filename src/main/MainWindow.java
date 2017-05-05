package main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FontUIResource;

import widget.FontAwesome;
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
		InitGlobalFont(new Font("΢���ź�", Font.PLAIN, 12));  //ͳһ��������
		
		frame = new JFrame("���ض�ͯ�������ϵͳ");
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ����tab��
		JPanel tabPanel = new JPanel();
		tabPanel.setLayout(new GridLayout(1, 4, 0, 0));

		TabButton firstPageBtn = new TabButton("fa-home", 26, "��ҳ");
		firstPageBtn.setBackground(new Color(230, 230, 230));
		firstPageBtn.setForeground(Color.BLACK);
		firstPageBtn.setIcon(FontAwesome.getIcon("fa-home", 26, Color.BLACK));
		tabPanel.add(firstPageBtn);
		TabButton showNewsBtn = new TabButton("fa-wpforms", 22, "����");
		tabPanel.add(showNewsBtn);
		TabButton statisticsBtn = new TabButton("fa-line-chart", 22, "ͳ��");
		tabPanel.add(statisticsBtn);
		TabButton recycleBtn = new TabButton("fa-trash", 24, "����վ");
		tabPanel.add(recycleBtn);

		frame.getContentPane().add(tabPanel, BorderLayout.NORTH);

		// ����ҳ������
		JPanel pagePanel = new JPanel();

		final CardLayout cardLayout = new CardLayout();
		pagePanel.setLayout(cardLayout);

		JPanel firstPagePanel = new JPanel();
		pagePanel.add("��ҳ", firstPagePanel);
		JPanel showNewsPanel = new JPanel();
		pagePanel.add("����", showNewsPanel);
		JPanel statisticsPanel = new JPanel();
		pagePanel.add("ͳ��", statisticsPanel);
		JPanel recyclePanel = new JPanel();
		pagePanel.add("����վ", recyclePanel);

		frame.getContentPane().add(pagePanel, BorderLayout.CENTER);

		// ��ҳ
		JTextField firstWelcomeInfoText = new JTextField("��ӭʹ�����ض�ͯ�������ϵͳ");
		firstPagePanel.add(firstWelcomeInfoText, BorderLayout.CENTER);

		// ��ʾ����ҳ��
		final CardLayout showNewsCardLayout = new CardLayout();
		showNewsPanel.setLayout(showNewsCardLayout);
		
		// ��ʾ����ҳ��->�����б����
		JPanel showNewsListPanel = new JPanel();
		showNewsListPanel.setLayout(new BorderLayout());
		showNewsPanel.add("�����б�", showNewsListPanel);
		
		String[] news = new String[]{
				"�����ꡰ��������Ҹж����ˡ���ѡ����",
				"ȷ��ũ����Ů���ܹ�������",
				"ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ...",
				"���ܺ���ũ�����ض�ͯ",
				"��ũ�塰���ض�ͯ�����������˼��",
				"�����߲�����ϣ�������Ǻ���ʡũ���ʦ�����ж��ƻ�",
				"��г�����Ұ�µĽ�����ƽ",
				"ũ��ѧ���ѧ�����ܹ�ע�ܼ�̹�����������������������������ս",
				"ũ�����ض�ͯ����ؽ����ǿ",
				"17��ί�쵼ο���ھ������˿���Ů��ũ�����ض�ͯ",
				"���ա��߲����ա������������",
				"��У����ũ�彨�����������������ʦ�������ũ����",
				"�й����񹫰���ѧ�ΰ�ϵ������̫Ԫ���͵ؽ�������������ض�ͯ",
				"�Ӵ󾭷ѱ����ṩ�ص���ָ�������������Դ��ũ����б",
				"�ֳ�ʮ���������߷ð�ʮ�����尲�մ�ѧ����ѧ��׫д����ũ�嵵����"};
		JList newsList = new JList();
		Border newsListBorder = new CompoundBorder(new CompoundBorder(BorderFactory.createEmptyBorder(25, 20, 35, 20),
				BorderFactory.createLineBorder(Color.BLACK, 1)), BorderFactory.createEmptyBorder(15, 25, 20, 25));
		newsList.setBorder(newsListBorder);
		newsList.setListData(news);
		newsList.setFixedCellHeight(25);
//		Border newsListBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20); 
//		newsList.setBorder(newsListBorder);
		newsList.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
		showNewsListPanel.add(newsList, BorderLayout.CENTER);
		
		
		JPanel newsBottomButtons = new JPanel();
		JButton newsFirstButton = new PageSelectButton("��ҳ");
		newsBottomButtons.add(newsFirstButton);
		JButton newsPreviousButton = new PageSelectButton("��һҳ");
		newsBottomButtons.add(newsPreviousButton);
		JButton newsNextButton = new PageSelectButton("��һҳ");
		newsBottomButtons.add(newsNextButton);
		JButton newsLastButton = new PageSelectButton("βҳ");
		newsBottomButtons.add(newsLastButton);
		showNewsListPanel.add(newsBottomButtons, BorderLayout.SOUTH);
		
		// ��ʾ���Ž�Ŀ->������ϸ���ݽ���
		JPanel showNewsDetailPanel = new JPanel();
		showNewsDetailPanel.setLayout(new BorderLayout());
		showNewsPanel.add("������ϸ����", showNewsDetailPanel);
		
		JPanel newsDetailMainTags = new JPanel();
		newsDetailMainTags.setLayout(new GridLayout(1, 9));
		newsDetailMainTags.setBackground(Color.WHITE);////
		JButton newsDetailPaperType = new JButton("��ֽ����");
		newsDetailMainTags.add(newsDetailPaperType);
		JButton newsDetailNewsType = new JButton("��������");
		newsDetailMainTags.add(newsDetailNewsType);
		JButton newsDetailTopic = new JButton("��������");
		newsDetailMainTags.add(newsDetailTopic);
		JButton newsDetailSource = new JButton("������Դ");
		newsDetailMainTags.add(newsDetailSource);
		JButton newsDetailMedia = new JButton("ý������");
		newsDetailMainTags.add(newsDetailMedia);
		JButton newsDetailHelpType = new JButton("��������������");
		newsDetailMainTags.add(newsDetailHelpType);
		JButton newsDetailNewsPeople = new JButton("��������������");
		newsDetailMainTags.add(newsDetailNewsPeople);
		JButton newsDetailHonourPeople = new JButton("���ö���");
		newsDetailMainTags.add(newsDetailHonourPeople);
		JButton newsDetailCantReason = new JButton("�޷��ڳ��ж���ԭ��");
		newsDetailMainTags.add(newsDetailCantReason);
		showNewsDetailPanel.add(newsDetailMainTags, BorderLayout.NORTH);
		
		// ͳ�ƽ���
		statisticsPanel.setLayout(new BorderLayout());
		
		JPanel mainTags = new JPanel();
		mainTags.setLayout(new GridLayout(1, 1));
		JButton paperType = new JButton("��ֽ����");
		mainTags.add(paperType);
		JButton newsType = new JButton("��������");
		mainTags.add(newsType);
		JButton topic = new JButton("��������");
		mainTags.add(topic);
		JButton source = new JButton("������Դ");
		mainTags.add(source);
		JButton media = new JButton("ý������");
		mainTags.add(media);
		JButton helpType = new JButton("��������������");
		mainTags.add(helpType);
		JButton newsPeople = new JButton("��������������");
		mainTags.add(newsPeople);
		JButton honourPeople = new JButton("���ö���");
		mainTags.add(honourPeople);
		JButton cantReason = new JButton("�޷��ڳ��ж���ԭ��");
		mainTags.add(cantReason);
		statisticsPanel.add(mainTags, BorderLayout.NORTH);
		
		JPanel subTags = new JPanel();
		
		// ����վ����
		recyclePanel.setLayout(new BorderLayout());
		
		String[] recycleNews = new String[]{
				"�����ꡰ��������Ҹж����ˡ���ѡ����",
				"ȷ��ũ����Ů���ܹ�������",
				"ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ...",
				"���ܺ���ũ�����ض�ͯ",
				"��ũ�塰���ض�ͯ�����������˼��",
				"�����߲�����ϣ�������Ǻ���ʡũ���ʦ�����ж��ƻ�",
				"��г�����Ұ�µĽ�����ƽ",
				"ũ��ѧ���ѧ�����ܹ�ע�ܼ�̹�����������������������������ս",
				"ũ�����ض�ͯ����ؽ����ǿ",
				"17��ί�쵼ο���ھ������˿���Ů��ũ�����ض�ͯ",
				"���ա��߲����ա������������",
				"��У����ũ�彨�����������������ʦ�������ũ����",
				"�й����񹫰���ѧ�ΰ�ϵ������̫Ԫ���͵ؽ�������������ض�ͯ",
				"�Ӵ󾭷ѱ����ṩ�ص���ָ�������������Դ��ũ����б",
				"�ֳ�ʮ���������߷ð�ʮ�����尲�մ�ѧ����ѧ��׫д����ũ�嵵����"};
		JList recycleNewsList = new JList();
		recycleNewsList.setListData(recycleNews);
		recycleNewsList.setFixedCellHeight(25);
		Border recycleListBorder = new CompoundBorder(new CompoundBorder(BorderFactory.createEmptyBorder(25, 20, 35, 20),
				BorderFactory.createLineBorder(Color.BLACK, 1)), BorderFactory.createEmptyBorder(15, 25, 20, 25));
		recycleNewsList.setBorder(recycleListBorder);
		recycleNewsList.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
		recyclePanel.add(recycleNewsList, BorderLayout.CENTER);
		
		JPanel recycleNewsBottomButtons = new JPanel();
		JButton recycleNewsFirstButton = new PageSelectButton("��ҳ");
		recycleNewsBottomButtons.add(recycleNewsFirstButton);
		JButton recycleNewsPreviousButton = new PageSelectButton("��һҳ");
		recycleNewsBottomButtons.add(recycleNewsPreviousButton);
		JButton recycleNewsNextButton = new PageSelectButton("��һҳ");
		recycleNewsBottomButtons.add(recycleNewsNextButton);
		JButton recycleNewsLastButton = new PageSelectButton("βҳ");
		recycleNewsBottomButtons.add(recycleNewsLastButton);
		recyclePanel.add(recycleNewsBottomButtons, BorderLayout.SOUTH);
		
		// Ϊtab����ť���õ���¼�
		ActionListener tabListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (e.getActionCommand()) {
				case "��ҳ":
					cardLayout.show(pagePanel, "��ҳ");
					break;
				case "����":
					cardLayout.show(pagePanel, "����");
					break;
				case "ͳ��":
					cardLayout.show(pagePanel, "ͳ��");
					break;
				case "����վ":
					cardLayout.show(pagePanel, "����վ");
					break;
				}
			}
		};
		firstPageBtn.addActionListener(tabListener);
		showNewsBtn.addActionListener(tabListener);
		statisticsBtn.addActionListener(tabListener);
		recycleBtn.addActionListener(tabListener);

		
		// ��������ʾ���Ž���->�����б���棬�����б����¼�
		newsList.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				showNewsCardLayout.show(showNewsPanel, "������ϸ����");
				// �˴�Ҫʵ����ʾ��ϸ���ݵ��߼�
			}
		});
		
		
//		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			SwingUtilities.updateComponentTreeUI(frame);
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
