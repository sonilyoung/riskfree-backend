package egovframework.com.domain.board.dao;

import java.util.List;
import egovframework.com.domain.board.domain.BbsMssg;
import egovframework.com.domain.board.domain.BbsMssgBody;
import egovframework.com.domain.board.parameter.BbsMssgBodyParameter;
import egovframework.com.domain.board.parameter.BbsMssgSearchParameter;

public interface BbsDAO {
    Long getMssgListCount(BbsMssgSearchParameter param);

    List<BbsMssg> getMssgList(BbsMssgSearchParameter param);

    List<BbsMssg> getMssgListExcel(BbsMssgSearchParameter param);

    BbsMssgBody getMssg(Long bbsBoardId, Long bbsMssgId);

    int insertMssg(BbsMssgBodyParameter param);

    int updateMssg(BbsMssgBodyParameter param);

    int deleteMssg(Long bbsBoardId, Long bbsMssgId);

    int deleteArrMssg(Long bbsBoardId, List<Long> idList);
}
