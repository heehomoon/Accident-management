import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

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
	private	String[] year = {"�⵵","2012","2013","2014","2015","2016"};
	protected String[] month = {"��","01","02","03","04","05","06","07","08","09","10","11","12"};
	
	private ArrayList <AccidentCase> accList = new ArrayList<AccidentCase>();
	
	protected JLabel menuBarAnalysis = new JLabel(ImageData.menuBarAnalysis);
	protected JButton analysisExit = new JButton(ImageData.exitButtonBasic);
	protected int mouseX; int mouseY;
	
	private JPanel primary;
	private JPanel boxPanel;
	
	private JComboBox yearBox;
	private JComboBox monthBox;
	
	private JButton showChartBtn = new JButton(ImageData.viewChartBasic);
	private JButton parsingButton = new JButton(ImageData.parsingBasic);
	private JButton deleteButton = new JButton(ImageData.resetBasic);
	
	private Javascript script = new Javascript();
	private JPanel infoPanel;

	// ��Ʈ
	private JFreeChart chart;
	private ChartPanel chartPanel;
	
    protected String [] header = {"1��", "2��", "3��", "4��", "5��", "6��", "7��", "8��", "9��", "10��","11��","12��","���"};
	
	private JLabel seoul;
	private JLabel kyeongki;
	private JLabel incheon;
	
	private JScrollPane scroll;
	DefaultTableModel basic =new DefaultTableModel();
	protected JTable table = new JTable(basic)
	{
	       public boolean isCellEditable(int row, int column)
	       {
	          return false;
	       }
	};
	
	
	final JWebBrowser webBrowser = new JWebBrowser();							//�������� ��ü ����
    
	public AccidentAnalysis()
	{
		setUndecorated(true);
		setSize(1300,600);
  	  	setLayout(null);
  	  	setResizable(false);
  	  	setAlwaysOnTop(true); //�׻� â ������
  	  	
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
		primary.setLayout(null);
		primary.setBounds(0,40,1300,600);
		
		boxPanel = new JPanel();
		boxPanel.setLayout(null);
		boxPanel.setBounds(0,0,650,50);
		
		yearBox = new JComboBox(year);
		yearBox.setBounds(10,10,140,40);
		boxPanel.add(yearBox);
		
		monthBox = new JComboBox(month);
		monthBox.setBounds(165,10, 140,40);
		boxPanel.add(monthBox);
		
		showChartBtn.setBounds(320, 10, 100,40);
		boxPanel.add(showChartBtn);
		
		parsingButton.setBounds(430,10,100,40);
		boxPanel.add(parsingButton);
		
		deleteButton.setBounds(540,10,100,40);
		boxPanel.add(deleteButton);
	
		primary.add(boxPanel);
		 
		// Table
	    for(int i =0; i<header.length; i++)
	    {
	         basic.addColumn(header[i]);   
	    }

	    scroll = new JScrollPane();
	    scroll.setViewportView(table);
	    scroll.setBounds(690,450,600,100);
	    
	    primary.add(scroll);
	    
		// ��Ʈ
		chart = new JFreeChart(new CategoryPlot());
  	  	chartPanel = new ChartPanel(chart);
  	  	
	    chartPanel.setChart(getChart("All"));
	    chartPanel.setLayout(null);
	    chartPanel.setBounds(650,30,640,400);
	    primary.add(chartPanel);	
		
	  
	    seoul = new JLabel("����", JLabel.CENTER);
	    seoul.setBounds(650,470,40,20);
	    seoul.setFont(new Font("Gothic", Font.BOLD, 11));
	    primary.add(seoul);
	    
	    kyeongki = new JLabel("���",JLabel.CENTER);
		kyeongki.setBounds(650,487,40,20);
		kyeongki.setFont(new Font("Gothic", Font.BOLD, 11));
		primary.add(kyeongki);
		
	    incheon = new JLabel("��õ",JLabel.CENTER);
	    incheon.setBounds(650,504,40,20);
	    incheon.setFont(new Font("Gothic", Font.BOLD, 11));
	    primary.add(incheon);
	    
	
	    
		NativeInterface.open();			
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	  createContent();
	    	  add(primary);
	    	  repaint();
	      }
	    });
	    
	    showChartBtn.addActionListener(new YearSearch());    //��ư �̺�Ʈ �߰�
	    showChartBtn.addMouseListener(new MouseAdapter()
	    		{
			    	public void mouseEntered(MouseEvent e)
					{
			    		showChartBtn.setIcon(ImageData.viewChartEntered);
					}
					public void mouseExited(MouseEvent e)
					{
						showChartBtn.setIcon(ImageData.viewChartBasic);
					}
	    		});
	    parsingButton.addActionListener(new YearSearch()); 
	    parsingButton.addMouseListener(new MouseAdapter()
		{
	    	public void mouseEntered(MouseEvent e)
			{
	    		parsingButton.setIcon(ImageData.parsingEntered);
			}
			public void mouseExited(MouseEvent e)
			{
				parsingButton.setIcon(ImageData.parsingBasic);
			}
		});
	    deleteButton.addActionListener(new YearSearch()); 
	    deleteButton.addMouseListener(new MouseAdapter()
		{
	    	public void mouseEntered(MouseEvent e)
			{
	    		deleteButton.setIcon(ImageData.resetEntered);
			}
			public void mouseExited(MouseEvent e)
			{
				deleteButton.setIcon(ImageData.resetBasic);
			}
		});
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
					script.setDetailMain(Double.toString(37.566542),Double.toString(126.977874));
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
			String selectedMonth;
			
			if(obj == showChartBtn)
			{
				selectedYear = (String) yearBox.getSelectedItem();
				selectedMonth = (String) monthBox.getSelectedItem();
				
				if(selectedYear.equals("�⵵"))
				{
					chart = getChart("All");	
					accList = AppManager.CreateInstance().getAccidentCaseDAO().getAll(); 
					JOptionPane.showMessageDialog(showChartBtn, "��ü ��� �����Ͱ� ������ ��Ʈ�� ǥ�õ˴ϴ�.\n ��ü �����Ͱ� ũ�� �ð��� �ټ� �ɸ� �� �ֽ��ϴ�. ��ø� ��ٷ� �ֽʽÿ�.");
					
					script.resetScript();
					script.setAnalysisMain(accList);
					webBrowser.executeJavascript(script.getScript());					//���� ����	
				}
				else if(selectedYear.equals("�⵵") == false)
				{
					if(selectedMonth.equals("��"))
					{
						chart = getChart(selectedYear);
						accList = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(selectedYear);
						script.resetScript();
						script.setAnalysisMain(accList);
						webBrowser.executeJavascript(script.getScript());					//���� ����	
						repaint();	
					}
					else
					{
						chart = getChart(selectedYear);
						accList = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(selectedYear, selectedMonth);
						script.resetScript();
						script.setAnalysisMain(accList);
						webBrowser.executeJavascript(script.getScript());					//���� ����	
						repaint();
					}
				}
				
			    chartPanel.setChart(chart);
			}
			else if(obj == parsingButton)
			{
				selectedYear = (String) yearBox.getSelectedItem();
				
				if(selectedYear.equals("�⵵"))
				{
					JOptionPane.showMessageDialog(parsingButton, "Parsing �� �⵵�� �����Ͻʽÿ�.");
				}
				else
				{
					//Parsing �� �������� Ȯ��
					int result = JOptionPane.showConfirmDialog(parsingButton, selectedYear+"�⵵ �����͸� Parsing �Ͻðڽ��ϱ�?"); 
					
					if(result == JOptionPane.YES_OPTION)     //Yes ��ư(�ʱ�ȭ ��)
					{
				    	if(AppManager.CreateInstance().getAccidentCaseDAO().ParsingAccidentData(selectedYear))
				    		JOptionPane.showMessageDialog(parsingButton, selectedYear+"�⵵ �����͸� Parsing �Ϸ�");
					}
				}
				
			}
			else if(obj == deleteButton)
			{
				//������ �ʱ�ȭ �� �������� Ȯ��
				int result = JOptionPane.showConfirmDialog(deleteButton, "DB�� �ʱ�ȭ �Ͻðڽ��ϱ�?"); 
				
				if(result == JOptionPane.YES_OPTION)     //Yes ��ư(�ʱ�ȭ ��)
				{
					AppManager.CreateInstance().getAccidentCaseDAO().deleteAllCase();
				}
			}

			showChartBtn.setIcon(ImageData.viewChartBasic);
			parsingButton.setIcon(ImageData.parsingBasic);
			deleteButton.setIcon(ImageData.resetBasic);
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
	    	
	    	 String [][] times = {{Integer.toString(sNum[1]),Integer.toString(sNum[2]),Integer.toString(sNum[3]),Integer.toString(sNum[4]),Integer.toString(sNum[5]),Integer.toString(sNum[6]),Integer.toString(sNum[7]),Integer.toString(sNum[8]),Integer.toString(sNum[9]),Integer.toString(sNum[10]),Integer.toString(sNum[11]),Integer.toString(sNum[12]), Integer.toString(average(sNum))},
			    		{Integer.toString(kNum[1]),Integer.toString(kNum[2]),Integer.toString(kNum[3]),Integer.toString(kNum[4]),Integer.toString(kNum[5]),Integer.toString(kNum[6]),Integer.toString(kNum[7]),Integer.toString(kNum[8]),Integer.toString(kNum[9]),Integer.toString(kNum[10]),Integer.toString(kNum[11]),Integer.toString(kNum[12]), Integer.toString(average(kNum)) },
			    		{Integer.toString(iNum[1]),Integer.toString(iNum[2]),Integer.toString(iNum[3]),Integer.toString(iNum[4]),Integer.toString(iNum[5]),Integer.toString(iNum[6]),Integer.toString(iNum[7]),Integer.toString(iNum[8]),Integer.toString(iNum[9]),Integer.toString(iNum[10]),Integer.toString(iNum[11]),Integer.toString(iNum[12]), Integer.toString(average(iNum)) }};
			    basic.setRowCount(0);
			    for(int i =0; i<times.length; i++)
			    {
			    	System.out.println();
			    	basic.addRow(times[i]);	
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
	    		chart.setTitle(selectedYear+ "��" + " ��� ���� ��� ���� �߻� �Ǽ�"); // ��Ʈ Ÿ��Ʋ
	        
	        return chart;
	    }
	
	public int average(int [] t)
	{
		int sum = 0;
		for(int i = 1; i<=12 ; i++)
		{
			sum += t[i];
		}
		
		return sum/12;
	}


}