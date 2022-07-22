package egovframework.com.domain.commcode.domain;

import java.sql.Date;
import egovframework.com.domain.common.domain.CommListVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통 코드 도메인 엔티티
 *
 * @fileName : CommCode.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommCode extends CommListVo {
    private String groupId;
    private String codeId;
    private String codeNameKor;
    private String codeNameEng;
    private String codeNameJpn;
    private String codeTemp1;
    private String codeTemp2;
    private String codeTemp3;
    private String remarks;
    private Integer sortOrder;
    private Integer isEnable;
    private Integer isDelete;
    private Long insertId;
    private Date insertDate;
    private Long updateId;
    private Date updateDate;
}
