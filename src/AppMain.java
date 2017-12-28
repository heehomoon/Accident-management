import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class AppMain extends JFrame{

	private String initText = "<html>��ư ���λ��� ���÷���<br/>���콺�� ��ư���� �÷��ּ���</html>";
	
	private JLabel[] labels;
	private JPanel[] panels;
	
	private JPanel primary;
	
	//��ư----------------------------------------------------------------------------------
	private JPanel bPanel;
	private JButton[] btns;
	
	//JTable---------------------------------------------------------------------------------
	private JTable table;
	private JScrollPane scroll;
	private String [] header = {"����ȣ", "��/��", "��/��", "�߻���", "��", "��", "�����", "�����", "�λ���", "��� ����"};
	private String contents[][] = {{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}
	,{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}};
	
	//��� �˻�--------------------------------------------------------------------------------
	private JDialog diaSearch;
	private JPanel searchUpPanel;
	private JPanel searchDownPanel;
	
	private JLabel siDolbl;
	private JLabel guGunlbl;
	private JComboBox siDo;
	private JComboBox guGun;
	
	//��� ���--------------------------------------------------------------------------------
	private JDialog dia;
	private	String[] year = {"�⵵","��ü","2000","2001","2002","2003","2004","2005","2006","2007","2008",
			"2009","2010","2011","2012","2013","2014","2015","2016","2017", "2018"};
	private String[] month = {"��","1","2","3","4","5","6","7","8","9","10","11","12",};
	private String[] day = {"��","1","2","3","4","5","6","7","8","9","10","11","12","13",
			"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	private String[] province = {"��ü","����Ư����","��õ������","��⵵"};
	private String[] sTown = {"�����","������","������","���ʱ�","������","������","���α�","�߱�","�߶���","���ϱ�","��õ��","��������",
			"���빮��","����","���۱�","������","���ı�","������","��걸","��õ��","���α�","������","���Ǳ�","���빮��","���ϱ�"};
	private String[] iTown = {"�߱�","����","����","������","������","����","��籸","����","��ȭ��","������"};
	private String[] gyTown = {"������","������","�����ν�","�Ⱦ��","��õ��","�����","���ý�","����õ��","�Ȼ��","����","��õ��","������","�����ֽ�","�����","�����",
			"������","�ǿս�","�ϳ���","���ν�","���ֽ�","��õ��","������","ȭ����","���ֽ�","���ֽ�","��õ��","���ֱ�","��õ��","����","����"};
	
	private JPanel leftPanel;
	
	private JLabel label1;	//���
	private JLabel label2; 	//��¥
	private	JLabel label3; 	//������ȣ
	private JLabel label4; 	//����ȣ
	private JLabel label5; 	//����� ��
	private JLabel label6;	//��� Ÿ��
	private JLabel label7; 	//"����, �浵
	//private JLabel label8 //"���"
	
	private JPanel rightPanel;
	
	private JTextField polno;
	private JTextField carno;
	private JTextField dead;
	private JTextField injured;
	private JTextField accType;
	private JTextField lati;
	private JTextField longi;
	
	//����Էºκ�
	private JPanel loc;
	private JComboBox pro;
	private JComboBox tow;
	
	//��¥�Էºκ�
	private JPanel time;
	private JComboBox yearcb;
	private JComboBox monthcb;
	private JComboBox daycb;
	
	//������ �Է� �κ�
	private JPanel casualty;
	private	JLabel tmp1;
	private	JLabel tmp2;
	
	//���� �浵 �Էºκ�
	private	JPanel locInfo;
	private	JLabel laTmp;
	private	JLabel loTmp;
	
	//�ϴ� �г�
	private JPanel subPanel;
	private	JButton regBtn;
	
	//��� ����/����-----------------------------------------------------------------------------------------
	private JDialog diaUpdate;
	private JPanel leftUpdatePanel;
	private JLabel caseNum;			//��� ��ȣ
	
	private JLabel labelUpdate1;	//���
	private JLabel labelUpdate2; 	//��¥
	private	JLabel labelUpdate3; 	//������ȣ
	private JLabel labelUpdate4; 	//����ȣ
	private JLabel labelUpdate5; 	//����� ��
	private JLabel labelUpdate6;	//��� Ÿ��
	private JLabel labelUpdate7; 	//"����, �浵
	//private JLabel label8 //"���"
	
	private JPanel rightUpdatePanel;
	
	private JPanel searchCaseNumPanel;	//���̽� �˻� �г�
	private JTextField caseNumTxt;		//��� ��ȣ �Է¶�
	private JButton searchUpdateBtn;	//��� ��ȣ �˻�
	
	private JTextField polnoUpdate;
	private JTextField carnoUpdate;
	private JTextField deadUpdate;
	private JTextField injuredUpdate;
	private JTextField accTypeUpdate;
	private JTextField latiUpdate;
	private JTextField longiUpdate;
	
	//����Էºκ�
	private JPanel locUpdate;
	private JComboBox proUpdate;
	private JComboBox towUpdate;
	
	//��¥�Էºκ�
	private JPanel timeUpdate;
	private JComboBox yearcbUpdate;
	private JComboBox monthcbUpdate;
	private JComboBox daycbUpdate;
	
	//������ �Է� �κ�
	private JPanel casualtyUpdate;
	private	JLabel tmp1Update;
	private	JLabel tmp2Update;
	
	//���� �浵 �Էºκ�
	private	JPanel locInfoUpdate;
	private	JLabel laTmpUpdate;
	private	JLabel loTmpUpdate;
	
	//�ϴ� �г�
	private JPanel subUpdatePanel;
	private JButton updateButton;
	private JButton deleteButton;
	
	//��� �м�----------------------------------------------------------------------------
	
	
	
	//�ϴ� �޼���-------------------------------------------------------------------------------
	private JLabel accInfo;
	//���콺 �̺�Ʈ-----------------------------------------------------------------------------
	MouseAction action = new MouseAction();
	MouseListen mouseMove = new MouseListen();
	
	public AppMain() {
		
		setTitle("���� ��� ���� �ý���");
		setSize(Execute.WIDTH,Execute.HEIGHT);
		setResizable(false);
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
		//bPanel.setBackground(Color.cyan);
		bPanel.setBounds(0, 0, 1300, 110);
		
		btns = new JButton[4];
		btns[0] = new JButton("��� ���� �˻�");
		btns[0].setBounds(120 + 20,10,200,90);
		bPanel.add(btns[0]);
		
		
		btns[1] = new JButton("��� ���");
		btns[1].setBounds(120 + 20 + 280,10,200,90);
		bPanel.add(btns[1]);
		
		btns[2] = new JButton("��� ���� ����/����");
		btns[2].setBounds(120 + 20 + 260 +20 +260 + 20,10,200,90);
		bPanel.add(btns[2]);
		
		btns[3] = new JButton("��� �м�");
		btns[3].setBounds(120 + 20 + 260 + 20 + 260 + 20 +260 + 20,10,200,90);
		
		//1300
		bPanel.add(btns[3]);
		
		for(int i=0;i<btns.length;i++) {
			btns[i].addActionListener(action);
			btns[i].addMouseListener(mouseMove);
		}
		primary.add(bPanel);
		
		
		//List ���� ����------------------------------------------------------------------------------
		table = new JTable(contents, header);

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
		searchUpPanel.setBounds(0,0,400,150);
		searchUpPanel.setBackground(Color.white);
		searchUpPanel.setLayout(null);
		
		siDolbl = new JLabel("��/��");
		siDolbl.setBounds(0,40,50,50);
		searchUpPanel.add(siDolbl);
		
		siDo = new JComboBox();
		siDo.setBounds(100,40,250,50);
		searchUpPanel.add(siDo);
		
		searchDownPanel = new JPanel();
		searchDownPanel.setBounds(0,150,400,150);
		searchDownPanel.setBackground(Color.white);
		searchDownPanel.setLayout(null);
		
		guGunlbl = new JLabel("��/��");
		guGunlbl.setBounds(0,0,50,50);
		searchDownPanel.add(guGunlbl);
		
		guGun = new JComboBox();
		guGun.setBounds(100,0,250,50);
		searchDownPanel.add(guGun);
		
		diaSearch.add(searchUpPanel);
		diaSearch.add(searchDownPanel);
		
		diaSearch.setVisible(true);
	}

	public void registration() {
		dia = new JDialog();
		dia.setTitle("��� ���");
		dia.setLayout(null);
		dia.setResizable(false);
		dia.setSize(810,700);
		
		//leftPanel ���� ���� --------------------------------------------------
		leftPanel = new JPanel();
		leftPanel.setBackground(Color.white);
		leftPanel.setLayout(new GridLayout(7,1));
		leftPanel.setBounds(0,0,100,600);
		dia.add(leftPanel);
		
		label1 = new JLabel("���",JLabel.CENTER);
		label2 = new JLabel("��¥",JLabel.CENTER);
		label3 = new JLabel("������ȣ",JLabel.CENTER);
		label4 = new JLabel("����ȣ",JLabel.CENTER);
		label5 = new JLabel("����� ��",JLabel.CENTER);
		label6 = new JLabel("��� Ÿ��",JLabel.CENTER);
		label7 = new JLabel("����, �浵",JLabel.CENTER);
		
		leftPanel.add(label1);
		leftPanel.add(label2);
		leftPanel.add(label3);
		leftPanel.add(label4);
		leftPanel.add(label5);
		leftPanel.add(label6);
		leftPanel.add(label7);
		
		//rightPanel ���� ���� --------------------------------------------------
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(7,1));
		rightPanel.setBounds(100,0,700,600);
		dia.add(rightPanel);
		
		polno = new JTextField(10);
		carno = new JTextField(10);
		dead = new JTextField(10);
		injured = new JTextField(10);
		accType = new JTextField(10);
		lati = new JTextField(10);
		longi = new JTextField(10);

		//����Էºκ�
		loc = new JPanel();
		loc.setLayout(new GridLayout(1,2));
		pro = new JComboBox(province);
		tow = new JComboBox();
		loc.add(pro);loc.add(tow);
		pro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox tmp = (JComboBox)arg0.getSource();
				String select = (String)tmp.getSelectedItem();

				if(select.equals("��ü")) {
					tow.setModel(new DefaultComboBoxModel());
				}
				else if(select.equals("����Ư����")) {
					tow.setModel(new DefaultComboBoxModel(sTown));
				}
				else if(select.equals("��õ������")) {
					tow.setModel(new DefaultComboBoxModel(iTown));
				}
				else {
					tow.setModel(new DefaultComboBoxModel(gyTown));
				}
			}
		});
		rightPanel.add(loc);
		
		//��¥�Էºκ�
		time = new JPanel();
		time.setLayout(new GridLayout(1,3));
		yearcb = new JComboBox(year);
		monthcb = new JComboBox(month);
		daycb = new JComboBox(day);
		time.add(yearcb);time.add(monthcb);time.add(daycb);
		yearcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox box = (JComboBox)arg0.getSource();
				String select = (String)box.getSelectedItem();

				if(select.equals("��ü")) {
					monthcb.setModel(new DefaultComboBoxModel());
					daycb.setModel(new DefaultComboBoxModel());
				}
				else {
					monthcb.setModel(new DefaultComboBoxModel(month));
					daycb.setModel(new DefaultComboBoxModel(day));
				}
			}
			
		});
		rightPanel.add(time);
		
		//������ȣ �Էºκ�
		rightPanel.add(polno);
		rightPanel.add(carno);
		
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
		subPanel.setBounds(0,600,810,100);
		dia.add(subPanel);
		
		regBtn = new JButton("���");
		regBtn.setBounds(405-20, 0, 40, 30);
		regBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton btn = (JButton)arg0.getSource();
				dia.dispose();
			}
		});
		subPanel.add(regBtn);
		
		dia.setVisible(true);
	}
	public void modifyDelete() {
		
		diaUpdate = new JDialog();
		diaUpdate.setTitle("��� ���");
		diaUpdate.setLayout(null);
		diaUpdate.setResizable(false);
		diaUpdate.setSize(810,800);
		
		//leftPanel ���� ���� --------------------------------------------------
		leftUpdatePanel = new JPanel();
		leftUpdatePanel.setBackground(Color.white);
		leftUpdatePanel.setLayout(new GridLayout(8,1));
		leftUpdatePanel.setBounds(0,0,100,700);
		diaUpdate.add(leftUpdatePanel);
		
		caseNum = new JLabel("��� ��ȣ �Է�", JLabel.CENTER);
		labelUpdate1 = new JLabel("���",JLabel.CENTER);
		labelUpdate2 = new JLabel("��¥",JLabel.CENTER);
		labelUpdate3 = new JLabel("������ȣ",JLabel.CENTER);
		labelUpdate4 = new JLabel("����ȣ",JLabel.CENTER);
		labelUpdate5 = new JLabel("����� ��",JLabel.CENTER);
		labelUpdate6 = new JLabel("��� Ÿ��",JLabel.CENTER);
		labelUpdate7 = new JLabel("����, �浵",JLabel.CENTER);
		
		leftUpdatePanel.add(caseNum);
		leftUpdatePanel.add(labelUpdate1);
		leftUpdatePanel.add(labelUpdate2);
		leftUpdatePanel.add(labelUpdate3);
		leftUpdatePanel.add(labelUpdate4);
		leftUpdatePanel.add(labelUpdate5);
		leftUpdatePanel.add(labelUpdate6);
		leftUpdatePanel.add(labelUpdate7);
		
		//rightPanel ���� ���� --------------------------------------------------
		rightUpdatePanel = new JPanel();
		rightUpdatePanel.setLayout(new GridLayout(8,1));
		rightUpdatePanel.setBounds(100,0,700,700);
		diaUpdate.add(rightUpdatePanel);
		
		polnoUpdate = new JTextField(10);
		carnoUpdate = new JTextField(10);
		deadUpdate = new JTextField(10);
		injuredUpdate = new JTextField(10);
		accTypeUpdate = new JTextField(10);
		latiUpdate = new JTextField(10);
		longiUpdate = new JTextField(10);

		//��� ��ȣ �Է� �κ�
		searchCaseNumPanel = new JPanel(new GridLayout(1,2));	//���̽� �˻� �г�
		caseNumTxt = new JTextField(10);		//��� ��ȣ �Է¶�
		searchUpdateBtn = new JButton("�˻�");	//��� ��ȣ �˻�
		searchCaseNumPanel.add(caseNumTxt);
		searchCaseNumPanel.add(searchUpdateBtn);
		rightUpdatePanel.add(searchCaseNumPanel);
		
		//����Էºκ�
		locUpdate = new JPanel();
		locUpdate.setLayout(new GridLayout(1,2));
		proUpdate = new JComboBox(province);
		towUpdate = new JComboBox();
		locUpdate.add(proUpdate);locUpdate.add(towUpdate);
		proUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox tmp = (JComboBox)arg0.getSource();
				String select = (String)tmp.getSelectedItem();

				if(select.equals("��ü")) {
					towUpdate.setModel(new DefaultComboBoxModel());
				}
				else if(select.equals("����Ư����")) {
					towUpdate.setModel(new DefaultComboBoxModel(sTown));
				}
				else if(select.equals("��õ������")) {
					towUpdate.setModel(new DefaultComboBoxModel(iTown));
				}
				else {
					towUpdate.setModel(new DefaultComboBoxModel(gyTown));
				}
			}
		});
		rightUpdatePanel.add(locUpdate);
		
		//��¥�Էºκ�
		timeUpdate = new JPanel();
		timeUpdate.setLayout(new GridLayout(1,3));
		yearcbUpdate = new JComboBox(year);
		monthcbUpdate = new JComboBox(month);
		daycbUpdate = new JComboBox(day);
		timeUpdate.add(yearcbUpdate);timeUpdate.add(monthcbUpdate);timeUpdate.add(daycbUpdate);
		yearcbUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox box = (JComboBox)arg0.getSource();
				String select = (String)box.getSelectedItem();

				if(select.equals("��ü")) {
					monthcbUpdate.setModel(new DefaultComboBoxModel());
					daycbUpdate.setModel(new DefaultComboBoxModel());
				}
				else {
					monthcbUpdate.setModel(new DefaultComboBoxModel(month));
					daycbUpdate.setModel(new DefaultComboBoxModel(day));
				}
			}
			
		});
		rightUpdatePanel.add(timeUpdate);
		
		//������ȣ �Էºκ�
		rightUpdatePanel.add(polnoUpdate);
		rightUpdatePanel.add(carnoUpdate);
		
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
		subUpdatePanel.setBounds(0,700,810,100);
		
		updateButton = new JButton("����");
		updateButton.setBounds(600,0,80,50);
		subUpdatePanel.add(updateButton);
		
		deleteButton = new JButton("����");
		deleteButton.setBounds(690, 0, 80,50);
		subUpdatePanel.add(deleteButton);
		
		diaUpdate.add(subUpdatePanel);
		
		
		
		diaUpdate.setVisible(true);
	}
	
	public void analysis()
	{
		new AccidentAnalysis();
	}
	
	private class MouseListen implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	private class MouseAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton btn = (JButton)arg0.getSource();
			for(int i=0;i<btns.length;i++) {
				if(btns[i]==btn) {
					if(i==0) {
						search();
					}
					else if(i==1) {
						registration();
					}
					else if(i==2) {
						modifyDelete();
					}
					else
					{
						analysis();
					}
				}
			}
		}			
	}
}

