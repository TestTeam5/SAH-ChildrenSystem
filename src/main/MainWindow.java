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
		InitGlobalFont(new Font("΢���ź�", Font.PLAIN, 12)); // ͳһ��������

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
		
		Object[][] showNewsTableData = {
				new Object[]{"�����ꡰ��������Ҹж����ˡ���ѡ����"},
				new Object[]{"ȷ��ũ����Ů���ܹ�������"},
				new Object[]{"ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ..."},
				new Object[]{"���ܺ���ũ�����ض�ͯ"},
				new Object[]{"��ũ�塰���ض�ͯ�����������˼��"},
				new Object[]{"�����߲�����ϣ�������Ǻ���ʡũ���ʦ�����ж��ƻ�"},
				new Object[]{"��г�����Ұ�µĽ�����ƽ"},
				new Object[]{"ũ��ѧ���ѧ�����ܹ�ע�ܼ�̹�����������������������������ս"},
				new Object[]{"ũ�����ض�ͯ����ؽ����ǿ"},
				new Object[]{"17��ί�쵼ο���ھ������˿���Ů��ũ�����ض�ͯ"},
				new Object[]{"���ա��߲����ա������������"},
				new Object[]{"��У����ũ�彨�����������������ʦ�������ũ����"},
				new Object[]{"�й����񹫰���ѧ�ΰ�ϵ������̫Ԫ���͵ؽ�������������ض�ͯ"},
				new Object[]{"�Ӵ󾭷ѱ����ṩ�ص���ָ�������������Դ��ũ����б"}, 
				new Object[]{"�ֳ�ʮ���������߷ð�ʮ�����尲�մ�ѧ����ѧ��׫д����ũ�嵵����"},
				new Object[]{"��ѧ��������Ϊ����ˮ����Ӣ������"},
				new Object[]{"ũ�����ض�ͯ������ذ�"},
				new Object[]{"־Ը����������лĻ���ഺ֮�衪�����人��ѧ����Сͤ�¼����Ĺ۲��놙ʾ"},
				new Object[]{"�����˴��ڡ�����ʮ����"},
				new Object[]{"��������ͬ�������������"},
				new Object[]{"����ũ������ʵ��ȫ����"},
				new Object[]{"����ũ��������Դ�ٽ�����ͳ�﷢չ"},
				new Object[]{"ȫ���������˿�ģ��ѡ�˼��"},
				new Object[]{"ȫ���������˿�ģ��ѡ�˼�飨�Ͻ�6�棩"},
				new Object[]{"���У����ӵ���ů��԰�����Ϸ�ȫ���ذ�δ�����˵Ĺ���"}
		};
		Object[] showNewsColumnTitle = {"����"};
		JTable showNewsTable = new NewsTable(showNewsTableData, showNewsColumnTitle);
		
		JScrollPane showNewsScrollPane = new NewsScrollPane(showNewsTable);
		Border showNewsScrollBorder = new CompoundBorder(
				new CompoundBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20),
						BorderFactory.createLineBorder(Color.BLACK, 1)),
				BorderFactory.createEmptyBorder(20, 25, 20, 25));
		showNewsScrollPane.setBorder(showNewsScrollBorder);
		showNewsListPanel.add(showNewsScrollPane, BorderLayout.CENTER);

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

		Border statisticsBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		statisticsPanel.setBorder(statisticsBorder);
		statisticsPanel.setBackground(Color.WHITE);
		
		JPanel mainTags = new JPanel();
		mainTags.setLayout(new GridLayout(1, 9));
		mainTags.setBackground(Color.WHITE);
		JButton paperType = new MainTagsButton("��ֽ����");
		mainTags.add(paperType);
		JButton newsType = new MainTagsButton("��������");
		mainTags.add(newsType);
		JButton topic = new MainTagsButton("��������");
		mainTags.add(topic);
		JButton source = new MainTagsButton("������Դ");
		mainTags.add(source);
		JButton media = new MainTagsButton("ý������");
		mainTags.add(media);
		JButton helpType = new MainTagsButton("��������������");
		mainTags.add(helpType);
		JButton newsPeople = new MainTagsButton("��������������");
		mainTags.add(newsPeople);
		JButton honourPeople = new MainTagsButton("���ö���");
		mainTags.add(honourPeople);
		JButton cantReason = new MainTagsButton("�޷��ڳ��ж���");
		mainTags.add(cantReason);
		statisticsPanel.add(mainTags, BorderLayout.NORTH);

		JPanel subTags = new JPanel();

		// ����վ����
		recyclePanel.setLayout(new BorderLayout());
		
		Object[][] recycleNewsTableData = {
				new Object[]{"�����ꡰ��������Ҹж����ˡ���ѡ����", "��ԭ"},
				new Object[]{"ȷ��ũ����Ů���ܹ�������", "��ԭ"},
				new Object[]{"ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ...", "��ԭ"},
				new Object[]{"���ܺ���ũ�����ض�ͯ", "��ԭ"},
				new Object[]{"��ũ�塰���ض�ͯ�����������˼��", "��ԭ"},
				new Object[]{"�����߲�����ϣ�������Ǻ���ʡũ���ʦ�����ж��ƻ�", "��ԭ"},
				new Object[]{"��г�����Ұ�µĽ�����ƽ", "��ԭ"},
				new Object[]{"ũ��ѧ���ѧ�����ܹ�ע�ܼ�̹�����������������������������ս", "��ԭ"},
				new Object[]{"ũ�����ض�ͯ����ؽ����ǿ", "��ԭ"},
				new Object[]{"17��ί�쵼ο���ھ������˿���Ů��ũ�����ض�ͯ", "��ԭ"},
				new Object[]{"���ա��߲����ա������������", "��ԭ"},
				new Object[]{"��У����ũ�彨�����������������ʦ�������ũ����", "��ԭ"},
				new Object[]{"�й����񹫰���ѧ�ΰ�ϵ������̫Ԫ���͵ؽ�������������ض�ͯ", "��ԭ"},
				new Object[]{"�Ӵ󾭷ѱ����ṩ�ص���ָ�������������Դ��ũ����б", "��ԭ"}, 
				new Object[]{"�ֳ�ʮ���������߷ð�ʮ�����尲�մ�ѧ����ѧ��׫д����ũ�嵵����", "��ԭ"},
				new Object[]{"��ѧ��������Ϊ����ˮ����Ӣ������", "��ԭ"},
				new Object[]{"ũ�����ض�ͯ������ذ�", "��ԭ"},
				new Object[]{"־Ը����������лĻ���ഺ֮�衪�����人��ѧ����Сͤ�¼����Ĺ۲��놙ʾ", "��ԭ"},
				new Object[]{"�����˴��ڡ�����ʮ����", "��ԭ"},
				new Object[]{"��������ͬ�������������", "��ԭ"},
				new Object[]{"����ũ������ʵ��ȫ����", "��ԭ"},
				new Object[]{"����ũ��������Դ�ٽ�����ͳ�﷢չ", "��ԭ"},
				new Object[]{"ȫ���������˿�ģ��ѡ�˼��", "��ԭ"},
				new Object[]{"ȫ���������˿�ģ��ѡ�˼�飨�Ͻ�6�棩", "��ԭ"},
				new Object[]{"���У����ӵ���ů��԰�����Ϸ�ȫ���ذ�δ�����˵Ĺ���", "��ԭ"}
		};
		Object[] recycleNewsColumnTitle = {"����", "����"};
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
					showNewsCardLayout.show(showNewsPanel, "�����б�");
					showNewsTable.clearSelection();
					break;
				case "ͳ��":
					cardLayout.show(pagePanel, "ͳ��");
					recycleNewsTable.clearSelection();
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
		showNewsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable src = null;
				if (e.getSource() instanceof JTable) {
					src = (JTable) e.getSource();
					showNewsCardLayout.show(showNewsPanel, "������ϸ����");
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
