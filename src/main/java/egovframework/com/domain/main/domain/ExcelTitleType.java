package egovframework.com.domain.main.domain;

public enum ExcelTitleType {

	TITLE1("1","안전보건 전담조직의 구성", "RATE1"),//
	TITLE2("2","도급, 용역, 위탁 시 평가기준·절차 마련 및 점검", "RATE2"),//
	TITLE3("3","안전보건관리책임자 등의 충실한 업무 수행을 위한 조치", "RATE3"),//
	TITLE4("4","안전보건에 관한 목표 및 경영방침 설정", "RATE4"),//
	TITLE5("5","유해·위험요인 확인·개선 절차 마련, 점검 및 필요조치", "RATE5"),//
	TITLE6("6","안전보건 전문인력 배치", "RATE6"),//
	TITLE7("7","안전보건 관련 예산 편성 및 집행", "RATE7"),//
	TITLE8("8","종사자 의견청취", "RATE8"),//
	TITLE9("9","안전ㆍ보건 관계법령에 따른 의무교육사항 이행 및 개선", "RATE9");//
	
    private String code;
    private String name;
    private String rate;
    
    ExcelTitleType(String code, String name, String rate) {
        this.code = code;
        this.name = name;
        this.rate = rate;
    }
	
    public String getCode() {
        return this.code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getRate() {
        return this.rate;
    }    
}