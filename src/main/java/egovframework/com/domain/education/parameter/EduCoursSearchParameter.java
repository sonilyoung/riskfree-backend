package egovframework.com.domain.education.parameter;

import lombok.Data;

@Data
public class EduCoursSearchParameter {
    private int currentPageNum;
    private int countPerPage;
    private EduCoursSearchData searchData;
}
