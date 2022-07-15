package egovframework.com.domain.board.parameter;

import egovframework.com.domain.board.domain.BoardType;
import lombok.Data;

@Data
public class FreeBoardParameter {

    private Long boardSeq;
    private BoardType boardType;
    private String title;
    private String contents;
}
