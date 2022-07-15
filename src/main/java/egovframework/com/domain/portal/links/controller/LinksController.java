package egovframework.com.domain.portal.links.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.links.domain.Links;
import egovframework.com.domain.portal.links.service.LinksService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.genid.GenIdService;
import egovframework.com.global.util.OfficeStringUtil;


/**
 * Portal - Links
 * 
 * @author
 * @since
 *
 */

@Controller
public class LinksController {

    @Resource(name = "LinksService")
    private LinksService linksService;

    @Resource(name = "GenIdService")
    private GenIdService genIdService;

    @Autowired
    private UserManageService userManageService;

    /**
     * Links 목록 조회
     * 
     * @param links
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/linksList.do")
    public ModelAndView linksList(@ModelAttribute("links") Links links, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {

        ModelAndView model = new ModelAndView();
        /*
         * model.addObject("selectedSystemId", null);
         * 
         * if (!OfficeStringUtil.isNull(request.getParameter("linksId"))) {
         * model.addObject("selectedSystemId", request.getParameter("linksId")); }
         */


        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        UserVO user = null;// userManageService.getUser(Login.getUserUniqId());

        links.setOrgnztId(user.getCompanyId());
        List<Links> linksList = linksService.linksList(links);

        model.addObject("linksList", linksList);
        model.setViewName("office/com/prtl/Links");
        return model;
    }

    /**
     * Link 상세 조회
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/linksDetail.do")
    @ResponseBody
    public Map<String, Object> linksDetail(@ModelAttribute("links") Links links,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        Links linksInfo = new Links();

        // Login Login = (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        // UserVO user = userManageService.getUser(Login.getUserUniqId());

        if (!OfficeStringUtil.isNull(links.getLinksId())) {
            linksInfo = linksService.linksDetail(links);
        }

        // Login Login = (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        // UserVO user = userManageService.getUser(Login.getUserUniqId());

        // result.put("user", user);
        // model.addObject("topOrgnztID", user.getCompanyId());
        // model.setViewName("office/com/prtl/popup/SystemDetail");

        result.put("data", linksInfo);

        return result;
    }

    /**
     * Link 저장
     * 
     * @param systemMenu
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveLinks.do")
    public String saveLinks(@ModelAttribute("links") Links links, HttpServletRequest request,
            HttpServletResponse response, HttpSession session) throws Exception {

        // Login Login = (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        // UserVO user = userManageService.getUser(Login.getUserUniqId());

        links.setLinksName(OfficeStringUtil.singleDoubleQuotationParsing(links.getLinksName()));
        if (OfficeStringUtil.isNull(links.getLinksId())) {
            // insert
            String linksId = genIdService.selectPrtlLinkNextId();
            links.setLinksId(linksId);

            linksService.insertLinks(links);
        } else {
            // update
            linksService.updateLinks(links);
        }

        return "redirect:/linksList.do";
    }

    /**
     * Link 삭제
     * 
     * @param deleteIds
     * @param request
     * @param response
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteLinks.do")
    @ResponseBody
    public Map<String, String> deleteLinks(
            @RequestParam(value = "checkedLinks[]") List<String> deleteIds,
            HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {

        Map<String, String> resultMap = new HashMap<String, String>();
        if (!OfficeStringUtil.isEmpty(request.getParameter("checkedLinks[]"))) {

            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("deleteIds", deleteIds);

            linksService.deleteLinks(hm);
            resultMap.put("RESULT", "0000");
        } else {
            resultMap.put("RESULT", "0001");
        }

        return resultMap;
    }

}
