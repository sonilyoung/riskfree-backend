package egovframework.com.domain.commcode.dao;

import java.util.List;
import egovframework.com.domain.commcode.domain.CommCode;
import egovframework.com.domain.commcode.parameter.CommCodeSearchParameter;

/**
 * 공통 코드 DAO
 *
 * @fileName : CommCodeDAO.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
public interface CommCodeDAO {
    List<CommCode> getCommCodeList(CommCodeSearchParameter param);

    List<CommCode> getCSCCodeList(CommCodeSearchParameter param);
}
