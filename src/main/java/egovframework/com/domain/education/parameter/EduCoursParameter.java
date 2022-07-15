package egovframework.com.domain.education.parameter;

import java.util.Date;
import lombok.Data;

@Data
public class EduCoursParameter {
    private Long eduCourseId;
    private Long eduClassId;
    private int eduClassSeq;
    private String eduLecturerName;
    private String eduPeriodCd;
    private Date eduStartDt;
    private Date eduEndDt;
    private String eduPlaceCd;
    private Long eduCoursRsltFileId;
    private String playBackYn;
    private Double eduProgress;
}
