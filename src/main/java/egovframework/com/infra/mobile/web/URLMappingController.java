
package egovframework.com.infra.mobile.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 각 JSP에 공통으로 적용되는 페이지를 위한 Controller 클래스
 *
 * @author suji.h
 * @version 1.0.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일		수정자		수정내용
 *  -------    	--------    ---------------------------
 *  2018.07.20 suji.h 	최초 생성
 *      </pre>
 * 
 * @since 2018.07.20
 */

@Controller
public class URLMappingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(URLMappingController.class);

	@RequestMapping(value = "/mobile/login", method = RequestMethod.GET)
	public String mobileLogin() {
		return "office/com/_mobile/Login";
	}
	
	@RequestMapping(value = "/mobile/home", method = RequestMethod.GET)
	public String mobileHome() {
		return "office/com/_mobile/Home";
	}
	
	@RequestMapping(value = "/mobile/apv/approvalMenu", method = RequestMethod.GET)
	public String mobileApprovalMenu() {
		return "office/com/_mobile/apv/ApprovalMenu";
	}
	
	@RequestMapping(value = "/mobile/apv/approvalList")
	public String mobileApprovalList(HttpServletRequest request) {
		return "office/com/_mobile/apv/ApprovalList";
	}
	
	@RequestMapping(value = "/mobile/apv/{pageName}")
	public String mobileApproval(@PathVariable String pageName) {
		String returnStr = "office/com/_mobile/apv/"+pageName;
		LOGGER.info("currentUrl = {}", returnStr);
		return returnStr;
	}
	
	@RequestMapping(value = "/mobile/apv/OpinionAdd", method = RequestMethod.GET)
	public String mobileOpinionAdd() {
		return "office/com/_mobile/apv/OpinionAdd";
	}
	
	@RequestMapping(value = "/mobile/efolder/eFolderMenu", method = RequestMethod.GET)
	public String mobileEFolderMenu() {
		return "office/com/_mobile/efolder/EFolderMenu";
	}
	
	@RequestMapping(value = "/mobile/org", method = RequestMethod.GET)
	public String mobileOrg() {
		return "office/com/_mobile/Org";
	}
	
	@RequestMapping(value = "/mobile/profile", method = RequestMethod.GET)
	public String mobileProfile() {
		return "office/com/_mobile/Profile";
	}
}
