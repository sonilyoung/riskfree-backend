package egovframework.com.domain.productmgmt.dao;

import java.util.List;
import egovframework.com.domain.productmgmt.domain.ProductMgmt;
import egovframework.com.domain.productmgmt.parameter.ProductMgmtSearchParameter;

/**
 * 제품 관리 DAO
 *
 * @fileName : ProductMgmtDAO.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
public interface ProductMgmtDAO {
	List<ProductMgmt> getProductList(ProductMgmtSearchParameter param);
}
