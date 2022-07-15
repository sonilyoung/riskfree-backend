package egovframework.com.domain.education.domain;

import java.util.Date;
import lombok.Data;

@Data
public class EduCours {
    private Long eduCourseId;
    private String eduYear;
    private Long eduClassId;
    private int eduClassSeq;
    private String eduLecturerName;
    private String eduPeriodCd;
    private String eduPeriodName;
    private Date eduStartDt;
    private String eduStartYmd;
    private Date eduEndDt;
    private String eduEndYmd;
    private String eduPlaceCd;
    private String eduPlaceName;
    private Long eduCoursRsltFileId;
    private String eduProgress;
    private String useYn;
    private Date regDate;
    private String regDateYmd;
    private Date updDate;
    private String updDateYmd;

    // 22.05.13 김주환 추가
    private String eduTypeName;
    private String eduClassName;
    private String eduClassTargetCd;
    private String eduClassTargetName;
    private Long eduTypeId;

    // 22.05.16 김주환 추가
    private Long eduCourseResultId;
    private Long eduUserId;
}
