package egovframework.com.domain.board.parameter;

import lombok.Data;

@Data
public class BbsMssgSearchParameter {
    private Long bbsBoardId;
    private int currentPageNum;
    private int countPerPage;
    private String useYn;
}
