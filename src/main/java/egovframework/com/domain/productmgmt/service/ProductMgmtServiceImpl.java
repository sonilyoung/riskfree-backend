package egovframework.com.domain.productmgmt.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import egovframework.com.domain.productmgmt.dao.ProductMgmtDAO;
import egovframework.com.domain.productmgmt.domain.ProductMgmt;
import egovframework.com.domain.productmgmt.parameter.ProductMgmtSearchParameter;

/**
 * 제품 관리 Service
 *
 * @fileName : ProductMgmtServiceImpl.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Service
public class ProductMgmtServiceImpl implements ProductMgmtService {
    @Inject
    private ProductMgmtDAO productMgmtDAO;

    @Override
    public List<ProductMgmt> getProductList(ProductMgmtSearchParameter param) {
        return productMgmtDAO.getProductList(param);
    }

}
