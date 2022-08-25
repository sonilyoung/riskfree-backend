package egovframework.com.domain.common.parameter;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 공통 Search Parameter
 *
 * @fileName : CommSearchParameter.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Data
public class CommSearchParameter {
	
	@ApiParam(value = "pageNumber", required = false, example = "1")
	private Integer pageNum;
	
	@ApiParam(value = "countPerPage", required = false, example = "1")
	private Integer countPerPage;
}
