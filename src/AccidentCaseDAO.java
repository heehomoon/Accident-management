/**
 * AccidentCaseDAO.class
 * @author ������, ������, ����ȣ
 * 
 * ���� �ۼ���: 2017�� 12�� 23��
 * ���� ������: 2018�� 1�� 5��
 */
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
public class AccidentCaseDAO {
	
	String DBid;     // MySQL username
	String DBpw;     // MySQL password
	
	String jdbcDriver = "com.mysql.jdbc.Driver";      // MySQL jdbc ����̹�
	String jdbcUrl = "jdbc:mysql://localhost/javadb"; // MySQL javadb schema    

	Connection conn;
	String sql;   		    	// SQL ��
	PreparedStatement pstmt;
	ResultSet rs;  				// SQL�� ���� ����� ��ȯ�Ǵ� Ʃ���� ����

	
	ArrayList<AccidentCase> datas;  // AccidentCase Ŭ���� ��ȯ�ϴ� ArrayList

	// �ν��Ͻ��� �����Ͽ� AppManager Ŭ������ �����ϴ� �޼ҵ�
	public AccidentCaseDAO()
	{
		AppManager.CreateInstance().setAccidentCaseDAO(this);
	}
	
	// DB(MySQL)�� username�� password�� ��ġ�ϴ��� Ȯ���ϴ� �޼ҵ�
	boolean connectTest(String id, String pw)
	{
		try {
			this.DBid= id;
			this.DBpw = pw;
			
			Class.forName(jdbcDriver);
			DriverManager.getConnection(jdbcUrl,DBid,DBpw);
		
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

	// DB�� �����ϴ� �޼ҵ�
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

	//DB�� ������ �����ϴ� �޼ҵ�
	void closeDB(){
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// ���� �߰��� ��� ��ȣ�� ��ȯ �ϴ� �޼ҵ�(���� DB�� �����ϴ� ���� ū ����ȣ ��ȯ)
	int getNewCaseCode() {
		
		connectDB();   //DB ����
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
		
		closeDB();    //DB ���� �ݱ�
		return newCsCode;
	}
	
	// ��� ��ȣ�� ���� �޾� �ش��ϴ� AccidentCase�� ��ȯ�ϴ� �޼ҵ� 
	AccidentCase getCase(int cscode) 
	{
		connectDB();    //DB ����
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
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
			}
			
			rs.close(); // resultSet �ݱ�
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();   //DB ���� �ݱ�
		return accCase;
	}
	
	// ��/��, ��/��, ��, ���� ���� �޾� ���ǿ� �´� ArrayList�� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> searchCase(String province, String town, String year, String month)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB(); //DB ����
		
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
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();   //resultSet �ݱ�

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();    // DB ���� �ݱ�
		return datas;
	}	

	// ��/��, ��/��, ���� ���� �޾� ���ǿ� �´� ArrayList�� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> searchCaseLocaYear(String province, String town, String year)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();   //DB ����
		sql = "select * from accidentcase where (province = ? and town = ? and year = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);
			pstmt.setString(2, town);
			pstmt.setString(3, year);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				accCase = new AccidentCase();
				accCase.setCscode(rs.getInt("cscode"));
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();   // resultSet ����

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();        //DB ���� �ݱ�
		return datas;
	}
	
	// ��/��, ��/��, ��, ���� ���� �޾� ���ǿ� �´� ArrayList�� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> searchCaseLocaMonth(String province, String town, String month)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB(); //DB ���� 
		sql = "select * from accidentcase where (province = ? and town = ? and month = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, province);
			pstmt.setString(2, town);
			pstmt.setString(3, month);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				accCase = new AccidentCase();
				accCase.setCscode(rs.getInt("cscode"));
				accCase.setProvince(rs.getString("province"));
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();           // resultSet ����

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();                // DB ���� ����  
		return datas;
	}
	
	// ��/��, ��/���� ���� �޾� ���ǿ� �´� ArrayList�� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> searchCaseLoca(String province, String town)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>(); 
		
		connectDB();   // DB ����
		sql = "select * from accidentcase where (province = ? and town = ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);   
			pstmt.setString(1, province);         // ù ��° ����ǥ ��ġ�� province ���� 
			pstmt.setString(2, town);             // �� ��° ����ǥ ��ġ�� town ����
			rs = pstmt.executeQuery();            
	
			while(rs.next()) {
				// AccidentCase��ü ����
				accCase = new AccidentCase();   
				// ���� ����� ��ȯ�� ���� AccidentCase ��ü�� ������ ���� 
				accCase.setCscode(rs.getInt("cscode"));              
				accCase.setProvince(rs.getString("province"));       
				accCase.setTown(rs.getString("town"));
				accCase.setYear(rs.getString("year"));
				accCase.setMonth(rs.getString("month"));
				accCase.setDay(rs.getString("day"));
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				// ArrayList�� AccidentCase �߰�
				datas.add(accCase);
			}
			
			rs.close(); //resultSet �ݱ�

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();   // DB ���� ���� 
		return datas;
	}	
	
	//��, ���� ���� �޾� ���ǿ� �´� ArrayList�� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> searchCaseTime(String year, String month)
	   {
	      AccidentCase accCase;
	      datas = new ArrayList<AccidentCase>();
	      connectDB();    // DB ���� 
	      
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
	               accCase.setDead(rs.getInt("dead"));
	               accCase.setInjured(rs.getInt("injured"));
	               accCase.setCasulity();
	               accCase.setActype(rs.getString("actype"));
	               accCase.setLatitude(rs.getDouble("latitude"));
	               accCase.setLongitude(rs.getDouble("longitude"));
	               
	               datas.add(accCase);
	            }
	            
	            rs.close();   // resultSet ���� 
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	         closeDB(); // DB ���� ����
	         return datas;  
	      }
		
	//���� ���� �޾� ���ǿ� �´� AccidentCase�� ArrayList�� ��� ��ȯ�ϴ� �޼ҵ� 	
	ArrayList<AccidentCase> searchCaseTime(String year)
	{
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		connectDB();    // DB ���� 
		
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
					accCase.setDead(rs.getInt("dead"));
					accCase.setInjured(rs.getInt("injured"));
					accCase.setCasulity();
					accCase.setActype(rs.getString("actype"));
					accCase.setLatitude(rs.getDouble("latitude"));
					accCase.setLongitude(rs.getDouble("longitude"));
					
					datas.add(accCase);
	         }
	         
	     	rs.close();  //resultSet ����
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      closeDB(); // DB ���� ����
	      return datas;
	   }

	//���� ���� �޾� ���ǿ� �´� AccidentCase�� ArrayList�� ��� ��ȯ�ϴ� �޼ҵ� 	
	ArrayList<AccidentCase> searchCaseMonthTime(String month)
	   {
	      AccidentCase accCase;
	      datas = new ArrayList<AccidentCase>();
	      connectDB();    // DB ���� 
	      
	         sql = "select * from accidentcase where month = ?";
	         try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1,month);
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               accCase = new AccidentCase();
	               accCase.setCscode(rs.getInt("cscode"));
	               accCase.setProvince(rs.getString("province"));
	               accCase.setTown(rs.getString("town"));
	               accCase.setYear(rs.getString("year"));
	               accCase.setMonth(rs.getString("month"));
	               accCase.setDay(rs.getString("day"));
	               accCase.setDead(rs.getInt("dead"));
	               accCase.setInjured(rs.getInt("injured"));
	               accCase.setCasulity();
	               accCase.setActype(rs.getString("actype"));
	               accCase.setLatitude(rs.getDouble("latitude"));
	               accCase.setLongitude(rs.getDouble("longitude"));
	               
	               datas.add(accCase);
	            }
	            rs.close();  // resultSet ����
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         
	         closeDB();   // DB ���� ����
	         return datas;  
	      }

	// DB�� �����ϴ� ��� ��� ��ʸ� ArrayList�� ��� ��ȯ�ϴ� �޼ҵ� 
	ArrayList<AccidentCase> getAll() {
		
		AccidentCase accCase;
		datas = new ArrayList<AccidentCase>();
		
		connectDB();  // DB ����
		
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
				accCase.setDead(rs.getInt("dead"));
				accCase.setInjured(rs.getInt("injured"));
				accCase.setCasulity();
				accCase.setActype(rs.getString("actype"));
				accCase.setLatitude(rs.getDouble("latitude"));
				accCase.setLongitude(rs.getDouble("longitude"));
				
				datas.add(accCase);
			}
			
			rs.close();  // resultSet ����

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		closeDB();   // DB ���� ����
		return datas;
	}
	 
	//AccidentCase�� ���޹޾� DB�� �����ϴ� �޼ҵ�  	
	 boolean insertCase(AccidentCase accCase) 
		{
			connectDB();     // DB ����
			int chk =0;
			boolean flag=false; 
			sql = "INSERT "+
				  "INTO accidentcase(province,town,year,month,day,dead,injured,actype,latitude,longitude) "+
				  "VALUES(?,?,?,?,?,?,?,?,?,?)";
	
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accCase.getProvince());
				pstmt.setString(2, accCase.getTown());
				pstmt.setString(3, accCase.getYear());
				pstmt.setString(4, accCase.getMonth());
				pstmt.setString(5, accCase.getDay());
				pstmt.setInt(6, accCase.getDead());
				pstmt.setInt(7, accCase.getInjured());
				pstmt.setString(8, accCase.getActype());
				pstmt.setDouble(9, accCase.getLatitude());
				pstmt.setDouble(10, accCase.getLongitude());
				
				chk = pstmt.executeUpdate();
				
				if(chk >0)   // ��ȭ��Ų ���� ���� ������ �� 
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			closeDB();    // DB ���� ����
			return flag;
		}
		
		//AccidentCase�� ���޹޾� DB�� �����ϴ� ������ �����ϴ� �޼ҵ�	
		boolean updateCase(AccidentCase accCase) 
		{
			connectDB();   // DB ����
			int chk;
			boolean flag = false; 
			
			sql = "UPDATE accidentcase " + 
				  "SET province = ? , town = ?, year = ? , month = ?, day = ?, dead = ?, injured =? , actype = ?, latitude = ?, longitude = ? " + 
				  "WHERE cscode = ? ";
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, accCase.getProvince());
				pstmt.setString(2, accCase.getTown());
				pstmt.setString(3, accCase.getYear());
				pstmt.setString(4, accCase.getMonth());
				pstmt.setString(5, accCase.getDay());
				pstmt.setInt(6, accCase.getDead());
				pstmt.setInt(7, accCase.getInjured());
				pstmt.setString(8, accCase.getActype());
				pstmt.setDouble(9, accCase.getLatitude());
				pstmt.setDouble(10, accCase.getLongitude());
				pstmt.setInt(11, accCase.getCscode());
				
				chk = pstmt.executeUpdate();
				
				if(chk>0)   // ��ȭ��Ų ���� ���� ������ �� 
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			closeDB();     // DB ����
			return flag;
		}

		// cscode�� ���޹޾� �ش��ϴ� ������ �����ϴ� �޼ҵ�
		boolean deleteCase(int cscode)
		{
			connectDB();    // DB ����
			sql = "delete from accidentcase where cscode = ?";
			int chk;
			boolean flag = false;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cscode);   			// ù ��° ����ǥ�� cscode ����
				chk = pstmt.executeUpdate();        // pstmt ����
				
				if(chk>0) // ��ȭ ��Ų ���� ���� ������ ��
					flag = true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeDB();      // DB ����
			return flag;
		}
		
		// DB�� �����ϴ� ��� �����͸� �����ϴ� �޼ҵ�
		void deleteAllCase()
		{
			connectDB();        // DB ����
			sql = "TRUNCATE accidentcase;";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();        // pstmt ����
				
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			closeDB();         // DB ����
		}
		
		//�⵵�� �Է¹޾� �ش��ϴ� ������ �Ľ��ϴ� �޼ҵ�
		boolean ParsingAccidentData(String year)
		{
			// JSON �����Ͱ� ����� url �ּҸ� �����ϴ� String �迭
			String url[] = new String[3];
			
			//����
			url[0] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear="+year+"&siDo=1100&guGun=";
			//���
			url[1] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear="+year+"&siDo=1300&guGun=";
			//��õ
			url[2] = "http://apis.data.go.kr/B552061/trafficAccidentDeath/getRestTrafficAccidentDeath?servicekey=2%2BbS76NE8TFgiPqTxDyXneu97RYUJJxFo3c1K5FHwD5tja3W8etlkdp6jXOr9zHl6xAZJB9LUaLrcEudogq5iQ%3D%3D&searchYear="+year+"&siDo=2300&guGun=";

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
				
			boolean flag = false;
			try
			{
				for(int i =0 ; i<3; i++)
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
			                
			 				accCase.setProvince(findProvince((String) jObj.get("cd_003_lv1")));
			 				accCase.setTown(findTown((String)jObj.get("cd_003"))); 
			 				
			 				String date = (String)jObj.get("dt_006");
			 				accCase.setYear(date.substring(0,4));
			 				accCase.setMonth(date.substring(4,6));
			 				accCase.setDay(date.substring(6,8));
			 				
			 				accCase.setDead(((Long)jObj.get("no_010")).intValue());
			 				accCase.setInjured(((Long)jObj.get("injpsn_co")).intValue());
			 				accCase.setCasulity();
			 				
			 				accCase.setActype(findAcctype((String) jObj.get("cd_014_lv1")));
			 				accCase.setLatitude(Double.parseDouble((String)jObj.get("grd_la")));
			 				accCase.setLongitude(Double.parseDouble((String)jObj.get("grd_lo")));
			 				
			 				insertCase(accCase);
			 				
			 				System.out.println("�õ�:"+accCase.getProvince());
			 				System.out.println("����:"+accCase.getTown());
			 				System.out.println("year:" +accCase.getYear());
			 				System.out.println("month:" +accCase.getMonth());
			 				System.out.println("day:" +accCase.getDay());
			 				System.out.println("����ڼ�:" +accCase.getCasulity());
			 				System.out.println("����ڼ�:" +accCase.getDead());
			 				System.out.println("�λ��ڼ�:" +accCase.getInjured());
			 				System.out.println("�������:" + accCase.getActype());
			                System.out.println("�浵:" + accCase.getLongitude());
			                System.out.println("����:" + accCase.getLatitude());
			   
			 			}
			 		
			 		flag = true;
				}
				} catch (Exception e) {
	
	 			e.printStackTrace();
	
	 		}
			
			return flag;
						
		}
		
		// ��/�� �ڵ带 ���޹޾� �ش��ϴ� ��/�� ���ڿ��� ��ȯ�ϴ� �޼ҵ�
		private String findProvince(String code)
		{
			String str[] = {"����Ư����","��⵵","��õ������"};
			
			if(code.equals("1100"))
				return str[0];
			else if(code.equals("1300"))
				return str[1];
			else if(code.equals("2300"))
				return str[2];
			else
				return "NULL";
		}
		
		// ��/�� �ڵ带 ���޹޾� �ش��ϴ� ��/�� ���ڿ��� ��ȯ�ϴ� �޼ҵ� 
		private String findTown(String code)
		{
			String seoul[] = {"���α�","�߱�","���","������","���빮��","���ϱ�","������","����","���빮��","������","������","���α�","��������","���۱�",
					"���Ǳ�","������","������","���ı�","���ʱ�","��õ��","�߶���","�����","������","���ϱ�","��õ��"};
			
			String kyenggi[] = {"������","������", "�����ν�","�Ⱦ��","��õ��","�Ȼ��","���ý�",	"�����",	"������", "���ֽ�",	"�����ֱ�","���ֽ�","���ñ�","ȭ����","�����","���ֽ�","����","���ֽ�",
					"��õ��","��õ��","����","����","��õ��","���ν�","�ȼ���","������","��ȭ��","������","����õ��","��ź��","��õ��","������","�����ֽ�","�����","�ǿս�","�ϳ���"};

			String incheon[] = {"�߱�","����","����","����","������","����","������","��籸","��ȭ��","������"	};
		
			int codeInt = Integer.parseInt(code);
			
			if(codeInt >1100 && codeInt <1200 )
			{
				return seoul[codeInt-1101];
			}
			else if(codeInt >1300 && codeInt <1400)
			{
				return kyenggi[codeInt-1302];
			}
			else if(codeInt >2300 && codeInt <2400)
			{
				return incheon[codeInt-2301];
			}
			else
				return "NULL";
			
		}
		
		// ���� ��� Ÿ�� �ڵ带 ���޹޾� �ش��ϴ� ���Ÿ�� ���ڿ� ��ȯ�ϴ� �޼ҵ�
		private String findAcctype(String code)
		{
			String acctype[] = {"������","������","�����ܵ�"};
			
			if(code.equals("01"))
				return acctype[0];
			else if(code.equals("02"))
				return acctype[1];
			else if(code.equals("03"))
				return acctype[2];
			else 
				return "NULL";
		}

}
