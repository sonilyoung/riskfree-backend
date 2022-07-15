package egovframework.com.domain.productmgmt.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 제품 관리 Search Parameter
 *
 * @fileName : ProductMgmtSearchParameter.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductMgmtSearchParameter extends CommSearchParameter {
	private String productName;
	private Long productId;
	private String progressState;
	private String substanceForm;
	private String packagingUnit;
	private String capacityUnit;
}
