package egovframework.com.domain.education.parameter;

import lombok.Data;

@Data
public class EduClassParameter {
    private Long eduClassId;
    private Long eduTypeId;
    private String eduClassName;
    private String eduClassTargetCd;
    private int eduClassHours;
    private int eduClassHRHours;
    private Long eduClassFileId;
}
