
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class AppMain extends JFrame{

	protected String [] header = {"����ȣ", "��/��", "��/��", "�߻���", "��", "��", "�����", "�����", "�λ���", "��� ����"};
	protected String contents[][] = {{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}
	,{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}};
	
	protected	String[] year = {"�⵵","2000","2001","2002","2003","2004","2005","2006","2007","2008",
			"2009","2010","2011","2012","2013","2014","2015","2016","2017", "2018"};
	protected String[] month = {"��","1","2","3","4","5","6","7","8","9","10","11","12",};
	protected String[] day = {"��","1","2","3","4","5","6","7","8","9","10","11","12","13",
			"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	protected String[] province = {"����Ư����","��õ������","��⵵"};
	protected String[] accOptionType = {"������", "������", "�����ܵ�"};

	//�⺻ �ɼ� �г� ��----------------------------------------------------------------------
	protected JPanel[] panels;
	protected JPanel primary;
	protected JLabel[] labels;
	
	//��ư----------------------------------------------------------------------------------
	protected JPanel bPanel;
	protected JButton[] btns;
	
	//JTable---------------------------------------------------------------------------------
	//DefaultTableModel �⺻ǥ��=new DefaultTableModel();

	DefaultTableModel basicTable =new DefaultTableModel();
	
	protected JTable table = new JTable(basicTable)
	{
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	protected JScrollPane scroll;
	
	//��� �˻�--------------------------------------------------------------------------------
	protected JDialog diaSearch;
	protected JPanel searchUpPanel;
	protected JPanel searchDownPanel;
	protected JPanel searchButtonPanel;
	
	protected JLabel siDolbl;
	protected JLabel guGunlbl;
	protected JComboBox siDo = new JComboBox(province);
	protected JComboBox guGun = new JComboBox();
	
	protected JButton searchButton = new JButton();
	
	//��� ���--------------------------------------------------------------------------------
	protected JDialog dia;
	protected JPanel leftPanel;
	
	protected JLabel label1;	//���
	protected JLabel label2; 	//��¥
	protected JLabel label3; 	//������ȣ
	protected JLabel label5; 	//����� ��
	protected JLabel label6;	//��� Ÿ��
	protected JLabel label7; 	//"����, �浵
	//protected JLabel label8 //"���"
	
	protected JPanel rightPanel;
	
	protected JTextField polno = new JTextField(10);
	protected JTextField dead = new JTextField(10);
	protected JTextField injured = new JTextField(10);
	protected JTextField lati = new JTextField(10);
	protected JTextField longi = new JTextField(10);
	
	//����Էºκ�
	protected JPanel loc;
	protected JComboBox pro = new JComboBox(province);
	protected JComboBox tow = new JComboBox();
	
	//��¥�Էºκ�
	protected JPanel time;
	protected JComboBox yearcb = new JComboBox(year);
	protected JComboBox monthcb = new JComboBox(month);
	protected JComboBox daycb = new JComboBox(day);
	
	//������ �Է� �κ�
	protected JPanel casualty;
	protected	JLabel tmp1;
	protected	JLabel tmp2;
	
	//��� Ÿ�� �κ�
	protected JComboBox accType = new JComboBox(accOptionType);
	
	//���� �浵 �Էºκ�
	protected	JPanel locInfo;
	protected	JLabel laTmp;
	protected	JLabel loTmp;
	
	//�ϴ� �г�
	protected JPanel subPanel;
	protected	JButton regBtn = new JButton("���");
	
	//��� ����/����-----------------------------------------------------------------------------------------
	protected JDialog diaUpdate;
	protected JPanel leftUpdatePanel;
	protected JLabel caseNum;			//��� ��ȣ
	
	protected JLabel labelUpdate1;	//���
	protected JLabel labelUpdate2; 	//��¥
	protected JLabel labelUpdate3; 	//������ȣ
	protected JLabel labelUpdate5; 	//����� ��
	protected JLabel labelUpdate6;	//��� Ÿ��
	protected JLabel labelUpdate7; 	//"����, �浵
	
	protected JPanel rightUpdatePanel;
	
	protected JPanel searchCaseNumPanel;	//���̽� �˻� �г�
	protected JTextField caseNumTxt;		//��� ��ȣ �Է¶�
	protected JButton searchUpdateBtn = new JButton("�˻�");	//��� ��ȣ �˻�;		//��� ��ȣ �˻�
	
	protected JTextField polnoUpdate = new JTextField(10);
	protected JTextField deadUpdate = new JTextField(10);
	protected JTextField injuredUpdate = new JTextField(10);
	protected JTextField latiUpdate = new JTextField(10);
	protected JTextField longiUpdate = new JTextField(10);

	//����Էºκ�
	protected JPanel locUpdate;
	protected JComboBox proUpdate = new JComboBox(province);
	protected JComboBox towUpdate = new JComboBox();
	
	//��¥�Էºκ�
	protected JPanel timeUpdate;
	protected JComboBox yearcbUpdate = new JComboBox(year);
	protected JComboBox monthcbUpdate = new JComboBox(month);
	protected JComboBox daycbUpdate = new JComboBox(day);
	
	//������ �Է� �κ�
	protected	JPanel casualtyUpdate;
	protected	JLabel tmp1Update;
	protected	JLabel tmp2Update;
	
	//���Ÿ�� �κ�
	protected JComboBox accTypeUpdate = new JComboBox(accOptionType);
	
	
	//���� �浵 �Էºκ�
	protected	JPanel locInfoUpdate;
	protected	JLabel laTmpUpdate;
	protected	JLabel loTmpUpdate;
	
	//�ϴ� �г�
	protected JPanel subUpdatePanel;
	protected JButton updateButton = new JButton("����");
	protected JButton deleteButton = new JButton("����");
	
	//��� �м�----------------------------------------------------------------------------
	
	//�ϴ� �޼���-------------------------------------------------------------------------------
	protected JLabel accInfo;

	public AppMain() {
		
		AppManager.CreateInstance().setAppMain(this);
		
		setTitle("���� ��� ���� �ý���");
		setSize(Execute.WIDTH,Execute.HEIGHT);
		setResizable(false);
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		primary = new JPanel();
		primary.setBounds(0,0,Execute.WIDTH,Execute.HEIGHT);
		primary.setLayout(null);
		getContentPane().add(primary);
		
		//�ش� ��¥ ��� ������ �ǽð�? ����
		
		accInfo = new JLabel("�ش� ��¥ ��� ����");
		accInfo.setBounds(0, 670, 1200, 30);
		primary.add(accInfo);
		
		//��� ��ư�� �߰�
		//��ư �߰� ����
		bPanel = new JPanel();
		bPanel.setLayout(null);
		bPanel.setBounds(0, 0, 1300, 110);
		
		btns = new JButton[4];
		btns[0] = new JButton("��� ���� �˻�");
		btns[0].setBounds(20,45,311,60);
		bPanel.add(btns[0]);
		
		btns[1] = new JButton("��� ���");
		btns[1].setBounds(20+311 + 5,45,311,60);
		bPanel.add(btns[1]);
		
		btns[2] = new JButton("��� ���� ����/����");
		btns[2].setBounds(20 + 311 + 5 + 311 + 5,45,311,60);
		bPanel.add(btns[2]);
		
		btns[3] = new JButton("��� �м�");
		btns[3].setBounds(20 +311 + 5+ 311 + 5 + 311 +5,45,311,60);
		
		//1300
		bPanel.add(btns[3]);
		
		primary.add(bPanel);
		
		//Table ���� ����------------------------------------------------------------------------------
	
		for(int i =0; i<header.length; i++)
		{
			basicTable.addColumn(header[i]);	
		}
		for(int i =0; i<contents.length; i++)
		{
			basicTable.addRow(contents[i]);
		}
		scroll = new JScrollPane();
		scroll.setViewportView(table);
      	scroll.setBounds(20,110,1260, 510);
      	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); //���ι���å
      	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //���ι���å
      	primary.add(scroll);
		
		add(primary);
		setVisible(true);
	}
	
	public void search() {
		diaSearch = new JDialog();
		diaSearch.setSize(400,300);
		diaSearch.setResizable(false);
		diaSearch.setLayout(null);
		diaSearch.setTitle("��� ���� �˻�");
		
		searchUpPanel = new JPanel();
		searchUpPanel.setBounds(0,0,400,100);
		searchUpPanel.setBackground(Color.white);
		searchUpPanel.setLayout(null);
		
		siDolbl = new JLabel("��/��");
		siDolbl.setBounds(0,40,50,50);
		searchUpPanel.add(siDolbl);
		
		siDo.setBounds(100,40,250,50);
		searchUpPanel.add(siDo);
		
		searchDownPanel = new JPanel();
		searchDownPanel.setBounds(0,100,400,100);
		searchDownPanel.setBackground(Color.white);
		searchDownPanel.setLayout(null);
		
		guGunlbl = new JLabel("��/��");
		guGunlbl.setBounds(0,0,50,50);
		searchDownPanel.add(guGunlbl);
		
		guGun.setBounds(100,0,250,50);
		searchDownPanel.add(guGun);
		
		searchButtonPanel = new JPanel();
		searchButtonPanel.setLayout(null);
		searchButtonPanel.setBounds(0,200,400,100);
		searchButton.setBounds(100, 0, 200, 100);
		searchButtonPanel.add(searchButton);
		
		diaSearch.add(searchUpPanel);
		diaSearch.add(searchDownPanel);
		diaSearch.add(searchButtonPanel);
		diaSearch.setVisible(true);
	}

	public void registration() {
		dia = new JDialog();
		dia.setTitle("��� ���");
		dia.setLayout(null);
		dia.setResizable(false);
		dia.setSize(810,600);
		
		//leftPanel ���� ���� --------------------------------------------------
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.white);
		leftPanel.setLayout(new GridLayout(6,1));
		leftPanel.setBounds(0,0,100,500);
		dia.add(leftPanel);
		
		label1 = new JLabel("���",JLabel.CENTER);
		label2 = new JLabel("��¥",JLabel.CENTER);
		label3 = new JLabel("������ȣ",JLabel.CENTER);
		label5 = new JLabel("����� ��",JLabel.CENTER);
		label6 = new JLabel("��� Ÿ��",JLabel.CENTER);
		label7 = new JLabel("����, �浵",JLabel.CENTER);
		
		leftPanel.add(label1);
		leftPanel.add(label2);
		leftPanel.add(label3);
		leftPanel.add(label5);
		leftPanel.add(label6);
		leftPanel.add(label7);
		
		//rightPanel ���� ���� --------------------------------------------------
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(6,1));
		rightPanel.setBounds(100,0,700,500);
		dia.add(rightPanel);
		
		polno = new JTextField(10);
		dead = new JTextField(10);
		injured = new JTextField(10);
		lati = new JTextField(10);
		longi = new JTextField(10);

		//����Էºκ�
		loc = new JPanel();
		loc.setLayout(new GridLayout(1,2));
		loc.add(pro);loc.add(tow);
		rightPanel.add(loc);
		
		//��¥�Էºκ�
		time = new JPanel();
		time.setLayout(new GridLayout(1,3));
		time.add(yearcb);time.add(monthcb);time.add(daycb);
		rightPanel.add(time);
		
		//������ȣ �Էºκ�
		rightPanel.add(polno);
		
		//������ �Է� �κ�
		casualty = new JPanel();
		casualty.setLayout(new GridLayout(1,4));
		tmp1 = new JLabel("����� ��");
		tmp2 = new JLabel("�λ��� ��");
		casualty.add(tmp1); casualty.add(dead);
		casualty.add(tmp2); casualty.add(injured);
		rightPanel.add(casualty);
		
		//���Ÿ�� �Էºκ�
		rightPanel.add(accType);
		
		//���� �浵 �Էºκ�
		locInfo = new JPanel();
		locInfo.setLayout(new GridLayout(1,4,70,0));
		laTmp = new JLabel("����");
		loTmp = new JLabel("�浵");
		locInfo.add(laTmp);locInfo.add(lati);
		locInfo.add(loTmp);locInfo.add(longi);
		rightPanel.add(locInfo);

		//subPanel----------------------------------------------------------------------------
		subPanel = new JPanel();
		subPanel.setBounds(0,500,810,100);
		dia.add(subPanel);
		
		regBtn.setBounds(405-20, 0, 40, 30);
		subPanel.add(regBtn);
		
		dia.setVisible(true);
	}
	public void modifyDelete() {
		
		diaUpdate = new JDialog();
		diaUpdate.setTitle("��� ���� / ����");
		diaUpdate.setLayout(null);
		diaUpdate.setResizable(false);
		diaUpdate.setSize(810,700);
		
		//leftPanel ���� ���� --------------------------------------------------
		leftUpdatePanel = new JPanel();
		leftUpdatePanel.setBackground(Color.white);
		leftUpdatePanel.setLayout(new GridLayout(7,1));
		leftUpdatePanel.setBounds(0,0,100,600);
		diaUpdate.add(leftUpdatePanel);
		
		caseNum = new JLabel("��� ��ȣ �Է�", JLabel.CENTER);
		labelUpdate1 = new JLabel("���",JLabel.CENTER);
		labelUpdate2 = new JLabel("��¥",JLabel.CENTER);
		labelUpdate3 = new JLabel("������ȣ",JLabel.CENTER);
		labelUpdate5 = new JLabel("����� ��",JLabel.CENTER);
		labelUpdate6 = new JLabel("��� Ÿ��",JLabel.CENTER);
		labelUpdate7 = new JLabel("����, �浵",JLabel.CENTER);
		
		leftUpdatePanel.add(caseNum);
		leftUpdatePanel.add(labelUpdate1);
		leftUpdatePanel.add(labelUpdate2);
		leftUpdatePanel.add(labelUpdate3);
		leftUpdatePanel.add(labelUpdate5);
		leftUpdatePanel.add(labelUpdate6);
		leftUpdatePanel.add(labelUpdate7);
		
		//rightPanel ���� ���� --------------------------------------------------
		rightUpdatePanel = new JPanel();
		rightUpdatePanel.setLayout(new GridLayout(7,1));
		rightUpdatePanel.setBounds(100,0,700,600);
		diaUpdate.add(rightUpdatePanel);
		
		//��� ��ȣ �Է� �κ�
		searchCaseNumPanel = new JPanel(new GridLayout(1,2));	//���̽� �˻� �г�
		caseNumTxt = new JTextField(10);		//��� ��ȣ �Է¶�
		
		searchCaseNumPanel.add(caseNumTxt);
		searchCaseNumPanel.add(searchUpdateBtn);
		rightUpdatePanel.add(searchCaseNumPanel);
		
		//����Էºκ�
		locUpdate = new JPanel();
		locUpdate.setLayout(new GridLayout(1,2));
		locUpdate.add(proUpdate);locUpdate.add(towUpdate);
		rightUpdatePanel.add(locUpdate);
		
		//��¥�Էºκ�
		timeUpdate = new JPanel();
		timeUpdate.setLayout(new GridLayout(1,3));
		timeUpdate.add(yearcbUpdate);timeUpdate.add(monthcbUpdate);timeUpdate.add(daycbUpdate);
		rightUpdatePanel.add(timeUpdate);
		
		//������ȣ �Էºκ�
		rightUpdatePanel.add(polnoUpdate);
		
		//������ �Է� �κ�
		casualtyUpdate= new JPanel();
		casualtyUpdate.setLayout(new GridLayout(1,4));
		tmp1Update = new JLabel("����� ��");
		tmp2Update = new JLabel("�λ��� ��");
		casualtyUpdate.add(tmp1Update); casualtyUpdate.add(deadUpdate);
		casualtyUpdate.add(tmp2Update); casualtyUpdate.add(injuredUpdate);
		rightUpdatePanel.add(casualtyUpdate);
		
		//���Ÿ�� �Էºκ�
		rightUpdatePanel.add(accTypeUpdate);
		
		//���� �浵 �Էºκ�
		locInfoUpdate = new JPanel();
		locInfoUpdate.setLayout(new GridLayout(1,4,70,0));
		laTmpUpdate = new JLabel("����");
		loTmpUpdate = new JLabel("�浵");
		locInfoUpdate.add(laTmpUpdate);locInfoUpdate.add(latiUpdate);
		locInfoUpdate.add(loTmpUpdate);locInfoUpdate.add(longiUpdate);
		rightUpdatePanel.add(locInfoUpdate);

		//subPanel----------------------------------------------------------------------------
		//diaUpdate.setSize(810,800);
		subUpdatePanel = new JPanel();
		subUpdatePanel.setLayout(null);
		subUpdatePanel.setBounds(0,600,810,100);
		
		updateButton.setBounds(600,0,80,50);
		subUpdatePanel.add(updateButton);
		
		deleteButton.setBounds(690, 0, 80,50);
		subUpdatePanel.add(deleteButton);
		
		diaUpdate.add(subUpdatePanel);
		diaUpdate.setVisible(true);
	}
	
	public void analysis()
	{
		new AccidentAnalysis();
	}
	
	
	public void addActionButtonListener(ActionListener action)
	{
		for(int i=0;i<btns.length;i++)
		{
			btns[i].addActionListener(action);
		}
	}
	
	//Search ���� ��ư ����---------------------------------------------------------------------
	
	public void addActionSearchProListener(ActionListener action)
	{
		siDo.addActionListener(action);
	}
	
	public void addActionSearchListener(ActionListener action)
	{
		searchButton.addActionListener(action);
	}

	//Register ���� ��ư ����---------------------------------------------------------------------
	public void addActionRegisterProListener(ActionListener action)
	{
		pro.addActionListener(action);		
	}
	public void addActionRegisterListener(ActionListener action)
	{
		regBtn.addActionListener(action);
	}
	
	//Update ���� ��ư ����---------------------------------------------------------------------
	public void addActionUpdateProListener(ActionListener action)
	{
		proUpdate.addActionListener(action);			
	}
	
	public void addActionUpdateSearchListener(ActionListener action)
	{
		searchUpdateBtn.addActionListener(action);
	}
	
	public void addActionUpdateListener(ActionListener action)
	{
		updateButton.addActionListener(action);
		deleteButton.addActionListener(action);
	}
	
	public void addMouseMainButtonListener(MouseListener mouse)
	{
	}
	
	public void addTableListener(MouseAdapter mouse)
	{
		table.addMouseListener(mouse);
	}
	
}