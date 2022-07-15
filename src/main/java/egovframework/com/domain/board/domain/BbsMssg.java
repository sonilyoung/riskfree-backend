package egovframework.com.domain.board.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class BbsMssg {
    private Long bbsBoardId;
    private Long bbsMssgId;
    private String bbsTitle;
    private String bbsPoster;
    private String useYn;
    private Date regDate;
    private String regDateYmd;
    private Date updDate;
    private String updDateYmd;
}
