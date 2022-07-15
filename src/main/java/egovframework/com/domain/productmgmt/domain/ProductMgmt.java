package egovframework.com.domain.productmgmt.domain;

import java.sql.Date;
import egovframework.com.domain.common.domain.CommListVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 제품 관리 도메인 엔티티
 *
 * @fileName : ProductMgmt.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductMgmt extends CommListVo {
    private Long productId;
    private Long customerId;
    private String productName;
    private String substanceForm;
    private String substanceFormName;
    private String flashPoint;
    private String ph;
    private String classfication;
    private String classficationName;
    private String packagingUnit;
    private String packagingUnitName;
    private Double capacity;
    private String capacityUnit;
    private String capacityUnitName;
    private String remarks;
    private String progressState;
    private String progressStateName;
    private Long insertId;
    private Date insertDate;
    private Long updateId;
    private Date updateDate;
}
