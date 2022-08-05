package egovframework.com.global.file.domain;

import java.sql.Timestamp;
import egovframework.com.domain.common.domain.CommListVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 파일 첨부
 * 
 * @fileName : Attach.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Attach extends CommListVo {
    private long atchFileId;
    private Timestamp creatDt;
    private String useAt;
}
