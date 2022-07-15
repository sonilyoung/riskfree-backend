package egovframework.com.global.http;

import lombok.Data;

@Data
public class SearchData {

	private String keyword;
	private String field;
	private String startYear;
	private String endYear;

	public SearchData() {

	}
}
