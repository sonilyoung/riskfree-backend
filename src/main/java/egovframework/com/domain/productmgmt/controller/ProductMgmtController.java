package egovframework.com.domain.productmgmt.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.common.domain.CommListWrapper;
import egovframework.com.domain.productmgmt.domain.ProductMgmt;
import egovframework.com.domain.productmgmt.parameter.ProductMgmtSearchParameter;
import egovframework.com.domain.productmgmt.service.ProductMgmtService;
import egovframework.com.global.http.BaseResponse;

/**
 * 제품 관리 Controller
 * 
 * @fileName : ProductMgmtController.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@RestController
@RequestMapping("/productMgmt")
public class ProductMgmtController {
    @Inject
    private ProductMgmtService productMgmtService;

    /**
     * @author : YeongJun Lee
     * @param param
     * @return
     */
    @PostMapping("/getProductList")
    public BaseResponse<CommListWrapper<ProductMgmt>> getProductList(
            @RequestBody ProductMgmtSearchParameter param) {
        List<ProductMgmt> list = productMgmtService.getProductList(param);
        CommListWrapper<ProductMgmt> listWrapper = new CommListWrapper<ProductMgmt>(list);
        return new BaseResponse<CommListWrapper<ProductMgmt>>(listWrapper);
    }
}
