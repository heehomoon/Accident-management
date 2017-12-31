import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import javax.swing.JTable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
public class AccidentCaseDAO {
	
	String DBid = "heeho";
	String DBpw = "1234";
	
	String jdbcDriver = "com.mysql.jdbc.Driver";
	String jdbcUrl = "jdbc:mysql://localhost/javadb";//mysql�� ���� �ȵǴ� ����� �����ڷᰪ�� �־����ϴ�.
	
	Connection conn;

	PreparedStatement pstmt;
	ResultSet rs;

	String sql;
	ArrayList<AccidentCase> datas;

	public AccidentCaseDAO()
	{
		AppManager.CreateInstance().setAccidentCaseDAO(this);
	}
	
	boolean connectTest(String id, String pw)
	{
		try {
			Class.forName(jdbcDriver);
			DriverManager.getConnection(jdbcUrl,id,pw);
			this.DBid= id;
			this.DBpw = pw;
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	//DB����
	void connectDB(){

		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl,DBid,DBpw);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//DB �ݱ�
	void closeDB(){
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	int getNewCaseCode() {
		
		connectDB();
		int newCsCode = 0;
		
		sql = "select MAX(cscode) from accidentcase";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				newCsCode = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeDB();
		
		return newCsCode;
	}
	
	
	
	AccidentCase getCase(int cscode) 
	{
		connectDB();
		AccidentCase accCase = new AccidentCase();
		sql = "select * from accidentcase where cscode = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cscode);
			rs = pstmt.executeQuery();

			if(rs.next())
			{
				accCase.setCscode(cscode);
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setPoliceno(rs.getString("policeno"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		return accCase;
	}
	
	ArrayList<AccidentCase> searchCase(String province, String town, String year, String month)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();
		sql = "select * from accidentcase where (province = ? and town = ? and year = ? and month = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);
			pstmt.setString(2, town);
			pstmt.setString(3, year);
			pstmt.setString(4, month);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				accCase = new AccidentCase();
				accCase.setCscode(rs.getInt("cscode"));
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setPoliceno(rs.getString("policeno"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();
		return datas;
	}	
	
	ArrayList<AccidentCase> searchCaseLoca(String province, String town)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();
		sql = "select * from accidentcase where (province = ? and town = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);
			pstmt.setString(2, town);
			rs = pstmt.executeQuery();
		

			while(rs.next()) {
				accCase = new AccidentCase();
				accCase.setCscode(rs.getInt("cscode"));
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setPoliceno(rs.getString("policeno"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();
		return datas;
	}	
	
	ArrayList<AccidentCase> searchCaseTime(String year, String month)
	   {
	      AccidentCase accCase;
	      datas = new ArrayList<AccidentCase>();
	      connectDB();
	      
	         sql = "select * from accidentcase where (year = ? and month = ?)";
	         try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1,year);
	            pstmt.setString(2,month);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               accCase = new AccidentCase();
	               accCase.setCscode(rs.getInt("cscode"));
	               accCase.setProvince(rs.getString("province"));
	               accCase.setTown(rs.getString("town"));
	               accCase.setYear(rs.getString("year"));
	               accCase.setMonth(rs.getString("month"));
	               accCase.setDay(rs.getString("day"));
	               accCase.setPoliceno(rs.getString("policeno"));
	               accCase.setDead(rs.getInt("dead"));
	               accCase.setInjured(rs.getInt("injured"));
	               accCase.setCasulity();
	               accCase.setActype(rs.getString("actype"));
	               accCase.setLatitude(rs.getDouble("latitude"));
	               accCase.setLongitude(rs.getDouble("longitude"));
	               
	               datas.add(accCase);
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         return datas;
	         
	      }
	
	
	ArrayList<AccidentCase> searchCaseTime(String year)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();
		
	      sql = "select * from accidentcase where year = ?";
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1,year);
	         rs = pstmt.executeQuery();
	         
	         while(rs.next())
	         {
					accCase = new AccidentCase();
					accCase.setCscode(rs.getInt("cscode"));
					accCase.setProvince(rs.getString("province"));
					accCase.setTown(rs.getString("town"));
					accCase.setYear(rs.getString("year"));
					accCase.setMonth(rs.getString("month"));
					accCase.setDay(rs.getString("day"));
					accCase.setPoliceno(rs.getString("policeno"));
					accCase.setDead(rs.getInt("dead"));
					accCase.setInjured(rs.getInt("injured"));
					accCase.setCasulity();
					accCase.setActype(rs.getString("actype"));
					accCase.setLatitude(rs.getDouble("latitude"));
					accCase.setLongitude(rs.getDouble("longitude"));
					
					datas.add(accCase);
	         }
	         
	     	rs.close();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return datas;
	   }
	
	
	String getPolCode(String dpcode)
	{
		int num = 0;
		Vector<String> list = new Vector<String>();
		
		connectDB();
		sql = "select * from police where dpcode = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dpcode);
			rs = pstmt.executeQuery();
				
			while(rs.next())
			{
				list.add(rs.getString("policeno"));
			}
			
			rs.close();
			num = list.size();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
		
		if(num>0)
			return list.get((int) (Math.random()*num));
		else
		{
			return "NULL";
		}
	}

	ArrayList<AccidentCase> getAll() {
		
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();
		sql = "select * from accidentcase";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				accCase = new AccidentCase();
				accCase.setCscode(rs.getInt("cscode"));
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setPoliceno(rs.getString("policeno"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();
		return datas;
	}
	
	 Police getPolice (String polno)
	   {
	      int num = 0;
	      Police pol = new Police();
	      
	      connectDB();
	      sql = "select * from police where policeno = ? ";
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, polno);
	         rs = pstmt.executeQuery();
	            
	         if(rs.next())
	         {
	            pol.setPoliceno(polno);
	            pol.setPolname(rs.getString("polname"));
	            pol.setRank(rs.getString("rank"));
	            pol.setDepart(rs.getString("depart"));
	            pol.setDpcode(rs.getString("dpcode"));
	         }
	         
	         rs.close();
	         
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      closeDB();
	      
	      return pol;
	   }

	 
	 boolean insertCase(AccidentCase accCase) 
		{
			connectDB();
			int chk =0;
			boolean flag=false; 
			sql = "INSERT "+
				  "INTO accidentcase(province,town,year,month,day,policeno,dead,injured,actype,latitude,longitude) "+
				  "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accCase.getProvince());
				pstmt.setString(2, accCase.getTown());
				pstmt.setString(3, accCase.getYear());
				pstmt.setString(4, accCase.getMonth());
				pstmt.setString(5, accCase.getDay());
				pstmt.setString(6, accCase.getPoliceno());
				pstmt.setInt(7, accCase.getDead());
				pstmt.setInt(8, accCase.getInjured());
				pstmt.setString(9, accCase.getActype());
				pstmt.setDouble(10, accCase.getLatitude());
				pstmt.setDouble(11, accCase.getLongitude());
				
				chk = pstmt.executeUpdate();
				
				if(chk >0)
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			closeDB();
			return flag;
		}
		
		boolean updateCase(AccidentCase accCase) 
		{
			connectDB();
			int chk;
			boolean flag=false; 
			
			sql = "UPDATE accidentcase " + 
				  "SET province = ? , town = ?, year = ? , month = ?, day = ?, policeno = ?, dead = ?, injured =? , actype = ?, latitude = ?, longitude = ? " + 
				  "WHERE cscode = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accCase.getProvince());
				pstmt.setString(2, accCase.getTown());
				pstmt.setString(3, accCase.getYear());
				pstmt.setString(4, accCase.getMonth());
				pstmt.setString(5, accCase.getDay());
				pstmt.setString(6, accCase.getPoliceno());
				pstmt.setInt(7, accCase.getDead());
				pstmt.setInt(8, accCase.getInjured());
				pstmt.setString(9, accCase.getActype());
				pstmt.setDouble(10, accCase.getLatitude());
				pstmt.setDouble(11, accCase.getLongitude());
				pstmt.setInt(12, accCase.getCscode());
				
				chk = pstmt.executeUpdate();
				
				if(chk>0)
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			closeDB();
			return flag;
		}

		boolean deleteCase(int cscode)
		{
			connectDB();
			sql = "delete from accidentcase where cscode = ?";
			int chk;
			boolean flag = false;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cscode);
				chk = pstmt.executeUpdate();
				
				if(chk>0)
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeDB();
			return flag;
		}
		
//		boolean deleteAllCase()
//		{
//			connectDB();
//			sql = "TRUNCATE accidentcase;";
//			
//			int chk;
//			boolean flag = false;
//			
//			try {
//				pstmt = conn.prepareStatement(sql);
//				chk = pstmt.executeUpdate();
//				
//				if(chk>0)
//					flag = true;
//				
//				
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			closeDB();
//			return flag;
//		}
//		
		
		void ParsingAccidentData()
		{
			// JSON �����Ͱ� ����� url �ּҸ� �����ϴ� String �迭
			String url[] = new String[9];
			
			//2016 �⵵
			url[0] = new String();  //����
			url[0] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2016&siDo=1100&guGun=";
			url[1] = new String();  //��⵵
			url[1] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2016&siDo=1300&guGun=";
			url[2] = new String();  //��õ������
			url[2] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2016&siDo=2300&guGun=";
	
			//2015
			url[3] = new String();  //����
			url[3] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2015&siDo=1100&guGun=";
			url[4] = new String();  //��⵵
			url[4] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2015&siDo=1300&guGun=";
			url[5] = new String();  //��õ������
			url[5] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2015&siDo=2300&guGun=";
	
			//2014
			url[6] = new String();   //����
			url[6] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2014&siDo=1100&guGun=";
			url[7] = new String();   //��⵵
			url[7] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2014&siDo=1300&guGun=";
			url[8] = new String();   //��õ������
			url[8] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear=2014&siDo=2300&guGun=";
	
			
			// Parsing�� ������ Parser ����
			JSONParser parser = new JSONParser(); 
			
			// Ư�� �� �ּҸ� ����Ű�� URL ��ü
			URL postUrl;
			
			// ��� ��Ʈ�� ����
			InputStreamReader isr;
			
			//JSON ���� �����͸� �����ϴ� ��ü
			JSONObject jsonObj;
			//JSON ������ �����͸� ���ڿ��� �����ϴ� String
			String jsonStr;
			JSONArray jsonArr;
				
						
			try
			{
				for(int i =0 ; i<9; i++)
				{
					// url[i] �ּҸ� ���� ���� ����Ű�� postUrl
					postUrl = new URL(url[i]);
					
					//postUrl�� ���� "UTF-8" ������ ���ڰ�����
					isr = new InputStreamReader(postUrl.openConnection().getInputStream(), "UTF-8");
					
					//Ű searchResult�� �ش��ϴ� �� jsonObject�� ����
					jsonObj = (JSONObject)((JSONObject)JSONValue.parseWithException(isr)).get("searchResult");
					//Ű accidentDeath�� �ش��ϴ� ���ڿ��� jsonStr�� ����
					jsonStr = jsonObj.get("accidentDeath").toString();
					System.out.println(jsonObj.get("accidentDeath").toString());
		
			 		//�Ľ��� ����� JSONobject �������� JSONarray�� ����
			 		jsonArr = (JSONArray)parser.parse(jsonStr);
	
			 		//����ʸ� ������ ��ü ������
			 		AccidentCase accCase = new AccidentCase();
			 		//JSON �迭���� �ϳ��� ���� ������  JSON ��ü
			 		
	 				JSONObject jObj;
			 		
			 		for (int j = 0; j < jsonArr.size(); j++) 
			 		{
			 				jObj = (JSONObject) jsonArr.get(j);
			                
			 				accCase.setProvince(funcProvince((String) jObj.get("cd_003_lv1")));
			 				accCase.setTown(funcTown((String)jObj.get("cd_003"))); 
			 				
			 				String date = (String)jObj.get("dt_006");
			 				accCase.setYear(date.substring(0,4));
			 				accCase.setMonth(date.substring(4,6));
			 				accCase.setDay(date.substring(6,8));
			 				
			 				accCase.setPoliceno(getPolCode((String)jObj.get("cd_003"))); 
			 				
			 				accCase.setDead(((Long)jObj.get("no_010")).intValue());
			 				accCase.setInjured(((Long)jObj.get("injpsn_co")).intValue());
			 				accCase.setCasulity();
			 				
			 				accCase.setActype(funcAcctype((String) jObj.get("cd_014_lv1")));
			 				accCase.setLatitude(Double.parseDouble((String)jObj.get("grd_la")));
			 				accCase.setLongitude(Double.parseDouble((String)jObj.get("grd_lo")));
			 				
			 				insertCase(accCase);
			 				
			 				System.out.println("�õ�:"+accCase.getProvince());
			 				System.out.println("����:"+accCase.getTown());
			 				System.out.println("year:" +accCase.getYear());
			 				System.out.println("month:" +accCase.getMonth());
			 				System.out.println("day:" +accCase.getDay());
			 				System.out.println("������ȣ:" +accCase.getPoliceno());
			 				System.out.println("����ڼ�:" +accCase.getCasulity());
			 				System.out.println("����ڼ�:" +accCase.getDead());
			 				System.out.println("�λ��ڼ�:" +accCase.getInjured());
			 				System.out.println("�������:" + accCase.getActype());
			                System.out.println("�浵:" + accCase.getLongitude());
			                System.out.println("����:" + accCase.getLatitude());
			   
			 			}
				}
				} catch (Exception e) {
	
	 			e.printStackTrace();
	
	 		}
						
		}
		
		String funcProvince(String code)
		{
			String str[] = {"����Ư����","��⵵","��õ������"};
			
			if(code.equals("1100"))
				return str[0];
			else if(code.equals("1300"))
				return str[1];
			else if(code.equals("2300"))
				return str[2];
			
			return "NULL";
		}
		
		String funcTown(String code)
		{
			String seoul[] = {"������","������","���ϱ�","������","���Ǳ�","������","���α�","��õ��","�����","������","���빮��","���۱�","������","���빮��"
					,"���ʱ�","������","���ϱ�","���ı�","��õ��","��������","��걸","����","���α�","�߱�","�߶���"};
			
			String kyenggi[] = {"����","��ȭ��","����","��õ��","�����","���ֽ�","������","������","������","�����ֱ�","�����ֽ�","����õ��","��õ��","������","��ź��","������","�����","�Ȼ��","�ȼ���","�Ⱦ��","���ֽ�","���㱺","���ֽ�","��õ��","�����","������","���ν�","�ǿս�","�����ν�"
					,"��õ��","���ֽ�","���ñ�","���ý�","��õ��","�ϳ���","ȭ����"};
			
			String incheon[] = {"��ȭ��","��籸","����","������","����","����","����","������","������","�߱�"	};
			
			if(code.equals("1116"))
				return seoul[0];
			else if(code.equals("1117"))
				return seoul[1];
			else if(code.equals("1124"))
				return seoul[2];
			else if(code.equals("1111"))
				return seoul[3];
			else if(code.equals("1115"))
				return seoul[4];
			else if(code.equals("1123"))
				return seoul[5];
			else if(code.equals("1112"))
				return seoul[6];
			else if(code.equals("1125"))
				return seoul[7];
			else if(code.equals("1122"))
				return seoul[8];
			else if(code.equals("1107"))
				return seoul[9];
			else if(code.equals("1105"))
				return seoul[10];
			else if(code.equals("1114"))
				return seoul[11];
			else if(code.equals("1110"))
				return seoul[12];
			else if(code.equals("1109"))
				return seoul[13];
			else if(code.equals("1119"))
				return seoul[14];
			else if(code.equals("1104"))
				return seoul[15];
			else if(code.equals("1106"))
				return seoul[16];
			else if(code.equals("1118"))
				return seoul[17];
			else if(code.equals("1120"))
				return seoul[18];
			else if(code.equals("1113"))
				return seoul[19];
			else if(code.equals("1103"))
				return seoul[20];
			else if(code.equals("1108"))
				return seoul[21];
			else if(code.equals("1101"))  
				return seoul[22];
			else if(code.equals("1102"))
				return seoul[23];
			else if(code.equals("1121"))
				return seoul[24];
			
			
			else if(code.equals("1322"))
				return kyenggi[0];
			else if(code.equals("1328"))
				return kyenggi[1];
			else if(code.equals("1318"))
				return kyenggi[2];
			else if(code.equals("1332"))
				return kyenggi[3];
			else if(code.equals("1309"))
				return kyenggi[4];
			else if(code.equals("1319"))
				return kyenggi[5];
			else if(code.equals("1310"))
				return kyenggi[6];
			else if(code.equals("1333"))
				return kyenggi[7];
			else if(code.equals("1327"))
				return kyenggi[8];
			else if(code.equals("1312"))
				return kyenggi[9];
			else if(code.equals("1334"))
				return kyenggi[10];
			else if(code.equals("1330"))
				return kyenggi[11];
			else if(code.equals("1306"))
				return kyenggi[12];
			else if(code.equals("1303"))
				return kyenggi[13];
			else if(code.equals("1331"))
				return kyenggi[14];
			else if(code.equals("1302"))
				return kyenggi[15];
			else if(code.equals("1316"))
				return kyenggi[16];
			else if(code.equals("1307"))
				return kyenggi[17];
			else if(code.equals("1326"))
				return kyenggi[18];
			else if(code.equals("1305"))
				return kyenggi[19];
			else if(code.equals("1311"))
				return kyenggi[20];
			else if(code.equals("1323"))
				return kyenggi[21];
			else if(code.equals("1313"))
				return kyenggi[22];
			else if(code.equals("1320"))
				return kyenggi[23];
			else if(code.equals("1335"))
				return kyenggi[24];
			else if(code.equals("1329"))
				return kyenggi[25];
			else if(code.equals("1325"))
				return kyenggi[26];
			else if(code.equals("1336"))
				return kyenggi[27];
			else if(code.equals("1304"))
				return kyenggi[28];
			else if(code.equals("1324"))
				return kyenggi[29];
			else if(code.equals("1317"))
				return kyenggi[30];
			else if(code.equals("1314"))
				return kyenggi[31];
			else if(code.equals("1308"))
				return kyenggi[32];
			else if(code.equals("1321"))
				return kyenggi[33];
			else if(code.equals("1337"))
				return kyenggi[34];
			else if(code.equals("1315"))
				return kyenggi[35];
			
			
			else if(code.equals("2309"))
				return incheon[0];
			else if(code.equals("2308"))
				return incheon[1];
			else if(code.equals("2303"))
				return incheon[2];
			else if(code.equals("2305"))
				return incheon[3];
			else if(code.equals("2302"))
				return incheon[4];
			else if(code.equals("2304"))
				return incheon[5];
			else if(code.equals("2306"))
				return incheon[6];
			else if(code.equals("2307"))
				return incheon[7];
			else if(code.equals("2310"))
				return incheon[8];
			else if(code.equals("2301"))
				return incheon[9];
			
			return "NULL";
		}
		
		String funcAcctype(String code)
		{
			String acctype[] = {"������","������","�����ܵ�"};
			
			if(code.equals("01"))
				return acctype[0];
			else if(code.equals("02"))
				return acctype[1];
			else if(code.equals("03"))
				return acctype[2];
			
			return "NULL";
		}
			
			




}
