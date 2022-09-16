package egovframework.com.domain.main.domain;

import lombok.Data;

@Data
public class Weather {

	private Long latitude;// x좌표
	private Long longitude;// y좌표
	private String weatherImgUrl;//이미지
	private String address;//주소
	private String temperature;//온도
	
}
