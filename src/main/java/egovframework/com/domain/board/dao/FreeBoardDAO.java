package egovframework.com.domain.board.dao;

import java.util.List;
import egovframework.com.domain.board.domain.FreeBoard;
import egovframework.com.domain.board.parameter.FreeBoardParameter;
import egovframework.com.domain.board.parameter.FreeBoardSearchParameter;

/**
 * 게시판 Repository
 * 
 * @author jkj0411
 *
 */
public interface FreeBoardDAO {
	Long getListCount(FreeBoardSearchParameter parameter);

	List<FreeBoard> getList(FreeBoardSearchParameter parameter);

	List<FreeBoard> getListExcel(FreeBoardSearchParameter parameter);

	FreeBoard get(Long boardSeq);

	int insert(FreeBoardParameter board);

	int update(FreeBoardParameter board);

	int delete(Long boardSeq);

	int deleteArr(List<Long> boardSeq);
}
