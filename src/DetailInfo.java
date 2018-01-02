import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class DetailInfo extends JDialog{

   protected static final String LS = System.getProperty("line.separator");      //html ���� ���� ��ɾ� 

   protected JLabel menuBarDetail = new JLabel(ImageData.menuBarDetail);
   protected JButton detailExit = new JButton(ImageData.exitButtonBasic);
   protected int mouseX; int mouseY;
   
   private AccidentCase detailCase = new AccidentCase();
   
   private String lat;
   private String lng;

   private JPanel primary;
   private JPanel boxPanel;
   private JLabel yearlbl;
   private JLabel monthlbl;
   private JButton searchDateBtn;
   
   private Javascript script = new Javascript();
   private JPanel infoPanel;
   
   private JLabel cscode;         //��� ��ȣ
   private JLabel province;        //��,��
   private JLabel town;            //��,��
   private JLabel year;            //�⵵
   private JLabel policeno;        //������ȣ
   private JLabel casulity;        //����� = ����� + �λ���
   private JLabel dead;            //�����
   private JLabel injured;         //�λ��� 
   private JLabel actype;          //������� (������, ������, �����ܵ�)
   private JLabel latitude;        //����
   private JLabel longitude;       //�浵 
   
   private JPanel policePanel;
   private JLabel policeNo;   //���� ��ȣ
   private JLabel polname;    //���� �̸�
   private JLabel rank;       //���� ���
   private JLabel depart;     //�Ҽ� �μ�
   private JLabel dpcode;     //�Ҽ� �μ� �ڵ�
   
   private JPanel mapPanel;
   
   public DetailInfo(AccidentCase tempCase)
   {
       setSize(930,505);
       setLayout(null);
       setResizable(false);
       setUndecorated(true);
       
	  JPanel upPanel = new JPanel();
	  upPanel.setBounds(0,0,940,40);
	  upPanel.setLayout(null);
	 
	  detailExit.setBounds(900, 5, 30, 30);
	  detailExit.setBorderPainted(false);
	  detailExit.setContentAreaFilled(false);
	  detailExit.setFocusPainted(false);
	  upPanel.add(detailExit);
	  
	  menuBarDetail.setBounds(0,0,940, 40);
	  upPanel.add(menuBarDetail);
	  add(upPanel);
	 
      detailCase = tempCase;
      
      this.lat = lat;
      this.lng = lng;
      
      primary = new JPanel();
      primary.setLayout(null);
      primary.setBounds(0,40,940,500);
      
      //AccidentCase Panel-----------------------------------------------------------------------------
      infoPanel = new JPanel();
      infoPanel.setLayout(null);
      infoPanel.setBounds(0,10,200,300);
      primary.add(infoPanel);
      
      cscode = new JLabel();
      cscode.setText("����ȣ: " +detailCase.getCscode());
      cscode.setBounds(10,0,210,300/13);
      infoPanel.add(cscode);
      
      province = new JLabel();
      province.setText("��/��: " + detailCase.getProvince());
      province.setBounds(10, 300/13 + 10, 200, 300/13);
      infoPanel.add(province);
      
      town = new JLabel();
      town.setText("��/��: " + detailCase.getTown());
      town.setBounds(10, 300/13*2 + 10, 200, 300/13);
      infoPanel.add(town);
      
      year = new JLabel();
      year.setText("�߻� ��¥: " + detailCase.getYear() + "�� " + detailCase.getMonth() + "�� " + detailCase.getDay() + "��");
      year.setBounds(10, 300/13*3 + 10, 200, 300/13);
      infoPanel.add(year);
      
      casulity = new JLabel();
      casulity.setText("����ڼ�: " + detailCase.getCasulity());
      casulity.setBounds(10,300/13*5 + 10, 200, 300/13);
      infoPanel.add(casulity);
      
      dead = new JLabel();
      dead.setText("����ڼ�: " + detailCase.getDead());
      dead.setBounds(10,300/13*6 + 10, 200, 300/13);
      infoPanel.add(dead);
      
      injured = new JLabel();
      injured.setText("�λ��ڼ�: " + detailCase.getInjured());
      injured.setBounds(10, 300/13*7 + 10, 200, 300/13);
      infoPanel.add(injured);
      
      actype = new JLabel();
      actype.setText("�������: " + detailCase.getActype());
      actype.setBounds(10, 300/13*8 + 10,  200, 300/13);
      infoPanel.add(actype);
      
      latitude = new JLabel();
      latitude.setText("����: " + detailCase.getLatitude());
      latitude.setBounds(10,300/13*9 + 10, 200,300/13);
      infoPanel.add(latitude);
      
      longitude = new JLabel();
      longitude.setText("�浵: " + detailCase.getLongitude());
      longitude.setBounds(10, 300/13*10 +10, 200, 300/13);
      infoPanel.add(longitude);
      
      //���� ����---------------------------------------------------------------------
      
      
      NativeInterface.open();         
       SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createContent();
            add(primary);
           
            repaint();
         }
       });
       setVisible(true);
      
   }
   
   public void createContent() {
          
         JPanel webBrowserPanel = new JPanel(new BorderLayout());               //������ ����� �г� ���� �� �ʱ�ȭ
         webBrowserPanel.setBounds(200,10,700,430);                           //�г� ũ�� ����
      
         final JWebBrowser webBrowser = new JWebBrowser();                     //�������� ��ü ����
          webBrowser.setBounds(0,0,680,400);
         webBrowser.setBarsVisible(false);
          webBrowser.setStatusBarVisible(true);
          final String htmlContent =
            "<html>" + LS +
            "<head>" + LS +
            "<style>\r\n"  + LS +
            "body {background-image: url(\"https://i.ytimg.com/vi/7Uvmww35oxw/maxresdefault.jpg\");\r\n"  + LS +
            "      background-repeat: no-repeat;\r\n"  + LS +
            "      background-attachment: fixed;\r\n"  + LS +
            "      background-position: right bottom;}\r\n"  + LS +
            "table, td {background-color: transparent;} \r\n"  + LS + 
            "</style>" + LS +
            "   </head>" + LS +
            "  <body>" + LS +
            "   <div id = \"map\"> <font size=30, color = white> <center> Loading... </center> </font> </div>" + LS +
            "  </body>" + LS +
            "</html>";
          
          webBrowser.setHTMLContent(htmlContent);
          webBrowser.addWebBrowserListener(new WebBrowserListener(){
            public void commandReceived(WebBrowserCommandEvent arg0) {
               
            }
            public void loadingProgressChanged(WebBrowserEvent arg0) {
            }
            public void locationChangeCanceled(WebBrowserNavigationEvent arg0) {
            }
            public void locationChanged(WebBrowserNavigationEvent arg0) {                  //�������� ���� ��ġ�� �ٲ���� ��
               
               script.setDetailMain(Double.toString(detailCase.getLatitude()), Double.toString(detailCase.getLongitude()));
               webBrowser.executeJavascript(script.getScript());   
            }
            public void locationChanging(WebBrowserNavigationEvent arg0) {
            }
            public void statusChanged(WebBrowserEvent arg0) {
            }
            public void titleChanged(WebBrowserEvent arg0) {
            }
            public void windowClosing(WebBrowserEvent arg0) {
            }
            public void windowOpening(WebBrowserWindowOpeningEvent arg0) {
            }
            public void windowWillOpen(WebBrowserWindowWillOpenEvent arg0) {
            }
          });            
          webBrowserPanel.add(webBrowser);                                       //���������� ���� �г�(webBrowserPanel)�� ���̱�
          primary.add(webBrowserPanel);                                          //subPanel �� ���� �г� ���̱�
         
   }

   public void addMouseDetailMenuBarListener(MouseAdapter mouse)
   {
	   menuBarDetail.addMouseListener(mouse);
   }
   public void addMouseDetailMenuBarMotionListener(MouseMotionAdapter mouse)
   {
	   menuBarDetail.addMouseMotionListener(mouse);
   }
   public void addMouseDetailExitListener(MouseAdapter mouse)
   {
	   detailExit.addMouseListener(mouse);
   }
   
   public void paint(Graphics g)
   {
      super.paintComponents(g);
      this.repaint();
   }
}