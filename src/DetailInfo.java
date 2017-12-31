import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

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

	protected static final String LS = System.getProperty("line.separator");		//html ���� ���� ��ɾ� 

	private AccidentCase detailCase = new AccidentCase();
	private Police police = new Police();
	
	private String lat;
	private String lng;

	private JPanel primary;
	private JPanel boxPanel;
	private JLabel yearlbl;
	private JLabel monthlbl;
	private JButton searchDateBtn;
	
	private Javascript script = new Javascript();
	private JPanel infoPanel;
	
	private JLabel cscode;   		//��� ��ȣ
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
	
	public DetailInfo(AccidentCase tempCase, Police tempPolice)
	{
		detailCase = tempCase;
		police = tempPolice;
		
		this.lat = lat;
		this.lng = lng;
		
		primary = new JPanel();
		primary.setLayout(null);
		primary.setBounds(0,0,600,500);
		
		//AccidentCase Panel-----------------------------------------------------------------------------
		infoPanel = new JPanel();
		infoPanel.setLayout(null);
		infoPanel.setBounds(0,0,300,300);
		primary.add(infoPanel);
		
		cscode = new JLabel();
		cscode.setText("����ȣ: " +detailCase.getCscode());
		cscode.setBounds(0,0,300,300/13);
		infoPanel.add(cscode);
		
		province = new JLabel();
		province.setText("��/��: " + detailCase.getProvince());
		province.setBounds(0, 300/13, 300, 300/13);
		infoPanel.add(province);
		
		town = new JLabel();
		town.setText("��/��: " + detailCase.getTown());
		town.setBounds(0, 300/13*2, 300, 300/13);
		infoPanel.add(town);
		
		year = new JLabel();
		year.setText("�߻� ��¥: " + detailCase.getYear() + "�� " + detailCase.getMonth() + "�� " + detailCase.getDay() + "��");
		year.setBounds(0, 300/13*3, 300, 300/13);
		infoPanel.add(year);
		
		policeno = new JLabel();
		policeno.setText("��� ������ȣ: "+ detailCase.getPoliceno());
		policeno.setBounds(0, 300/13*4, 300, 300/13);
		infoPanel.add(policeno);
		
		casulity = new JLabel();
		casulity.setText("����ڼ�: " + detailCase.getCasulity());
		casulity.setBounds(0,300/13*5, 300, 300/13);
		infoPanel.add(casulity);
		
		dead = new JLabel();
		dead.setText("����ڼ�: " + detailCase.getDead());
		dead.setBounds(0,300/13*6, 300, 300/13);
		infoPanel.add(dead);
		
		injured = new JLabel();
		injured.setText("�λ��ڼ�: " + detailCase.getInjured());
		injured.setBounds(0, 300/13*7, 300, 300/13);
		infoPanel.add(injured);
		
		actype = new JLabel();
		actype.setText("�������: " + detailCase.getActype());
		actype.setBounds(0, 300/13*8,  300, 300/13);
		infoPanel.add(actype);
		
		latitude = new JLabel();
		latitude.setText("����: " + detailCase.getLatitude());
		latitude.setBounds(0,300/13*9, 300,300/13);
		infoPanel.add(latitude);
		
		longitude = new JLabel();
		longitude.setText("�浵: " + detailCase.getLongitude());
		longitude.setBounds(0, 300/13*10, 300, 300/13);
		infoPanel.add(longitude);
		
		//���� �г�---------------------------------------------------------------------------------
		policePanel = new JPanel();
		policePanel.setLayout(null);
		policePanel.setBounds(0,300,300,200);
		primary.add(policePanel);
		
		policeNo = new JLabel();   //���� ��ȣ
		policeNo.setText("��� ������ȣ: " + police.getPoliceno());
		policeNo.setBounds(0,0, 300,200/5);
		policePanel.add(policeNo);
		
		polname = new JLabel();    //���� �̸�
		polname.setText("��������: " + police.getPolname());
		polname.setBounds(0,200/5, 300,200/5);
		policePanel.add(policeNo);
		
		rank = new JLabel();       //���� ���
		rank.setText("���: " + police.getRank());
		rank.setBounds(0, 200/5*2, 300,200/5);
		policePanel.add(rank);
		
		depart = new JLabel();     //�Ҽ� �μ�
		depart.setText("�ҼӺμ�: " + police.getDepart());
		depart.setBounds(0,200/5*3, 300,200/5);
		policePanel.add(depart);
		
		dpcode = new JLabel();     //�Ҽ� �μ� �ڵ�
		dpcode.setText("�ҼӺμ��ڵ�: "  + police.getDpcode());
		dpcode.setBounds(0,200/5*4, 300,200/5);
		policePanel.add(dpcode);
		primary.add(policePanel);
		
		//���� ����---------------------------------------------------------------------
		mapPanel = new JPanel();
		mapPanel.setBounds(300,0,300,600);
		mapPanel.setLayout(null);
		primary.add(mapPanel);
		
		
		NativeInterface.open();			
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  setSize(600,500);
	    	  setLayout(null);
	    	  setResizable(false);
	    	  createContent();
	    	  add(primary);
	  		
	    	  repaint();
	      }
	    });
	    setVisible(true);
		
	}
	
	public void createContent() {
		    
			JPanel webBrowserPanel = new JPanel(new BorderLayout());					//������ ����� �г� ���� �� �ʱ�ȭ
			webBrowserPanel.setBounds(10,10,280,480);									//�г� ũ�� ����
			
		    final JWebBrowser webBrowser = new JWebBrowser();							//�������� ��ü ����
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
		      "	</head>" + LS +
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
				public void locationChanged(WebBrowserNavigationEvent arg0) {						//�������� ���� ��ġ�� �ٲ���� ��
					
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
		    webBrowserPanel.add(webBrowser);													//���������� ���� �г�(webBrowserPanel)�� ���̱�
		    mapPanel.add(webBrowserPanel);														//subPanel �� ���� �г� ���̱�
		   
	}

	public void paint(Graphics g)
	{
		super.paintComponents(g);
		this.repaint();
	}
}
