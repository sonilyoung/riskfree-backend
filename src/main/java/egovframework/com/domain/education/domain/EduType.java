package egovframework.com.domain.education.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class EduType {
    private Long eduTypeId;
    private String eduTypeName;
    private String useYn;
    private Date regDate;
    private String regDateYmd;
    private Date updDate;
    private String updDateYmd;
}
