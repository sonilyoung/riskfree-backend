package egovframework.com.domain.productmgmt.service;

import java.util.List;
import egovframework.com.domain.productmgmt.domain.ProductMgmt;
import egovframework.com.domain.productmgmt.parameter.ProductMgmtSearchParameter;

/**
 * 제품 관리 Service
 *
 * @fileName : ProductMgmtService.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
public interface ProductMgmtService {
	List<ProductMgmt> getProductList(ProductMgmtSearchParameter param);
}
