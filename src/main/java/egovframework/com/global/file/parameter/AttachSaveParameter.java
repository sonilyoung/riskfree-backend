package egovframework.com.global.file.parameter;

import java.util.List;
import egovframework.com.global.file.domain.AttachDetail;
import lombok.Data;

/**
 * 파일첨부 Save Parameter
 * 
 * @fileName : AttachSaveParameter.java
 * @author : YeongJun Lee
 * @date : 2022.07.15
 */
@Data
public class AttachSaveParameter {
    private Long atchFileId;
    private List<AttachDetail> deleteItems;
}
