package egovframework.com.domain.education.service;

import java.util.List;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.parameter.EduClassParameter;
import egovframework.com.domain.education.parameter.EduClassSearchParameter;

public interface EduClassService {

    public Long getListCount(EduClassSearchParameter param);

    public List<EduClass> getList(EduClassSearchParameter param);

    public List<EduClass> getListExcel(EduClassSearchParameter param);

    public EduClass get(Long id);

    public void insert(EduClassParameter param);

    public void update(EduClassParameter param);

    public void delete(Long id);

    public void deleteArr(List<Long> idList);

}
