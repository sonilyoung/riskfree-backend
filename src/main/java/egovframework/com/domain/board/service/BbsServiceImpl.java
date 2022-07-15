package egovframework.com.domain.board.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.board.dao.BbsDAO;
import egovframework.com.domain.board.domain.BbsMssg;
import egovframework.com.domain.board.domain.BbsMssgBody;
import egovframework.com.domain.board.parameter.BbsMssgBodyParameter;
import egovframework.com.domain.board.parameter.BbsMssgSearchParameter;

@Service
@Transactional(readOnly = true)
public class BbsServiceImpl implements BbsService {

    @Autowired
    private BbsDAO repository;

    /**
     * 목록 갯수
     * 
     * @param param
     * @return
     */
    @Override
    public Long getMssgListCount(BbsMssgSearchParameter param) {
        return repository.getMssgListCount(param);
    }

    /**
     * 목록 리턴
     * 
     * @param param
     * @return
     */
    @Override
    public List<BbsMssg> getMssgList(BbsMssgSearchParameter param) {
        return repository.getMssgList(param);
    }

    /**
     * 엑셀용 목록 리턴
     */
    @Override
    public List<BbsMssg> getMssgListExcel(BbsMssgSearchParameter param) {
        return repository.getMssgListExcel(param);
    }

    /**
     * 상세정보 리턴
     * 
     * @param Seq
     * @return
     */
    @Override
    public BbsMssgBody getMssg(Long bbsBoardId, Long bbsMssgId) {
        return repository.getMssg(bbsBoardId, bbsMssgId);
    }

    /**
     * 등록처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void insertMssg(BbsMssgBodyParameter param) {
        repository.insertMssg(param);

    }

    /**
     * 수정처리
     * 
     * @param param
     */
    @Override
    @Transactional
    public void updateMssg(BbsMssgBodyParameter param) {
        repository.updateMssg(param);
    }

    /**
     * 삭제처리
     * 
     * @param id
     */
    @Override
    @Transactional
    public void deleteMssg(Long bbsBoardId, Long bbsMssgId) {
        repository.deleteMssg(bbsBoardId, bbsMssgId);
    }

    /**
     * 삭제처리(여러개)
     */
    @Override
    public void deleteArrMssg(Long bbsBoardId, List<Long> idList) {
        repository.deleteArrMssg(bbsBoardId, idList);
    }
}
