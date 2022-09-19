package egovframework.com.domain.main.domain;

public enum WeatherInfo {

	FINE("1","/home/jun/apps/riskfree/webapps/static_file/fine.png"),//맑음
	CLOUDY("2","/home/jun/apps/riskfree/webapps/static_file/cloudy.png"),//구름
	PARTIALLY_CLOUDY("3","/home/jun/apps/riskfree/webapps/static_file/partially_cloudy.png"),//구름낀 맑음
	RAINY("4","/home/jun/apps/riskfree/webapps/static_file/rainy.png"),//비
	SNOWY("5","home/jun/apps/riskfree/webapps/static_file/snowy.png"),//눈
	API_KEY("6","a21136b8d1cb78c63a4954cd5300c47e");//api 키
	
	
    private String code;
    private String value;
    
    WeatherInfo(String code, String value) {
        this.code = code;
        this.value = value;
    }
	
    public String getCode() {
        return this.code;
    }
    
    public String getValue() {
        return this.value;
    }
}