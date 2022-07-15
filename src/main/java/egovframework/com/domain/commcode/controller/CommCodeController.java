package egovframework.com.domain.commcode.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;
import egovframework.com.domain.commcode.service.CommCodeService;
import egovframework.com.domain.common.domain.CommListWrapper;
import egovframework.com.global.http.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

/**
 * 공통 코드 Controller
 *
 * @fileName : CommCodeController.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
@RestController
@RequestMapping("/commCode")
@Api(tags = "Comm code Management API")
public class CommCodeController {
    @Inject
    private CommCodeService commCodeService;

    /**
     * @author : YeongJun Lee
     * @param param
     * @return
     */
    @PostMapping("/getCommCodeList")
    public BaseResponse<CommListWrapper<CommCode>> getCommCodeList(
            @ApiParam @RequestBody CommCodeSearchParameter param) {
        List<CommCode> list = commCodeService.getCommCodeList(param);
        CommListWrapper<CommCode> listWrapper = new CommListWrapper<>(list);
        return new BaseResponse<>(listWrapper);
    }

    /**
     * @author : YeongJun Lee
     * @param param
     * @return
     */
    @PostMapping("/getCSCCodeList")
    public BaseResponse<CommListWrapper<CommCode>> getCSCCodeList(
            @ApiParam @RequestBody CommCodeSearchParameter param) {
        List<CommCode> list = commCodeService.getCSCCodeList(param);
        CommListWrapper<CommCode> listWrapper = new CommListWrapper<>(list);
        return new BaseResponse<>(listWrapper);
    }

}
