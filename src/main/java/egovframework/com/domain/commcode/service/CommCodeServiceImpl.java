package egovframework.com.domain.commcode.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import egovframework.com.domain.commcode.dao.CommCodeDAO;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;

/**
 * 공통 코드 Service
 *
 * @fileName : CommCodeServiceImpl.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
@Service
public class CommCodeServiceImpl implements CommCodeService {
    @Inject
    private CommCodeDAO commCodeDAO;

    @Override
    public List<CommCode> getCommCodeList(CommCodeSearchParameter param) {
        return commCodeDAO.getCommCodeList(param);
    }

    @Override
    public List<CommCode> getCSCCodeList(CommCodeSearchParameter param) {
        return commCodeDAO.getCSCCodeList(param);
    }
}
