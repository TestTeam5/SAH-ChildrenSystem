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
		frame = new JFrame("���ض�ͯ�������ϵͳ");
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ����tab��
		JPanel tabPanel = new JPanel();
		tabPanel.setLayout(new GridLayout(1, 4, 0, 0));

		JButton firstPageBtn = new JButton("��ҳ");
		tabPanel.add(firstPageBtn);
		JButton showNewsBtn = new JButton("����");
		tabPanel.add(showNewsBtn);
		JButton statisticsBtn = new JButton("ͳ��");
		tabPanel.add(statisticsBtn);
		JButton recycleBtn = new JButton("����վ");
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
		showNewsPanel.setLayout(new BorderLayout());
		
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
		newsList.setListData(news);
		//newsList.setAlignmentY((float)0.5);
		showNewsPanel.add(newsList, BorderLayout.CENTER);
		
		JPanel newsBottomButtons = new JPanel();
		JButton newsFirstButton = new JButton("��ҳ");
		newsBottomButtons.add(newsFirstButton);
		JButton newsPreviousButton = new JButton("��һҳ");
		newsBottomButtons.add(newsPreviousButton);
		JButton newsNextButton = new JButton("��һҳ");
		newsBottomButtons.add(newsNextButton);
		JButton newsLastButton = new JButton("βҳ");
		newsBottomButtons.add(newsLastButton);
		showNewsPanel.add(newsBottomButtons, BorderLayout.SOUTH);

		// ͳ�ƽ���
		statisticsPanel.setLayout(new BorderLayout());
		
		JPanel mainTags = new JPanel();
		mainTags.setLayout(new GridLayout(1, 9));
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
		recyclePanel.add(recycleNewsList, BorderLayout.CENTER);
		
		JPanel recycleNewsBottomButtons = new JPanel();
		JButton recycleNewsFirstButton = new JButton("��ҳ");
		recycleNewsBottomButtons.add(recycleNewsFirstButton);
		JButton recycleN = new JButton("��һҳ");
		recycleNewsBottomButtons.add(newsPreviousButton);
		JButton recycleNewsNextButton = new JButton("��һҳ");
		recycleNewsBottomButtons.add(recycleNewsNextButton);
		JButton recycleNewsLastButton = new JButton("βҳ");
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
