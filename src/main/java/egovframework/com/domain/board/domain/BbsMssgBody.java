package egovframework.com.domain.board.domain;

import java.sql.Date;
import lombok.Data;

@Data
public class BbsMssgBody {
    private Long bbsBoardId;
    private Long bbsMsgId;
    private String bbsTitle;
    private String bbsContent;
    private String bbsPosterId;
    private String bbsPosterName;
    // private List<Attach> bbsAttachList;
    private String useYn;
    private Date regDate;
    private String regDateYmd;
    private Date updDate;
    private String updDateYmd;
}
