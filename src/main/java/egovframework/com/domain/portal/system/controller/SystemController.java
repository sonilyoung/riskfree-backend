package egovframework.com.domain.portal.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.system.domain.SystemMenu;
import egovframework.com.domain.portal.system.service.SystemService;
import egovframework.com.global.common.domain.ComDefaultConstants;
import egovframework.com.global.genid.GenIdService;
import egovframework.com.global.util.OfficeStringUtil;


/**
 * Portal - System
 * 
 * @author 박선희
 * @since 2018.07.30
 *
 */


@Controller
public class SystemController {

    @Resource(name = "SystemService")
    private SystemService systemService;

    @Resource(name = "GenIdService")
    private GenIdService genIdService;

    @Autowired
    private UserManageService userManageService;

    /**
     * System 목록 조회
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/systemList.do")
    public ModelAndView getSystemList(@ModelAttribute("systemMenu") SystemMenu systemMenu,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        ModelAndView model = new ModelAndView();
        model.addObject("selectedSystemId", null);

        if (!OfficeStringUtil.isNull(request.getParameter("systemId"))) {
            model.addObject("selectedSystemId", request.getParameter("systemId"));
        }

        List<SystemMenu> systemList = systemService.getSystemList();

        model.addObject("systemList", systemList);
        model.setViewName("office/com/prtl/Index");
        return model;
    }

    /**
     * System 상세 조회
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/systemDetail.do")
    @ResponseBody
    public Map<String, Object> getSystem(@ModelAttribute("systemMenu") SystemMenu systemMenu,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        SystemMenu system = new SystemMenu();

        if (!OfficeStringUtil.isNull(systemMenu.getSystemId())) {
            system = systemService.getSystem(systemMenu.getSystemId());
        }

        // Login Login = (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        // UserVO user = userManageService.getUser(Login.getUserUniqId());

        // result.put("user", user);
        // model.addObject("topOrgnztID", user.getCompanyId());
        // model.setViewName("office/com/prtl/popup/SystemDetail");

        result.put("data", system);

        return result;
    }

    /**
     * System 저장
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/systemSave.do")
    public String setSystem(@ModelAttribute("systemMenu") SystemMenu systemMenu,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        if (!OfficeStringUtil.isNull(systemMenu.getUrl())) {
            systemMenu.setUrl(systemMenu.getUrl().replace("&apos;", "\"").replace("&quot;", "\""));
        }

        if (OfficeStringUtil.isNull(systemMenu.getSystemId())) {
            // insert
            String systemId = genIdService.selectPrtlSystemNextId();
            systemMenu.setSystemId(systemId);
            systemMenu.setEditF(ComDefaultConstants.FLAG_TRUE);

            Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
            // systemService.insertSystem(systemMenu, Login.getCompanyId());
        } else {
            systemService.updateSystem(systemMenu);
        }

        return "redirect:/systemList.do";
    }

    /**
     * System 삭제
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/systemDelete.do")
    public String removeSystem(@ModelAttribute("systemMenu") SystemMenu systemMenu,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        if (!OfficeStringUtil.isEmpty(request.getParameter("systemIds"))) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("deleteIds", systemMenu.getSystemIds());

            systemService.deleteSystem(hm);
        }

        return "redirect:/systemList.do";
    }

}
