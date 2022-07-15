package egovframework.com.domain.board.service;

import java.util.List;
import egovframework.com.domain.board.domain.BbsMssg;
import egovframework.com.domain.board.domain.BbsMssgBody;
import egovframework.com.domain.board.parameter.BbsMssgBodyParameter;
import egovframework.com.domain.board.parameter.BbsMssgSearchParameter;

public interface BbsService {

    public Long getMssgListCount(BbsMssgSearchParameter param);

    public List<BbsMssg> getMssgList(BbsMssgSearchParameter param);

    public List<BbsMssg> getMssgListExcel(BbsMssgSearchParameter param);

    public BbsMssgBody getMssg(Long bbsBoardId, Long bbsMssgId);

    public void insertMssg(BbsMssgBodyParameter param);

    public void updateMssg(BbsMssgBodyParameter param);

    public void deleteMssg(Long bbsBoardId, Long bbsMssgId);

    public void deleteArrMssg(Long bbsBoardId, List<Long> idList);

}
