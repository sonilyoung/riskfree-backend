package egovframework.com.domain.productmgmt.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.productmgmt.domain.ProductMgmt;
import egovframework.com.domain.productmgmt.parameter.ProductMgmtSearchParameter;

/**
 * 제품 관리 DAO
 *
 * @fileName : ProductMgmtDAOImpl.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Repository
public class ProductMgmtDAOImpl implements ProductMgmtDAO {
    @Inject
    private SqlSession sqlSession;

    private static final String Namespace =
            "egovframework.com.domain.productmgmt.dao.ProductMgmtDAO";

    @Override
    public List<ProductMgmt> getProductList(ProductMgmtSearchParameter param) {
        return sqlSession.selectList(Namespace + ".getProductList", param);
    }

}
