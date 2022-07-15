package egovframework.com.global.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.common.domain.FileVO;
import egovframework.com.global.common.service.FileMngService;
import egovframework.com.global.common.service.FileMngUtil;
import egovframework.com.global.util.sim.service.OfficeClntInfo;

/**
 * 파일 조회, 삭제, 다운로드 처리를 위한 컨트롤러 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.3.25  이삼섭          최초 생성
 *
 *      </pre>
 */
@Controller
public class FileMngController {

    @Resource(name = "FileMngService")
    private FileMngService fileService;

    @Resource(name = "FileMngUtil")
    private FileMngUtil fileUtil;

    private String tempDir = System.getProperty("java.io.tmpdir");

    /**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfs.do")
    public String selectFileInfs(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap, ModelMap model,
            @RequestParam(value = "tableView", required = false) String tableView)
            throws Exception {
        String atchFileId = (String) commandMap.get("param_atchFileId");

        if (StringUtils.isNotBlank(atchFileId)) {
            fileVO.setAtchFileId(atchFileId);
            List<FileVO> result = fileService.selectFileInfs(fileVO);

            /*
             * // For DRM PDF Viewer MaUrlRdBase64 clMaUrlRdBase64 = new MaUrlRdBase64(); for(FileVO
             * fileDetail : result) { String filePath =
             * PathUtil.getFullPathOfFile(fileDetail.getFileStreCours()) + File.separator +
             * fileDetail.getStreFileNm() + "." + fileDetail.getFileExtsn(); String
             * strEncOrgFilePath = clMaUrlRdBase64.strUrlRdEncode(filePath);
             * fileDetail.setStrEncOrgFilePath(strEncOrgFilePath); }
             */

            model.addAttribute("fileList", result);
            model.addAttribute("updateFlag", "N");
            model.addAttribute("fileListCnt", result.size());
        }

        model.addAttribute("atchFileId", atchFileId);

        if (tableView == null) {
            return "office/com/cmm/fms/FileList";
        } else {
            return "office/com/cmm/fms/FileList_tableStyle_viewer";
        }
    }

    /**
     * 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/cmm/fms/selectFileInfs.jdo")
    @ResponseBody
    public Map<String, Object> selectFileInfsj(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap, HttpServletRequest request,
            @RequestParam(value = "tableView", required = false) String tableView)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> dataMap = new HashMap<String, Object>();

        try {
            String AUTH_GSAS = request.getParameter("AUTH_GSAS");
            String userIp = OfficeClntInfo.getClntIP((HttpServletRequest) request);

            Login Login = null;// loginService.Token2Vo(AUTH_GSAS, userIp);
            if (Login == null) {
                dataMap.put("AUTH_GSAS", AUTH_GSAS);
                resultMap.put("RET_CODE", "-1");
                resultMap.put("RET_DESC", "Invalid AuthGSAS");
                return resultMap;
            }

            // DynamicContextHolder.setDynamicType(Login.getCompanyCd());

            // Start Code

            String atchFileId = (String) commandMap.get("param_atchFileId");

            if (StringUtils.isNotBlank(atchFileId)) {
                fileVO.setAtchFileId(atchFileId);
                List<FileVO> result = fileService.selectFileInfs(fileVO);

                dataMap.put("fileList", result);
                dataMap.put("updateFlag", "N");
                dataMap.put("fileListCnt", result.size());
            }

            dataMap.put("atchFileId", atchFileId);

            // End Code

            resultMap.put("RET_CODE", "0000");
            resultMap.put("RET_DESC", "Success");
            resultMap.put("RET_DATA", dataMap);

        } catch (Exception e) {
            resultMap.put("RET_CODE", "-1");
            resultMap.put("RET_DESC", e.getMessage());
        }

        return resultMap;
    }

    /**
     *
     * Modal 첨부파일에 대한 목록을 조회한다.
     * 
     * @param fileVO
     * @param commandMap
     * @param model
     * @param tableView
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectModalFileInfs.do")
    public String selectModalFileInfs(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap, ModelMap model,
            @RequestParam(value = "tableView", required = false) String tableView)
            throws Exception {
        String atchFileId = (String) commandMap.get("param_atchFileId");

        if (StringUtils.isNotBlank(atchFileId)) {
            fileVO.setAtchFileId(atchFileId);
            List<FileVO> result = fileService.selectFileInfs(fileVO);

            /*
             * // For DRM PDF Viewer MaUrlRdBase64 clMaUrlRdBase64 = new MaUrlRdBase64(); for(FileVO
             * fileDetail : result) { String filePath =
             * PathUtil.getFullPathOfFile(fileDetail.getFileStreCours()) + File.separator +
             * fileDetail.getStreFileNm() + "." + fileDetail.getFileExtsn();
             * 
             * String strEncOrgFilePath = clMaUrlRdBase64.strUrlRdEncode(filePath);
             * fileDetail.setStrEncOrgFilePath(strEncOrgFilePath); }
             */

            model.addAttribute("fileList", result);
            model.addAttribute("updateFlag", "N");
            model.addAttribute("fileListCnt", result.size());
        }

        model.addAttribute("atchFileId", atchFileId);

        if (tableView == null) {
            return "office/com/cmm/fms/ModalFileList";
        } else {
            return "office/com/cmm/fms/FileList_tableStyle_viewer";
        }
    }

    /**
     * 첨부파일에 대한 목록을 조회한다. (+ File Url Link)
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileURLInfo.do")
    public String selectFileURLInfo(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap, ModelMap model,
            @RequestParam(value = "tableView", required = false) String tableView)
            throws Exception {
        String atchFileId = (String) commandMap.get("param_atchFileId");

        fileVO.setAtchFileId(atchFileId);
        List<FileVO> result = fileService.selectFileInfs(fileVO);

        /*
         * // For DRM PDF Viewer MaUrlRdBase64 clMaUrlRdBase64 = new MaUrlRdBase64(); for(FileVO
         * fileDetail : result) { String filePath =
         * PathUtil.getFullPathOfFile(fileDetail.getFileStreCours()) + File.separator +
         * fileDetail.getStreFileNm() + "." + fileDetail.getFileExtsn(); String strEncOrgFilePath =
         * clMaUrlRdBase64.strUrlRdEncode(filePath);
         * fileDetail.setStrEncOrgFilePath(strEncOrgFilePath); }
         */

        model.addAttribute("fileList", result);
        model.addAttribute("updateFlag", "N");
        model.addAttribute("fileListCnt", result.size());
        model.addAttribute("atchFileId", atchFileId);

        return "office/com/cmm/fms/FileList_url";
    }

    /**
     * 첨부파일 변경을 위한 수정페이지로 이동한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectFileInfsForUpdate.do")
    public String selectFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap,
            // SessionVO sessionVO,
            @RequestParam(value = "tableView", required = false) String tableView, // bbs 첨부파일리스트
                                                                                   // tableView일 경우
            ModelMap model) throws Exception {

        String atchFileId = (String) commandMap.get("param_atchFileId");

        fileVO.setAtchFileId(atchFileId);

        List<FileVO> result = fileService.selectFileInfs(fileVO);

        /*
         * // For DRM PDF Viewer MaUrlRdBase64 clMaUrlRdBase64 = new MaUrlRdBase64(); for(FileVO
         * fileDetail : result) { String filePath =
         * PathUtil.getFullPathOfFile(fileDetail.getFileStreCours()) + File.separator +
         * fileDetail.getStreFileNm() + "." + fileDetail.getFileExtsn();
         * 
         * String strEncOrgFilePath = clMaUrlRdBase64.strUrlRdEncode(filePath);
         * fileDetail.setStrEncOrgFilePath(strEncOrgFilePath); }
         */

        model.addAttribute("fileList", result);
        model.addAttribute("updateFlag", "Y");
        model.addAttribute("fileListCnt", result.size());
        model.addAttribute("atchFileId", atchFileId);
        model.addAttribute("tempF", 0);

        if (tableView == null) {
            return "office/com/cmm/fms/FileList";
        } else {
            return "office/com/cmm/fms/FileList_tableStyle_upload";
        }
    }

    /**
     * 임시 첨부파일 변경을 위한 수정페이지로 이동한다.
     *
     * @param fileVO
     * @param atchFileId
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/selectTmpFileInfsForUpdate.do")
    public String selectTmpFileInfsForUpdate(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam Map<String, Object> commandMap,
            // SessionVO sessionVO,
            @RequestParam(value = "tableView", required = false) String tableView, // bbs 첨부파일리스트
                                                                                   // tableView일 경우
            ModelMap model) throws Exception {

        String atchFileId = (String) commandMap.get("param_atchFileId");

        fileVO.setAtchFileId(atchFileId);

        List<FileVO> result = fileService.selectTmpFileInfs(fileVO);

        model.addAttribute("fileList", result);
        model.addAttribute("updateFlag", "Y");
        model.addAttribute("fileListCnt", result.size());
        model.addAttribute("atchFileId", atchFileId);
        model.addAttribute("tempF", 1);

        if (tableView == null) {
            return "office/com/cmm/fms/FileList";
        } else {
            return "office/com/cmm/fms/FileList_tableStyle_upload";
        }
    }

    /**
     * 첨부파일에 대한 삭제를 처리한다.
     *
     * @param fileVO
     * @param returnUrl
     * @param sessionVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/cmm/fms/deleteFileInfs.do")
    public String deleteFileInf(@ModelAttribute("searchVO") FileVO fileVO,
            @RequestParam("returnUrl") String returnUrl,
            // SessionVO sessionVO,
            HttpServletRequest request, ModelMap model) throws Exception {

        Boolean isAuthenticated = null;// OfficeUserInfoHelper.isAuthenticated();

        if (isAuthenticated) {
            fileService.deleteFileInf(fileVO);
        }

        // --------------------------------------------
        // contextRoot가 있는 경우 제외 시켜야 함
        // --------------------------------------------
        //// return "forward:/cmm/fms/selectFileInfs.do";
        // return "forward:" + returnUrl;

        if ("".equals(request.getContextPath()) || "/".equals(request.getContextPath())) {
            return "forward:" + returnUrl;
        }

        if (returnUrl.startsWith(request.getContextPath())) {
            return "forward:" + returnUrl.substring(returnUrl.indexOf("/", 1));
        } else {
            return "forward:" + returnUrl;
        }
        //// ------------------------------------------
    }

    /**
     * 이미지 첨부파일에 대한 목록을 조회한다.
     *
     * @param fileVO
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectImageFileInfs.do")
    public String selectImageFileInfs(@ModelAttribute("searchVO") FileVO fileVO, ModelMap model)
            throws Exception {
        List<FileVO> result = fileService.selectImageFileList(fileVO);

        model.addAttribute("fileList", result);

        return "office/com/cmm/fms/ImgFileList";
    }

    @RequestMapping(value = "/uploadBridge.do", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadBridge(final MultipartHttpServletRequest multiRequest,
            @RequestParam Map params) throws Exception {
        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        return fileService.uploadBridge_core(multiRequest, params, Login);
    }

    @RequestMapping(value = "/downloadBridge.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> downloadBridge(final HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        return fileService.downloadBridge_core(request, response, Login);
    }

    @RequestMapping(value = "/downloadBridgeTask.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> downloadBridgeTask(final HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        String mailTy = "";// Login.getMailTy(); // 1:zimbra, 2:owa
        String zmGwIp = "";// Login.getZmGwIp();
        String zmGwPort = "";// Login.getZmGwPort();

        // String gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_download_task.do?" +
        // request.getQueryString();

        String gurl = null;
        if ("1".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_download_task.do?"
                    + request.getQueryString();
        } else if ("2".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_download_ex.do?"
                    + request.getQueryString();
        }

        // System.out.println("AUTH_TOKEN ::: " + AUTH_TOKEN);
        System.out.println("Download URL ::: " + gurl);

        Map<String, String> resultMap = new HashMap<String, String>();

        String fileName = fileUtil.downloadFile(gurl, tempDir);
        String realName = null;
        String filePath = tempDir + File.separator + fileName;

        try {
            realName = new String(java.util.Base64.getDecoder().decode(fileName.getBytes()));
        } catch (Exception e) {
        }

        realName = MimeUtility.decodeText(new String(realName.getBytes("8859_1"), "utf8"));
        System.out.println("RealName ::: " + realName);


        System.out.println("Downloaded Path ::: " + filePath);
        InputStream fis = new FileInputStream(filePath);
        ServletOutputStream outStream = response.getOutputStream();

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + URLEncoder.encode(realName, "UTF-8").replace("+", "%20") + "\"");

        IOUtil.copyCompletely(fis, outStream);
        outStream.flush();
        fis.close();

        if (fileName != null)
            new File(filePath).delete();

        return resultMap;
    }

    @RequestMapping(value = "/downloadInline.do", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> downloadInline(final HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        String mailTy = "";// Login.getMailTy(); // 1:zimbra, 2:owa
        String inlineName = request.getParameter("iname");
        String zmGwIp = "";// Login.getZmGwIp();
        String zmGwPort = "";// Login.getZmGwPort();

        String gurl = null;
        if ("1".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_inline_download.do?"
                    + request.getQueryString();
        } else if ("2".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_inline_download.do?"
                    + request.getQueryString();
        }

        // System.out.println("AUTH_TOKEN ::: " + AUTH_TOKEN);
        System.out.println("Download URL ::: " + gurl);
        System.out.println("Temp    Path ::: " + tempDir);

        Map<String, String> resultMap = new HashMap<String, String>();

        String fileName = fileUtil.downloadFile(gurl, tempDir);
        String filePath = tempDir + File.separator + inlineName;

        try {
            System.out.println("Downloaded Path ::: " + filePath);
            InputStream fis = new FileInputStream(filePath);
            ServletOutputStream outStream = response.getOutputStream();

            response.setHeader("Content-Disposition", "inline; filename=\""
                    + URLEncoder.encode(fileName, "UTF-8").replace("+", "%20") + "\"");

            IOUtil.copyCompletely(fis, outStream);
            outStream.flush();
            fis.close();

            if (fileName != null)
                new File(filePath).delete();
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return resultMap;
    }
}
