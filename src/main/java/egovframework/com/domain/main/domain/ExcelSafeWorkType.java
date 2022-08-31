package egovframework.com.domain.main.domain;

public enum ExcelSafeWorkType {

	TITLE1("1","화기"),//
	TITLE2("2","밀폐"),//
	TITLE3("3","정전"),//
	TITLE4("4","굴착"),//
	TITLE5("5","방사선"),//
	TITLE6("6","고소"),//
	TITLE7("7","중장비");//
	
    private String code;
    private String name;
    
    ExcelSafeWorkType(String code, String name) {
        this.code = code;
        this.name = name;
    }
	
    public String getCode() {
        return this.code;
    }
    
    public String getName() {
        return this.name;
    }
}