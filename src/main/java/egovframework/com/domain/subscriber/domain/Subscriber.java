package egovframework.com.domain.subscriber.domain;

import java.util.List;

import egovframework.com.domain.company.domain.Workplace;
import lombok.Data;

@Data
public class Subscriber {

	private Long companyId;
	private Long workplaceId;
	private Long userCount;
	private Long contractAmount;
	private Long contractFileId;
	private String companyName;
	private String contractDate;
	private String status;
	//private List<Workplace> workplaceInfo;
}
