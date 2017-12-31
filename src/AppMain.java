
//table ��ȯ�۾� �ʿ�
//���, ���� ������ ���
//Detail info ���� ���� ���
//Analysis Ʋ ���
//Detail info, Analysis ��ư �̺�Ʈ Ŭ���� ���� ����

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
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
	
	protected String[] province = {"��ü","����Ư����","��õ������","��⵵"};
	protected String[] accOptionType = {"������", "������", "�����ܵ�"};

	//�⺻ �ɼ� �г� ��----------------------------------------------------------------------
	protected JPanel[] panels;
	protected JLabel[] labels;
	
	protected CardLayout cardLayout = new CardLayout();
	protected JPanel primary;
	protected Container cardPanel = new JPanel();
	protected JPanel tablePanel = new JPanel();
	protected JPanel imagePanel = new JPanel();
	protected JLabel imageLabel = new JLabel(ImageData.mainImage);
	
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
	protected JButton regBtn = new JButton("���");
	
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
	protected JTextField caseNumTxt = new JTextField();		//��� ��ȣ �Է¶�
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
		
		//Table�� �⺻ �̹��� ���� ����------------------------------------------------------------------------------
		
		cardPanel.setBounds(20,110,1260,510);
		cardPanel.setLayout(cardLayout);
		
		imagePanel.setBounds(0,0,1260,510);
		imageLabel.setBounds(0,0,1260,510);
		imagePanel.add(imageLabel);
		
		//table ���� ���� �����
		tablePanel.setBounds(0,0,1260,510);
		tablePanel.setLayout(null);
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
      	scroll.setBounds(0,0,1260,510);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); //���ι���å
      	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //���ι���å
      	tablePanel.add(scroll);
      	
      	cardPanel.add(tablePanel, "table");
      	cardPanel.add(imagePanel, "image");
      	cardLayout.show(cardPanel,"image");		
      	
      	primary.add(cardPanel);
      	
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
	      dia.setSize(550,550);
	      
	      //leftPanel ���� ���� --------------------------------------------------
	      leftPanel = new JPanel();
	      leftPanel.setBackground(Color.white);
	      leftPanel.setLayout(null);
	      leftPanel.setBounds(0,0,100,430);

	      label1 = new JLabel("���",JLabel.CENTER);
	      label2 = new JLabel("��¥",JLabel.CENTER);
	      label3 = new JLabel("������ȣ",JLabel.CENTER);
	      label5 = new JLabel("����� ��",JLabel.CENTER);
	      label6 = new JLabel("��� Ÿ��",JLabel.CENTER);
	      label7 = new JLabel("����, �浵",JLabel.CENTER);

	      label1.setBounds(0, 20,100,50);
	      label2.setBounds(0, 90, 100, 50);
	      label3.setBounds(0, 160, 100, 50);
	      label5.setBounds(0, 230, 100, 50);
	      label6.setBounds(0, 300, 100, 50);
	      label7.setBounds(0, 370, 100, 50);
	      leftPanel.add(label1);
	      leftPanel.add(label2);
	      leftPanel.add(label3);
	      leftPanel.add(label5);
	      leftPanel.add(label6);
	      leftPanel.add(label7);

	      dia.add(leftPanel);

	      //rightPanel ���� ���� --------------------------------------------------
	      rightPanel = new JPanel();
	      rightPanel.setLayout(null);
	      rightPanel.setBounds(100,0,700,500);
	      //rightPanel.setBackground(Color.GREEN);
	      dia.add(rightPanel);


	      //����Էºκ�
	      loc = new JPanel();
	      loc.setLayout(null);
	      loc.setBounds(10, 20, 370, 50);
	      pro.setBounds(0, 0, 200, 50);
	      tow.setBounds(220, 0, 150, 50);
	      loc.add(pro);loc.add(tow);
	      rightPanel.add(loc);

	      //��¥�Էºκ�
	      time = new JPanel();
	      time.setLayout(null);
	      time.setBounds(10, 90, 410, 50);
	      yearcb.setBounds(0,0,170,50);
	      monthcb.setBounds(190,0,100,50);
	      daycb.setBounds(310,0,100,50);
	      time.add(yearcb);time.add(monthcb);time.add(daycb);
	      rightPanel.add(time);

	      //������ȣ �Էºκ�
	      //JPanel tm = new JPanel();
	      polno.setBounds(10,160,70,50);
	      rightPanel.add(polno);

	      //������ �Է� �κ�
	      casualty = new JPanel();
	      casualty.setLayout(null);
	      casualty.setBounds(10, 230, 440, 50);
	      tmp1 = new JLabel("����� ��");
	      tmp2 = new JLabel("�λ��� ��");
	      tmp1.setBounds(0, 0, 70, 50);
	      dead.setBounds(80, 0, 70, 50);
	      tmp2.setBounds(160, 0, 70, 50);
	      injured.setBounds(240,0,70,50);
	      casualty.add(tmp1); casualty.add(dead);
	      casualty.add(tmp2); casualty.add(injured);
	      rightPanel.add(casualty);

	      //���Ÿ�� �Էºκ�
	      accType.setBounds(10, 300, 150, 50);
	      rightPanel.add(accType);

	      //���� �浵 �Էºκ�
	      locInfo = new JPanel();
	      locInfo.setLayout(null);
	      locInfo.setBounds(10, 370, 740, 50);
	      laTmp = new JLabel("����");
	      loTmp = new JLabel("�浵");
	      laTmp.setBounds(0, 0,50,50);
	      lati.setBounds(60, 0, 70, 50);
	      loTmp.setBounds(140, 0, 50, 50);
	      longi.setBounds(200, 0, 70, 50);
	      locInfo.add(laTmp);locInfo.add(lati);
	      locInfo.add(loTmp);locInfo.add(longi);
	      rightPanel.add(locInfo);

	      //subPanel----------------------------------------------------------------------------
	      /*subPanel = new JPanel();
	      subPanel.setBounds(0,500,810,100);
	      dia.add(subPanel);*/

	      /*regBtn.setBounds(405-20, 0, 40, 30);
	      subPanel.add(regBtn);*/

	      regBtn.setBounds(130,450,60,50);
	      rightPanel.add(regBtn);

	      dia.setVisible(true);
	   }
	   public void modifyDelete() {

	      diaUpdate = new JDialog();
	      diaUpdate.setTitle("��� ���� / ����");
	      diaUpdate.setLayout(null);
	      diaUpdate.setResizable(false);
	      diaUpdate.setSize(550,600);

	      //leftPanel ���� ���� --------------------------------------------------
	      leftUpdatePanel = new JPanel();
	      leftUpdatePanel.setBackground(Color.white);
	      leftUpdatePanel.setLayout(null);
	      leftUpdatePanel.setBounds(0,0,100,500);


	      caseNum = new JLabel("��� ��ȣ �Է�",JLabel.CENTER);
	      labelUpdate1 = new JLabel("���",JLabel.CENTER);
	      labelUpdate2 = new JLabel("��¥",JLabel.CENTER);
	      labelUpdate3 = new JLabel("������ȣ",JLabel.CENTER);
	      labelUpdate5 = new JLabel("����� ��",JLabel.CENTER);
	      labelUpdate6 = new JLabel("��� Ÿ��",JLabel.CENTER);
	      labelUpdate7 = new JLabel("����, �浵",JLabel.CENTER);

	      caseNum.setBounds(0, 20, 100, 50);
	      labelUpdate1.setBounds(0, 20+70,100,50);
	      labelUpdate2.setBounds(0, 90+70, 100, 50);
	      labelUpdate3.setBounds(0, 160+70, 100, 50);
	      labelUpdate5.setBounds(0, 230+70, 100, 50);
	      labelUpdate6.setBounds(0, 300+70, 100, 50);
	      labelUpdate7.setBounds(0, 370+70, 100, 50);
	      leftUpdatePanel.add(caseNum);
	      leftUpdatePanel.add(labelUpdate1);
	      leftUpdatePanel.add(labelUpdate2);
	      leftUpdatePanel.add(labelUpdate3);
	      leftUpdatePanel.add(labelUpdate5);
	      leftUpdatePanel.add(labelUpdate6);
	      leftUpdatePanel.add(labelUpdate7);

	      diaUpdate.add(leftUpdatePanel);

	      //rightPanel ���� ���� --------------------------------------------------
	      rightUpdatePanel = new JPanel();
	      rightUpdatePanel.setLayout(null);
	      rightUpdatePanel.setBounds(100,0,700,500);
	      //rightPanel.setBackground(Color.GREEN);
	      diaUpdate.add(rightUpdatePanel);

	      //��� ��ȣ �Է� �κ�
	      searchCaseNumPanel = new JPanel();//���̽� �˻� �г�
	      searchCaseNumPanel.setLayout(null);
	      searchCaseNumPanel.setBounds(10, 20, 370, 50);
	      caseNumTxt = new JTextField(10);      //��� ��ȣ �Է¶�
	      caseNumTxt.setBounds(0,0,70,50);
	      searchUpdateBtn.setBounds(90,0,70,50);
	      searchCaseNumPanel.add(caseNumTxt);
	      searchCaseNumPanel.add(searchUpdateBtn);
	      rightUpdatePanel.add(searchCaseNumPanel);

	      //����Էºκ�
	      locUpdate = new JPanel();
	      locUpdate.setLayout(null);
	      locUpdate.setBounds(10, 20+70, 370, 50);
	      proUpdate.setBounds(0, 0, 200, 50);
	      towUpdate.setBounds(220, 0, 150, 50);
	      locUpdate.add(proUpdate);locUpdate.add(towUpdate);
	      rightUpdatePanel.add(locUpdate);

	      //��¥�Էºκ�
	      timeUpdate = new JPanel();
	      timeUpdate.setLayout(null);
	      timeUpdate.setBounds(10, 90+70, 410, 50);
	      yearcbUpdate.setBounds(0,0,170,50);
	      monthcbUpdate.setBounds(190,0,100,50);
	      daycbUpdate.setBounds(310,0,100,50);
	      timeUpdate.add(yearcbUpdate);timeUpdate.add(monthcbUpdate);timeUpdate.add(daycbUpdate);
	      rightUpdatePanel.add(timeUpdate);

	      //������ȣ �Էºκ�
	      //JPanel tm = new JPanel();
	      polnoUpdate.setBounds(10,160+70,70,50);
	      rightUpdatePanel.add(polnoUpdate);

	      //������ �Է� �κ�
	      casualtyUpdate = new JPanel();
	      casualtyUpdate.setLayout(null);
	      casualtyUpdate.setBounds(10, 230+70, 440, 50);
	      tmp1Update = new JLabel("����� ��");
	      tmp2Update = new JLabel("�λ��� ��");
	      tmp1Update.setBounds(0, 0, 70, 50);
	      deadUpdate.setBounds(80, 0, 70, 50);
	      tmp2Update.setBounds(160, 0, 70, 50);
	      injuredUpdate.setBounds(240,0,70,50);
	      casualtyUpdate.add(tmp1Update); casualtyUpdate.add(deadUpdate);
	      casualtyUpdate.add(tmp2Update); casualtyUpdate.add(injuredUpdate);
	      rightUpdatePanel.add(casualtyUpdate);

	      //���Ÿ�� �Էºκ�
	      accTypeUpdate.setBounds(10, 300+70, 150, 50);
	      rightUpdatePanel.add(accTypeUpdate);

	      //���� �浵 �Էºκ�
	      locInfoUpdate = new JPanel();
	      locInfoUpdate.setLayout(null);
	      locInfoUpdate.setBounds(10, 370+70, 740, 50);
	      laTmpUpdate = new JLabel("����");
	      loTmpUpdate = new JLabel("�浵");
	      laTmpUpdate.setBounds(0, 0,50,50);
	      latiUpdate.setBounds(60, 0, 70, 50);
	      loTmpUpdate.setBounds(140, 0, 50, 50);
	      longiUpdate.setBounds(200, 0, 70, 50);
	      locInfoUpdate.add(laTmpUpdate);locInfoUpdate.add(latiUpdate);
	      locInfoUpdate.add(loTmpUpdate);locInfoUpdate.add(longiUpdate);
	      rightUpdatePanel.add(locInfoUpdate);

	      //550 600
	      subUpdatePanel = new JPanel();
	      subUpdatePanel.setBounds(0, 500, 550, 100);
	      subUpdatePanel.setLayout(null);
	      
	      updateButton.setBounds(200,0,70,40);
	      deleteButton.setBounds(300,0,70,40);
	      subUpdatePanel.add(updateButton);subUpdatePanel.add(deleteButton);
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