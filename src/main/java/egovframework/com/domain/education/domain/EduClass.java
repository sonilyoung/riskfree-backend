package egovframework.com.domain.education.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class EduClass {
    private Long eduClassId;
    private Long eduTypeId;
    private String eduTypeName;
    private String eduClassName;
    private String eduClassTargetCd;
    private String eduClassTargetName;
    private int eduClassHours;
    private int eduClassHRHours;
    private Long eduClassFileId;
    private String useYn;
    private Date regDate;
    private String regDateYmd;
    private Date updDate;
    private String updDateYmd;
}
