
public class AccidentCase {

	private int cscode;   			//��� ��ȣ
	private String province;        //��,��
	private String town;            //��,��
	private String year;            //�⵵
	private String month;           //��
	private String day;             //��
	private String policeno;        //������ȣ
	private int casulity;           //����� = ����� + �λ���
	private int dead;               //�����
	private int injured;            //�λ��� 
	private String actype;          //������� (������, ������, �����ܵ�)
	private double latitude;        //����
	private double longitude;       //�浵
	
	public int getCscode() {
		return cscode;
	}
	public void setCscode(int cscode) {
		this.cscode = cscode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getPoliceno() {
		return policeno;
	}
	public void setPoliceno(String policeno) {
		this.policeno = policeno;
	}
	public int getCasulity() {
		return casulity;
	}
	public void setCasulity() {
		this.casulity = this.dead + this.injured;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getInjured() {
		return injured;
	}
	public void setInjured(int injured) {
		this.injured = injured;
	}
	public String getActype() {
		return actype;
	}
	public void setActype(String actype) {
		this.actype = actype;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}