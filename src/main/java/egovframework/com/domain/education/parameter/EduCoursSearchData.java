package egovframework.com.domain.education.parameter;

import java.util.Date;
import lombok.Data;

@Data
public class EduCoursSearchData {

    private long eduTypeId;
    private long eduClassSeq;
    private long eduClassId;
    private Date eduStartDt;
    private Date eduEndDt;
    private String eduLecturerName;
    private String eduPlaceCd;
    private String eduPeriodCd;

    public EduCoursSearchData() {

    }
}
