package egovframework.com.global.file.domain;

import egovframework.com.domain.common.domain.CommListVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 파일 첨부 상세
 * 
 * @fileName : AttachDetail.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AttachDetail extends CommListVo {
    private Long atchFileId;
    private int fileSn;
    private String filePath;
    private String saveFileName; 
    private String originalFileName; 
    private String fileExt;
    private int fileSize;
    private boolean saved;
    private boolean deleted;
}
