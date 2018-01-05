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
	
	public static boolean searchOpenedFlag;													//�˻� Dialog ���� ���� Ȯ�� ����
	public static boolean registerOpenedFlag;												//��� Dialog ���� ���� Ȯ�� ����
	public static boolean updateOpenedFlag;													//����, ���� Dialog ���� ���� Ȯ�� ����
	public static boolean analysisOpenedFlag;												//�м� Dialog ���� ���� Ȯ�� ����
	
	protected String[] sTown = {"�����","������","������","���ʱ�","������","������","���α�","�߱�","�߶���","���ϱ�","��õ��","��������",
			"���빮��","����","���۱�","������","���ı�","������","��걸","��õ��","���α�","������","���Ǳ�","���빮��","���ϱ�"};				//���� �� ���� String
	protected String[] iTown = {"�߱�","����","����","������","������","����","��籸","����","��ȭ��","������"};								//��õ ���� ���� ���� String
	protected String[] gyTown = {"������","������","�����ν�","�Ⱦ��","��õ��","�����","���ý�","����õ��","�Ȼ��","����","��õ��","������","�����ֽ�","�����","�����",
			"������","�ǿս�","�ϳ���","���ν�","���ֽ�","��õ��","������","ȭ����","���ֽ�","���ֽ�","��õ��","���ֱ�","��õ��","����","����"};	//��⵵ ���� ���� ���� String
	protected String[] month = {"��","01","02","03","04","05","06","07","08","09","10","11","12"};									//�� ���� String
	
	protected String[] day = {"��","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15",						//�� ���� String
			"16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	

	public AppController()
	{
		//��� Dialog ������ �ִ� ����
		searchOpenedFlag = false;
		registerOpenedFlag = false;
		updateOpenedFlag = false;
		analysisOpenedFlag = false;
		
		AppManager.CreateInstance().setAppController(this);																			//AppManager �� AppController ��ü  ���
		
		AppManager.CreateInstance().getAppMain().introPanel.addActionLoginButtonListener(new ActionListener()						//�α��� Action Listener ����
				{
					@Override
					public void actionPerformed(ActionEvent e) {																	//�α��� ��ư Ŭ����
						
						String id = AppManager.CreateInstance().getAppMain().introPanel.idField.getText();							//�α��� id ��������
						String pwd = new String(AppManager.CreateInstance().getAppMain().introPanel.pwField.getPassword());			//�α��� ��й�ȣ ��������
						if(AppManager.CreateInstance().getAccidentCaseDAO().connectTest(id, pwd))									//���� �׽�Ʈ �ǽ�
						{
							AppManager.CreateInstance().getAppMain().getContentPane().removeAll();									//�α��� ������ ���� �α��� Content ����
							AppManager.CreateInstance().getAppMain().getContentPane().add(AppManager.CreateInstance().getAppMain().primary);	//primary Panel ����.
							AppManager.CreateInstance().getAppMain().revalidate();
							AppManager.CreateInstance().getAppMain().primary.requestFocus();
							AppManager.CreateInstance().getAppMain().status = true;													//Status =true �� paint �Լ� ���� ����
							AppManager.CreateInstance().getAppMain().repaint();														//primary �г� �ٽ� paint
						}
						else																										//���� ���н�
						{
							JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().introPanel.idField, "DB ���� ����");//DB���� ����
							AppManager.CreateInstance().getAppMain().introPanel.idField.setText("");								//���̵� ĭ �ʱ�ȭ
							AppManager.CreateInstance().getAppMain().introPanel.pwField.setText("");								//��й�ȣ ĭ �ʱ�ȭ
						}
					}
				});
		AppManager.CreateInstance().getAppMain().introPanel.addMouseLoginButtonListener(new MouseAdapter()							//�α��� ���콺 �̺�Ʈ ����			
				{
					public void mouseEntered(MouseEvent e)																			//���콺 �α��� ��ư �����
					{
						Object obj = e.getSource();	
						if(obj == AppManager.CreateInstance().getAppMain().introPanel.loginBtn)
						{
							AppManager.CreateInstance().getAppMain().introPanel.loginBtn.setIcon(ImageData.loginEnteredImage);		//�α��� ������ �ٲٱ�
						}
					}
					@Override
					public void mouseExited(MouseEvent e)																			//���콺 �α��� ��ư ������ ������
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().introPanel.loginBtn)
						{
							AppManager.CreateInstance().getAppMain().introPanel.loginBtn.setIcon(ImageData.loginBaiscImage);		//�α��� ������ ������� ���ٱ�
						}
						
					}
				});
	
		AppManager.CreateInstance().getAppMain().addActionButtonListener(new ActionListener()										//Main ȭ�� �⺻ ��ư ActionListener
				{
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						JButton btn = (JButton)arg0.getSource();
						for(int i=0;i<AppManager.CreateInstance().getAppMain().btns.length;i++) {
							if(AppManager.CreateInstance().getAppMain().btns[i]==btn) {
								if(i==0 && searchOpenedFlag == false) {																//�˻� ��ư�̰�, �����ִ� �����ϰ��
									
									AppManager.CreateInstance().getAppMain().search();												//�˻� Dialog Ű��

									AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonBasic);			//������ ������� ����
									
									//Ÿ ��ư�� Ŭ�� ��ȿȭ
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									//Dialog �⺻ �ɼ����� ��� ����
									AppManager.CreateInstance().getAppMain().siDo.setSelectedItem("��ü");
									AppManager.CreateInstance().getAppMain().guGun.setSelectedItem("");
									AppManager.CreateInstance().getAppMain().yearcbSearch.setSelectedItem("�⵵");
									AppManager.CreateInstance().getAppMain().monthcbSearch.setSelectedItem("��");
									searchOpenedFlag =true;																			//�˻� Dialog open ���·� ����
								}
								else if(i==1 && registerOpenedFlag == false) {														//��� ��ư�̰�, �����ִ� �����ϰ��
									AppManager.CreateInstance().getAppMain().registration();										//��� Dialog Ű��
									AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonBasic);		//������ ������� ����
									//Ÿ ��ư�� Ŭ�� ��ȿȭ
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									//Dialog �⺻ �ɼ����� ��� ����
									AppManager.CreateInstance().getAppMain().pro.setSelectedItem("��ü");
									AppManager.CreateInstance().getAppMain().tow.setSelectedItem("");
									AppManager.CreateInstance().getAppMain().yearcb.setSelectedItem("�⵵");
									AppManager.CreateInstance().getAppMain().monthcb.setSelectedItem("��");
									AppManager.CreateInstance().getAppMain().daycb.setSelectedItem("��");
									AppManager.CreateInstance().getAppMain().dead.setText("");
									AppManager.CreateInstance().getAppMain().injured.setText("");
									AppManager.CreateInstance().getAppMain().accType.setSelectedItem("������");
									AppManager.CreateInstance().getAppMain().lati.setText("");
									AppManager.CreateInstance().getAppMain().longi.setText("");
									
									registerOpenedFlag =true;																		//��� Dialog open ���·� ����
								}
								else if(i==2 && updateOpenedFlag == false) {														//����/���� ��ư�̰�, �����ִ� �����ΰ��
									AppManager.CreateInstance().getAppMain().modifyDelete();										//���� Dialog Ű��
									AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonBasic);			//������ ������� ����
									
									//Ÿ ��ư�� Ŭ�� ��ȿȭ
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									
									//Dialog �⺻ �ɼ����� ��� ����
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
									
									updateOpenedFlag =true;																			//����/���� Dialog open ���·� ����
								}
								else if(i == 3 && analysisOpenedFlag == false)														//�м� ��ư�̰�, �����ִ� ������ ���
								{
									AppManager.CreateInstance().getAppMain().analysis();											//�м� Dialog Ű��
									
									//Ÿ ��ư�� Ŭ�� ��ȿȭ
									AppManager.CreateInstance().getAppMain().btns[0].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[1].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[2].setEnabled(false);
									AppManager.CreateInstance().getAppMain().btns[3].setEnabled(false);
									analysisOpenedFlag = true;																		//�м� Dialog Ű��
								}
							}
						}
					}			
				});
		AppManager.CreateInstance().getAppMain().addMouseAdapterHoverListener(new MouseAdapter()									//���� ��ư Hover Listener
				{
					public void mouseEntered(MouseEvent e)																			//���콺�� �÷���������
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().btns[0])
						{
							AppManager.CreateInstance().getAppMain().btns[0].setIcon(ImageData.searchEntered);						//�˻� ��ư Hover �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[1])
						{
							AppManager.CreateInstance().getAppMain().btns[1].setIcon(ImageData.regEntered);							//��� ��ư Hover �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[2])
						{
							AppManager.CreateInstance().getAppMain().btns[2].setIcon(ImageData.updateEntered);						//���� ��ư Hover �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[3])
						{
							AppManager.CreateInstance().getAppMain().btns[3].setIcon(ImageData.analysisEntered);					//�м� ��ư Hover �̹����� ���� 
						}
					}
					@Override
					public void mouseExited(MouseEvent e)																			//���콺�� ������ ��������
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().btns[0])
						{
							AppManager.CreateInstance().getAppMain().btns[0].setIcon(ImageData.searchBasic);						//�˻���ư �⺻ �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[1])
						{
							AppManager.CreateInstance().getAppMain().btns[1].setIcon(ImageData.regBasic);							//��� ��ư �⺻ �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[2])
						{
							AppManager.CreateInstance().getAppMain().btns[2].setIcon(ImageData.updateBasic);						//���� ��ư �⺻ �̹����� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().btns[3])
						{
							AppManager.CreateInstance().getAppMain().btns[3].setIcon(ImageData.analysisBasic);						//�м� ��ư �⺻ �̹����� ���� 
						}
					}
				});
		AppManager.CreateInstance().getAppMain().addMouseAdapterButtonListener(new MouseAdapter()									//�޴��ٰ��� ���콺 �̺�Ʈ
				{
						public void mousePressed(MouseEvent e)																		//�޴��ٸ� ����� �� ������ǥ�� �޾ƿ�
						{
							Object obj = e.getSource();
							if(obj == AppManager.CreateInstance().getAppMain().menuBar)
							{
								AppManager.CreateInstance().getAppMain().mainMouseX = e.getX();										//���콺 x ������ǥ ��������
								AppManager.CreateInstance().getAppMain().mainMouseY = e.getY();										//���콺 y ������ǥ ��������
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarSearch)									//�˻� Dialog �޴����ΰ��
							{
								AppManager.CreateInstance().getAppMain().searchMouseX = e.getX();									//���콺 x ������ǥ ��������
								AppManager.CreateInstance().getAppMain().searchMouseY = e.getY();									//���콺 y ������ǥ ��������
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarRegist)									//��� Dialog �޴����ΰ��
							{
								AppManager.CreateInstance().getAppMain().registMouseX = e.getX();									//���콺 x ���� ��ǥ ��������
								AppManager.CreateInstance().getAppMain().registMouseY = e.getY();									//���콺 y ���� ��ǥ ��������
							}
							else if(obj == AppManager.CreateInstance().getAppMain().menuBarUpdate)									//���� Dilaog �޴����ΰ��
							{
								AppManager.CreateInstance().getAppMain().updateMouseX = e.getX();									//���콺 x ������ǥ ��������
								AppManager.CreateInstance().getAppMain().updateMouseY = e.getY();									//���콺 y ������ǥ ��������
							}
						}
				});
		AppManager.CreateInstance().getAppMain().addMouseMotionButtonListener(new MouseMotionAdapter() {							//�޴��� ���콺 ��� �̺�Ʈ ����
			
			public void mouseDragged(MouseEvent e)
			{
				Object obj = e.getSource();
				if(obj == AppManager.CreateInstance().getAppMain().menuBar)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().setLocation(x - AppManager.CreateInstance().getAppMain().mainMouseX,
							y - AppManager.CreateInstance().getAppMain().mainMouseY);												//Main �޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarSearch)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().diaSearch.setLocation(x - AppManager.CreateInstance().getAppMain().searchMouseX,
							y - AppManager.CreateInstance().getAppMain().searchMouseY);												//�˻� Dialog �޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarRegist)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().dia.setLocation(x - AppManager.CreateInstance().getAppMain().registMouseX,
							y - AppManager.CreateInstance().getAppMain().registMouseY);												//��� Dialog �޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
					
				}
				else if(obj == AppManager.CreateInstance().getAppMain().menuBarUpdate)
				{
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					AppManager.CreateInstance().getAppMain().diaUpdate.setLocation(x - AppManager.CreateInstance().getAppMain().updateMouseX,
							y - AppManager.CreateInstance().getAppMain().updateMouseY);												//���� Dialog �޴��ٸ� ��� �������� �� ��ü �����ӵ� �����̰� ����
				}
			}
			
		});
		AppManager.CreateInstance().getAppMain().addExitMouseListener(new MouseAdapter()											//���� ��ư ���콺 �̺�Ʈ ����
				{
					@Override
					public void mouseEntered(MouseEvent e)																			//���� ��ư ���콺 Hover ���� �ϰ��
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)	
						{
							AppManager.CreateInstance().getAppMain().exit.setIcon(ImageData.exitButtonEntered);						//�������� ��ư ������ ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonEntered);				//�˻� dialog ���� ��ư ������ ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonEntered);				//��� dialog ���� ��ư ������ ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonEntered);				//���� dialog ���� ��ư ������ ����
						}
					}
					@Override
					public void mouseExited(MouseEvent e)																			//���� ��ư ���콺�� ������
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)							
						{
							AppManager.CreateInstance().getAppMain().exit.setIcon(ImageData.exitButtonBasic);						//���� ���� ��ư �������·� ����			
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().searchExit.setIcon(ImageData.exitButtonBasic);					//�˻� dialog ���� ��ư �������·� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().registerExit.setIcon(ImageData.exitButtonBasic);				//��� dialog ���� ��ư �������·� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().updateExit.setIcon(ImageData.exitButtonBasic);					//���� dialog ���� ��ư ���� ���·� ����
						}
					}
					@Override
					public void mouseReleased(MouseEvent e)																			//���콺�� ���������
					{
						Object obj = e.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().exit)													//���� ��ư ���������
						{
							System.exit(0);																							//���α׷� ����
						}
						else if(obj == AppManager.CreateInstance().getAppMain().searchExit)
						{
							AppManager.CreateInstance().getAppMain().diaSearch.dispose();											//�˻� dialog ����
							
							//������ ��ư Ȱ��ȭ
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);	
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
							searchOpenedFlag = false;																				//�˻� dialog ���� ���·� �ٲ�
						}
						else if(obj == AppManager.CreateInstance().getAppMain().registerExit)
						{
							AppManager.CreateInstance().getAppMain().dia.dispose();													//��� dialog ����
							
							//������ ��ư Ȱ��ȭ
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
	
							registerOpenedFlag = false;																				//��� dialog ���� ���·� �ٲ�
						}
						else if(obj == AppManager.CreateInstance().getAppMain().updateExit)
						{
							AppManager.CreateInstance().getAppMain().diaUpdate.dispose();											//���� dialog ����
							
							//������ ��ư Ȱ��ȭ
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
							updateOpenedFlag = false;																				//���� dialog ���� ���·� �ٲ�
						}
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionSearchProListener(new ActionListener()									//�˻� Dialog ���� ������ ComboBox �ʱ�ȭ �̺�Ʈ ����
				{
					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						JComboBox tmp = (JComboBox)arg0.getSource();
						String select = (String)tmp.getSelectedItem();
						if(select.equals("��ü")) {																					//��ü�ϰ��
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel());					//���� ���� null ���·� �ʱ�ȭ
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(sTown));				//����Ư�����ϰ�� �������� sTown String���� �ʱ�ȭ
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(iTown));				//��õ�������ϰ�� �������� iTown String���� �ʱ�ȭ
						}
						else {
							AppManager.CreateInstance().getAppMain().guGun.setModel(new DefaultComboBoxModel(gyTown));				//��⵵ �ϰ�� ���� ���� gyTown String ���� �ʱ�ȭ
						}	
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionSearchListener(new ActionListener()										//��� ���� �̺�Ʈ
				{
					public void actionPerformed(ActionEvent e) {
						
						JButton btn = (JButton)e.getSource();
						if(btn == AppManager.CreateInstance().getAppMain().searchButton)
						{
							ArrayList <AccidentCase> outputDatas = new ArrayList<AccidentCase>();									//��� ���� ������ ArrayList ����
							
							//������ �޾ƿ���
							String pro = (String)AppManager.CreateInstance().getAppMain().siDo.getSelectedItem();					//�õ� �ɼ� ��������
							String tow = (String)AppManager.CreateInstance().getAppMain().guGun.getSelectedItem();					//���� �ɼ� ��������
							String year = (String)AppManager.CreateInstance().getAppMain().yearcbSearch.getSelectedItem();			//�� �ɼ� ��������
							String month = (String)AppManager.CreateInstance().getAppMain().monthcbSearch.getSelectedItem();		//�� �ɼ� ��������
							
							if(pro.equals("��ü"))																					//��Ұ� ��ü �ϰ��
							{
								if(year.contentEquals("�⵵") && month.equals("��"))		//��Ұ� ��ü �̸鼭 ��¥�� ���� ��� ��� ��������.
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().getAll();	
								}
								else if(!year.equals("�⵵") && month.equals("��"))				//���� ���� �Ǿ� �ְ�, ���� ���� �ȵǾ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(year);
								}
								else if(year.equals("�⵵") && !month.equals("��"))				//���� ���õǾ� ���� �ʰ�, ���� ���õǾ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseMonthTime(month);
								}
								else												//��Ұ� ��ü �̸鼭 Ư����¥�� �����Ǿ��ִ� ���
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseTime(year,month);
								}
							}
							else													//��Ұ� ��ü�� �ƴ� ��� (��Ұ� �����Ǿ��ִ� ���
							{
								if(year.equals("�⵵") && month.equals("��"))					//Ư�� ��� + ��¥ ���� 
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLoca(pro, tow);							
								}
								else if(year.equals("�⵵") && !month.equals("��"))				//Ư����� + �� ����
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLocaMonth(pro, tow, month);
								}
								else if(!year.equals("�⵵") && month.equals("��"))				//Ư����� + �� ����
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCaseLocaYear(pro, tow, year);
								}
								else															//Ư����� + ��� ����
								{
									outputDatas = AppManager.CreateInstance().getAccidentCaseDAO().searchCase(pro, tow, year, month);			
								}
							}
							
							// ��� ����
							AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");		//table ȭ�� �����ֱ�.
							AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);															//table ��ü �ʱ�ȭ
							
							//Table ������ �ٽ� ä���
							for(AccidentCase outputCase: outputDatas)																					//��ȯ ���� ������ ���������� table �� ä���
							{
								String [] temp = {Integer.toString(outputCase.getCscode()),
										outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
										outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
										Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
										outputCase.getActype()};
								AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);														//row �߰�
							}
							
							//��ư ��� Ȱ��ȭ
							AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
							AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
	
							AppManager.CreateInstance().getAppMain().diaSearch.dispose();																//�˻� dialog �ݱ�
							searchOpenedFlag = false;																									//�˻� dialog ���� ���·� �ٲٱ�
							AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnBasic);								//��ư �̹��� ������� �ٲٱ�
							
						}
					}
			
				});
	
		AppManager.CreateInstance().getAppMain().addActionRegisterProListener(new ActionListener()														//��� Dialog �� ComboBox���� �̺�Ʈ
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						JComboBox tmp = (JComboBox)e.getSource();
						String select = (String)tmp.getSelectedItem();

						if(select.equals("��ü")) {	
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel());											//��ü�� �����Ǿ����� ���, �������� null�� �ʱ�ȭ
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(sTown));										//����Ư���÷� �����Ǿ��� ���, �������� sTown ���� �ʱ�ȭ
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(iTown));										//��õ�������� ���, �������� iTown ���� �ʱ�ȭ
						}
						else {
							AppManager.CreateInstance().getAppMain().tow.setModel(new DefaultComboBoxModel(gyTown));									//��⵵�� ��� , �������� gyTown ���� �ʱ�ȭ
						}	
					}
				});

		AppManager.CreateInstance().getAppMain().addActionRegisterListener(new ActionListener()													//��ϰ��� ActionListener
				{
					public void actionPerformed(ActionEvent arg0) {
						Object obj = arg0.getSource();
						
						if(obj == AppManager.CreateInstance().getAppMain().regBtn)																//��� ��ư�� ������ ���
						{
							//������
							boolean insertFlag;																									//��� �������� boolean
							AccidentCase tempCase = new AccidentCase();
							
							//������ ���� ��� tempCase ������
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
								insertFlag = false;																								//�����Ͱ� �Ѱ��� ������� ��� ��� ���з� ����
							else
								insertFlag = AppManager.CreateInstance().getAccidentCaseDAO().insertCase(tempCase);								//��� ä���� ��� ��� �������� ����
							
							
							//�������� �κ�-----------------------------------------------------------------------------------------------------------------------
							if(insertFlag == true)																								//����� �����Ǿ��������
							{	
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().regBtn, "��ϵǾ����ϴ�.");					//��ϵǾ��ٴ� �޼����� ���
								
								int maxIndex = AppManager.CreateInstance().getAccidentCaseDAO().getNewCaseCode();								//���� �ֻ��� csCode�� ������
								AccidentCase outputCase = new AccidentCase();
								
								outputCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(maxIndex);								//csCode�� �ٽð˻��Ͽ� case �� ������
								String [] temp = {Integer.toString(outputCase.getCscode()),
										outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
										outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
										Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
										outputCase.getActype()};
								
								//Table �ʱ�ȭ
								AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);												//table �ʱ�ȭ
								
								//Table ������ �ٽ� ä���
								AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");	//table ȭ�� �����ֱ�.
								AppManager.CreateInstance().getAppMain().dia.dispose();															//��� Dialog ����
								
								//������ ��ư Ȱ��ȭ
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								
								registerOpenedFlag = false;																						//��� Dialog ���� ���·� �����
								AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);							//��� ��ư �ʱ�ȭ
				
							}
							else																												//��� ����
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "��� ����!\n���� ��Ȯ�� �Է��Ͻʽÿ�!", "���", JOptionPane.PLAIN_MESSAGE);
								AppManager.CreateInstance().getAppMain().dia.dispose();															//��� Dialog ����
								
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								registerOpenedFlag = false;																						//��� Dialoag ���� ���·� �����

								AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);							//��� ��ư ������ ������� �����
								
							}
						}		
						
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionUpdateProListener(new ActionListener()												//���� Dialog ComboBox ���� �̺�Ʈ
				{
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						JComboBox tmp = (JComboBox)arg0.getSource();
						String select = (String)tmp.getSelectedItem();
						
						if(select.equals("��ü")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel());							//��ü�� �����Ǿ�������� ���� ���� null�� ����
						}
						else if(select.equals("����Ư����")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(sTown));						//����Ư���÷� �����Ǿ� ������� ���� ���� sTown���� ����
						}
						else if(select.equals("��õ������")) {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(iTown));						//��õ�����÷� �����Ǿ� ���� ��� ���� ���� iTown ���� ����
						}
						else {
							AppManager.CreateInstance().getAppMain().towUpdate.setModel(new DefaultComboBoxModel(gyTown));						//��⵵�� �����Ǿ� ���� ��� ���� ���� gyTown ���� ����
						}
					}
				});
		
		AppManager.CreateInstance().getAppMain().addActionUpdateSearchListener(new ActionListener()											//Dialog �� ��� �˻� ���� �̺�Ʈ
				{
					public void actionPerformed(ActionEvent arg0)
					{
						Object obj = arg0.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)													//�˻�Ŭ����
						{
							AccidentCase temp = new AccidentCase();
							int accNum = Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText());					//��� ��ȣ ��������
							temp = AppManager.CreateInstance().getAccidentCaseDAO().getCase(accNum);										//temp �� ������ ���� ����
							
							if(temp != null)																								//�˻� ������
							{
								//�� �ɼǿ� �°� �� ����
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
							else																											//�˻� ���н� ���â ���
							{	
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "����ȣ�� ��Ȯ�� �Է��Ͻÿ�!", "���", JOptionPane.PLAIN_MESSAGE);
							}
						}
						
					}
					
				});
		
		AppManager.CreateInstance().getAppMain().addActionUpdateListener(new ActionListener()												//����/���� Dialog �� ���ߵ� �̺�Ʈ
				{
					public void actionPerformed(ActionEvent arg0)
					{
						Object obj = arg0.getSource();
						if(obj == AppManager.CreateInstance().getAppMain().updateButton)													//���� ��ư�� Ŭ���� ���
						{
							
							AccidentCase tempCase = new AccidentCase();																		//�ӽ� AccidentCase ����� ����
							boolean succFlag;																								//���� ���� ���� boolean
							
							int caseNum = Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText());					//��� ��ȣ ��������
							
							//�缳���� ���� tempCase�� ����
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
								succFlag = false;																							//�ϳ��� �� �ɼ��� ������ ���з� ����
							}
							else
							{
								succFlag = AppManager.CreateInstance().getAccidentCaseDAO().updateCase(tempCase);							//������ ���
							}
							
							
							if(succFlag == true)
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().deleteButton, "�����Ǿ����ϴ�.");		//���� ���� �޼��� ����
								
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table"); //���������� table �� �ٲ�.
								
								AccidentCase outputCase = new AccidentCase();
								outputCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(caseNum);								//������ ������ �ٽ� ������
								
								String [] temp = {Integer.toString(outputCase.getCscode()),
										outputCase.getProvince(),outputCase.getTown(), outputCase.getYear(),
										outputCase.getMonth(), outputCase.getDay(),Integer.toString(outputCase.getCasulity()),
										Integer.toString(outputCase.getDead()),Integer.toString(outputCase.getInjured()),
										outputCase.getActype()};
							
								//Table �ʱ�ȭ
								AppManager.CreateInstance().getAppMain().basicTable.setRowCount(0);											//table �ʱ�ȭ
								//Table ������ �ٽ� ä���
								AppManager.CreateInstance().getAppMain().basicTable.addRow(temp);											//������ ������ row �߰��ϱ�.
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "table");	//table ȭ�� �����ֱ�.
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();												//���� Dialog ����
								
								//������ ��ư Ȱ��ȭ
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
								
								updateOpenedFlag = false;																					//���� Dialog ������·� �ٲ�
								
								//��ư ���� �̹����� �ٲ�.
								AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);
								AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);
								AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);
								
							}
							else
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "���� ����!!", "���", JOptionPane.PLAIN_MESSAGE);	//�������� â ����
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();												//���� Dialog ����
								//������ ��ư Ȱ��ȭ
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;																					//���� Dialog ���� ���·� �ٲ�.
								//��ư ���� �̹����� �ٲ�
								AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);
								AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);
								AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);
							
							}
						}
						else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)												//������ư�ϰ��
						{
							boolean delSuccess;																								//���� ���� ���� ����
							delSuccess = AppManager.CreateInstance().getAccidentCaseDAO().deleteCase(Integer.parseInt(AppManager.CreateInstance().getAppMain().caseNumTxt.getText())); //������ ����
							
							if(delSuccess == true)																							//���� ������
							{
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().deleteButton, "�����Ǿ����ϴ�.");		//���� ���� �޼��� ����
								//ȭ�� CardLayout ���� ���� ���� ��ȯ
								AppManager.CreateInstance().getAppMain().cardLayout.show(AppManager.CreateInstance().getAppMain().cardPanel, "image");	//table ���¿��� image �� �ٲ�.
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();												//���� Dialog ����
								
								//������ ��ư Ȱ��ȭ
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;																					//���� Dialog ���� ���·� �ٲ�.
								
							}
							else																											//���� ���н�
							{	
								JOptionPane.showMessageDialog(AppManager.CreateInstance().getAppMain().diaUpdate, "���� ����!!", "���", JOptionPane.PLAIN_MESSAGE);	//���� ���� �޼��� ����
								AppManager.CreateInstance().getAppMain().diaUpdate.dispose();												//���� ���� �޼��� ����
								
								//������ ��ư Ȱ��ȭ
								AppManager.CreateInstance().getAppMain().btns[0].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[1].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[2].setEnabled(true);
								AppManager.CreateInstance().getAppMain().btns[3].setEnabled(true);
		
								updateOpenedFlag = false;																					//���� Dialog ���� ���·� �ٲ�.
							}
							
						}
					}
					
				});
		AppManager.CreateInstance().getAppMain().addTableListener(new MouseAdapter()														//main table ���� �̺�Ʈ
				{
					public void mouseClicked(MouseEvent e)
					{
						if(e.getButton() ==1)
						{
							
						}
						if(e.getClickCount() ==2)																							//���� Ŭ���� �Ұ��
						{
							int row = AppManager.CreateInstance().getAppMain().table.getSelectedRow();										//�� ��ȣ ��������
							int accNum = Integer.parseInt((String)AppManager.CreateInstance().getAppMain().table.getValueAt(row, 0));		//�� ��ȣ ���� �� ���� �Ӽ��� ��������
			
							AccidentCase tempCase = new AccidentCase();																		//�ӽ� Case ���� ����
							tempCase = AppManager.CreateInstance().getAccidentCaseDAO().getCase(accNum);									//��� ��ȣ �̻��� ������ case �� ����
				
							
							DetailInfo accDetailInfo = new DetailInfo(tempCase);															//DetailInfo Dialog Ű��
							accDetailInfo.addMouseDetailMenuBarListener(new MouseAdapter()													//DetailInfo Dialog �޴��� ���� �̺�Ʈ
							{
								@Override
								public void mousePressed(MouseEvent e)																		//�޴��ٸ� ����� �� ������ǥ�� �޾ƿ�
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.menuBarDetail)
									{
										accDetailInfo.mouseX = e.getX();
										accDetailInfo.mouseY = e.getY();				
									}
								}
							});
							accDetailInfo.addMouseDetailMenuBarMotionListener(new MouseMotionAdapter()										//�޴��ٸ� ��� �������� �� ��ġ �ʱ�ȭ ��Ŵ
							{
								@Override
								public void mouseDragged(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.menuBarDetail)
									{
										int x = e.getXOnScreen();
										int y = e.getYOnScreen();
										accDetailInfo.setLocation(x - accDetailInfo.mouseX, y - accDetailInfo.mouseY);							//�޴��ٸ� ��� �������� �� ��ü Dialog�� �����̰� ����		
									}
								}
							});
							accDetailInfo.addMouseDetailExitListener(new MouseAdapter()														//DetailInfo ���� ��ư Mouse Listener
							{
								@Override
								public void mouseEntered(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
										accDetailInfo.detailExit.setIcon(ImageData.exitButtonEntered);										//���콺�� �ö������� Hover �̹����� ����
									}
								}
								@Override
								public void mouseExited(MouseEvent e)
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
											accDetailInfo.detailExit.setIcon(ImageData.exitButtonBasic);									//���콺�� �������� ���� �̹����� ����	
										}
								}
								@Override
								public void mouseReleased(MouseEvent e)																		//���콺�� �������
								{
									Object obj = e.getSource();
									if(obj == accDetailInfo.detailExit)
									{
										accDetailInfo.dispose();																			//DetailInfo Dialog ����
									}
								}
							});
						}
						if(e.getButton() == 3)
						{
							
						}
					}
					
				});
		AppManager.CreateInstance().getAppMain().addInsideMouseListener(new MouseAdapter()											//����  Dialog ���� ��ư ���� ���콺 Hover Listener 
		{
				public void mouseEntered(MouseEvent e)																				//���콺�� �ö��� ������
				{
					Object obj = e.getSource();	
					if(obj == AppManager.CreateInstance().getAppMain().searchButton)												
					{
						AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnEntered);			//�˻� ��ư Hover �̹����� ��ȯ
					}
					else if(obj == AppManager.CreateInstance().getAppMain().regBtn)
					{
						AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnEntered);						//��� ��ư hover �̹����� ��ȯ
					}
					else if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)
					{
						 AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnEntered);		//����/���� Dialog ���� �˻� ��ư hover�̹����� ��ȯ
					}
					else if(obj == AppManager.CreateInstance().getAppMain().updateButton)
					{
						AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnEntered);			//���� ��ư hover�̹����� ��ȯ
					}
					else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)
					{
						AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnEntered);			//���� ��ư Hover �̹����� ��ȯ
					}
					
				}
				@Override
				public void mouseExited(MouseEvent e)																				//���콺�� ������ ������ ���
				{
					Object obj = e.getSource();
					if(obj == AppManager.CreateInstance().getAppMain().searchButton)
					{
						AppManager.CreateInstance().getAppMain().searchButton.setIcon(ImageData.searchDialogBtnBasic);				//�˻� ��ư ���� �̹����� ����
					}
					else if(obj == AppManager.CreateInstance().getAppMain().regBtn)
					{
						AppManager.CreateInstance().getAppMain().regBtn.setIcon(ImageData.regDialogBtnBasic);						//��� ��ư ���� �̹����� ����
					}
					else if(obj == AppManager.CreateInstance().getAppMain().searchUpdateBtn)
					{
						 AppManager.CreateInstance().getAppMain().searchUpdateBtn.setIcon(ImageData.updateSearchBtnBasic);			//���� Dialog ���� �˻� ��ư ���� �̹����� ����
					}
					else if(obj == AppManager.CreateInstance().getAppMain().updateButton)
					{
						AppManager.CreateInstance().getAppMain().updateButton.setIcon(ImageData.updateDialogBtnBasic);				//���� ��ư ���� �̹����� ����
					}
					else if(obj == AppManager.CreateInstance().getAppMain().deleteButton)
					{
						AppManager.CreateInstance().getAppMain().deleteButton.setIcon(ImageData.deleteDialogBtnBasic);				//���� ��ư ���� �̹����� ����
					}

					
				}
		});

	}
}




