package egovframework.com.domain.education.service;

import java.util.List;
import egovframework.com.domain.education.domain.EduType;
import egovframework.com.domain.education.parameter.EduTypeParameter;
import egovframework.com.domain.education.parameter.EduTypeSearchParameter;

public interface EduTypeService {

    public Long getListCount();

    public List<EduType> getList(EduTypeSearchParameter param);

    public List<EduType> getListExcel(EduTypeSearchParameter param);

    public EduType get(Long id);

    public void insert(EduTypeParameter param);

    public void update(EduTypeParameter param);

    public void delete(Long id);

    public void deleteArr(List<Long> idList);

}
