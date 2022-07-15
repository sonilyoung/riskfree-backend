package egovframework.com.domain.education.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class PlanDB {

    private Long eduClassId;
    private Long eduTypeId;
    private String eduTypeName;
    private String eduClassName;
    private String eduClassTargetCd;
    private String eduClassTargetName;
    private int eduClassHours;
    private int eduClassHRHours;
    private Long eduClassFileId;
    private Long eduCourseId;
    private String eduYear;
    private int eduClassSeq;
    private String eduLecturerName;
    private String eduPeriodCd;
    private String eduPeriodName;
    private Date eduStartDt;
    private Date eduEndDt;
    private String eduPlaceCd;
    private String eduPlaceName;
    private Long eduCoursRsltFileId;
    private String eduProgress;

    // 22.05.16 김주환 추가
    private Long eduCourseResultId;
    private Long eduUserId;
}
