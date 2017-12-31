import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class AppController {
	
	protected boolean searchUpdateFlag;
	protected String contents[][] = {{"133333", "��õ������", "��ģ��", "2017", "02", "28", "1", "0" ,"1", "������"}
	,{"1", "����Ư����", "������", "2017", "02", "28", "1", "0" ,"1", "������"}};
	
	protected String[] sTown = {"�����","������","������","���ʱ�","������","������","���α�","�߱�","�߶���","���ϱ�","��õ��","��������",
			"���빮��","����","���۱�","������","���ı�","������","��걸","��õ��","���α�","������","���Ǳ�","���빮��","���ϱ�"};
	protected String[] iTown = {"�߱�","����","����","������","������","����","��籸","����","��ȭ��","������"};
	protected String[] gyTown = {"������","������","�����ν�","�Ⱦ��","��õ��","�����","���ý�","����õ��","�Ȼ��","����","��õ��","������","�����ֽ�","�����","�����",
			"������","�ǿս�","�ϳ���","���ν�","���ֽ�","��õ��","������","ȭ����","���ֽ�","���ֽ�","��õ��","���ֱ�","��õ��","����","����"};
	protected String[] month = {"��","1","2","3","4","5","6","7","8","9","10","11","12"};
	
	protected String[] day = {"��","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
			"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	

	public AppController()
	{
		searchUpdateFlag = false;
		
		AppManager.CreateInstance().setAppController(this);
		AppManager.CreateInstance().getAppMain().addActionButtonListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JButton btn = (JButton)arg0.getSource();
						for(int i=0;i<AppManager.CreateInstance().getAppMain().btns.length;i++) {
							if(AppManager.CreateInstance().getAppMain().btns[i]==btn) {
								if(i==0) {
									AppManager.CreateInstance().getAppMain().search();
								}
								else if(i==1) {
									AppManager.CreateInstance().getAppMain().registration();
								}
								else if(i==2) {
									AppManager.CreateInstance().getAppMain().modifyDelete();
								}
								else
								{
									AppManager.CreateInstance().getAppMain().analysis();
								}
							}
						}
					}			
				});
		AppManager.CreateInstance().getAppMain().addActionSearchProListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JComboBox tmp = (JComboBox)arg0.getSource();
						String select = (String)tmp.getSelectedItem();
					
						if(select.equals("��ü")) {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel());
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(sTown));
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(iTown));
						}
						else {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(gyTown));
						}	
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionSearchListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						JButton btn = (JButton)e.getSource();
						if(btn == AppManager.CreateInstance().getAppMain().searchButton)
						{
							ArrayList <AccidentCase> outputDatas = new ArrayList<AccidentCase>();
							//������ �޾ƿ���
							String pro = (String)AppManager.CreateInstance().getAppMain().siDo.getSelectedItem();
							String tow = (String)AppManager.CreateInstance().getAppMain().guGun.getSelectedItem();
							
							outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLoca(pro, tow);
							// ��� ����
							AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");
							
							AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);
							//Table ������ �ٽ� ä���
							for(AccidentCase outputCase: outputDatas)
							{
								String [] temp = {Integer.toString(outputCase.getCscode()),
										outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
										outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
										Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
										outputCase.getActype()};
								AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);				
							}
							AppManager.CreateInstance().getAppMain().diaSearch.dispose();			
						}
					}
			
				});
	
		AppManager.CreateInstance().getAppMain().addActionRegisterProListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						JComboBox tmp = (JComboBox)e.getSource();
						String select = (String)tmp.getSelectedItem();

						if(select.equals("��ü")) {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel());
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(sTown));
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(iTown));
						}
						else {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(gyTown));
						}	
					}
				});


		AppManager.CreateInstance().getAppMain().addActionRegisterListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						Object obj = arg0.getSource();
						
						if(obj == AppManager.CreateInstance().getAppMain().regBtn)
						{
							//������
							AccidentCase tempCase = new AccidentCase();
							tempCase.setProvince((String)AppManager.CreateInstance().getAppMain().pro.getSelectedItem());
							tempCase.setTown((String)AppManager.CreateInstance().getAppMain().tow.getSelectedItem());
							tempCase.setYear((String)AppManager.CreateInstance().getAppMain().yearcb.getSelectedItem());
							tempCase.setMonth((String)AppManager.CreateInstance().getAppMain().monthcb.getSelectedItem());
							tempCase.setDay((String)AppManager.CreateInstance().getAppMain().daycb.getSelectedItem());
							tempCase.setPoliceno((String)AppManager.CreateInstance().getAppMain().polno.getText());
							tempCase.setDead(Integer.parseInt(AppManager.CreateInstance().getAppMain().dead.getText()));
							tempCase.setInjured(Integer.parseInt(AppManager.CreateInstance().getAppMain().injured.getText()));
							tempCase.setCasulity();
							tempCase.setActype((String)AppManager.CreateInstance().getAppMain().accType.getSelectedItem());
							tempCase.setLatitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().lati.getText()));
							tempCase.setLongitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().longi.getText()));
							
							AppManager.CreateInstance().getAccidentCaseDAO().insertCase(tempCase);
							AppManager.CreateInstance().getAppMain().dia.dispose();
							
							//�������� �κ�--------------------------------------------------------------------------------
							
							int maxIndex = AppManager.CreateInstance().getAccidentCaseDAO().getNewCaseCode();
							AccidentCase outputCase = new AccidentCase();
							outputCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(maxIndex);
							String [] temp = {Integer.toString(outputCase.getCscode()),
									outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
									outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
									Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
									outputCase.getActype()};
						
							//Table �ʱ�ȭ
							AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);
							//Table ������ �ٽ� ä���
							AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);
							AppManager.CreateInstance().getAppMain().dia.dispose();
						}		
						
					}
				});
		AppManager.CreateInstance().getAppMain().addActionUpdateProListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						JComboBox tmp = (JComboBox)arg0.getSource();
						String select = (String)tmp.getSelectedItem();
						if(select.equals("��ü")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel());
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(sTown));
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(iTown));
						}
						else {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(gyTown));
						}
					}
				});
		AppManager.CreateInstance().getAppMain().addActionUpdateSearchListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						Object obj = arg0.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)
						{
							AccidentCase temp = new AccidentCase();
							int accNum = Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText());
							temp = AppManager.CreateInstance().getAccidentCaseDAO().getCase(accNum);
							
							if(temp != null)
							{
								searchUpdateFlag = true;
								AppManager.CreateInstance().getAppMain().proUpdate.setSelectedItem(temp.getProvince());
								AppManager.CreateInstance().getAppMain().towUpdate.setSelectedItem(temp.getTown());
								AppManager.CreateInstance().getAppMain().yearcbUpdate.setSelectedItem(temp.getYear());
								AppManager.CreateInstance().getAppMain().monthcbUpdate.setSelectedItem(temp.getMonth());
								AppManager.CreateInstance().getAppMain().daycbUpdate.setSelectedItem(temp.getDay());
								AppManager.CreateInstance().getAppMain().polnoUpdate.setText(temp.getPoliceno());
								AppManager.CreateInstance().getAppMain().deadUpdate.setText(Integer.toString(temp.getDead()));
								AppManager.CreateInstance().getAppMain().injuredUpdate.setText(Integer.toString(temp.getInjured()));
								AppManager.CreateInstance().getAppMain().accTypeUpdate.setSelectedItem(temp.getActype());
								AppManager.CreateInstance().getAppMain().latiUpdate.setText(Double.toString(temp.getLatitude()));
								AppManager.CreateInstance().getAppMain().longiUpdate.setText(Double.toString(temp.getLongitude()));
							}
							else
							{
								
							}
						}
						
					}
					
				});
		
		AppManager.CreateInstance().getAppMain().addActionUpdateListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						Object obj = arg0.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().updateButton && searchUpdateFlag == true)
						{
							
							//��� ���� �����ͼ� tempCase �� �����ϱ�.
							AccidentCase tempCase = new AccidentCase();
							boolean succFlag;
							int caseNum = Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNum.getText());
							
							tempCase.setCscode(Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNum.getText()));
							tempCase.setProvince((String)AppManager.CreateInstance().getAppMain().pro.getSelectedItem());
							tempCase.setTown((String)AppManager.CreateInstance().getAppMain().tow.getSelectedItem());
							tempCase.setYear((String)AppManager.CreateInstance().getAppMain().yearcb.getSelectedItem());
							tempCase.setMonth((String)AppManager.CreateInstance().getAppMain().monthcb.getSelectedItem());
							tempCase.setDay((String)AppManager.CreateInstance().getAppMain().daycb.getSelectedItem());
							tempCase.setPoliceno((String)AppManager.CreateInstance().getAppMain().polno.getText());
							tempCase.setDead(Integer.parseInt(AppManager.CreateInstance().getAppMain().dead.getText()));
							tempCase.setInjured(Integer.parseInt(AppManager.CreateInstance().getAppMain().injured.getText()));
							tempCase.setCasulity();
							tempCase.setActype((String)AppManager.CreateInstance().getAppMain().accType.getSelectedItem());
							tempCase.setLatitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().lati.getText()));
							tempCase.setLongitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().longi.getText()));
							
							//tempCase ������Ʈ �ǽ�
							succFlag = AppManager.CreateInstance().getAccidentCaseDAO().updateCase(tempCase);
							
							
							//�ٽ� �����ͼ� Table�� �Ѹ���
							AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");
							
							AccidentCase outputCase = new AccidentCase();
							outputCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(caseNum);
							
							String [] temp = {Integer.toString(outputCase.getCscode()),
									outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
									outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
									Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
									outputCase.getActype()};
						
							//Table �ʱ�ȭ
							AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);
							//Table ������ �ٽ� ä���
							AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);
							AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
							searchUpdateFlag = false;					
						}
						else if(obj == AppManager.CreateInstance().getAppMain().deleteButton && searchUpdateFlag == true)
						{
							boolean delSuccess;
							delSuccess = AppManager.CreateInstance().getAccidentCaseDAO().deleteCase(Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNum.getText()));
							
							if(delSuccess == true)
							{
								//ȭ�� CardLayout ���� ���� ���� ��ȯ
								searchUpdateFlag = false;
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "image");
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();	
							}
							else
							{	
								//���� �޼��� ���.
							}
							
						}
					}
					
				});
		AppManager.CreateInstance().getAppMain().addTableListener(new MouseAdapter()
				{
					public void mouseClicked(MouseEvent e)
					{
						if(e.getButton() ==1)
						{
							
						}
						if(e.getClickCount() ==2)
						{
							int row = AppManager.CreateInstance().getAppMain().table.getSelectedRow();
							int accNum = Integer.parseInt((String)AppManager.CreateInstance().getAppMain().table.getValueAt(row, 0));
							System.out.println(accNum);
							new DetailInfo(accNum);
						}
						if(e.getButton() == 3)
						{
							
						}
					}
					
				});
		}
	
}



/*	AppManager.CreateInstance().getAppMain().addActionRegisterYearListener(new ActionListener()
{
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JComboBox box = (JComboBox)arg0.getSource();
		String select = (String)box.getSelectedItem();
		
		if(select.equals("��ü")) {
			AppManager.CreateInstance().getAppMain().monthcb.setModel(new DefaultComboBoxModel());
			AppManager.CreateInstance().getAppMain().daycb.setModel(new DefaultComboBoxModel());
		}
		else {
			AppManager.CreateInstance().getAppMain().monthcb.setModel(new DefaultComboBoxModel(month));
			AppManager.CreateInstance().getAppMain().daycb.setModel(new DefaultComboBoxModel(day));
		}
	}
});*/


