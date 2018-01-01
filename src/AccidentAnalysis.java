import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserCommandEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserListener;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserNavigationEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowOpeningEvent;
import chrriis.dj.nativeswing.swtimpl.components.WebBrowserWindowWillOpenEvent;

public class AccidentAnalysis extends JDialog{

	protected static final String LS = System.getProperty("line.separator");		//html ���� ���� ��ɾ� 
	private	String[] year = {"��ü", "2012","2013","2014","2015","2016"};
	
	private ArrayList <AccidentCase> accList = new ArrayList<AccidentCase>();
	
	protected JLabel menuBarAnalysis = new JLabel(ImageData.menuBarAnalysis);
	protected JButton analysisExit = new JButton(ImageData.exitButtonBasic);
	protected int mouseX; int mouseY;
	
	private JPanel primary;
	private JPanel boxPanel;
	private JLabel yearlbl;
	
	private JComboBox yearBox;
	private JButton searchDateBtn;
	private JButton parsingButton;
	private JButton deleteButton;
	
	private Javascript script = new Javascript();
	private JPanel infoPanel;

	// ��Ʈ
	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	final JWebBrowser webBrowser = new JWebBrowser();							//�������� ��ü ����
    ;
	
	public AccidentAnalysis()
	{
		setUndecorated(true);
		setSize(1300,640);
  	  	setLayout(null);
  	  	setResizable(false);
 
	    JPanel upPanel = new JPanel();
	    upPanel.setBounds(0,0,1300,40);
	    upPanel.setLayout(null);
      
	    analysisExit.setBounds(1260, 5, 30, 30);
	    analysisExit.setBorderPainted(false);
	    analysisExit.setContentAreaFilled(false);
	    analysisExit.setFocusPainted(false);
	    analysisExit.addMouseListener(new MouseAdapter(){
					
					@Override
					public void mouseEntered(MouseEvent e)
					{
						analysisExit.setIcon(ImageData.exitButtonEntered);
					}
					@Override
					public void mouseExited(MouseEvent e)
					{
						analysisExit.setIcon(ImageData.exitButtonBasic);
					}
					@Override
					public void mouseReleased(MouseEvent e)
					{
						dispose();
						AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
						AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
						AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
						AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
						AppManager.CreateInstance().getAppController().analysisOpenedFlag = false;
					}
				});
	    upPanel.add(analysisExit);
	    
	    menuBarAnalysis.setBounds(0,0,1300, 40);
	    menuBarAnalysis.addMouseListener(new MouseAdapter()
		{	@Override
				public void mousePressed(MouseEvent e)							//�޴��ٸ� ����� �� ������ǥ�� �޾ƿ�
				{
					mouseX = e.getX();
					mouseY = e.getY();
				}
		});
	    menuBarAnalysis.addMouseMotionListener(new MouseMotionAdapter()	
		{
			@Override
			public void mouseDragged(MouseEvent e)
			{
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
			}
		});
	    upPanel.add(menuBarAnalysis);
	    add(upPanel);
      
		primary = new JPanel();
		primary.setBackground(Color.LIGHT_GRAY);
		primary.setLayout(null);
		primary.setBounds(0,40,1300,600);
		
		boxPanel = new JPanel();
		boxPanel.setLayout(null);
		boxPanel.setBounds(0,0,650,50);
		
		yearlbl = new JLabel("��", JLabel.CENTER);
		yearlbl.setBounds(5,10,40,40);
		boxPanel.add(yearlbl);
		
		yearBox = new JComboBox(year);
		yearBox.setBounds(50,10,250,40);
		boxPanel.add(yearBox);
			
		searchDateBtn = new JButton("��Ʈ ����");
		searchDateBtn.setBounds(320, 10, 100,40);
		boxPanel.add(searchDateBtn);
		
		parsingButton = new JButton("�Ľ�");
		parsingButton.setBounds(430,10,100,40);
		boxPanel.add(parsingButton);
		
		deleteButton = new JButton("�ʱ�ȭ");
		deleteButton.setBounds(540,10,100,40);
		boxPanel.add(deleteButton);
	
		primary.add(boxPanel);
		
		chart = new JFreeChart(new CategoryPlot());
  	  	chartPanel = new ChartPanel(chart);
  	  	
	    chartPanel.setChart(getChart("All"));
	    chartPanel.setLayout(null);
	    chartPanel.setBounds(650,60,640,400);
	    primary.add(chartPanel);	
		
		NativeInterface.open();			
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  createContent();
	    	  add(primary);
	    	  repaint();
	      }
	    });
	    
	    searchDateBtn.addActionListener(new YearSearch());    //��ư �̺�Ʈ �߰�
	    parsingButton.addActionListener(new YearSearch()); 
	    deleteButton.addActionListener(new YearSearch()); 

	    setVisible(true);
		
	}
	
	public void createContent() {
		
			JPanel webBrowserPanel = new JPanel(new BorderLayout());					//������ ����� �г� ���� �� �ʱ�ȭ
			webBrowserPanel.setBounds(10,60,630,480);									//�г� ũ�� ����
			  
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
					script.resetScript();
					script.setAnalysisMain(accList);
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
		    primary.add(webBrowserPanel);														//subPanel �� ���� �г� ���̱�
		   
	}

	private class YearSearch implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Object obj = e.getSource();
			String selectedYear;
			
			if(obj == searchDateBtn)
			{
				
				selectedYear = (String) yearBox.getSelectedItem();
				if(selectedYear.equals("��ü"))
				{
					chart = getChart("All");	
					accList = AppManager.CreateInstance().getAccidentCaseDAO().getAll(); 
					script.resetScript();
					script.setAnalysisMain(accList);
					webBrowser.executeJavascript(script.getScript());					//���� ����	
				}
				else
				{
					chart = getChart(selectedYear);
					accList = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(selectedYear);
					script.resetScript();
					script.setAnalysisMain(accList);
					webBrowser.executeJavascript(script.getScript());					//���� ����	
				}
					
			    chartPanel.setChart(chart);
			}
			else if(obj == parsingButton)
			{
				selectedYear = (String) yearBox.getSelectedItem();
		    	AppManager.CreateInstance().getAccidentCaseDAO().ParsingAccidentData(selectedYear);
			}
			else if(obj == deleteButton)
			{
		    	AppManager.CreateInstance().getAccidentCaseDAO().deleteAllCase();
			}
		}
	}

	
	public void paint(Graphics g)
	{
		super.paintComponents(g);
		this.repaint();
	}
	
	public JFreeChart getChart(String selectedYear)
	{
	
	    	int sNum[] = new int[13];
	    	int kNum[] = new int[13];
	    	int iNum[] = new int[13];
	    	ArrayList<AccidentCase> datas = new ArrayList<AccidentCase>();
	    	
	    	if(selectedYear.equals("All"))
	    		datas = AppManager.CreateInstance().getAccidentCaseDAO().getAll();
	    	else
	    		datas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(selectedYear);

	    	for(int iter = 0 ; iter < datas.size(); iter++)
	    		{
	    			if(datas.get(iter).getProvince().equals("����Ư����"))
	    			{
	    				sNum[Integer.parseInt(datas.get(iter).getMonth())]++;
	    			}
	    			else if(datas.get(iter).getProvince().equals("��⵵"))
	    			{
	    				kNum[Integer.parseInt(datas.get(iter).getMonth())]++;
	    			}
	    			else if(datas.get(iter).getProvince().equals("��õ������"))
	    			{
	    				iNum[Integer.parseInt(datas.get(iter).getMonth())]++;
	    			}
	    		}
	    	
	        // ������ ����
	        DefaultCategoryDataset sDataSet = new DefaultCategoryDataset();                // bar chart 
	        DefaultCategoryDataset kDataSet = new DefaultCategoryDataset();               // bar chart 2
	        DefaultCategoryDataset iDataSet = new DefaultCategoryDataset();                // line chart 1

	        // ������ �Է� ( ��, ����, ī�װ� )
	        
	        // �׷��� 1      
	        sDataSet.addValue(sNum[1], "����", "1��");
	        sDataSet.addValue(sNum[2], "����", "2��");
	        sDataSet.addValue(sNum[3], "����", "3��");
	        sDataSet.addValue(sNum[4], "����", "4��");
	        sDataSet.addValue(sNum[5], "����", "5��");
	        sDataSet.addValue(sNum[6], "����", "6��");
	        sDataSet.addValue(sNum[7], "����", "7��");
	        sDataSet.addValue(sNum[8], "����", "8��");
	        sDataSet.addValue(sNum[9], "����", "9��");
	        sDataSet.addValue(sNum[10], "����", "10��");
	        sDataSet.addValue(sNum[11], "����", "11��");
	        sDataSet.addValue(sNum[12], "����", "12��");
	        
	        // �׷��� 2       
	        kDataSet.addValue(kNum[1], "���", "1��");
	        kDataSet.addValue(kNum[2], "���", "2��");
	        kDataSet.addValue(kNum[3], "���", "3��");
	        kDataSet.addValue(kNum[4], "���", "4��");
	        kDataSet.addValue(kNum[5],  "���", "5��");
	        kDataSet.addValue(kNum[6],  "���", "6��");
	        kDataSet.addValue(kNum[7],  "���", "7��");
	        kDataSet.addValue(kNum[8],  "���", "8��");
	        kDataSet.addValue(kNum[9], "���", "9��");
	        kDataSet.addValue(kNum[10],  "���", "10��");
	        kDataSet.addValue(kNum[11],  "���", "11��");
	        kDataSet.addValue(kNum[12],  "���", "12��");

	        // �׷��� 3       
	        iDataSet.addValue(iNum[1], "��õ", "1��");
	        iDataSet.addValue(iNum[2], "��õ", "2��");
	        iDataSet.addValue(iNum[3], "��õ", "3��");
	        iDataSet.addValue(iNum[4], "��õ", "4��");
	        iDataSet.addValue(iNum[5], "��õ", "5��");
	        iDataSet.addValue(iNum[6], "��õ", "6��");
	        iDataSet.addValue(iNum[7], "��õ", "7��");
	        iDataSet.addValue(iNum[8], "��õ", "8��");
	        iDataSet.addValue(iNum[9], "��õ", "9��");
	        iDataSet.addValue(iNum[10], "��õ", "10��");
	        iDataSet.addValue(iNum[11], "��õ", "11��");
	        iDataSet.addValue(iNum[12], "��õ", "12��");

	        // ������ ���� �� ����

	        // ������ ����
	        
	        final LineAndShapeRenderer sRender = new LineAndShapeRenderer();
	        final LineAndShapeRenderer kRender = new LineAndShapeRenderer();
	        final LineAndShapeRenderer iRender = new LineAndShapeRenderer();

	        // ���� �ɼ� ����
	        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
	        final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
	        final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);

	        Font f = new Font("Gothic", Font.BOLD, 15);
	        Font axisF = new Font("Gulim", Font.PLAIN, 14);

	        // ������ ����
	        // �׷��� 1
	        sRender.setSeriesPaint(0, new Color(0,0,255));
	        sRender.setBaseItemLabelFont(f);
	        sRender.setBasePositiveItemLabelPosition(p_center);
	        sRender.setBaseItemLabelGenerator(generator);
	        sRender.setBaseItemLabelsVisible(true);

	        // �׷��� 2       
	        kRender.setSeriesPaint(0, new Color(255,0,0));
	        kRender.setBaseItemLabelFont(f);
	        kRender.setBasePositiveItemLabelPosition(p_center);
	        kRender.setBaseItemLabelGenerator(generator);
	        kRender.setBaseItemLabelsVisible(true);

	        // �׷��� 3       
	        iRender.setSeriesPaint(0, new Color(0,255,0));
	        iRender.setBaseItemLabelFont(f);
	        iRender.setBasePositiveItemLabelPosition(p_center);
	        iRender.setBaseItemLabelGenerator(generator);
	        iRender.setBaseItemLabelsVisible(true);

	        // plot ����
	        CategoryPlot plot = new CategoryPlot();

	        // plot �� ������ ����
	        plot.setDataset(sDataSet);
	        plot.setRenderer(sRender);
	        plot.setDataset(1,kDataSet);
	        plot.setRenderer(1,kRender);
	        plot.setDataset(2, iDataSet);
	        plot.setRenderer(2, iRender);

	        // plot �⺻ ����

	        plot.setOrientation(PlotOrientation.VERTICAL);             // �׷��� ǥ�� ����
	        plot.setRangeGridlinesVisible(true);                       // X�� ���̵� ���� ǥ�ÿ���
	        plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���


	        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
	        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
	       
	        // X�� ����

	        plot.setDomainAxis(new CategoryAxis());              // X�� ���� ����
	        plot.getDomainAxis().setTickLabelFont(axisF);  		 // X�� ���ݶ� ��Ʈ ����
	        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����

	        // Y�� ����

	        plot.setRangeAxis(new NumberAxis());                 // Y�� ���� ����
	        plot.getRangeAxis().setTickLabelFont(axisF);        // Y�� ���ݶ� ��Ʈ ����


	        // ���õ� plot�� �������� chart ����

	        JFreeChart chart = new JFreeChart(plot);
	        
	        if(selectedYear.equals("All"))
	        	chart.setTitle("��ü ��� ���� ��� ���� �߻� �Ǽ�"); // ��Ʈ Ÿ��Ʋ
	    	else
	    		chart.setTitle(selectedYear+ "��" + "��� ���� ��� ���� �߻� �Ǽ�"); // ��Ʈ Ÿ��Ʋ
	        
	        return chart;
	    }



}