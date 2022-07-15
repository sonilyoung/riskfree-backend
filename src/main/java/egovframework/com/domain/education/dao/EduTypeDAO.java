package egovframework.com.domain.education.dao;

import java.util.List;
import egovframework.com.domain.education.domain.EduType;
import egovframework.com.domain.education.parameter.EduTypeParameter;
import egovframework.com.domain.education.parameter.EduTypeSearchParameter;

public interface EduTypeDAO {
    Long getListCount();

    List<EduType> getList(EduTypeSearchParameter param);

    List<EduType> getListExcel(EduTypeSearchParameter param);

    EduType get(Long id);

    int insert(EduTypeParameter param);

    int update(EduTypeParameter param);

    int delete(Long id);

    int deleteArr(List<Long> idList);
}
