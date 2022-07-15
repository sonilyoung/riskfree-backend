package egovframework.com.domain.education.parameter;

import lombok.Data;

@Data
public class EduClassSearchParameter {
    private int currentPageNum;
    private int countPerPage;
    private EduClassSearchData searchData;
}
