package egovframework.com.domain.education.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.education.dao.EduClassDAO;
import egovframework.com.domain.education.domain.EduClass;
import egovframework.com.domain.education.parameter.EduClassParameter;
import egovframework.com.domain.education.parameter.EduClassSearchParameter;

@Service
@Transactional(readOnly = true)
public class EduClassServiceImpl implements EduClassService {

    @Autowired
    private EduClassDAO repository;

    /**
     * 목록 갯수
     * 
     * @param param
     * @return
     */
    @Override
    public Long getListCount(EduClassSearchParameter param) {
        return repository.getListCount(param);
    }

    /**
     * 목록 리턴
     * 
     * @param param
     * @return
     */
    @Override
    public List<EduClass> getList(EduClassSearchParameter param) {
        return repository.getList(param);
    }

    /**
     * 엑셀용 목록 리턴
     */
    @Override
    public List<EduClass> getListExcel(EduClassSearchParameter param) {
        return repository.getListExcel(param);
    }

    /**
     * 상세정보 리턴
     * 
     * @param Seq
     * @return
     */
    @Override
    public EduClass get(Long id) {
        return repository.get(id);
    }

    /**
     * 등록처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void insert(EduClassParameter param) {
        repository.insert(param);

    }

    /**
     * 수정처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void update(EduClassParameter param) {
        repository.update(param);
    }

    /**
     * 삭제처리
     * 
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    /**
     * 삭제처리(여러개)
     */
    @Override
    public void deleteArr(List<Long> idList) {
        repository.deleteArr(idList);
    }
}
