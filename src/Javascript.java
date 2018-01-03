import java.util.ArrayList;

public class Javascript {
	private String myLocation;									//GuideToSeoul.class �� ���� ���� ����� ��ġ ����
	private String offset;										//(javascript)�⺻ �ɼ�
	private String markers;										//(javascript)��Ŀ (������) �ɼ�
	private String others;										//(javascript)���� �ɼ�
	private String src;											//(javascript)�ʼ� �ɼ�
	
	private ArrayList <AccidentCase> accCase = new ArrayList<AccidentCase>();
	
	public Javascript() {
		myLocation = new String("");
		offset = new String("");
		markers = new String("");
		others = new String("");
		src = new String("");
	}
	public String getScript() {									//javascript �ڵ� ��ȯ
		String script = new String(offset+markers+others+src);
		return script;
	}
	public void resetScript()
	{
		myLocation ="";
		offset = "";
		markers = "";
		others = "";
		src = "";
		
	}
	public void setDetailMain(String dstLat, String dstLng)	//ClonedDetailFrame ���� ����� �Լ� javascript�� �ʿ��� �ڵ带 �ѹ��� ����
	{
		offset = new String("document.body.style.padding = 0;"+								//���� �ʱ�ȭ
				"document.body.style.margin = 0;"+
				"var infowindow;\r\n" + 
				"var map;\r\n" + 		
				"var maps = document.getElementById('map');\r\n" + 
  	            "maps.style.width = '100%';\r\n" + 
  	            "maps.style.height = '100%';" +     
  	            "var seoul = {lat:"+ dstLat +", lng:"+ dstLng + "};\r\n" + 
  	            "function initMap() {\r\n" + 
  	            "	map = new google.maps.Map(document.getElementById('map'), {\r\n" + 
  	            "       zoom: 13," + 
  	            "       center: seoul"+
  	            "   });\r\n"
  	            );
		markers = new String(
				"   var h = {lat: "+dstLat+", lng: "+ dstLng+"};\r\n" + 							//������ ���� �浵  ����
		    	"   var marker = new google.maps.Marker({\r\n" + 
		    	"	animation: google.maps.Animation.DROP," +
				"   	position: h,\r\n" + 														//���� �浵 ��ġ ����
				"       map: map\r\n" + 															//�ʿ� �ٿ��ֱ�
				"   });\n"
				);
		others = new String(
				"}"
				);
		src = new String("function includeJs(jsFilePath)\r\n" + 									//googleAPI�� javascript �ڵ带 �������� ����Ҽ� �ִ� ���� ����
                "{ var js = document.createElement(\"script\")\r\n" + 
                "\r\n" + 
                "js.type = \"text/javascript\";\r\n" + 
                "js.src = jsFilePath;\r\n" + 
                "\r\n" + 
                "document.body.appendChild(js);\r\n" + 
                "}\r\n" + 
                "includeJs(\"https://maps.googleapis.com/maps/api/js?key=AIzaSyDu5LHktof5c2e81HbeiMy3tm4vIfkEs9c&libraries=places&callback=initMap\");\r\n");
	}
	
	public void setAnalysisMain(ArrayList<AccidentCase> temp)
	{
		
		offset = new String(		"document.body.style.padding = 0;"+								//���� �ʱ�ȭ
				"document.body.style.margin = 0;"+
				"var infowindow;\r\n" + 
				"var map;\r\n" + 		
				"var maps = document.getElementById('map');\r\n" + 
  	            "maps.style.width = '100%';\r\n" + 
  	            "maps.style.height = '100%';" +     
  	            "var seoul = {lat: 37.566294 , lng: 126.977423};\r\n" + 
  	            "function initMap() {\r\n" +
  	            "	map = new google.maps.Map(document.getElementById('map'), {\r\n" + 
  	            "       zoom: 8," + 
  	            "       center: seoul"+
  	            "   });\r\n" +
	  	        "var icon1 = {\r\n" +                                           //���� ������ �ɼ� ����
	            "    url:'https://cdn.pixabay.com/photo/2013/07/13/11/42/map-158493_960_720.png',\r\n" + 
	            "    scaledSize: new google.maps.Size(13, 16) \r\n" + 
	            "};"
  	            );
		
		for(int i =0; i<temp.size(); i++)
		{
			String dstLat = Double.toString(temp.get(i).getLatitude());
			String dstLng = Double.toString(temp.get(i).getLongitude());
			System.out.println(dstLat + dstLng);
			markers +=
					"   var h = {lat: "+dstLat+", lng: "+ dstLng+"};\r\n" + 							//������ ���� �浵  ����
			    	"   var marker = new google.maps.Marker({\r\n" + 
			    	"	animation: google.maps.Animation.DROP," +
					"   	position: h,\r\n" + 
			    	"		icon: icon1,\r\n"+
					"       map: map\r\n" + 															//�ʿ� �ٿ��ֱ�
					"   });"+ 
					"\n";
		}
		
		others = new String("}");
		src = new String("function includeJs(jsFilePath)\r\n" + 																//googleAPI�� javascript �ڵ带 �������� ����Ҽ� �ִ� ���� ����
                "{ var js = document.createElement(\"script\")\r\n" + 
                "\r\n" + 
                "js.type = \"text/javascript\";\r\n" + 
                "js.src = jsFilePath;\r\n" + 
                "\r\n" + 
                "document.body.appendChild(js);\r\n" + 
                "}\r\n" + 
                "includeJs(\"https://maps.googleapis.com/maps/api/js?"
                + "key=AIzaSyDu5LHktof5c2e81HbeiMy3tm4vIfkEs"
                + "9c&libraries=places&callback=initMap\");\r\n");

	}

}
