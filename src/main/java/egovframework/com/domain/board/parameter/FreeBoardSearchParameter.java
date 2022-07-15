package egovframework.com.domain.board.parameter;

import java.util.List;
import egovframework.com.domain.board.domain.BoardType;
import egovframework.com.global.http.SearchData;
import lombok.Data;

/**
 * 
 * @author jkj0411
 *
 */
@Data
public class FreeBoardSearchParameter {

    private List<BoardType> boardTypes;
    private SearchData searchData;
    private int currentPageNum;
    private int countPerPage;

    public FreeBoardSearchParameter() {

    }
}
