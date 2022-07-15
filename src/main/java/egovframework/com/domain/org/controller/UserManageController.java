package egovframework.com.domain.org.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springmodules.validation.commons.DefaultBeanValidator;
import egovframework.com.domain.org.service.OrgnztManageService;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.links.service.LinksService;
import egovframework.com.domain.portal.portlet.service.PortletService;
import egovframework.com.domain.portal.system.service.SystemService;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.common.service.EmailMngService;
import egovframework.com.global.util.OfficeStringUtil;
import egovframework.com.infra.noty.service.NotificationService;
import egovframework.com.infra.noty.service.NotificationUtil;

/**
 * 사용자관련 요청을 비지니스 클래스로 전달하고 처리된결과를 해당 웹 화면으로 전달하는 Controller를 정의한다
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *   2011.08.26	 정진오			IncludedInfo annotation 추가
 *   2014.12.08	 이기하			암호화방식 변경(OfficeFileScrty.encryptPassword)
 *   2015.06.16	 조정국			수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19	 조정국			미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2018.09.03	 박선희			portlet메뉴기능 추가
 *
 *      </pre>
 */

@Controller
public class UserManageController {

    /** userManageService */
    @Autowired
    private UserManageService userManageService;

    /** OrgnztManageService */
    @Resource(name = "OrgnztManageService")
    private OrgnztManageService orgnztService;

    @Resource(name = "PropertiesService")
    Map<String, String> propertyService;

    OfficeMessageSource officeMessageSource;

    @Resource(name = "PortletService")
    PortletService portletService;

    @Resource(name = "SystemService")
    SystemService systemService;

    private DefaultBeanValidator beanValidator;

    @Resource(name = "LinksService")
    private LinksService linksService;

    @Resource(name = "EmailMngService")
    private EmailMngService emailMngService;

    @Resource(name = "NotificationService")
    private NotificationService notificationService;

    @Resource(name = "NotificationUtil")
    private NotificationUtil notificationUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManageController.class);
    private static final String DEFAULT_PASSWORD = "defaultpw";

    private String stringUnescape(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = OfficeStringUtil.unescapeHtml(str);
            str = StringEscapeUtils.unescapeXml(str);
        }
        return str;
    }
}
