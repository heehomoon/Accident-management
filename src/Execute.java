/**
 * Execute.class
 * @author ������, ������, ����ȣ
 * 
 * ���� �ۼ���: 2017�� 12�� 23��
 * ���� ������: 2018�� 1�� 2��
 */
public class Execute {
	
	public final static int WIDTH = 1300;					//������ ���� ����
	public final static int HEIGHT = 700;					//������ ���� ����
	
	public static void main(String[] args) {
		
		new AppMain();										//AppMain ��ü ����
		new AppController();								//AppController ��ü ����
		new AccidentCaseDAO();								//AccidentCaseDAO ��ü ����
		
	}
}
