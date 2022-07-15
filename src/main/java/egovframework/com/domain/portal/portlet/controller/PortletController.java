package egovframework.com.domain.portal.portlet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.portlet.domain.Portlet;
import egovframework.com.domain.portal.portlet.service.PortletService;
import egovframework.com.domain.portal.system.domain.SystemMenu;
import egovframework.com.domain.portal.system.service.SystemService;
import egovframework.com.global.common.domain.ComDefaultConstants;
import egovframework.com.global.genid.GenIdService;
import egovframework.com.global.util.OfficeStringUtil;


/**
 * Portal - Portlet
 * 
 * @author 박선희
 * @since 2018.07.30
 */


@Controller
public class PortletController {

    @Resource(name = "PortletService")
    private PortletService portletService;

    @Resource(name = "SystemService")
    private SystemService systemService;

    @Resource(name = "GenIdService")
    private GenIdService genIdService;


    /**
     * Portlet 목록 조회
     * 
     * @param portlet
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/portletList.do")
    @ResponseBody
    public Map<String, Object> getPortletList(@ModelAttribute("portlet") Portlet portlet,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(portlet.getSystemId())) {
            List<Portlet> portletList = portletService.getPortletList(portlet);
            result.put("data", portletList);
        }
        return result;
    }

    /**
     * Portlet 상세조회
     * 
     * @param portlet
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/portletDetail.do")
    @ResponseBody
    public Map<String, Object> getPortlet(@ModelAttribute("portlet") Portlet portlet,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        Portlet portletDetail = new Portlet();

        if (!OfficeStringUtil.isNull(portlet.getPortletId())) {
            portletDetail = portletService.getPortlet(portlet.getPortletId());
        } else {
            SystemMenu system = systemService.getSystem(portlet.getSystemId());
            portletDetail.setSystemId(system.getSystemId());
            portletDetail.setSystemName(system.getSystemName());
        }

        result.put("data", portletDetail);
        return result;
    }

    /**
     * Portlet 저장
     * 
     * @param portlet
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/portletSave.do")
    public ModelAndView setPortlet(@ModelAttribute("portlet") Portlet portlet,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        ModelAndView model = new ModelAndView();
        if (!OfficeStringUtil.isNull(portlet.getUrl())) {
            portlet.setUrl(portlet.getUrl().replace("&apos;", "\"").replace("&quot;", "\""));
            if (!"/".equals(portlet.getUrl().substring(0, 1))) {
                portlet.setUrl("/" + portlet.getUrl());
            }
        }

        if (OfficeStringUtil.isNull(portlet.getPortletId())) {
            // insert
            String portletId = genIdService.selectPrtlPortletNextId();
            portlet.setPortletId(portletId);
            portlet.setEditF(ComDefaultConstants.FLAG_TRUE);

            Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
            // portletService.insertPortlet(portlet, Login.getCompanyId());
        } else {
            // update
            portletService.updatePortlet(portlet);
        }

        model.addObject("systemId", portlet.getSystemId());
        model.setViewName("redirect:/systemList.do");
        return model;
    }

    /**
     * Portlet 삭제
     * 
     * @param Portlet
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/portletDelete.do")
    public ModelAndView removePortlet(@ModelAttribute("portlet") Portlet portlet,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        ModelAndView model = new ModelAndView();

        String systemId = "";

        if (!portlet.getPortletIds().isEmpty()) {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("deleteIds", portlet.getPortletIds());

            portletService.deletePortlet(hm);

            if (!OfficeStringUtil.isNull((String) request.getAttribute("systemId"))) {
                systemId = (String) request.getAttribute("systemId");
            }

        }

        model.addObject("selectedSystemId", systemId);
        model.setViewName("redirect:/systemList.do");
        return model;
    }

    /**
     * 여러 시스템의 Portlet 존재 여부 확인
     * 
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkSystemDelete.do")
    @ResponseBody
    public Map<String, Object> checkSystemDelete(HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        List<String> systemNames = new ArrayList<>();

        if (!OfficeStringUtil.isEmpty(request.getParameter("systemIds"))) {
            String[] systemIds = request.getParameter("systemIds").split(",");
            for (String systemID : systemIds) {
                Portlet portlet = new Portlet();
                portlet.setSystemId(systemID);
                List<Portlet> portletList = portletService.getPortletList(portlet);
                if (null != portletList && 0 < portletList.size()) {
                    systemNames.add(portletList.get(0).getSystemName());
                }
            }
        }

        result.put("data", systemNames);
        return result;
    }
}
