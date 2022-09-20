package egovframework.com.global.file.parameter;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 파일 첨부 Search Parameter
 * 
 * @fileName : AttachSearchParameter.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachSearchParameter {
    private long atchFileId;
    private int fileSn;
}
