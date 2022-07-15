package egovframework.com.domain.board.service;

import java.util.List;
import egovframework.com.domain.board.domain.FreeBoard;
import egovframework.com.domain.board.parameter.FreeBoardParameter;
import egovframework.com.domain.board.parameter.FreeBoardSearchParameter;

public interface FreeBoardService {

	public Long getListCount(FreeBoardSearchParameter parameter);

	public List<FreeBoard> getList(FreeBoardSearchParameter parameter);

	public List<FreeBoard> getListExcel(FreeBoardSearchParameter parameter);

	public FreeBoard get(Long boardSeq);

	public void insert(FreeBoardParameter parameter);

	public void update(FreeBoardParameter parameter);

	public void delete(Long boardSeq);

	public void deleteArr(List<Long> boardSeq);
}
