package egovframework.com.domain.commcode.parameter;

import java.util.List;
import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통 코드 Search Parameter
 *
 * @fileName : CommCodeSearchParameter.java
 * @author : YeongJun Lee
 * @date : 2022.04.05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommCodeSearchParameter extends CommSearchParameter {
    private List<String> groupIds;
    private String groupId;
    private String codeId;
    private String codeName;
}
