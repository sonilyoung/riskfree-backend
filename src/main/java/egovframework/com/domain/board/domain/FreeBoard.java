package egovframework.com.domain.board.domain;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class FreeBoard {
    private Long boardSeq;
    private BoardType boardType;
    private String boardTypeLabel;
    private String title;
    private String contents;
    private Timestamp regDate;
    private String regDateYmd;
}
