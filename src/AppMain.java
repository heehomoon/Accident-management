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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class AppMain extends JFrame{

	private String initText = "<html>��ư ���λ��� ���÷���<br/>���콺�� ��ư���� �÷��ּ���</html>";
	private JLabel accInfo;
	private JLabel[] labels;
	private JPanel[] panels;
	private JButton[] btns;
	
	private JPanel primary;
	
	//��ư----------------------------------------------------------------------------------
	private JPanel bPanel;
	
	
	//JList---------------------------------------------------------------------------------
	private JList list;	
	private JScrollPane scroll;
	private String [] listStr = {"���ѹα�", "�̱�"};
	
	//��� �˻�--------------------------------------------------------------------------------
	private JDialog diaSearch;
	private JPanel searchPanel;
	
	private JLabel siDolbl;
	private JLabel guGunlbl;
	private JComboBox siDo;
	private JComboBox guGun;
	
	//��� ���--------------------------------------------------------------------------------
	private JDialog dia;
	private	String[] year = {"�⵵","��ü","2000","2001","2002","2003","2004","2005","2006","2007","2008",
			"2009","2010","2011","2012","2013","2014","2015","2016","2017"};
	private String[] month = {"��","1","2","3","4","5","6","7","8","9","10","11","12",};
	private String[] day = {"��","1","2","3","4","5","6","7","8","9","10","11","12","13",
			"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
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
	
	private JPanel subPanel;
	JButton regBtn;
	
	//��� ����/����----------------------------------------------------------------------------
	
	
	
	//��� �м�----------------------------------------------------------------------------
	
	
	
	
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
		
		/*accInfo = new JLabel("�ش� ��¥ ��� ����");
		accInfo.setBounds(0, 670, 1200, 30);
		accInfo.setVerticalAlignment(SwingConstants.TOP);
		accInfo.setHorizontalAlignment(SwingConstants.LEFT);
		primary.add(accInfo);
		 */

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
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    list.setListData(listStr); //����Ʈ�� �����Ͱ� �� ��� ����
		//list.addListSelectionListener();
		
		scroll = new JScrollPane();
		scroll.setViewportView(list);
      	scroll.setBounds(20,110,1260, 510);
      	scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); //���ι���å
      	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //���ι���å
      	primary.add(scroll);
		
		add(primary);
		setVisible(true);
	}
	
	public void search() {
		
		diaSearch = new JDialog();
		diaSearch.setSize(new Dimension(400,300));
		diaSearch.setResizable(false);
		diaSearch.setTitle("��� ���� �˻�");
		
		searchPanel = new JPanel();
		searchPanel.setBounds(0,0,400,300);
		searchPanel.setBackground(Color.white);
		searchPanel.setLayout(null);
		diaSearch.add(searchPanel);
		
		siDolbl = new JLabel("��/��");
		siDolbl.setBounds(0,0,50,150);
		searchPanel.add(siDolbl);
		
		guGunlbl = new JLabel("��/��");
		guGunlbl.setBounds(0,150,50,150);
		searchPanel.add(guGunlbl);
		
		siDo = new JComboBox();
		siDo.setBounds(50,0,250,50);
		searchPanel.add(siDo);
		
		guGun = new JComboBox();
		guGun.setBounds(50,150,250,50);
		searchPanel.add(guGun);
		
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
		JPanel loc = new JPanel();
		loc.setLayout(new GridLayout(1,2));
		JComboBox pro = new JComboBox(province);
		JComboBox tow = new JComboBox();
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
		JPanel time = new JPanel();
		time.setLayout(new GridLayout(1,3));
		JComboBox yearcb = new JComboBox(year);
		JComboBox monthcb = new JComboBox(month);
		JComboBox daycb = new JComboBox(day);
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
		JPanel casualty = new JPanel();
		casualty.setLayout(new GridLayout(1,4));
		JLabel tmp1 = new JLabel("����� ��");
		JLabel tmp2 = new JLabel("�λ��� ��");
		casualty.add(tmp1); casualty.add(dead);
		casualty.add(tmp2); casualty.add(injured);
		rightPanel.add(casualty);
		
		//���Ÿ�� �Էºκ�
		rightPanel.add(accType);
		
		//���� �浵 �Էºκ�
		JPanel locInfo = new JPanel();
		locInfo.setLayout(new GridLayout(1,4,70,0));
		JLabel laTmp = new JLabel("����");
		JLabel loTmp = new JLabel("�浵");
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
	
	

	
	public void modify() {
		JDialog dia = new JDialog();
		dia.setTitle("��� ���� ����");
		dia.setSize(300,300);
		dia.setVisible(true);
	}
	public void delete() {
		JDialog dia = new JDialog();
		dia.setTitle("��� ��� ����");
		dia.setSize(300,300);
		dia.setVisible(true);
	}
	
	private class MouseListen implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			JButton btn = (JButton)arg0.getSource();
			for(int i=0;i<btns.length;i++) {
				if(btns[i] == btn) {
					if(i==0) {
						/*btns[i].setLayout(new GridLayout(3,1));
						btns[i].add(new JLabel("1. �Ⱓ, ��, �� ���� �̿� �˻�"));
						btns[i].add(new JLabel("2. ����ȣ�� �̿��� �� ���� �˻�"));
						btns[i].add(new JLabel("3. ���� ��ȣ�� �̿��� ��� ���� �˻�"));*/
						btns[i].setText("");
						btns[i].setText("<html>1. ��¥, ��, �� ���� �̿� �˻�"
								+ "<br/>2. ����ȣ�� �̿��� �� ���� �˻�"
								+ "<br/>3. ���� ��ȣ�� �̿��� ��� ���� �˻�</html>");
					}
					else if(i==1) {
						btns[i].setText("");
						btns[i].setText("<html>1. ���� ���� ���� ����<br/>2. �޽��� ���� ���� ����</html>");
					}
					else if(i==2) {
						btns[i].setText("");
						btns[i].setText("1. ��� ��ȸ �� ��� ��� ������ ����");
					}
					else {
						btns[i].setText("");
						btns[i].setText("<html>1. ó���� ��� ����<br/>2. ���� ��� ����</html>");
					}
				}
			}
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			JButton btn = (JButton)arg0.getSource();
			btn.setText(initText);
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
						modify();
					}
					else {
						delete();
					}
				}
			}
		}			
	}

}
