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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import util.DeletedNewsGetter;
import util.Initializer;
import util.NewsGetter;
import util.StatisticsGetter;
import widget.BarChartFactory;
import widget.FontAwesome;
import widget.NewsScrollPane;
import widget.NewsTable;
import widget.PageSelectButton;
import widget.PieChartFactory;
import widget.ShiftButton;
import widget.TabButton;
import widget.TagButtonGroup;

public class MainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		// ��־���
		Logger logger = Logger.getLogger(MainWindow.class.getName());
		logger.debug("�����ʼ����ʼ");
		
		logger.debug("���ݳ�ʼ����ʼ");
		Initializer.initData();
		NewsGetter.init();
		DeletedNewsGetter.init();
		logger.debug("���ݳ�ʼ�����");
		
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
		logger.debug("�����ʼ�����");
		
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

		String[] mainTagsText = { "��ֽ���", "��������", "��������", "������Դ", "ý������", "��������������", "��������������", "���ö���", "�޷��ڳ��ж���ԭ��" };
		String[][] subTagsText = { { "����һ������", "ʡί���ر�", "���б�" }, { "��������", "�ظ�����д", "����", "����" },
				{ "������", "����Ϳ���", "����", "��������", "�������", "��ͯ����", "��������", "Ŭ���Ͻ�", "��ĸ����", "����" },
				{ "����", "����", "��ҵ", "��ҵ��λ", "��������", "ר��ѧ��", "�����쵼", "����" }, { "��������", "��������", "����Ҹ�", "�����ͯ", "����" },
				{ "һ���԰���", "���λ", "��ѿ���", "������Ŀ", "����" }, { "��������", "��ҵ", "��ҵ��λ", "��������", "����" },
				{ "��������", "��ҵ", "��ҵ��λ", "��������", "����" }, { "�ޱ��ػ���", "ѧ�Ѹ�", "��ѧ����", "��ѧ�ʸ�", "����" } };

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

		Object[][] showNewsTableData = NewsGetter.getNews();
		Object[] showNewsColumnTitle = { "����" };
		DefaultTableModel showNewsModel = new DefaultTableModel(); // �½�һ��Ĭ������ģ��
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
		JButton newsFirstButton = new PageSelectButton("��ҳ");
		newsBottomButtons.add(newsFirstButton);
		JButton newsPreviousButton = new PageSelectButton("��һҳ");
		newsBottomButtons.add(newsPreviousButton);
		JButton newsNextButton = new PageSelectButton("��һҳ");
		newsBottomButtons.add(newsNextButton);
		JButton newsLastButton = new PageSelectButton("βҳ");
		newsBottomButtons.add(newsLastButton);
		showNewsListPanel.add(newsBottomButtons, BorderLayout.SOUTH);
		
		// �����ҳ�¼�
		newsFirstButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewsGetter.init();
				Object[][] showNewsTableData = NewsGetter.getNews();
				Object[] showNewsColumnTitle = { "����" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// ���ù���������������
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if(showNewsScrollBar != null){
					showNewsScrollBar.setValue(0);
				}
			}
		});
		
		// �����һҳ�¼�
		newsPreviousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] showNewsTableData = NewsGetter.getPreviousNews();
				Object[] showNewsColumnTitle = { "����" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// ���ù���������������
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if(showNewsScrollBar != null){
					showNewsScrollBar.setValue(0);
				}
			}
		});
		
		// �����һҳ�¼�
		newsNextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] showNewsTableData = NewsGetter.getNews();
				Object[] showNewsColumnTitle = { "����" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// ���ù���������������
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if(showNewsScrollBar != null){
					showNewsScrollBar.setValue(0);
				}
			}
		});
		
		// ���βҳ�¼�
		newsLastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] showNewsTableData = NewsGetter.getLastNews();
				Object[] showNewsColumnTitle = { "����" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// ���ù���������������
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if(showNewsScrollBar != null){
					showNewsScrollBar.setValue(0);
				}
			}
		});
		
		
		

		// ��ʾ���Ž���->������ϸ���ݽ���
		JPanel showNewsDetailPanel = new JPanel();
		showNewsDetailPanel.setLayout(new BorderLayout());

		Border showNewsDetailBorder = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		showNewsDetailPanel.setBorder(showNewsDetailBorder);
		showNewsDetailPanel.setBackground(Color.WHITE);

		showNewsPanel.add("������ϸ����", showNewsDetailPanel);

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

		// ������ϸ����->����ǩ�·����ⲿ����
		JPanel showNewsDetailMainPanel = new JPanel();
		showNewsDetailMainPanel.setLayout(new BorderLayout());
		showNewsDetailMainPanel.setBackground(Color.WHITE);
		showNewsDetailMainPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
		showNewsDetailPanel.add(showNewsDetailMainPanel, BorderLayout.CENTER);

		// ������ϸ����->�ӱ�ǩ��CardLayout����
		JPanel newsDetailSubTagsCardPanel = new JPanel();
		final CardLayout newsDetailSubTagsCardLayout = new CardLayout();
		newsDetailSubTagsCardPanel.setLayout(newsDetailSubTagsCardLayout);
		showNewsDetailMainPanel.add(newsDetailSubTagsCardPanel, BorderLayout.NORTH);

		// ÿ���ӱ�ǩ����
		JPanel[] newsDetailSubTagsPanels = new JPanel[9];
		// ÿ���ӱ�ǩ�İ�ť��
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
				
				// ����ӱ�ǩ����¼�
				newsDetailSubTagsBtnGroup[i].get(j).addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						for (int j = 0; j < subTagsText[NewsGetter.getSelectedMainTag()].length - 1 ; j++) {
							if (e.getActionCommand().equals(subTagsText[NewsGetter.getSelectedMainTag()][j])) {
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

			newsDetailSubTagsGb.setConstraints(newsDetailSubTagsBtnGroup[i].get(subTagsText[i].length - 1),
					newsDetailSubTagsGbc);
			newsDetailSubTagsPanels[i].add(newsDetailSubTagsBtnGroup[i].get(subTagsText[i].length - 1));
		}

		// �������ǩ����¼�
		for (int i = 0; i < 9; i++) {
			newsDetailMainTagsGroup.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							newsDetailSubTagsCardLayout.show(newsDetailSubTagsCardPanel, Integer.toString(j));
							NewsGetter.setSelectedMainTag(j);
							int temp = NewsGetter.getSelectedSubTag();
							if(temp != -1){
								newsDetailSubTagsBtnGroup[j].select(temp);
							}
						}
					}
				}
			});
		}
		
		// ��ϸ���Ž���ײ����ذ�ť��ɾ����ť������
		JPanel backPanel = new JPanel();
		backPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		backPanel.setBackground(Color.WHITE);
		JButton backButton = new JButton("����");
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setForeground(Color.WHITE);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backPanel.add(backButton);
		JPanel tempPanel = new JPanel();
		tempPanel.setSize(1, 1);
		tempPanel.setBackground(Color.WHITE);
		backPanel.add(tempPanel);
		JButton deleteButton = new JButton("ɾ��");
		deleteButton.setBackground(Color.DARK_GRAY);
		deleteButton.setForeground(Color.WHITE);
		deleteButton.setFocusPainted(false);
		deleteButton.setBorderPainted(false);
		backPanel.add(deleteButton);
		showNewsDetailMainPanel.add(backPanel, BorderLayout.SOUTH);
		
		// ���ɾ����ť�ĵ���¼�
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] showNewsTableData = NewsGetter.deleteSelectedNews();
				Object[] showNewsColumnTitle = { "����" };
				showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
				// ���ù���������������
				JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
				if(showNewsScrollBar != null){
					showNewsScrollBar.setValue(0);
				}
				showNewsCardLayout.show(showNewsPanel, "�����б�");
			}
		});
		
		// ��ӷ��ذ�ť�ĵ���¼�
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showNewsCardLayout.show(showNewsPanel, "�����б�");
			}
		});
		
		// �����ϸ�������ݰ���
		JEditorPane newsContentPane = new JEditorPane();
		newsContentPane.setBackground(Color.WHITE);
		newsContentPane.setContentType("text/html");
		newsContentPane.setEditable(false);
		newsContentPane.setSelectionColor(new Color(230, 230, 230));
		newsContentPane.setText("<html><body><H1>���ض�ͯ��ؼҸ�ĸ������</H1><span>2014-01-29</span>&nbsp;&nbsp;<span>�����ձ�(���ֱ�)</span><br/><span>06,�̿�����</span><P>��Ϣ��Դ��Ĵ������� / Cited from http://www.newssc.org/<P>�������������ɼ��� ��С�᱾������ �����<P>�����㼴����<P>С�����ⰻȻ�����治��һ��������¥�����ݸ�Ǭ��������11��13�գ������߽��ɶ��н�����˫��·�ֵ��帣�������������ʱ�����پ�����Χ��һ�����졣����������ˣ��������ܺܿ�õ���������������¾��ã��Դ�������������������Խ��Խ�����ˡ�<P>����Ժ�䵳֧�������������Ϊ־Ը�߷���������ʵ�С���Ժ��Ժ����������λ��ơ���������ί���Ҧ�޺���ܣ�Ŀǰ�帣������һ��������11�������֯��ע��־Ը�߶��1500������ȫ���ɱ�����������ɣ������ϡ���ܡ��������εȷ���Ϊ�����ṩ����<P>���������Խ��Խ�ߣ������Խ��Խ�ã������帣������������������ĸ��ܡ����ϣ�������и������������������㣬����������<P>���᳡���ߡ�<P>�α�������־ ʮ�˴����������ί��Ƕ����� ʮ�˴������֦���ж�������·�ֵ�����·������Ա<P>��ͨ����������Ա�ɳ�����<P>������ί���Ҧ�޺飺ϣ�����ˡ��ơ������Դ��������õ���������һ���������������Ա�����ʺʹ�����<P>����־���������Ǹ�ϵͳ���̣���ʵ����Ҫ�ӳ�������ץ�𣬺�ʵ�������������������ơ��������ӡ������³�����Ϊȫ�����������Ե����֮һ������������������һЩ̽�������ǽ�������Ա�������ꡢ�칫�����������Ԥ�㣬����������Ȼ�������ơ�ͬʱ����ǿ�������齨�裬ÿ��������ί�ᶼ�䱸��5��9��������Ա����Ƹ�������ۺϷ���Э��Ա����¼��ѧ����������������飬�Ӵ�������������Ա�����ȷ�չ��Ա����¼����Ա��ѡ�λ����쵼�ɲ��Ĺ������ȣ���һ����ͨ������������Ա�ĳɳ�������������ҽ�����������Ͷ�뱣�ϻ��ƣ��ر��ǽ���������� ��������ת����ת��֧�����ƣ�Ϊ��ǿ�ʹ����������ṩ�������ϡ�<P>����Ͻ����ԴӦ�г�Ч����<P>����Ƽҷ�����������ÿ�ܶ��Ὺչ����������ǵ��������Ӻ�г������ʱ�������֯���Ѳ���������ϣ�����и����ʽ������֧�֡�<P>�����ף�ʮ�˴󱨸����Ҫǿ������ҵ��λ������������������ͷ����е�ְ�����������֯��������չ������Ⱥ�\����������Ļ������á���Ϊһ����ͨ�ĵ�Ա������20����������������У�����ͬ�С�Ŀǰ����������������Ͻ����Դ��Э���������������һЩ���ԣ�������Ȼȱ�����ƶȹ̶���������Դ���ϳ�Ч���ơ�����ܹ�����һ������ǣͷ��Ͻ����ҵ���롢������������߻�������ʹ������Ͻ����λ�Ĺ�ϵ���ƶȻ����淶���������ĸ�����Ṥ���ͻ�����׿�չ��Ч��Ҳ��úܶࡣ<P>��Ⱥ�\���������ܰ����<P>������ӱ̣�����ϣ���и�����������صĹ��������ܹ�������㣬����������<P>����־��ʮ�˴󱨸����Ҫ�Ľ������ṩ��������ʽ����ǿ����������ͷ�����ϵ���裬��ǿ�������������ܡ�����Ҫ��ְѷ���Ⱥ�\��Ϊ��������ĺ��ĺ͹ؼ���������������������ϵ����ʵ����������⡣һ����Ҫ�����������ƽ̨������������Ͷ���ҵ����ᱣ�ϵȷ���ְ�ܴӽֵ������򣩱�������������������죬����ʵ��Ⱥ�\���������ܰ������¡���һ���棬ҲҪ�Ľ����ǵ���������ʽ������������������Ⱥ�\��ѷ��ŷ�����ϵ������������Ⱥ�\����Ǽ��ƶȣ�����������Ա������������̽�ÿճ����ˡ��м��ˡ����ض�ͯ�ȣ��������ʵ�����ѣ�����Ϊ��Щ������֮��Ч�İ취��<P>����������11��13�յ磩 </P></body></html>");
		
		JScrollPane newsDetailContentPanel = new NewsScrollPane(newsContentPane);
		newsDetailContentPanel.setBackground(Color.WHITE);
		Border newsDetailContentBorder = new CompoundBorder(BorderFactory.createEmptyBorder(10,0, 10, 0),
				BorderFactory.createLineBorder(Color.BLACK, 1));
		newsDetailContentPanel.setBorder(newsDetailContentBorder);
		showNewsDetailMainPanel.add(newsDetailContentPanel, BorderLayout.CENTER);
		

		// ͳ�ƽ���
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

		// ����ӱ�ǩ������
		gbc.weightx = 0;
		gbc.gridwidth = 1;
		gbc.weighty = 1;
		// ��Ÿ�����ǩ��CardLayout����
		JPanel statisticsSubTags = new JPanel();
		statisticsSubTags.setBackground(Color.WHITE);
		statisticsSubTags.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 1));
		final CardLayout statisticsSubTagsLayout = new CardLayout();
		statisticsSubTags.setLayout(statisticsSubTagsLayout);
		gb.setConstraints(statisticsSubTags, gbc);
		statisticsPanel.add(statisticsSubTags);

		// ÿ���ӱ�ǩ����
		JPanel[] statisticsSubTagsPanels = new JPanel[9];
		// ÿ���ӱ�ǩ�İ�ť��
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

		// �������ǩ����¼�
		for (int i = 0; i < 9; i++) {
			statisticsMainTags.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int j = 0; j < 9; j++) {
						if (e.getActionCommand().equals(mainTagsText[j])) {
							statisticsSubTagsLayout.show(statisticsSubTags, Integer.toString(j));
							// ���ѡ��״̬
							statisticsSubTagsBtnGroup[j].clearSelection();
						}
					}
				}
			});
		}

		// ͳ�ƽ�������������
		gbc.gridwidth = 8;
		JPanel statisticsFigurePanel = new JPanel();
		statisticsFigurePanel.setBackground(Color.WHITE);
		gb.setConstraints(statisticsFigurePanel, gbc);
		statisticsPanel.add(statisticsFigurePanel);
		
		JPanel statisticsSelectMode = new JPanel();
		statisticsSelectMode.setLayout(new GridLayout(1, 6));
		for(int i = 0; i < 4; i++){
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			statisticsSelectMode.add(temp);
		}
		TagButtonGroup modeSelectGroup = new TagButtonGroup();
		JButton trendStatistics = new JButton("����ͳ��");
		modeSelectGroup.add(trendStatistics);
		statisticsSelectMode.add(trendStatistics);
		JButton tendencyComparison = new JButton("�����ԱȽ�");
		modeSelectGroup.add(tendencyComparison);
		statisticsSelectMode.add(tendencyComparison);
		
		statisticsFigurePanel.setLayout(new BorderLayout());
		statisticsFigurePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		statisticsFigurePanel.add(statisticsSelectMode, BorderLayout.NORTH);
		
		// ����ͳ��ͼ����壨�������Ұ�ť��
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
		
		// ����ͳ�����
		JPanel trendStatisticsPanel = new JPanel();
		trendStatisticsPanel.setLayout(new BorderLayout());
		statisticsFigureContent.add("����ͳ��", trendStatisticsPanel);
		
		// �����ԱȽ����
		JPanel tendencyComparisonPanel = new JPanel();
		tendencyComparisonPanel.setLayout(new BorderLayout());
		statisticsFigureContent.add("�����ԱȽ�", tendencyComparisonPanel);
		
		// ��������ť����¼�
		leftButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StatisticsGetter.changePreviousNewspaper();
				if(!StatisticsGetter.isTendencyComparison){
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				}else{
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getBarChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});
		
		// �������ҵ���¼�
		rightButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				StatisticsGetter.changeNextNewspaper();
				if(!StatisticsGetter.isTendencyComparison){
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				}else{
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getBarChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});
		
		// ����ͳ�Ƶ���¼�
		trendStatistics.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(StatisticsGetter.isTendencyComparison){
					StatisticsGetter.setIsTendencyComparison(false);
					trendStatisticsPanel.remove(StatisticsGetter.getOldBarChartPanel());
					trendStatisticsPanel.add(StatisticsGetter.getBarChartPanel());
					trendStatisticsPanel.updateUI();
				}
			}
		});
		
		// �����ԱȽϵ���¼�
		tendencyComparison.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!StatisticsGetter.isTendencyComparison){
					StatisticsGetter.setIsTendencyComparison(true);
					tendencyComparisonPanel.remove(StatisticsGetter.getOldPieChartPanel());
					tendencyComparisonPanel.add(StatisticsGetter.getPieChartPanel());
					tendencyComparisonPanel.updateUI();
				}
			}
		});
		
		
//		// ����Ԥ��ͳ��ͼ
//		// ������״ͼ���ݼ�����
//		//��������  
//        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();  
//        //���ݳ�ʼ��  
//        barDataset.addValue(1, "ĳ��ֽ", "2014��");  
//        barDataset.addValue(7, "ĳ��ֽ", "2015��");  
//        barDataset.addValue(3, "ĳ��ֽ", "2016��");  
//        
//        String title = "�����ձ�";
//        ChartPanel barChartPanel = BarChartFactory.getBarChartPanel(barDataset, title);
//        trendStatisticsPanel.add(barChartPanel, BorderLayout.CENTER);
		
//		// �����ԱȽ�ͳ��ͼ
//		// ��������ͼ���ݼ�����  
//        DefaultPieDataset pieDataset = new DefaultPieDataset();  
//        // �ֱ�ͼ�������˵��������  
//        pieDataset.setValue("��������", 100);  
//        pieDataset.setValue("��������", 75);  
//        pieDataset.setValue("����Ҹ�", 74);  
//        pieDataset.setValue("�����ͯ", 60);  
//        pieDataset.setValue("����", 50);
//        
//        ChartPanel pieChartPanel = PieChartFactory.getPieChartPanel(pieDataset, title);
//        tendencyComparisonPanel.add(pieChartPanel, BorderLayout.CENTER);

		// ����վ����
		recyclePanel.setLayout(new BorderLayout());

		Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
		Object[] recycleNewsColumnTitle = { "����", "����" };
		DefaultTableModel recycleNewsModel = new DefaultTableModel(); // �½�һ��Ĭ������ģ��
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
		JButton recycleNewsFirstButton = new PageSelectButton("��ҳ");
		recycleNewsBottomButtons.add(recycleNewsFirstButton);
		JButton recycleNewsPreviousButton = new PageSelectButton("��һҳ");
		recycleNewsBottomButtons.add(recycleNewsPreviousButton);
		JButton recycleNewsNextButton = new PageSelectButton("��һҳ");
		recycleNewsBottomButtons.add(recycleNewsNextButton);
		JButton recycleNewsLastButton = new PageSelectButton("βҳ");
		recycleNewsBottomButtons.add(recycleNewsLastButton);
		recyclePanel.add(recycleNewsBottomButtons, BorderLayout.SOUTH);
		
		// ������¼�
		recycleNewsTable.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JTable src = null;
				if (e.getSource() instanceof JTable) {
					src = (JTable) e.getSource();
					if(src.getSelectedColumn() == 1){
						Object[][] recycleNewsTableData = DeletedNewsGetter.restore(src.getSelectedRow());
						Object[] recycleNewsColumnTitle = { "����", "����" };
						recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
						recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
						// ���ù���������������
						JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
						if(recycleNewsScrollBar != null){
							recycleNewsScrollBar.setValue(0);
						}
					}
				}
			}
		});
		
//		TableColumn tableColumn = recycleNewsTable.getColumn("����"); 
//		 //DefaultTableCellRenderer����Ի��Ƶ�Ԫ��ı�����������ɫ�ȹ���   
//        DefaultTableCellRenderer recycleNewsColume2Render = new DefaultTableCellRenderer();   
//        //���Ʋ����еı���Ϊ��ɫ   
//        recycleNewsColume2Render.setBackground(Color.blue);
//        tableColumn.setCellRenderer(recycleNewsColume2Render);
		
		// �����ҳ�¼�
		recycleNewsFirstButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DeletedNewsGetter.init();
				Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
				Object[] recycleNewsColumnTitle = { "����", "����" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// ���ù���������������
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if(recycleNewsScrollBar != null){
					recycleNewsScrollBar.setValue(0);
				}
			}
		});
		
		// �����һҳ�¼�
		recycleNewsPreviousButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] recycleNewsTableData = DeletedNewsGetter.getPreviousNews();
				Object[] recycleNewsColumnTitle = { "����", "����" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// ���ù���������������
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if(recycleNewsScrollBar != null){
					recycleNewsScrollBar.setValue(0);
				}
			}
		});
		
		// �����һҳ�¼�
		recycleNewsNextButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
				Object[] recycleNewsColumnTitle = { "����", "����" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// ���ù���������������
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if(recycleNewsScrollBar != null){
					recycleNewsScrollBar.setValue(0);
				}
			}
		});
		
		// ���βҳ�¼�
		recycleNewsLastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[][] recycleNewsTableData = DeletedNewsGetter.getLastNews();
				Object[] recycleNewsColumnTitle = { "����", "����" };
				recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
				recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
				// ���ù���������������
				JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
				if(recycleNewsScrollBar != null){
					recycleNewsScrollBar.setValue(0);
				}
			}
		});
		

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
					//showNewsTable.clearSelection();
					NewsGetter.init();
					Object[][] showNewsTableData = NewsGetter.getNews();
					Object[] showNewsColumnTitle = { "����" };
					DefaultTableModel showNewsModel = new DefaultTableModel(); // �½�һ��Ĭ������ģ��
					showNewsModel.setDataVector(showNewsTableData, showNewsColumnTitle);
					showNewsTable.clearSelection();
					// ���ù���������������
					JScrollBar showNewsScrollBar = showNewsScrollPane.getVerticalScrollBar();
					if(showNewsScrollBar != null){
						showNewsScrollBar.setValue(0);
					}
					break;
				case "ͳ��":
					cardLayout.show(pagePanel, "ͳ��");
					recycleNewsTable.clearSelection();
					statisticsMainTags.select(0);
					// ��ʾ��һ������ǩ��Ӧ�ӱ�ǩ
					statisticsSubTagsLayout.show(statisticsSubTags, "0");
					// ���ѡ��״̬
					statisticsSubTagsBtnGroup[0].clearSelection();
					// ����ģʽѡ��ťѡ�е�һ��
					modeSelectGroup.select(0);
					
					StatisticsGetter.init();
					trendStatisticsPanel.add(StatisticsGetter.getOldBarChartPanel(), BorderLayout.CENTER);
					tendencyComparisonPanel.add(StatisticsGetter.getOldPieChartPanel(), BorderLayout.CENTER);
					break;
				case "����վ":
					cardLayout.show(pagePanel, "����վ");
					DeletedNewsGetter.init();
					Object[][] recycleNewsTableData = DeletedNewsGetter.getNews();
					Object[] recycleNewsColumnTitle = { "����", "����" };
					recycleNewsModel.setDataVector(recycleNewsTableData, recycleNewsColumnTitle);
					recycleNewsTable.getColumn(recycleNewsColumnTitle[1]).setMaxWidth(60);
					recycleNewsTable.clearSelection();
					// ���ù���������������
					JScrollBar recycleNewsScrollBar = recycleScrollPane.getVerticalScrollBar();
					if(recycleNewsScrollBar != null){
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

		// ��������ʾ���Ž���->�����б���棬�����б����¼�
		showNewsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable src = null;
				if (e.getSource() instanceof JTable) {
					src = (JTable) e.getSource();
					
					NewsGetter.setSelected(src.getSelectedRow());
					newsContentPane.setText(NewsGetter.getSelectedContent());
					
					// ��������ǩĬ��ѡ�е�һ��
					newsDetailMainTagsGroup.select(0);
					
					newsContentPane.setCaretPosition(0);
        
			        showNewsCardLayout.show(showNewsPanel, "������ϸ����");
				}
			}
		});
		
		// ���� ����ͳ�� �� �����ԱȽ� ��ť����¼�
		trendStatistics.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				figureCardLayout.show(statisticsFigureContent, "����ͳ��");
			}
		});
		
		tendencyComparison.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				figureCardLayout.show(statisticsFigureContent, "�����ԱȽ�");
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
