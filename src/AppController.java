/**
 * AccidentController.class
 * @author ������, ������, ����ȣ
 * 
 * ���� �ۼ���: 2017�� 12�� 23��
 * ���� ������: 2018�� 1�� 2��
 */
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AppController {
	
	public static boolean searchOpenedFlag;
	public static boolean registerOpenedFlag;
	public static boolean updateOpenedFlag;
	public static boolean analysisOpenedFlag;
	
	protected String[] sTown = {"�����","������","������","���ʱ�","������","������","���α�","�߱�","�߶���","���ϱ�","��õ��","��������",
			"���빮��","����","���۱�","������","���ı�","������","��걸","��õ��","���α�","������","���Ǳ�","���빮��","���ϱ�"};
	protected String[] iTown = {"�߱�","����","����","������","������","����","��籸","����","��ȭ��","������"};
	protected String[] gyTown = {"������","������","�����ν�","�Ⱦ��","��õ��","�����","���ý�","����õ��","�Ȼ��","����","��õ��","������","�����ֽ�","�����","�����",
			"������","�ǿս�","�ϳ���","���ν�","���ֽ�","��õ��","������","ȭ����","���ֽ�","���ֽ�","��õ��","���ֱ�","��õ��","����","����"};
	protected String[] month = {"��","01","02","03","04","05","06","07","08","09","10","11","12"};
	
	protected String[] day = {"��","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",
			"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	

	public AppController()
	{
		searchOpenedFlag = false;
		registerOpenedFlag = false;
		updateOpenedFlag = false;
		analysisOpenedFlag = false;
		AppManager.CreateInstance().setAppController(this);
		
		AppManager.CreateInstance().getAppMain().introPanel.addActionLoginButtonListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						String id = AppManager.CreateInstance().getAppMain().introPanel.idField.getText();
						String pwd = new String(AppManager.CreateInstance().getAppMain().introPanel.pwField.getPassword());
						if(AppManager.CreateInstance().getAccidentCaseDAO().connectTest(id, pwd))
						{
							AppManager.CreateInstance().getAppMain().getContentPane().removeAll();
							AppManager.CreateInstance().getAppMain().getContentPane().add(AppManager.CreateInstance().getAppMain().primary);
							AppManager.CreateInstance().getAppMain().revalidate();
							AppManager.CreateInstance().getAppMain().primary.requestFocus();
							AppManager.CreateInstance().getAppMain().status = true;
							AppManager.CreateInstance().getAppMain().repaint();
						}
						else
						{
							JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().introPanel.idField, "DB ���� ����");
							AppManager.CreateInstance().getAppMain().introPanel.idField.setText("");
							AppManager.CreateInstance().getAppMain().introPanel.pwField.setText("");
						}
					}
				});
		AppManager.CreateInstance().getAppMain().introPanel.addMouseLoginButtonListener(new MouseAdapter()
				{
					public void mouseEntered(MouseEvent e)
					{
						Object obj = e.getSource();	
						if(obj == AppManager.CreateInstance().getAppMain().introPanel.loginBtn)
						{
							AppManager.CreateInstance().getAppMain().introPanel.loginBtn.setIcon(ImageData.loginEnteredImage);
						}
					}
					@Override
					public void mouseExited(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().introPanel.loginBtn)
						{
							AppManager.CreateInstance().getAppMain().introPanel.loginBtn.setIcon(ImageData.loginBaiscImage);
						}
						
					}
				});
	
		AppManager.CreateInstance().getAppMain().addActionButtonListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JButton btn = (JButton)arg0.getSource();
						for(int i=0;i<AppManager.CreateInstance().getAppMain().btns.length;i++) {
							if(AppManager.CreateInstance().getAppMain().btns[i]==btn) {
								if(i==0 && searchOpenedFlag == false) {
									AppManager.CreateInstance().getAppMain().search();
									AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonBasic);
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									AppManager.CreateInstance().getAppMain().siDo.setSelectedItem("��ü");
									AppManager.CreateInstance().getAppMain().guGun.setSelectedItem("");
									AppManager.CreateInstance().getAppMain().yearcbSearch.setSelectedItem("�⵵");
									AppManager.CreateInstance().getAppMain().monthcbSearch.setSelectedItem("��");
									searchOpenedFlag =true;
								}
								else if(i==1 && registerOpenedFlag == false) {
									AppManager.CreateInstance().getAppMain().registration();
									AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonBasic);
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									AppManager.CreateInstance().getAppMain().pro.setSelectedItem("��ü");
									AppManager.CreateInstance().getAppMain().tow.setSelectedItem("");
									AppManager.CreateInstance().getAppMain().yearcb.setSelectedItem("�⵵");
									AppManager.CreateInstance().getAppMain().monthcb.setSelectedItem("��");
									AppManager.CreateInstance().getAppMain().daycb.setSelectedItem("��");
									//AppManager.CreateInstance().getAppMain().polno.setText("");
									AppManager.CreateInstance().getAppMain().dead.setText("");
									AppManager.CreateInstance().getAppMain().injured.setText("");
									AppManager.CreateInstance().getAppMain().accType.setSelectedItem("������");
									AppManager.CreateInstance().getAppMain().lati.setText("");
									AppManager.CreateInstance().getAppMain().longi.setText("");
									
									registerOpenedFlag =true;
								}
								else if(i==2 && updateOpenedFlag == false) {
									AppManager.CreateInstance().getAppMain().modifyDelete();
									AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonBasic);
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									AppManager.CreateInstance().getAppMain().caseNumTxt.setText("");
									AppManager.CreateInstance().getAppMain().proUpdate.setSelectedItem("��ü");
									AppManager.CreateInstance().getAppMain().towUpdate.setSelectedItem("");
									AppManager.CreateInstance().getAppMain().yearcbUpdate.setSelectedItem("�⵵");
									AppManager.CreateInstance().getAppMain().monthcbUpdate.setSelectedItem("��");
									AppManager.CreateInstance().getAppMain().daycbUpdate.setSelectedItem("��");
									AppManager.CreateInstance().getAppMain().polnoUpdate.setText("");
									AppManager.CreateInstance().getAppMain().deadUpdate.setText("");
									AppManager.CreateInstance().getAppMain().injuredUpdate.setText("");
									AppManager.CreateInstance().getAppMain().accTypeUpdate.setSelectedItem("������");
									AppManager.CreateInstance().getAppMain().latiUpdate.setText("");
									AppManager.CreateInstance().getAppMain().longiUpdate.setText("");
									
									updateOpenedFlag =true;
								}
								else if(i == 3 && analysisOpenedFlag == false)
								{
									AppManager.CreateInstance().getAppMain().analysis();
									
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									analysisOpenedFlag = true;
								}
							}
						}
					}			
				});
		AppManager.CreateInstance().getAppMain().addMouseAdapterHoverListener(new MouseAdapter()
				{
					public void mouseEntered(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().btns[0])
						{
							AppManager.CreateInstance().getAppMain().btns[0].setIcon(ImageData.searchEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[1])
						{
							AppManager.CreateInstance().getAppMain().btns[1].setIcon(ImageData.regEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[2])
						{
							AppManager.CreateInstance().getAppMain().btns[2].setIcon(ImageData.updateEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[3])
						{
							AppManager.CreateInstance().getAppMain().btns[3].setIcon(ImageData.analysisEntered);
						}
					}
					@Override
					public void mouseExited(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().btns[0])
						{
							AppManager.CreateInstance().getAppMain().btns[0].setIcon(ImageData.searchBasic);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[1])
						{
							AppManager.CreateInstance().getAppMain().btns[1].setIcon(ImageData.regBasic);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[2])
						{
							AppManager.CreateInstance().getAppMain().btns[2].setIcon(ImageData.updateBasic);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[3])
						{
							AppManager.CreateInstance().getAppMain().btns[3].setIcon(ImageData.analysisBasic);
						}
					}
				});
		AppManager.CreateInstance().getAppMain().addMouseAdapterButtonListener(new MouseAdapter()
				{
						public void mousePressed(MouseEvent e)							//�޴��ٸ� ����� �� ������ǥ�� �޾ƿ�
						{
							Object obj = e.getSource();
							if(obj == AppManager.CreateInstance().getAppMain().menuBar)
							{
								AppManager.CreateInstance().getAppMain().mainMouseX = e.getX();
								AppManager.CreateInstance().getAppMain().mainMouseY = e.getY();			
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarSearch)
							{
								AppManager.CreateInstance().getAppMain().searchMouseX = e.getX();
								AppManager.CreateInstance().getAppMain().searchMouseY = e.getY();						
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarRegist)
							{
								AppManager.CreateInstance().getAppMain().registMouseX = e.getX();
								AppManager.CreateInstance().getAppMain().registMouseY = e.getY();					
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarUpdate)
							{
								AppManager.CreateInstance().getAppMain().updateMouseX = e.getX();
								AppManager.CreateInstance().getAppMain().updateMouseY = e.getY();					
							}
						}
				});
		AppManager.CreateInstance().getAppMain().addMouseMotionButtonListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent e)
			{
				Object obj = e.getSource();
				if(obj == AppManager.CreateInstance().getAppMain().menuBar)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().setLocation(x - AppManager.CreateInstance().getAppMain().mainMouseX,
							y - AppManager.CreateInstance().getAppMain().mainMouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarSearch)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().diaSearch.setLocation(x - AppManager.CreateInstance().getAppMain().searchMouseX,
							y - AppManager.CreateInstance().getAppMain().searchMouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarRegist)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().dia.setLocation(x - AppManager.CreateInstance().getAppMain().registMouseX,
							y - AppManager.CreateInstance().getAppMain().registMouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarUpdate)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().diaUpdate.setLocation(x - AppManager.CreateInstance().getAppMain().updateMouseX,
							y - AppManager.CreateInstance().getAppMain().updateMouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
				}
			}
			
		});
		AppManager.CreateInstance().getAppMain().addExitMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseEntered(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)
						{
							AppManager.CreateInstance().getAppMain().exit.setIcon(ImageData.exitButtonEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonEntered);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonEntered);
						}
					}
					@Override
					public void mouseExited(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)
						{
							AppManager.CreateInstance().getAppMain().exit.setIcon(ImageData.exitButtonBasic);			
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonBasic);			
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonBasic);		
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonBasic);	
						}
					}
					@Override
					public void mouseReleased(MouseEvent e)
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)
						{
							System.exit(0);
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().diaSearch.dispose();
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
							searchOpenedFlag = false;
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().dia.dispose();
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
	
							registerOpenedFlag = false;
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
							updateOpenedFlag = false;
						}
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionSearchProListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
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
							String year = (String)AppManager.CreateInstance().getAppMain().yearcbSearch.getSelectedItem();
							String month = (String)AppManager.CreateInstance().getAppMain().monthcbSearch.getSelectedItem();
							
							if(pro.equals("��ü"))//��Ұ� ��ü �ϰ��
							{
								if(year == "�⵵" && month == "��")		//��Ұ� ��ü �̸鼭 ��¥�� ���� ��� ��� ��������.
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().getAll();	
								}
								else if(year != "�⵵" && month == "��")				//���� ���� �Ǿ� �ְ�, ���� ���� �ȵǾ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(year);
								}
								else if(year == "�⵵ " && month != "��")				//���� ���õǾ� ���� �ʰ�, ���� ���õǾ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseMonthTime(month);
								}
								else												//��Ұ� ��ü �̸鼭 Ư����¥�� �����Ǿ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(year,month);
								}
							}
							else									//��Ұ� ��ü�� �ƴ� ��� (��Ұ� �����Ǿ��ִ� ���
							{
								if(year == "�⵵" && month == "��")	//Ư�� ��� + ��¥ ���� 
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLoca(pro, tow);							
								}
								else if(year == "�⵵" && month != "��")
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLocaMonth(pro, tow, month);
								}
								else if(year != "�⵵" && month == "��")
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLocaYear(pro, tow, year);
								}
								else if(year != "�⵵" && month != "��")
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCase(pro, tow, year, month);			
								}
							}
							// ��� ����
							AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");	//table ȭ�� �����ֱ�.
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
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
	
							AppManager.CreateInstance().getAppMain().diaSearch.dispose();
							searchOpenedFlag = false;
							AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnBasic);
							
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
							boolean insertFlag;
							AccidentCase tempCase = new AccidentCase();
							tempCase.setProvince((String)AppManager.CreateInstance().getAppMain().pro.getSelectedItem());
							tempCase.setTown((String)AppManager.CreateInstance().getAppMain().tow.getSelectedItem());
							tempCase.setYear((String)AppManager.CreateInstance().getAppMain().yearcb.getSelectedItem());
							tempCase.setMonth((String)AppManager.CreateInstance().getAppMain().monthcb.getSelectedItem());
							tempCase.setDay((String)AppManager.CreateInstance().getAppMain().daycb.getSelectedItem());
							tempCase.setDead(Integer.parseInt(AppManager.CreateInstance().getAppMain().dead.getText()));
							tempCase.setInjured(Integer.parseInt(AppManager.CreateInstance().getAppMain().injured.getText()));
							tempCase.setCasulity();
							tempCase.setActype((String)AppManager.CreateInstance().getAppMain().accType.getSelectedItem());
							tempCase.setLatitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().lati.getText()));
							tempCase.setLongitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().longi.getText()));
						
							if(tempCase.getProvince().equals("��ü")|| tempCase.getYear().equals("�⵵") || tempCase.getMonth().equals("��") || tempCase.getDay().equals("��"))
								insertFlag = false;
							else
								insertFlag = AppManager.CreateInstance().getAccidentCaseDAO().insertCase(tempCase);		
							
							
							//�������� �κ�--------------------------------------------------------------------------------
							if(insertFlag == true)
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().regBtn, "��ϵǾ����ϴ�.");
								
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
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");	//table ȭ�� �����ֱ�.
								AppManager.CreateInstance().getAppMain().dia.dispose();
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								
								registerOpenedFlag = false;
								AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);
				
							}
							else
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "��� ����!\n���� ��Ȯ�� �Է��Ͻʽÿ�!", "���", JOptionPane.PLAIN_MESSAGE);
								AppManager.CreateInstance().getAppMain().dia.dispose();
								
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								registerOpenedFlag = false;

								AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);
								
							}
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
								AppManager.CreateInstance().getAppMain().proUpdate.setSelectedItem(temp.getProvince());
								AppManager.CreateInstance().getAppMain().towUpdate.setSelectedItem(temp.getTown());
								AppManager.CreateInstance().getAppMain().yearcbUpdate.setSelectedItem(temp.getYear());
								AppManager.CreateInstance().getAppMain().monthcbUpdate.setSelectedItem(temp.getMonth());
								AppManager.CreateInstance().getAppMain().daycbUpdate.setSelectedItem(temp.getDay());
								AppManager.CreateInstance().getAppMain().deadUpdate.setText(Integer.toString(temp.getDead()));
								AppManager.CreateInstance().getAppMain().injuredUpdate.setText(Integer.toString(temp.getInjured()));
								AppManager.CreateInstance().getAppMain().accTypeUpdate.setSelectedItem(temp.getActype());
								AppManager.CreateInstance().getAppMain().latiUpdate.setText(Double.toString(temp.getLatitude()));
								AppManager.CreateInstance().getAppMain().longiUpdate.setText(Double.toString(temp.getLongitude()));
							}
							else
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "����ȣ�� ��Ȯ�� �Է��Ͻÿ�!", "���", JOptionPane.PLAIN_MESSAGE);
							}
						}
						
					}
					
				});
		
		AppManager.CreateInstance().getAppMain().addActionUpdateListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						Object obj = arg0.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().updateButton)
						{
							//��� ���� �����ͼ� tempCase �� �����ϱ�.
							AccidentCase tempCase = new AccidentCase();
							boolean succFlag;
							
							int caseNum = Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText());
							tempCase.setCscode(Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText()));
							tempCase.setProvince((String)AppManager.CreateInstance().getAppMain().proUpdate.getSelectedItem());
							tempCase.setTown((String)AppManager.CreateInstance().getAppMain().towUpdate.getSelectedItem());
							tempCase.setYear((String)AppManager.CreateInstance().getAppMain().yearcbUpdate.getSelectedItem());
							tempCase.setMonth((String)AppManager.CreateInstance().getAppMain().monthcbUpdate.getSelectedItem());
							tempCase.setDay((String)AppManager.CreateInstance().getAppMain().daycbUpdate.getSelectedItem());
							tempCase.setDead(Integer.parseInt(AppManager.CreateInstance().getAppMain().deadUpdate.getText()));
							tempCase.setInjured(Integer.parseInt(AppManager.CreateInstance().getAppMain().injuredUpdate.getText()));
							tempCase.setCasulity();
							tempCase.setActype((String)AppManager.CreateInstance().getAppMain().accTypeUpdate.getSelectedItem());
							tempCase.setLatitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().latiUpdate.getText()));
							tempCase.setLongitude(Double.parseDouble(AppManager.CreateInstance().getAppMain().longiUpdate.getText()));
							
							//tempCase ������Ʈ �ǽ�
							if(tempCase.getProvince().equals("��ü") || tempCase.getYear().equals("�⵵") ||
									tempCase.getMonth().equals("��") || tempCase.getDay().equals("��"))
							{
								succFlag = false;
							}
							else
							{
								succFlag = AppManager.CreateInstance().getAccidentCaseDAO().updateCase(tempCase);		
							}
							
							
							if(succFlag == true)
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().deleteButton, "�����Ǿ����ϴ�.");
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
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");	//table ȭ�� �����ֱ�.
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								updateOpenedFlag = false;
								AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);
								AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);
								AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);
								
							}
							else
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "���� ����!!", "���", JOptionPane.PLAIN_MESSAGE);
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;
								AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);
								AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);
								AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);
							
							}
						}
						else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)
						{
							boolean delSuccess;
							delSuccess = AppManager.CreateInstance().getAccidentCaseDAO().deleteCase(Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText()));
							
							if(delSuccess == true)
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().deleteButton, "�����Ǿ����ϴ�.");
								//ȭ�� CardLayout ���� ���� ���� ��ȯ
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "image");
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;
								
							}
							else
							{	
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "���� ����!!", "���", JOptionPane.PLAIN_MESSAGE);
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;
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
			
							AccidentCase tempCase = new AccidentCase();
							tempCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(accNum);
				
							
							DetailInfo accDetailInfo = new DetailInfo(tempCase);
							accDetailInfo.addMouseDetailMenuBarListener(new MouseAdapter()
							{
								@Override
								public void mousePressed(MouseEvent e)							//�޴��ٸ� ����� �� ������ǥ�� �޾ƿ�
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.menuBarDetail)
									{
										accDetailInfo.mouseX = e.getX();
										accDetailInfo.mouseY = e.getY();				
									}
								}
							});
							accDetailInfo.addMouseDetailMenuBarMotionListener(new MouseMotionAdapter()
							{
								@Override
								public void mouseDragged(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.menuBarDetail)
									{
										int x = e.getXOnScreen();
										int y = e.getYOnScreen();
										accDetailInfo.setLocation(x - accDetailInfo.mouseX, y - accDetailInfo.mouseY);							//�޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����		
									}
								}
							});
							accDetailInfo.addMouseDetailExitListener(new MouseAdapter()
							{
								@Override
								public void mouseEntered(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
										accDetailInfo.detailExit.setIcon(ImageData.exitButtonEntered);			
									}
								}
								@Override
								public void mouseExited(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
										accDetailInfo.detailExit.setIcon(ImageData.exitButtonBasic);			
									}
								}
								@Override
								public void mouseReleased(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
										accDetailInfo.dispose();			
									}
								}
							});
						}
						if(e.getButton() == 3)
						{
							
						}
					}
					
				});
		AppManager.CreateInstance().getAppMain().addInsideMouseListener(new MouseAdapter()
		{
				public void mouseEntered(MouseEvent e)
				{
					Object obj = e.getSource();	
					if(obj == AppManager.CreateInstance().getAppMain().searchButton)
					{
						AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnEntered);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().regBtn)
					{
						AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnEntered);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)
					{
						 AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnEntered);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().updateButton)
					{
						AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnEntered);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)
					{
						AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnEntered);
					}
					
				}
				@Override
				public void mouseExited(MouseEvent e)
				{
					Object obj = e.getSource();	
					if(obj == AppManager.CreateInstance().getAppMain().searchButton)
					{
						AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnBasic);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().regBtn)
					{
						AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)
					{
						 AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().updateButton)
					{
						AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);
					}
					else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)
					{
						AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);
					}

					
				}
		});

	}
}




