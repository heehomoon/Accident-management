/**
 * AppManager.class
 * @author ������, ������, ����ȣ
 * 
 * ���� �ۼ���: 2017�� 12�� 23��
 * ���� ������: 2018�� 1�� 2��
 */
public class AppManager {												//������ ��ü�� ���� ���� ����
	
	private static AppManager s_instance;								//AppManager instance ��ü ����
	
	private AppMain m_appMain;											//appMain ��ü ����
	private AppController m_appController;								//appController ��ü ����
	private AccidentCaseDAO m_accidentCaseDAO;							//accidentCaseDAO ��ü ����
	
	private AppManager()												//AppManager private ������ 
	{
		 s_instance =null;
	}
	public AppMain getAppMain() {										//AppMain ��ȯ
		return m_appMain;
	}

	public void setAppMain(AppMain m_appMain) {							//AppMain ��ü ���̱�
		this.m_appMain = m_appMain;
	}

	public AppController getAppController() {							//AppController ��ȯ
		return m_appController;
	}

	public void setAppController(AppController m_appController) {		//AppController ��ü���̱�
		this.m_appController = m_appController;
	}

	public AccidentCaseDAO getAccidentCaseDAO() {						//AccidentCaseDAO ��ȯ
		return m_accidentCaseDAO;
	}

	public void setAccidentCaseDAO(AccidentCaseDAO m_accidentCaseDAO) {	//AccidentCaseDAO ��ü ���̱�
		this.m_accidentCaseDAO = m_accidentCaseDAO;
	}

	public static AppManager CreateInstance()							//AppManager Instance ����
	{
		if(s_instance == null)											//Instance ������ ������ ���� �Ͽ���ȯ
		{
			s_instance = new AppManager();
			return s_instance;
		}
		else															//Instance ������ �ִٸ� �״�� ��ȯ
		{
			return s_instance;		
		}
	}
	
}
