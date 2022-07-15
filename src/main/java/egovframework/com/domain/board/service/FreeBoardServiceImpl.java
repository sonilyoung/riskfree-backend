package egovframework.com.domain.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.board.dao.FreeBoardDAO;
import egovframework.com.domain.board.domain.FreeBoard;
import egovframework.com.domain.board.parameter.FreeBoardParameter;
import egovframework.com.domain.board.parameter.FreeBoardSearchParameter;

/**
 * 게시판 Service
 * 
 * @author jkj0411
 *
 */
@Service
@Transactional(readOnly = true)
public class FreeBoardServiceImpl implements FreeBoardService {

    @Autowired
    private FreeBoardDAO repository;

    /**
     * 목록 갯수
     * 
     * @param parameter
     * @return
     */
    @Override
    public Long getListCount(FreeBoardSearchParameter parameter) {
        return repository.getListCount(parameter);
    }

    /**
     * 목록 리턴
     * 
     * @param parameter
     * @return
     */
    @Override
    public List<FreeBoard> getList(FreeBoardSearchParameter parameter) {
        return repository.getList(parameter);
    }

    /**
     * 엑셀용 목록 리턴
     */
    @Override
    public List<FreeBoard> getListExcel(FreeBoardSearchParameter parameter) {
        return repository.getListExcel(parameter);
    }

    /**
     * 상세정보 리턴
     * 
     * @param boardSeq
     * @return
     */
    @Override
    public FreeBoard get(Long boardSeq) {
        return repository.get(boardSeq);
    }

    /**
     * 등록처리
     * 
     * @param parameter
     */
    @Override
    @Transactional
    public void insert(FreeBoardParameter parameter) {
        repository.insert(parameter);

    }

    /**
     * 수정처리
     * 
     * @param parameter
     */
    @Override
    @Transactional
    public void update(FreeBoardParameter parameter) {
        repository.update(parameter);
    }

    /**
     * 삭제처리
     * 
     * @param boardSeq
     */
    @Override
    @Transactional
    public void delete(Long boardSeq) {
        repository.delete(boardSeq);
    }

    /**
     * 삭제처리(여러개)
     */
    @Override
    public void deleteArr(List<Long> boardSeq) {
        repository.deleteArr(boardSeq);

    }
}
