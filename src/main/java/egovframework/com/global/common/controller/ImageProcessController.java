package egovframework.com.global.common.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.common.domain.FileVO;
import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.common.service.FileMngService;
import egovframework.com.global.util.UAgentInfo;
import egovframework.com.global.util.fcc.service.OfficeFormBasedFileUtil;
import egovframework.com.global.util.sim.service.OfficeFileScrty;
import egovframework.com.global.util.wed.filter.DirectoryPathManager;


/**
 * @Class Name : ImageProcessController.java
 * @Description :
 * @Modification Information
 *
 *               수정일 수정자 수정내용 ---------- --------- ------------------- 2009.04.02 이삼섭 최초생성
 *               2014.03.31 유지보수 fileSn 오류수정 2019.07.09 suji.h 이미지파일 base64인코딩
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 *
 */
@Controller
public class ImageProcessController {

    @Resource(name = "FileMngService")
    private FileMngService fileService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageProcessController.class);

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param response
     * @throws Exception
     */
    @RequestMapping("/imagePreview.do")
    public void getImageInf(@RequestParam("atchFileId") String atchFileId,
            @RequestParam("fileSn") String fileSn, HttpServletResponse response) throws Exception {
        FileVO vo = new FileVO();
        vo.setAtchFileId(atchFileId);
        vo.setFileSn(fileSn);

        // ------------------------------------------------------------
        // fileSn이 없는 경우 마지막 파일 참조
        // ------------------------------------------------------------
        if (fileSn == null || fileSn.equals("")) {
            int newMaxFileSN = fileService.getMaxFileSN(vo);
            vo.setFileSn(Integer.toString(newMaxFileSN - 1));
        }
        // ------------------------------------------------------------

        FileVO fvo = fileService.selectFileInf(vo);

        String uploadDir = "";// PathUtil.getFullPathOfFile(fvo.getFileStreCours());
        String physical = fvo.getStreFileNm();

        OfficeFormBasedFileUtil.viewFile(response, uploadDir, physical, null);
    }

    /**
     * 업로드한 이미지를 base64로 인코딩한다
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/imageEncode.do")
    public void getImageInf(MultipartHttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final Map<String, MultipartFile> files = request.getFileMap();
        if (files == null) {
            LOGGER.error("The target file does not exist.");
        }

        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        while (itr.hasNext()) {
            Map.Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            String fileName = file.getOriginalFilename();

            if (org.apache.commons.lang.StringUtils.isBlank(fileName)) {
                continue;
            }

            StringBuilder dataUrl = new StringBuilder();

            String encodeData = "";
            try {
                encodeData = OfficeFileScrty.encodeBinary(file.getBytes());
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }

            if (StringUtils.isNotBlank(encodeData)) {
                dataUrl.append("data:");
                dataUrl.append(file.getContentType());
                dataUrl.append(";base64,");
                dataUrl.append(encodeData);
            }
            PrintWriter out = response.getWriter();
            out.print(dataUrl.toString());
            out.flush();
            out.close();
        }
    }

    /**
     * 업로드한 이미지를 File Url 형태로 리턴한다
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/imageFileUrl.do")
    public void getImageFileUrl(MultipartHttpServletRequest request, HttpServletResponse response)
            throws Exception {
        final Map<String, MultipartFile> files = request.getFileMap();
        if (files == null) {
            LOGGER.error("The target file does not exist.");
        }

        String fileUrl = null;
        Iterator<Map.Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        while (itr.hasNext()) {
            Map.Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            String fileName = file.getOriginalFilename();

            if (org.apache.commons.lang.StringUtils.isBlank(fileName)) {
                continue;
            }

            try {
                // cmm.image.url
                String imageDomain = GlobalsProperties.getProperty("cmm.image.url");

                if (StringUtils.isBlank(imageDomain)) {
                    imageDomain = "/downloadCkeditorImg.jsp";
                }

                // GET context path
                String contextPath = "";

                if (request.getServerName().contains("smartadmin")) {

                    UAgentInfo agent = new UAgentInfo(request.getHeader("user-agent"),
                            request.getHeader("Accept"));

                    if (agent.detectSmartphone())
                        contextPath = "https://" + request.getServerName() + ":1443"
                                + request.getContextPath();
                    else
                        contextPath = "https://" + request.getServerName() + ":443"
                                + request.getContextPath();
                } else
                    contextPath = request.getScheme() + "://" + request.getServerName() + ":"
                            + request.getServerPort() + request.getContextPath();

                imageDomain = contextPath + imageDomain;

                HttpSession session = request.getSession();
                Login Login = (Login) session.getAttribute("Login");

                // PathUtil pathUtil = new PathUtil();
                String imageBaseDir = "";// pathUtil.getBaseDirWithCompanyId();

                /*
                 * if(Login != null) { imageBaseDir = StringUtils.join(new String[] {imageBaseDir,
                 * Login.getCompanyId()}, ""); } else { //not find CompanyID imageBaseDir =
                 * StringUtils.join(new String[] {imageBaseDir, "unknown"}, ""); }
                 */

                // Get Application
                String app = request.getParameter("app");
                String appDir = "";
                if (app.equals(GlobalsProperties.getProperty("appCode.e-approval"))) {
                    // E-Approval
                    appDir = GlobalsProperties.getProperty("Globals.docFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.bbs"))) {
                    // Bulletin Board
                    appDir = GlobalsProperties.getProperty("Globals.bbsFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.e-document"))) {
                    // E-Folder
                    appDir = GlobalsProperties.getProperty("Globals.edmFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.e-mail"))) {
                    // E-Mail
                    appDir = GlobalsProperties.getProperty("Globals.emlFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.login"))) {
                    // Login
                    appDir = GlobalsProperties.getProperty("Globals.lgnFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.schedule"))) {
                    // Schedule
                    appDir = GlobalsProperties.getProperty("Globals.schFileStorePath");
                } else if (app.equals(GlobalsProperties.getProperty("appCode.task"))) {
                    // Task
                    appDir = GlobalsProperties.getProperty("Globals.tskFileStorePath");
                }

                // filename
                String subDir = File.separator + Login.getCompanyId() + appDir + File.separator
                        + DirectoryPathManager.getDirectoryPathByDateType(
                                DirectoryPathManager.DIR_DATE_TYPE.DATE_POLICY_YYYY_MM_DD);
                String saveFileName = RandomStringUtils.randomAlphanumeric(20) + "."
                        + StringUtils.lowerCase(StringUtils.substringAfterLast(fileName, "."));

                File newFile = new File(imageBaseDir + subDir + saveFileName);
                File fileToSave = DirectoryPathManager.getUniqueFile(newFile.getAbsoluteFile());

                try {
                    FileUtils.writeByteArrayToFile(fileToSave, file.getBytes());
                } catch (IOException e) {
                    // e.printStackTrace();
                    LOGGER.error(e.getMessage());
                }

                String savedFileName = FilenameUtils.getName(fileToSave.getAbsolutePath());
                fileUrl = imageDomain + "?subDir=" + StringUtils.replace(subDir, "\\", "/")
                        + "&savedFileName=" + savedFileName;

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            PrintWriter out = response.getWriter();
            out.print(fileUrl);
            out.flush();
            out.close();
        }
    }

}
