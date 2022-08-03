package egovframework.com.domain.commcode.parameter;

import egovframework.com.domain.common.parameter.CommSearchParameter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 공통 코드 Search Parameter
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class CommCodeSearchParameter extends CommSearchParameter {
    
	private String groupId;				// 그룹코드ID
    private String codeId;  			// 코드ID
    private String codeTemp;			// 코드TEMP
    
}
