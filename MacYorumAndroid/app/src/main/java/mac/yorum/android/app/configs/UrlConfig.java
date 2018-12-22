package mac.yorum.android.app.configs;

public class UrlConfig {

	@SuppressWarnings("unused")
	//http://api.lojisoft.com/ 
	public static final String TEST = "http://192.168.2.24:3232/";
	public static final String LOCALHOST = "http://192.168.2.7:19203/";
	private static final String PROD = "http://api.lojisoft.com";
	public static final String API = PROD +"/api";

	public static final String API_RETROFIT = PROD;

	public static final String GOOGLEMAPS_ADDRESS= "http://www.maps.google.com/";


	public static final String ACCOUNT_LOGIN = API + "/DomMobileLogon/Post?UserName=%s&Password=%s";


	public static final String GETROUTEINFO = "https://maps.googleapis.com/maps/api/directions/json?origin=%s&destination=%s&alternatives=%s&mode=%s&sensor=%s&key=%s";


	public static final String DOMPOSITIONLIST = API +"/DomMobilePositionList/Post?UserName=%s&Password=%s";
	public static final String DOMMOBILELOADLIST = API +"/DomMobileLoadList/Post?PositionId=%s&UserName=%s&Password=%s";
	public static final String DOMMOBILELOADLISTDETAIL = API +"/DomMobileLoadDetail/Post?LoadId=%s&UserName=%s&Password=%s";
	//public static final String DOMMOBILECOMPLATE = API +"/DomMobileComplete/Post?UserName=%s&Password=%s";
	public static final String DOMMOBILECOMPLATE = API +"/DomMobileComplete/Post?";

	public static final String AIRMOBILECOMPLATE = API +"/AirMobileComplete/Post?";

	public static final String DOMMOBILESENDPOSITIONDRIVERACTION = API +"/DomMobileSendPositionDriverAction/Post?PositionId=%s&UserName=%s&Password=%s";
	//String reqURL = "http://maps.googleapis.com/maps/api/geocode/json?latlng="+ latitude+","+longitude +"&sensor=true";
	public static final String MAPAPI="http://maps.googleapis.com/maps/api/geocode/json?latlng=%s&sensor=%s";
	public static final String DOMMOBILEPOSITIONLISTPOSTENDEDPOSITION = API+"/DomMobilePositionList/PostEndedPosition?UserName=%s&Password=%s&PlaningDate=%s";
	public static final String SETCOMESTATUS = API + "/DomMobileLoadDetail/SetComeStatus?LoadId=%s&UserName=%s&Password=%s";


	/*
	* Volley kutuphanesi icin olusturulan url degiskenleri
	*
	* */
	public static final String VOLLEY_ACCOUNT_LOGIN = API +"/DomMobileLogon/Post";
	public static final String VOLLEY_DOMPOSITIONLIST = API +"/DomMobilePositionList/Post";
	public static final String VOLLEY_DOMMOBILELOADLIST = API +"/DomMobileLoadList/Post";
	public static final String VOLLEY_DOMMOBILESENDPOSITIONDRIVERACTION = API +"/DomMobileSendPositionDriverAction/Post";
	public static final String VOLLEY_MOBILEGPSLOG = API +"/MobileGPSLog/Post";


	/*
	* Retrofit kutuphanesi icin olusturulan url de?i?kenleri
	* */
	public static final String ACCOUNT_LOGIN_RETROFIT = API;
}
