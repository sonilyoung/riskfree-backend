package egovframework.com.domain.common.parameter;

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
	private Integer pageNum;
	private Integer countPerPage;
}
