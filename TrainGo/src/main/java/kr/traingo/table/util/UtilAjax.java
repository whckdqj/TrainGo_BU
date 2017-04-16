package kr.traingo.table.util;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import kr.traingo.table.domain.CtyCodeListCommand;
import kr.traingo.table.domain.ResultTimeTableCommand;
import kr.traingo.table.domain.TrainSttnListCommand;
import kr.traingo.table.domain.VhcleKndListCommand;



public class UtilAjax {
    public static final String STTNCODE_TABLE="CTYACCTOTRAINSTTNLIST";
    public static final String CTYCODE_TABLE="CTYCODELIST";
    public static final String TABLE_MGR="TABLEMANAGER";
    public static final String VHCLE_KND_TABLE="VHCLEKNDLIST";
    private static final String VALID_KEY="%2B2EojD9TwJf4SL%2BEReQYRLoD4IXbQw1jl9c0Bk8dsJfzCBNT1pDfGQ3BzEoIxJkGafQrTLUBRAveiQ9CbiBq6w%3D%3D";
    
    // ----- Access For City Codes -----
	public List<CtyCodeListCommand> getCityCodeFromServer(){
		List<CtyCodeListCommand> ctyCodeList = null;
		CtyCodeListCommand ctyList = null;
		
		// URL For CityCode
		String url = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyCodeList?ServiceKey="+UtilAjax.VALID_KEY;
		
		ctyCodeList = accessServer(url, ctyCodeList, ctyList);
		
		return ctyCodeList;
	}
	
	// For Overloading - CityCode
	private List<CtyCodeListCommand> accessServer(String urls, List<CtyCodeListCommand> ctyCodeList, CtyCodeListCommand cList){
		ctyCodeList = new ArrayList<CtyCodeListCommand>();
		// int r=0;
		
		try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputStream is_ctyCode = new URL(urls).openStream();

            Document document = builder.parse(is_ctyCode);
            Element device = document.getDocumentElement();
            NodeList items = device.getElementsByTagName("item");
            
            for (int i = 0; i < items.getLength(); i++) {
            	cList = new CtyCodeListCommand();
            	
                Node itms = items.item(i);
                NodeList elementsNode = itms.getChildNodes();
                for(int j=0; j<elementsNode.getLength(); j++){
                	Node values = elementsNode.item(j);
                	// System.out.println("("+i+", "+j+")³ëµå°ª : "+values.getFirstChild().getNodeValue());
                	if(j==0){
                		// citycode
                		cList.setCityCode(values.getFirstChild().getNodeValue());
                	}
                	else if(j==1){
                		// cityname
                		cList.setCityName(values.getFirstChild().getNodeValue());
                	}
                	else{
                		// Not-Doing
                	}
                }
                ctyCodeList.add(cList);
            }
            
            // Debug
            /*for(CityCodeList ctList: ctyCodeList){
            	System.out.println(r+" : "+ctList.getCityCode()+":"+ctList.getCityName());
            	r++;
            }*/
            return ctyCodeList;
        }
        catch (Exception e) {
        	System.out.println(this.toString());
            e.printStackTrace();
            return null;
        }
	}
// ----- Access For City Codes -----

// ----- Access For Vhcle Code -----
	public List<VhcleKndListCommand> getVhcleCodeFromServer(){
		List<VhcleKndListCommand> vcleCodeList = null;
		VhcleKndListCommand vcleList = null;

		// URL For VhcleCode
		String url = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getVhcleKndList?ServiceKey="+UtilAjax.VALID_KEY;

		vcleCodeList = accessServer(url, vcleCodeList, vcleList);

		if(vcleCodeList==null)
			return null;

		return vcleCodeList;
	}
	
	// For Overloading - VhcleList
	private List<VhcleKndListCommand> accessServer(String urls, List<VhcleKndListCommand> vhcleList, VhcleKndListCommand vList){
		vhcleList = new ArrayList<VhcleKndListCommand>();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			InputStream is_ctyCode = new URL(urls).openStream();
			// InputStream is_vhcleKnd = new URL(vhcleKndUrl).openStream();

			Document document = builder.parse(is_ctyCode);
			Element device = document.getDocumentElement();
			NodeList items = device.getElementsByTagName("item");

			for (int i = 0; i < items.getLength(); i++) {
				vList = new VhcleKndListCommand();

				Node itms = items.item(i);
				NodeList elementsNode = itms.getChildNodes();
				for(int j=0; j<elementsNode.getLength(); j++){
					Node values = elementsNode.item(j);
					if(j==0){
						// v-id
						vList.setVehicleKndId(values.getFirstChild().getNodeValue());
					}
					else if(j==1){
						// v-name
						vList.setVehicleKndNm(values.getFirstChild().getNodeValue());
					}
					else{
						// Do-Nothing
					}
				}
				vhcleList.add(vList);
			}

			// Debug
			for(VhcleKndListCommand vhList: vhcleList){
	            	System.out.println(vhList.getVehicleKndId()+":"+vhList.getVehicleKndNm());
	        }
			return vhcleList;
		}
		catch (Exception e) {
			System.out.println(this.toString());
			e.printStackTrace();
			return null;
		}
	}
// ----- Access For Vhcle Code -----

// ----- Access For Station Code -----
	public List<TrainSttnListCommand> getSttnCodeFromServer(List<String> ctyCodes){
		List<TrainSttnListCommand> sttnCodeList = new ArrayList<TrainSttnListCommand>();
		TrainSttnListCommand sttnList = null;
		String totalCount=null;
		
		// Get CityCode
		
		// All Station Is Too Many... So Looping
		for(String url_ctyCode:ctyCodes){
			// URL For StationCode - Init
			String url = "http://openapi.tago.go.kr/openapi/service/TrainInfoService/getCtyAcctoTrainSttnList?ServiceKey="+UtilAjax.VALID_KEY;
			
			// Gererate URL for TotalCount
			url+= "&cityCode="+url_ctyCode;
			// Step1 - Get Total Count
			totalCount = accessServer(url);
			
			// URL Remake for StationCodes
			url += "&numOfRows="+totalCount;
			// Step2 - Get All SationCode
			sttnCodeList.addAll(accessServer(url, sttnCodeList, sttnList, url_ctyCode));
		}
		
		return sttnCodeList;
	}

	// For Overloading - Station TotalCount
	private String accessServer(String urls){
		String tots=null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			InputStream is_ctyCode = new URL(urls).openStream();
			// InputStream is_vhcleKnd = new URL(vhcleKndUrl).openStream();

			Document document = builder.parse(is_ctyCode);
			Element device = document.getDocumentElement();
			NodeList items = device.getElementsByTagName("totalCount");
			
			Node itms = items.item(0);
			tots = itms.getFirstChild().getNodeValue();

			// Debug
		    System.out.println("Total Count : "+tots);
			return tots;
		}
		catch (Exception e) {
			System.out.println(this.toString());
			e.printStackTrace();
			return null;
		}
	}
	
	// For Overloading - StationList
		private List<TrainSttnListCommand> accessServer(String urls, List<TrainSttnListCommand> sttnList, TrainSttnListCommand sList, String cCode){
			sttnList = new ArrayList<TrainSttnListCommand>();

			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				InputStream is_ctyCode = new URL(urls).openStream();
				// InputStream is_vhcleKnd = new URL(vhcleKndUrl).openStream();

				Document document = builder.parse(is_ctyCode);
				Element device = document.getDocumentElement();
				NodeList items = device.getElementsByTagName("item");

				for (int i = 0; i < items.getLength(); i++) {
					sList = new TrainSttnListCommand();

					Node itms = items.item(i);
					NodeList elementsNode = itms.getChildNodes();
					for(int j=0; j<elementsNode.getLength(); j++){
						Node values = elementsNode.item(j);
						if(j==0){
							// node-id
							sList.setNodeId(values.getFirstChild().getNodeValue());
						}
						else if(j==1){
							// node-name
							sList.setNodeName(values.getFirstChild().getNodeValue());
						}
						else{
							// Do-Nothing
						}
					}
					sList.setCityCode(cCode);
					sttnList.add(sList);
				}

				// Debug
				for(TrainSttnListCommand stList: sttnList){
			        System.out.println(stList.getNodeId()+":"+stList.getNodeName()+":"+stList.getCityCode());
			    }
				return sttnList;
			}
			catch (Exception e) {
				System.out.println(this.toString());
				e.printStackTrace();
				return null;
			}
		}
// ----- Access For Station Code -----
// ----- Access For Time Table -----
		public List<ResultTimeTableCommand> getTimeTableFromServer(String depId, String arrId, String depTime){
			List<ResultTimeTableCommand> timeTableList=new ArrayList<ResultTimeTableCommand>();
			
			timeTableList = getTimeTableFromServer(depId, arrId, depTime, null);
			return timeTableList;
		}
		public List<ResultTimeTableCommand> getTimeTableFromServer(String depId, String arrId, String depTime, String[] trainGradeCode){
			List<ResultTimeTableCommand> timeTableList=new ArrayList<ResultTimeTableCommand>();
			ResultTimeTableCommand timeTable=null;
			String totalCount=null;

			// URL For StationCode - Init
			StringBuffer url = new StringBuffer("http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?ServiceKey=");
			url.append(UtilAjax.VALID_KEY);
			url.append("&depPlaceId="+depId);
			url.append("&arrPlaceId="+arrId);
			url.append("&depPlandTime=");
			
			// YYYY-MM-DD -> YYYYMMDD
			String[] dTime = depTime.split("-");
			for(String str:dTime){
				url.append(str);
			}
			
			// Debug
			System.out.println(url.toString());
			// Step1 - Get Total Count
			totalCount = accessServer(url.toString());
			
			// URL Remake for TimeTable
			url.append("&numOfRows="+totalCount);
			
			// URL Remake for Specific Train
			if(trainGradeCode==null){
				// Step2 - Get All SationCode
				accessServer(url.toString(), timeTableList, timeTable);
			}
			else if(trainGradeCode.length > 0){
				for(String str:trainGradeCode){
					try{
					    Integer.parseInt(str);
					    url.append("&trainGradeCode="+str);
					}
					catch(Exception e){
					    System.out.println("Not a Correct Vihicle Code...");
					}
				}
				// Step2 - Get All SationCode
				accessServer(url.toString(), timeTableList, timeTable);
			}
			
			// For Debug
			/*for(ResultTimeTableCommand tcurr:timeTableList){
				System.out.println(tcurr.getDepPlandTime());
			}*/
			
			return timeTableList;
			
		}
		
		// For Overloading - StationList
		private List<ResultTimeTableCommand> accessServer(String urls, List<ResultTimeTableCommand> tTableList, ResultTimeTableCommand tList){
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();

				InputStream is_ctyCode = new URL(urls).openStream();
				// InputStream is_vhcleKnd = new URL(vhcleKndUrl).openStream();

				Document document = builder.parse(is_ctyCode);
				Element device = document.getDocumentElement();
				NodeList items = device.getElementsByTagName("item");

				for (int i = 0; i < items.getLength(); i++) {
					tList = new ResultTimeTableCommand();

					Node itms = items.item(i);
					NodeList elementsNode = itms.getChildNodes();
					for(int j=0; j<elementsNode.getLength(); j++){
						Node values = elementsNode.item(j);
						if(j==0){
							// adultcharge
							tList.setAdultCharge(values.getFirstChild().getNodeValue());
						}
						else if(j==1){
							// arrplacename
							tList.setArrPlaceName(values.getFirstChild().getNodeValue());
						}
						else if(j==2){
							// arrplandtime
							tList.setArrPlandTime(values.getFirstChild().getNodeValue());
						}
						else if(j==3){
							// depplacename
							tList.setDepPlaceName(values.getFirstChild().getNodeValue());
						}
						else if(j==4){
							// depplandtime
							tList.setDepPlandTime(values.getFirstChild().getNodeValue());
						}
						else if(j==5){
							// traingradename
							tList.setTrainGradeName(values.getFirstChild().getNodeValue());
						}
						else{
							// Do-Nothing
						}
					}
					tTableList.add(tList);
				}

				// Debug
				/*for(ResultTimeTableCommand tTaList: tTableList){
			        System.out.println(this.toString());
					System.out.println(tTaList.getDepPlandTime());
			    }*/
				return tTableList;
			}
			catch (Exception e) {
				System.out.println(this.toString());
				e.printStackTrace();
				return null;
			}
		}
// ----- Access For Time Table -----
}
