package egovframework.com.domain.commcode.service;

import java.util.List;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;

/**
 * 공통 코드 Service
 *
 * @fileName : CommCodeService.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
public interface CommCodeService {
    List<CommCode> getCommCodeList(CommCodeSearchParameter param);

    List<CommCode> getCSCCodeList(CommCodeSearchParameter param);
}
