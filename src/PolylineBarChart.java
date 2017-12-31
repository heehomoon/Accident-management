import java.awt.*;
import java.util.ArrayList;

import javax.swing.JFrame;

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

public class PolylineBarChart {
	
	PolylineBarChart()
	{
		 PolylineBarChart demo = new PolylineBarChart();
	     JFreeChart chart = demo.getChart();
	       
	     JFrame f = new JFrame("Barchart");
	     f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     f.setSize(650,500); 
	     f.setResizable(false);
	       
	     ChartPanel chartPanel = new ChartPanel(chart);
	     f.add(chartPanel);
	       
	     f.setVisible(true);
	}
 
   
    public JFreeChart getChart()
    {
    	int sNum[] = new int[13];
    	int kNum[] = new int[13];
    	int iNum[] = new int[13];
   
    	
    	AccidentCaseDAO dao = new AccidentCaseDAO();
    	ArrayList<AccidentCase> datas = new ArrayList<AccidentCase>();
    	
    	dao.connectTest("heeho", "1234");
    	datas = dao.searchCaseTime("2014");
    	
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
        final CategoryPlot plot = new CategoryPlot();

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

        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("��� ���� ��� ���� �߻� �Ǽ�"); // ��Ʈ Ÿ��Ʋ
        return chart;
    }
}

 
