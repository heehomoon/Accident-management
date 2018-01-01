
//table ��ȯ�۾� �ʿ�
//���, ���� ������ ���
//Detail info ���� ���� ���
//Analysis Ʋ ���
//Detail info, Analysis ��ư �̺�Ʈ Ŭ���� ���� ����

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class AppMain extends JFrame{

   public boolean status = false;
   protected String [] header = {"����ȣ", "��/��", "��/��", "�߻���", "��", "��", "�����", "�����", "�λ���", "��� ����"};
   protected String contents[][] = {{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}
   ,{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}};
   
   protected   String[] year = {"�⵵","2000","2001","2002","2003","2004","2005","2006","2007","2008",
         "2009","2010","2011","2012","2013","2014","2015","2016","2017", "2018"};
   protected String[] month = {"��","01","02","03","04","05","06","07","08","09","10","11","12",};
   protected String[] day = {"��","01","02","03","04","05","06","07","08","09","10","11","12","13",
         "14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
   
   protected String[] province = {"��ü","����Ư����","��õ������","��⵵"};
   protected String[] accOptionType = {"������", "������", "�����ܵ�"};

   //�� Dialog MenuBar ��  Exit ��ư ����
   protected JLabel menuBar = new JLabel(ImageData.menuBar);
   protected JLabel menuBarSearch = new JLabel(ImageData.menuBarSearch);
   protected JLabel menuBarRegist = new JLabel(ImageData.menuBarRegist);
   protected JLabel menuBarUpdate = new JLabel(ImageData.menuBarUpdate);
   
   protected JButton exit = new JButton(ImageData.exitButtonBasic);
   protected JButton searchExit = new JButton(ImageData.exitButtonBasic);
   protected JButton registerExit = new JButton(ImageData.exitButtonBasic);
   protected JButton updateExit = new JButton(ImageData.exitButtonBasic);
   
   protected int mainMouseX; int mainMouseY;
   protected int searchMouseX; int searchMouseY;
   protected int registMouseX; int registMouseY;
   protected int updateMouseX; int updateMouseY;
   
   //�⺻ �ɼ� �г� ��----------------------------------------------------------------------
   protected JPanel[] panels;
   protected JLabel[] labels;
   protected Intro introPanel = new Intro();
   protected JPanel primary = new JPanel();
   

   //��ư----------------------------------------------------------------------------------
   protected JPanel bPanel;
   protected JButton[] btns;
   
   //JTable---------------------------------------------------------------------------------

   DefaultTableModel basicTable =new DefaultTableModel();
   
   protected JTable table = new JTable(basicTable)
   {
      public boolean isCellEditable(int row, int column)
      {
         return false;
      }
   };
   protected JScrollPane scroll;
   protected CardLayout cardLayout = new CardLayout();
   protected Container cardPanel = new JPanel();
   protected JPanel tablePanel = new JPanel();
   protected JPanel imagePanel = new JPanel();
   protected JLabel imageLabel = new JLabel(ImageData.mainImage);

   //��� �˻�--------------------------------------------------------------------------------
   protected JDialog diaSearch;
   protected JPanel searchUpPanel;
   protected JPanel searchDownPanel;
   protected JPanel searchButtonPanel;
   
   
   protected JLabel siDolbl;
   protected JLabel guGunlbl;
   protected JComboBox siDo = new JComboBox(province);
   protected JComboBox guGun = new JComboBox();
   
   protected JComboBox yearcbSearch = new JComboBox(year);
   protected JComboBox monthcbSearch  = new JComboBox(month);
   protected JButton searchButton = new JButton(ImageData.searchDialogBtnBasic);
   
   
   //��� ���--------------------------------------------------------------------------------
   protected JDialog dia;
   protected JPanel leftPanel;
   
   protected JLabel label1;   //���
   protected JLabel label2;    //��¥
   protected JLabel label3;    //������ȣ
   protected JLabel label5;    //����� ��
   protected JLabel label6;   //��� Ÿ��
   protected JLabel label7;    //"����, �浵
   
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
   protected   JLabel tmp1;
   protected   JLabel tmp2;
   
   //��� Ÿ�� �κ�
   protected JComboBox accType = new JComboBox(accOptionType);
   
   //���� �浵 �Էºκ�
   protected   JPanel locInfo;
   protected   JLabel laTmp;
   protected   JLabel loTmp;
   
   //�ϴ� �г�
   protected JPanel subPanel;
   protected JButton regBtn = new JButton(ImageData.regDialogBtnBasic);
   
   //��� ����/����-----------------------------------------------------------------------------------------
   protected JDialog diaUpdate;
   protected JPanel leftUpdatePanel;
   protected JLabel caseNum;         //��� ��ȣ
   
   protected JLabel labelUpdate1;   //���
   protected JLabel labelUpdate2;    //��¥
   protected JLabel labelUpdate3;    //������ȣ
   protected JLabel labelUpdate5;    //����� ��
   protected JLabel labelUpdate6;   //��� Ÿ��
   protected JLabel labelUpdate7;    //"����, �浵
   
   protected JPanel rightUpdatePanel;
   
   protected JPanel searchCaseNumPanel;   //���̽� �˻� �г�
   
   protected JTextField caseNumTxt = new JTextField();      //��� ��ȣ �Է¶�
   protected JButton searchUpdateBtn = new JButton(ImageData.updateSearchBtnBasic);   //��� ��ȣ �˻�;      //��� ��ȣ �˻�
   
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
   protected   JPanel casualtyUpdate;
   protected   JLabel tmp1Update;
   protected   JLabel tmp2Update;
   
   //���Ÿ�� �κ�
   protected JComboBox accTypeUpdate = new JComboBox(accOptionType);
   
   
   //���� �浵 �Էºκ�
   protected   JPanel locInfoUpdate;
   protected   JLabel laTmpUpdate;
   protected   JLabel loTmpUpdate;
   
   //�ϴ� �г�
   protected JPanel subUpdatePanel;
   protected JButton updateButton = new JButton(ImageData.updateDialogBtnBasic);
   protected JButton deleteButton = new JButton(ImageData.deleteDialogBtnBasic);
   
   //��� �м�----------------------------------------------------------------------------
   
   //�ϴ� �޼���-------------------------------------------------------------------------------
   protected JLabel accInfo;

   public AppMain() {
      
      AppManager.CreateInstance().setAppMain(this);
      setTitle("���� ��� ���� �ý���");
      setSize(Execute.WIDTH,Execute.HEIGHT-40);
      setResizable(false);
      setUndecorated(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //getContentPane().add(introPanel);
      getContentPane().add(primary);
      //------------------------------------------------------------------------
      exit.setBounds(1260, 5, 30, 30);
      exit.setBorderPainted(false);
      exit.setContentAreaFilled(false);
      exit.setFocusPainted(false);
      primary.add(exit);
      //-----------------------
      menuBar.setBounds(0,0,1300, 40);
      primary.add(menuBar);
      
      primary.setBounds(0,0,Execute.WIDTH,Execute.HEIGHT);
      primary.setLayout(null);
   
      bPanel = new JPanel();
      bPanel.setLayout(null);
      bPanel.setBounds(0, 0, 1300, 110);
      
      btns = new JButton[4];
      btns[0] = new JButton(ImageData.searchBasic);
      btns[0].setBounds(20,45,311,60);
      btns[0].setBorder(null);
      bPanel.add(btns[0]);
      
      btns[1] = new JButton(ImageData.regBasic);
      btns[1].setBounds(20+311 + 5,45,311,60);
      btns[1].setBorder(null);
      bPanel.add(btns[1]);
      
      btns[2] = new JButton(ImageData.updateBasic);
      btns[2].setBounds(20 + 311 + 5 + 311 + 5,45,311,60);
      btns[2].setBorder(null);
      bPanel.add(btns[2]);
      
      btns[3] = new JButton(ImageData.analysisBasic);
      btns[3].setBorder(null);
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
         
      setVisible(true);
   }
   
   public class Intro extends JPanel
   {
      //intro���� ��ҵ�------------------------------------------------------------------------
      JTextField idField = new JTextField();
       JPasswordField pwField = new JPasswordField();
        JButton loginBtn = new JButton(ImageData.loginBaiscImage);
        
      public Intro()
      {
         setBounds(0,0,Execute.WIDTH,Execute.HEIGHT);
         setLayout(null);
         idField.setBounds(980, 500, 140, 30);
            pwField.setBounds(980, 540, 140,30);
           loginBtn.setBounds(1130,500,70,70);
           
           add(idField);
          add(pwField);
         add(loginBtn);
      }
      public void paintComponent(Graphics g)
      {
         if(status == false)
         {
            super.paintComponents(g);
            g.drawImage(ImageData.introImage,0, 0, null);
            
         }
      }
      public void addActionLoginButtonListener(ActionListener action) {
         // TODO Auto-generated method stub
         introPanel.loginBtn.addActionListener(action);
      }
   }
   public void search() {
       diaSearch = new JDialog();
       diaSearch.setSize(430,300);
       diaSearch.setResizable(false);
       diaSearch.setUndecorated(true);
       diaSearch.setLayout(null);
       diaSearch.setTitle("��� ���� �˻�");
       
       JPanel upPanel = new JPanel();
       upPanel.setBounds(0,0,430,40);
       upPanel.setLayout(null);
       
       searchExit.setBounds(390, 5, 30, 30);
       searchExit.setBorderPainted(false);
       searchExit.setContentAreaFilled(false);
       searchExit.setFocusPainted(false);
       upPanel.add(searchExit);
       
       //-----------------------
       menuBarSearch.setBounds(0,0,430, 40);
       upPanel.add(menuBarSearch);
       diaSearch.add(upPanel);
       
       
       //���г�
       searchUpPanel = new JPanel();
       searchUpPanel.setBounds(0,40,100,200);
       searchUpPanel.setLayout(new GridLayout(3,1));
       searchUpPanel.setBackground(Color.LIGHT_GRAY);

      
       JPanel siDoPn = new JPanel();
       siDoPn.setLayout(null);
       siDoPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       siDolbl = new JLabel("��/��");
       siDolbl.setBounds(35,10,50,50);
       siDoPn.add(siDolbl);
       searchUpPanel.add(siDoPn);
       
       JPanel guGunPn = new JPanel();
       guGunPn.setLayout(null);
       guGunPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       guGunlbl = new JLabel("��/��");
       guGunlbl.setBounds(35,10,50,50);
       guGunPn.add(guGunlbl);
       searchUpPanel.add(guGunPn);
       
       JPanel CalPn = new JPanel();
       CalPn.setLayout(null);
       CalPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       JLabel calLbl = new JLabel("��/��");
       calLbl.setBounds(35,10,50,50);
       CalPn.add(calLbl);
       searchUpPanel.add(CalPn);
       
       //�޺��ڽ� �гΰ���
       searchDownPanel = new JPanel();
       searchDownPanel.setBounds(100,40,330,200);
       searchDownPanel.setBackground(Color.WHITE);
       searchDownPanel.setLayout(new GridLayout(3,1));
       
       JPanel sidoCbPn = new JPanel();
       sidoCbPn.setLayout(null);
       sidoCbPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       sidoCbPn.setBackground(Color.WHITE);
       siDo.setBounds(10,20,120,30);
       sidoCbPn.add(siDo);
       searchDownPanel.add(sidoCbPn);

       JPanel gunguCbPn = new JPanel();
       gunguCbPn.setLayout(null);
       gunguCbPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       gunguCbPn.setBackground(Color.WHITE);
       guGun.setBounds(10,20,120,30);
       gunguCbPn.add(guGun);
       searchDownPanel.add(gunguCbPn);
       
       JPanel calCbPn = new JPanel();
       calCbPn.setLayout(null);
       calCbPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       calCbPn.setBackground(Color.WHITE);
       yearcbSearch.setBounds(10,20,120,30);
       monthcbSearch.setBounds(170,20,100,30);
       calCbPn.add(yearcbSearch);calCbPn.add(monthcbSearch);
       
       searchDownPanel.add(calCbPn);

       
       //��ư�гΰ���
       searchButtonPanel = new JPanel();
       searchButtonPanel.setLayout(null);
       searchButtonPanel.setBounds(0,200+40,400,100);
       searchButton.setBounds(135, 5, 150, 50);
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
       dia.setUndecorated(true);
       
       JPanel upRegist = new JPanel();
       upRegist.setLayout(null);
       upRegist.setBounds(0,0,550,40);
       dia.add(upRegist);
       
       registerExit.setBounds(520, 5,30 ,30);
       registerExit.setBorderPainted(false);
       registerExit.setContentAreaFilled(false);
       registerExit.setFocusPainted(false);
       upRegist.add(registerExit);
       
       menuBarRegist.setBounds(0,0,550,40);
       upRegist.add(menuBarRegist);

       //leftPanel ���� ���� --------------------------------------------------
       leftPanel = new JPanel();
       leftPanel.setBackground(Color.LIGHT_GRAY);
       //leftPanel.setLayout(null);
       leftPanel.setLayout(new GridLayout(6,1));
       leftPanel.setBounds(0,40,100,430);

       JPanel lap1 = new JPanel();
       JPanel lap2 = new JPanel();
       JPanel lap3 = new JPanel();
       JPanel lap5 = new JPanel();
       JPanel lap6 = new JPanel();
       JPanel lap7 = new JPanel();
       lap1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lap2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lap3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lap5.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lap6.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lap7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

       lap1.setLayout(null);lap2.setLayout(null);lap3.setLayout(null);
       lap5.setLayout(null);lap6.setLayout(null);lap7.setLayout(null);

       label1 = new JLabel("���",JLabel.CENTER);
       label2 = new JLabel("��¥",JLabel.CENTER);
       label3 = new JLabel("������ȣ",JLabel.CENTER);
       label5 = new JLabel("����� ��",JLabel.CENTER);
       label6 = new JLabel("��� Ÿ��",JLabel.CENTER);
       label7 = new JLabel("����, �浵",JLabel.CENTER);


       label1.setBounds(35,20,30,30);
       label2.setBounds(35,20,30,30);
       label3.setBounds(20,20,60,30);
       label5.setBounds(20,20,60,30);
       label6.setBounds(20,20,60,30);
       label7.setBounds(20,20,60,30);
       lap1.add(label1);lap2.add(label2);lap3.add(label3);
       lap5.add(label5);lap6.add(label6);lap7.add(label7);


       leftPanel.add(lap1);
       leftPanel.add(lap2);
       leftPanel.add(lap3);
       leftPanel.add(lap5);
       leftPanel.add(lap6);
       leftPanel.add(lap7);

       dia.add(leftPanel);

       //rightPanel ���� ���� --------------------------------------------------

       rightPanel = new JPanel();
       rightPanel.setBounds(100,40,700,430);
       rightPanel.setLayout(new GridLayout(6,1));
       rightPanel.setBackground(Color.WHITE);

       dia.add(rightPanel);


       //����Էºκ�
       loc = new JPanel();
       loc.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       loc.setLayout(null);
       loc.setBackground(Color.WHITE);
       pro.setBounds(10,15,120,40);
       tow.setBounds(140,15,100,40);

       loc.add(pro);
       loc.add(tow);
       rightPanel.add(loc);

       //��¥�Էºκ�
       time = new JPanel();
       time.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       time.setLayout(null);
       time.setBackground(Color.WHITE);

       yearcb.setBounds(10,15,120,40);
       monthcb.setBounds(140,15,100,40);
       daycb.setBounds(260,15,100,40);

       time.add(yearcb);
       time.add(monthcb);
       time.add(daycb);
       rightPanel.add(time);

       //������ȣ �Էºκ�
       JPanel tm = new JPanel();
       tm.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       tm.setLayout(null);
       tm.setBackground(Color.WHITE);
       polno.setFont(new Font("����",Font.PLAIN,20));
       polno.setBounds(10,15,120,40);
       tm.add(polno);
       rightPanel.add(tm);

       //������ �Է� �κ�
       casualty = new JPanel();
       casualty.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       casualty.setLayout(null);
       casualty.setBackground(Color.WHITE);

       tmp1 = new JLabel("����� ��");
       tmp2 = new JLabel("�λ��� ��");

       tmp1.setBounds(10,15,60,40);
       dead.setBounds(80,15,50,40);
       dead.setFont(new Font("����",Font.PLAIN,15));
       tmp2.setBounds(140,15,60,40);
       injured.setBounds(210,15,50,40);
       injured.setFont(new Font("����",Font.PLAIN,15));

       casualty.add(tmp1); casualty.add(dead);
       casualty.add(tmp2); casualty.add(injured);
       rightPanel.add(casualty);

       //���Ÿ�� �Էºκ�
       JPanel typeTmp = new JPanel();
       typeTmp.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       typeTmp.setLayout(null);
       accType.setBounds(10,15,120,40);
       typeTmp.setBackground(Color.WHITE);
       typeTmp.add(accType);
       rightPanel.add(typeTmp);

       //���� �浵 �Էºκ�
       locInfo = new JPanel();
       locInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       locInfo.setLayout(null);
       locInfo.setBackground(Color.WHITE);
       laTmp = new JLabel("����");
       loTmp = new JLabel("�浵");

       laTmp.setBounds(10,15,40,40);
       lati.setBounds(60,15,70,40);
       lati.setFont(new Font("����",Font.PLAIN,15));
       loTmp.setBounds(140,15,40,40);
       longi.setBounds(190,15,70,40);
       longi.setFont(new Font("����",Font.PLAIN,15));
       locInfo.add(laTmp);locInfo.add(lati);
       locInfo.add(loTmp);locInfo.add(longi);
       rightPanel.add(locInfo);

       //subPanel----------------------------------------------------------------------------
       subPanel = new JPanel();
       subPanel.setLayout(null);
       subPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       subPanel.setBounds(0,470,550,120);
       regBtn.setBounds(193, 15, 150, 50);
       subPanel.add(regBtn);
       dia.add(subPanel);


       dia.setVisible(true);
    }
    public void modifyDelete() {

       diaUpdate = new JDialog();
       diaUpdate.setTitle("��� ���� / ����");
       diaUpdate.setLayout(null);
       diaUpdate.setResizable(false);
       diaUpdate.setSize(550,550);
       diaUpdate.setUndecorated(true);
       
       JPanel upUpdate = new JPanel();
       upUpdate.setLayout(null);
       upUpdate.setBounds(0,0,550,40);
       diaUpdate.add(upUpdate);
       
       updateExit.setBounds(520, 5,30 ,30);
       updateExit.setBorderPainted(false);
       updateExit.setContentAreaFilled(false);
       updateExit.setFocusPainted(false);
       upUpdate.add(updateExit);
       
       menuBarUpdate.setBounds(0,0,550,40);
       upUpdate.add(menuBarUpdate);

       
       //leftPanel ���� ���� --------------------------------------------------
       leftUpdatePanel = new JPanel();
       leftUpdatePanel.setBackground(Color.LIGHT_GRAY);
       leftUpdatePanel.setLayout(new GridLayout(7,1));
       leftUpdatePanel.setBounds(0,40,100+20,430);

       JPanel caseLap = new JPanel();
       JPanel lapUpdate1 = new JPanel();
       JPanel lapUpdate2 = new JPanel();
       JPanel lapUpdate3 = new JPanel();
       JPanel lapUpdate5 = new JPanel();
       JPanel lapUpdate6 = new JPanel();
       JPanel lapUpdate7 = new JPanel();

       caseLap.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate5.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate6.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       lapUpdate7.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

       caseLap.setLayout(null);
       lapUpdate1.setLayout(null);lapUpdate2.setLayout(null);lapUpdate3.setLayout(null);
       lapUpdate5.setLayout(null);lapUpdate6.setLayout(null);lapUpdate7.setLayout(null);


       caseNum = new JLabel("����ȣ �Է�",JLabel.CENTER);
       labelUpdate1 = new JLabel("���",JLabel.CENTER);
       labelUpdate2 = new JLabel("��¥",JLabel.CENTER);
       labelUpdate3 = new JLabel("������ȣ",JLabel.CENTER);
       labelUpdate5 = new JLabel("����� ��",JLabel.CENTER);
       labelUpdate6 = new JLabel("��� Ÿ��",JLabel.CENTER);
       labelUpdate7 = new JLabel("����, �浵",JLabel.CENTER);

       caseNum.setBounds(15, 15, 90, 30);
       labelUpdate1.setBounds(43,18,30,30);
       labelUpdate2.setBounds(43,18,30,30);
       labelUpdate3.setBounds(28,18,60,30);
       labelUpdate5.setBounds(28,18,60,30);
       labelUpdate6.setBounds(28,18,60,30);
       labelUpdate7.setBounds(28,18,60,30);

       caseLap.add(caseNum);
       lapUpdate1.add(labelUpdate1);lapUpdate2.add(labelUpdate2);lapUpdate3.add(labelUpdate3);
       lapUpdate5.add(labelUpdate5);lapUpdate6.add(labelUpdate6);lapUpdate7.add(labelUpdate7);


       leftUpdatePanel.add(caseLap);
       leftUpdatePanel.add(lapUpdate1);
       leftUpdatePanel.add(lapUpdate2);
       leftUpdatePanel.add(lapUpdate3);
       leftUpdatePanel.add(lapUpdate5);
       leftUpdatePanel.add(lapUpdate6);
       leftUpdatePanel.add(lapUpdate7);

       diaUpdate.add(leftUpdatePanel);

       //rightPanel ���� ���� --------------------------------------------------

       rightUpdatePanel = new JPanel();
       rightUpdatePanel.setBounds(100+20,40,700-20,430);
       rightUpdatePanel.setLayout(new GridLayout(7,1));
       rightUpdatePanel.setBackground(Color.WHITE);

       diaUpdate.add(rightUpdatePanel);

       //��� ��ȣ �Է� �κ�
       searchCaseNumPanel = new JPanel();//���̽� �˻� �г�
       searchCaseNumPanel.setLayout(null);
       searchCaseNumPanel.setBackground(Color.WHITE);
       searchCaseNumPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       caseNumTxt = new JTextField(10);      //��� ��ȣ �Է¶�
       caseNumTxt.setFont(new Font("����",Font.PLAIN,20));
       caseNumTxt.setBounds(10,7,70,50);
       searchUpdateBtn.setBounds(90,7,70,50);
       searchCaseNumPanel.add(caseNumTxt);
       searchCaseNumPanel.add(searchUpdateBtn);
       rightUpdatePanel.add(searchCaseNumPanel);

       //����Էºκ�
       locUpdate = new JPanel();
       locUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       locUpdate.setLayout(null);
       locUpdate.setBackground(Color.WHITE);
       proUpdate.setBounds(10,10,120,40);
       towUpdate.setBounds(140,10,100,40);

       locUpdate.add(proUpdate);
       locUpdate.add(towUpdate);
       rightUpdatePanel.add(locUpdate);

       //��¥�Էºκ�
       timeUpdate = new JPanel();
       timeUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       timeUpdate.setLayout(null);
       timeUpdate.setBackground(Color.WHITE);

       yearcbUpdate.setBounds(10,10,120,40);
       monthcbUpdate.setBounds(140,10,100,40);
       daycbUpdate.setBounds(260,10,100,40);

       timeUpdate.add(yearcbUpdate);
       timeUpdate.add(monthcbUpdate);
       timeUpdate.add(daycbUpdate);
       rightUpdatePanel.add(timeUpdate);

       //������ȣ �Էºκ�
       JPanel tmUpdate = new JPanel();
       tmUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       tmUpdate.setLayout(null);
       tmUpdate.setBackground(Color.WHITE);
       polnoUpdate.setFont(new Font("����",Font.PLAIN,20));
       polnoUpdate.setBounds(10,10,120,40);
       tmUpdate.add(polnoUpdate);
       rightUpdatePanel.add(tmUpdate);

       //������ �Է� �κ�
       casualtyUpdate = new JPanel();
       casualtyUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       casualtyUpdate.setLayout(null);
       casualtyUpdate.setBackground(Color.WHITE);

       tmp1Update = new JLabel("����� ��");
       tmp2Update = new JLabel("�λ��� ��");

       tmp1Update.setBounds(10,10,60,40);
       deadUpdate.setBounds(80,10,50,40);
       deadUpdate.setFont(new Font("����",Font.PLAIN,15));
       tmp2Update.setBounds(140,10,60,40);
       injuredUpdate.setBounds(210,10,50,40);
       injuredUpdate.setFont(new Font("����",Font.PLAIN,15));

       casualtyUpdate.add(tmp1Update); casualtyUpdate.add(deadUpdate);
       casualtyUpdate.add(tmp2Update); casualtyUpdate.add(injuredUpdate);
       rightUpdatePanel.add(casualtyUpdate);

       //���Ÿ�� �Էºκ�
       JPanel typeTmpUpdate = new JPanel();
       typeTmpUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       typeTmpUpdate.setLayout(null);
       accTypeUpdate.setBounds(10,10,120,40);
       typeTmpUpdate.setBackground(Color.WHITE);
       typeTmpUpdate.add(accTypeUpdate);
       rightUpdatePanel.add(typeTmpUpdate);

       //���� �浵 �Էºκ�
       locInfoUpdate = new JPanel();
       locInfoUpdate.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       locInfoUpdate.setLayout(null);
       locInfoUpdate.setBackground(Color.WHITE);
       laTmpUpdate = new JLabel("����");
       loTmpUpdate = new JLabel("�浵");

       
       laTmpUpdate.setBounds(10,15,40,40);
       latiUpdate.setBounds(60,15,70,40);
       latiUpdate.setFont(new Font("����",Font.PLAIN,15));
       loTmpUpdate.setBounds(140,15,40,40);
       longiUpdate.setBounds(190,15,70,40);
       
       longiUpdate.setFont(new Font("����",Font.PLAIN,15));
       laTmpUpdate.setBounds(10,10,40,40);
       latiUpdate.setBounds(60,10,70,40);
       latiUpdate.setFont(new Font("����",Font.PLAIN,15));
       loTmpUpdate.setBounds(140,10,40,40);
       longiUpdate.setBounds(190,10,70,40);
       longiUpdate.setFont(new Font("����",Font.PLAIN,15));
       locInfoUpdate.add(laTmpUpdate);locInfoUpdate.add(latiUpdate);
       locInfoUpdate.add(loTmpUpdate);locInfoUpdate.add(longiUpdate);
       rightUpdatePanel.add(locInfoUpdate);

       //subPanel----------------------------------------------------------------------------
       subUpdatePanel = new JPanel();
       subUpdatePanel.setLayout(null);
       subUpdatePanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
       subUpdatePanel.setBounds(0,470,550,120);
       updateButton.setBounds(100,10,150,50);
       deleteButton.setBounds(290,10,150,50);
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
   
   public void addMouseAdapterHoverListener(MouseAdapter mouse)
   {
	   btns[0].addMouseListener(mouse);
	   btns[1].addMouseListener(mouse);
	   btns[2].addMouseListener(mouse);
	   btns[3].addMouseListener(mouse);
   }
   
   public void addMouseAdapterButtonListener(MouseAdapter mouse)
   {
      menuBar.addMouseListener(mouse);
      menuBarSearch.addMouseListener(mouse);
      menuBarRegist.addMouseListener(mouse);
      menuBarUpdate.addMouseListener(mouse);
   }
   
   public void addMouseMotionButtonListener(MouseMotionAdapter mouse)
   {
      menuBar.addMouseMotionListener(mouse);
      menuBarSearch.addMouseMotionListener(mouse);
      menuBarRegist.addMouseMotionListener(mouse);
      menuBarUpdate.addMouseMotionListener(mouse);
   }
   
   public void addExitMouseListener(MouseAdapter mouse)
   {
      exit.addMouseListener(mouse);
      searchExit.addMouseListener(mouse);
      registerExit.addMouseListener(mouse);
      updateExit.addMouseListener(mouse);   
   }
   
   public void addTableListener(MouseAdapter mouse)
   {
      table.addMouseListener(mouse);
   }

}