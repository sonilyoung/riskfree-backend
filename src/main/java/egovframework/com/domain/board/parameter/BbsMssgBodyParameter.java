package egovframework.com.domain.board.parameter;

import lombok.Data;

@Data
public class BbsMssgBodyParameter {
    private Long bbsBoardId;
    private Long bbsMssgId;
    private String bbsTitle;
    private String bbsContent;
    private String useYn;
    // private List<Attach> bbsAttachList;
}
