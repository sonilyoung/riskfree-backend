package egovframework.com.domain.education.dao;

import java.util.List;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.parameter.EduClassParameter;
import egovframework.com.domain.education.parameter.EduClassSearchParameter;

public interface EduClassDAO {
    Long getListCount(EduClassSearchParameter param);

    List<EduClass> getList(EduClassSearchParameter param);

    List<EduClass> getListExcel(EduClassSearchParameter param);

    EduClass get(Long id);

    int insert(EduClassParameter param);

    int update(EduClassParameter param);

    int delete(Long id);

    int deleteArr(List<Long> idList);
}
