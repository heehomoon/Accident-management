import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AppMain extends JFrame implements ActionListener,MouseListener{

	String initText = "<html>��ư ���λ��� ���÷���<br/>���콺�� ��ư���� �÷��ּ���</html>";
	JLabel accInfo;
	JLabel[] labels;
	JPanel[] panels;
	JButton[] btns;
	JList[] list;
	AccidentDataDAO dao;
	ArrayList<AccidentData> datas;
	Container c;

	public AppMain() {
		setTitle("��� ���� �ý���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = getContentPane();
		c.setLayout(null);


		//�ش� ��¥ ��� ������ �ǽð�? ����
		accInfo = new JLabel("�ش� ��¥ ��� ����");
		accInfo.setBounds(0, 0, 1200, 30);
		c.add(accInfo);


		//��� ��ư�� �߰�
		labels = new JLabel[5];
		labels[0] = new JLabel("��� ���� �˻�",JLabel.CENTER);
		labels[1] = new JLabel("��� ���",JLabel.CENTER);
		labels[2] = new JLabel("��� ���� ����",JLabel.CENTER);
		labels[3] = new JLabel("��� ��� ����",JLabel.CENTER);


		btns = new JButton[4];
		btns[0] = new JButton();
		btns[1] = new JButton();
		btns[2] = new JButton();
		btns[3] = new JButton();

		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(4,2,5,5));
		bPanel.setBounds(0, 30, 500, 570);
		for(int i=0;i<btns.length;i++) {
			labels[i].setSize(150,140);
			bPanel.add(labels[i]);
			btns[i].setSize(350,140);
			bPanel.add(btns[i]);
			btns[i].addActionListener(this);
			btns[i].addMouseListener(this);
			btns[i].setText(initText);
		}

		c.add(bPanel);

		//�޽����ڸ� �ӽù�ư
		JButton b = new JButton("�޽����κ�");
		b.setBounds(820, 40, 400, 570);

		c.add(b);


		setSize(1300,700);
		setVisible(true);
	}
	public void search() {
		JDialog dia = new JDialog();
		String[] tmp1 = {"1","2","3"};
		String[] tmp2 = {"4","5","6"};
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		BorderLayout layout = (BorderLayout)dia.getLayout();
		for(int i=0;i<10;i++) {
			panel1.add(new JList(tmp1));
		}
		for(int i=0;i<10;i++) {
			panel2.add(new JList(tmp2));
		}
		
		String[] list = {"��¥, ��, �� ���� �̿� �˻�","����ȣ�� �̿��� �� ���� �˻�","���� ��ȣ�� �̿��� ��� ���� �˻�"};
		JComboBox cb = new JComboBox(list);
		Component[] compoList = {panel1,panel2};
		dia.setTitle("��� ���� �˻�");
		dia.setLayout(new BorderLayout());
		dia.add(cb,BorderLayout.NORTH);
		
		dia.add(panel1,BorderLayout.CENTER);
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JComboBox cb = (JComboBox)arg0.getSource();
				String tmp = (String)cb.getSelectedItem();
				
				
				System.out.println(layout.getLayoutComponent(BorderLayout.CENTER));
				//Component[] list = dia.getComponents();
				if(tmp.equals("��¥, ��, �� ���� �̿� �˻�")) {

					//�ش� ��ġ�� ������Ʈ ���ΰ�ħ �ϰ� ������ �ȵ�
					//dia.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					dia.add(panel1,BorderLayout.CENTER);
					dia.revalidate();
					dia.repaint();
				}
				else if(tmp.equals("����ȣ�� �̿��� �� ���� �˻�")) {
					dia.remove(layout.getLayoutComponent(BorderLayout.CENTER));
					dia.add(panel2,BorderLayout.CENTER);
					dia.revalidate();
					dia.repaint();
				}
				else {
					
				}
			}
			
		});
		
		
		/*datas = dao.getAll();
		int col = dao.entireColNum(), row = dao.entireRowNum();

		list = new JList[col];*/
		//list[0] = 
		//�ش� ������ ���õ� ������� ��� Į���� ��� ���
		/*JPanel information = new JPanel();
		information.setLayout(new GridLayout(1,col));
		for(int i=0;i<col;i++) {

		}*/
		dia.setSize(1000,600);
		dia.setVisible(true);
	}
	
	public void registration() {
		JDialog dia = new JDialog();
		String[] year = {"�⵵","2000","2001","2002","2003","2004","2005","2006","2007","2008",
				"2009","2010","2011","2012","2013","2014","2015","2016","2017"};
		String[] month = {"��","1","2","3","4","5","6","7","8","9","10","11","12",};
		String[] day = {"��","1","2","3","4","5","6","7","8","9","10","11","12","13",
				"14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
		String[] province = {"��ü","����Ư����","�λ걤����","����������","�뱸������","��õ������","���ֱ�����","��걤����"};
		String[] sTown = {"�����","������","������","���ʱ�","������","������","���α�","�߱�","�߶���","���ϱ�","��õ��","��������",
				"���빮��","����","���۱�","������","���ı�","������","��걸","��õ��","���α�","������","���Ǳ�","���빮��","���ϱ�"};
		String[] bTown = {"�߱�","����","����","������","�λ�����","������","����","�ϱ�","�ؿ�뱸","���ϱ�","������","������","������","������","����","���屺"};
		String [] dgTown = {"�߱�","����","����","����","�ϱ�","������","�޼���","�޼���"};
		String[] iTown = {"�߱�","����","����","������","������","����","��籸","����","��ȭ��","������"};
		String[] gwTown = {"����","����","����","�ϱ�","���걸"};
		String[] dzTown = {"����","�߱�","����","������","�����"};
		String[] wTown = {"�߱�","����","����","�ϱ�","���ֱ�"};

		dia.setTitle("��� ���");
		//dia.setLayout(null);
		dia.setLayout(new GridLayout(9,2));
		JLabel label1 = new JLabel("���",JLabel.CENTER);
		JLabel label2 = new JLabel("��¥",JLabel.CENTER);
		JLabel label3 = new JLabel("������ȣ",JLabel.CENTER);
		JLabel label4 = new JLabel("����ȣ",JLabel.CENTER);
		JLabel label5 = new JLabel("����� ��",JLabel.CENTER);
		JLabel label6 = new JLabel("��� Ÿ��",JLabel.CENTER);
		JLabel label7 = new JLabel("����, �浵",JLabel.CENTER);
		JLabel label8 = new JLabel("���",JLabel.CENTER);
		
		
		JTextField polno = new JTextField(10);
		JTextField carno = new JTextField(10);
		JTextField dead = new JTextField(10);
		JTextField injured = new JTextField(10);
		JTextField accType = new JTextField(10);
		JTextField lati = new JTextField(10);
		JTextField longi = new JTextField(10);

		//����Էºκ�
		JPanel loc = new JPanel();
		loc.setLayout(new GridLayout(1,2));
		JComboBox pro = new JComboBox(province);
		JComboBox tow = new JComboBox();
		loc.add(pro);loc.add(tow);
		pro.addActionListener(new ActionListener() {

			@Override
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
				else if(select.equals("�λ걤����")) {
					tow.setModel(new DefaultComboBoxModel(bTown));
				}
				else if(select.equals("����������")) {
					tow.setModel(new DefaultComboBoxModel(dzTown));
				}
				else if(select.equals("�뱸������")) {
					tow.setModel(new DefaultComboBoxModel(dgTown));
				}
				else if(select.equals("��õ������")) {
					tow.setModel(new DefaultComboBoxModel(iTown));
				}
				else if(select.equals("���ֱ�����")) {
					tow.setModel(new DefaultComboBoxModel(gwTown));
				}
				else {
					tow.setModel(new DefaultComboBoxModel(wTown));
				}

			}

		});
		dia.add(label1);
		dia.add(loc);
		
		//��¥�Էºκ�
		JPanel time = new JPanel();
		time.setLayout(new GridLayout(1,3));
		JComboBox yearcb = new JComboBox(year);
		JComboBox monthcb = new JComboBox(month);
		JComboBox daycb = new JComboBox(day);
		time.add(yearcb);time.add(monthcb);time.add(daycb);
		
		dia.add(label2);
		dia.add(time);
		
		//������ȣ �Էºκ�
		dia.add(label3);
		dia.add(polno);
		
		//����ȣ �Էºκ�
		dia.add(label4);
		dia.add(carno);
		
		//������ �Է� �κ�
		JPanel casualty = new JPanel();
		casualty.setLayout(new GridLayout(1,4));
		JLabel tmp1 = new JLabel("����� ��");
		JLabel tmp2 = new JLabel("�λ��� ��");
		casualty.add(tmp1); casualty.add(dead);
		casualty.add(tmp2); casualty.add(injured);
		dia.add(label5);
		dia.add(casualty);
		
		//���Ÿ�� �Էºκ�
		dia.add(label6);
		dia.add(accType);
		
		//���� �浵 �Էºκ�
		JPanel locInfo = new JPanel();
		locInfo.setLayout(new GridLayout(1,4,70,0));
		JLabel laTmp = new JLabel("����");
		JLabel loTmp = new JLabel("�浵");
		locInfo.add(laTmp);locInfo.add(lati);
		locInfo.add(loTmp);locInfo.add(longi);
		
		dia.add(label7);
		dia.add(locInfo);
		
		//��Ϲ�ư
		JButton regBtn = new JButton("������ ���� ���� �ѹ� ��Ź�帳�ϴ�.");
		dia.add(label8);
		dia.add(regBtn);
		regBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton btn = (JButton)arg0.getSource();
				dia.dispose();
			}
			
		});
		
		dia.setSize(800,700);
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppMain();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
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
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		JButton btn = (JButton)arg0.getSource();
		btn.setText(initText);
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
