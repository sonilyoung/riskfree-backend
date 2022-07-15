package egovframework.com.global.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.com.global.DynamicContextHolder;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.common.domain.FileVO;
import egovframework.com.global.common.service.FileMngService;
import egovframework.com.global.util.BasicLogger;
import egovframework.com.global.util.ResourceCloseHelper;

/**
 * 파일 다운로드를 위한 컨트롤러 클래스
 * 
 * @author 공통서비스개발팀 이삼섭
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *     수정일      	수정자           수정내용
 *  ------------   --------    ---------------------------
 *   2009.03.25  	이삼섭          최초 생성
 *   2014.02.24		이기하          IE11 브라우저 한글 파일 다운로드시 에러 수정
 *
 * Copyright (C) 2009 by MOPAS  All right reserved.
 *      </pre>
 */
@Controller
public class FileDownloadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDownloadController.class);

    @Resource(name = "FileMngService")
    private FileMngService fileService;

    OfficeMessageSource officeMessageSource;

    /**
     * 브라우저 구분 얻기.
     *
     * @param request
     * @return
     */
    private String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        if (header.indexOf("MSIE") > -1) {
            return "MSIE";
        } else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
            return "Trident";
        } else if (header.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if (header.indexOf("Opera") > -1) {
            return "Opera";
        }
        return "Firefox";
    }

    /**
     * Disposition 지정하기.
     *
     * @param filename
     * @param request
     * @param response
     * @throws Exception
     */
    private void setDisposition(String filename, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String browser = getBrowser(request);

        String dispositionPrefix = "attachment; filename=";
        String encodedFilename = null;

        if (browser.equals("MSIE")) {
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
            encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Opera")) {
            encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < filename.length(); i++) {
                char c = filename.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            encodedFilename = sb.toString();
        } else {
            throw new IOException("Not supported browser");
        }

        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);

        if ("Opera".equals(browser)) {
            response.setContentType("application/octet-stream;charset=UTF-8");
        }
    }

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다.(내부접근URL)
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/internal/FileDown.do")
    public void cvplFileDownloadInInternal(@RequestParam Map<String, Object> commandMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // For mobile
        /*
         * if(StringUtils.isNotBlank(request.getParameter("orgnztCd"))) { String companyCd =
         * request.getParameter("orgnztCd"); DynamicContextHolder.setDynamicType(companyCd); }
         */

        String atchFileId = (String) commandMap.get("atchFileId");
        String fileSn = (String) commandMap.get("fileSn");
        String tempF = (String) commandMap.get("tempF");
        String isMac = (String) commandMap.get("isMac");

        Boolean isAuthenticated = null;// OfficeUserInfoHelper.isAuthenticated();
        if (isAuthenticated) {
            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(atchFileId);
            fileVO.setFileSn(fileSn);

            FileVO fvo = new FileVO();
            if ("1".equals(tempF)) {
                fvo = fileService.selectTmpFileInf(fileVO);
            } else {
                fvo = fileService.selectFileInf(fileVO);
            }

            File uFile = new File("");
            // PathUtil.getFullPathOfFile(fvo.getFileStreCours()), fvo.getStreFileNm());
            if (uFile.exists()) {
                BufferedInputStream in = null;
                BufferedOutputStream out = null;

                try {
                    // DRM적용
                    String mimetype = "application/x-msdownload";
                    response.setContentType(mimetype);
                    setDisposition(fvo.getOrignlFileNm(), request, response);

                    if (fvo.getDrmF() == 0 || "1".equals(isMac)) { // 업로드시 drm 미적용 파일일 경우 or Mac 유저인
                                                                   // 경우
                        LOGGER.info("File is Decrypted.");
                        in = new BufferedInputStream(new FileInputStream(uFile));
                        out = new BufferedOutputStream(response.getOutputStream());
                        FileCopyUtils.copy(in, out);
                    } else { // 업로드시 drm 적용 파일일 경우
                        LOGGER.info("File is Encrypted.");
                        int resultEncInt = 0;// drmUtil.streamDrmEnc(uFile,
                                             // response.getOutputStream());
                        if (resultEncInt != 0) {
                            // TODO : DRM ENC Error...
                        }
                    }
                } catch (IOException ex) {
                    // 다음 Exception 무시 처리
                    // Connection reset by peer: socket write error
                    BasicLogger.ignore("IO Exception", ex);
                } finally {
                    ResourceCloseHelper.close(in, out);
                }

            } else {
                response.setContentType("application/x-msdownload");
                PrintWriter printwriter = response.getWriter();

                printwriter.println("<html>");
                printwriter.println("<br><br><br><h2>Could not get file name:<br>"
                        + fvo.getOrignlFileNm() + "</h2>");
                printwriter.println(
                        "<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
                printwriter.println("<br><br><br>&copy; webAccess");
                printwriter.println("</html>");
                printwriter.flush();
                printwriter.close();
            }
        }
    }

    /**
     * 첨부파일로 등록된 파일에 대하여 다운로드를 제공한다. (외부접근URL)
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/fms/FileDown.do")
    public void cvplFileDownloadInExternal(@RequestParam Map<String, Object> commandMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // For mobile
        if (StringUtils.isNotBlank(request.getParameter("orgnztCd"))) {
            String companyCd = request.getParameter("orgnztCd");
            DynamicContextHolder.setDynamicType(companyCd);
        }

        String atchFileId = (String) commandMap.get("atchFileId");
        String fileSn = (String) commandMap.get("fileSn");
        String tempF = (String) commandMap.get("tempF");
        String isMac = (String) commandMap.get("isMac");

        Boolean isAuthenticated = null;// OfficeUserInfoHelper.isAuthenticated();

        if (isAuthenticated) {

            FileVO fileVO = new FileVO();
            fileVO.setAtchFileId(atchFileId);
            fileVO.setFileSn(fileSn);

            FileVO fvo = new FileVO();
            if ("1".equals(tempF)) {
                fvo = fileService.selectTmpFileInf(fileVO);
            } else {
                fvo = fileService.selectFileInf(fileVO);
            }

            File uFile = new File("");
            // PathUtil.getFullPathOfFile(fvo.getFileStreCours()), fvo.getStreFileNm());
            /*
             * long fSize = uFile.length();
             * 
             * if (fSize > 0) {
             */
            if (uFile.exists()) {

                // DRM적용 전
                /*
                 * String mimetype = "application/x-msdownload"; response.setContentType(mimetype);
                 * setDisposition(fvo.getOrignlFileNm(), request, response);
                 */

                // response.setHeader("Content-Disposition", "attachment; filename=\"" +
                // URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
                // response.setContentLength(fSize);
                // response.setBufferSize(fSize); // OutOfMemeory 발생

                /*
                 * FileCopyUtils.copy(in, response.getOutputStream()); in.close();
                 * response.getOutputStream().flush(); response.getOutputStream().close();
                 */
                BufferedInputStream in = null;
                BufferedOutputStream out = null;

                try {
                    // DRM적용 전
                    /*
                     * in = new BufferedInputStream(new FileInputStream(uFile)); out = new
                     * BufferedOutputStream(response.getOutputStream());
                     * 
                     * FileCopyUtils.copy(in, out); out.flush();
                     */

                    // DRM적용
                    String mimetype = "application/x-msdownload";
                    response.setContentType(mimetype);
                    setDisposition(fvo.getOrignlFileNm(), request, response);

                    if (fvo.getDrmF() == 0 || "1".equals(isMac)) { // 업로드시 drm 미적용 파일일 경우 or Mac 유저인
                                                                   // 경우
                        LOGGER.info("File is Decrypted.");
                        in = new BufferedInputStream(new FileInputStream(uFile));
                        out = new BufferedOutputStream(response.getOutputStream());
                        FileCopyUtils.copy(in, out);
                    } else { // 업로드시 drm 적용 파일일 경우
                        LOGGER.info("File is Encrypted.");
                        int resultEncInt = 0;// drmUtil.streamDrmEnc(uFile,
                                             // response.getOutputStream());
                        if (resultEncInt != 0) {
                            // TODO : DRM ENC Error...
                        }
                    }

                } catch (IOException ex) {
                    // 다음 Exception 무시 처리
                    // Connection reset by peer: socket write error
                    BasicLogger.ignore("IO Exception", ex);
                } finally {
                    ResourceCloseHelper.close(in, out);
                }

            } else {
                response.setContentType("application/x-msdownload");

                PrintWriter printwriter = response.getWriter();

                printwriter.println("<html>");
                printwriter.println("<br><br><br><h2>Could not get file name:<br>"
                        + fvo.getOrignlFileNm() + "</h2>");
                printwriter.println(
                        "<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
                printwriter.println("<br><br><br>&copy; webAccess");
                printwriter.println("</html>");

                printwriter.flush();
                printwriter.close();
            }
        }
    }

    /**
     * URL 링크로 등록된 파일에 대하여 다운로드를 제공한다.
     *
     * @param commandMap
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/cmm/fms/UrlLinkFileDown.do")
    public void UrlLinkFileDown(@RequestParam Map<String, Object> commandMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fileUrlLink = (String) commandMap.get("fileUrlLink");

        // Boolean isAuthenticated = OfficeUserInfoHelper.isAuthenticated();
        Boolean isAuthenticated = true;

        if (isAuthenticated) {
            FileVO fvo = null; // fileService.selectFileInfByUrlLink(fileUrlLink);
            File uFile = new File("");
            // PathUtil.getFullPathOfFile(fvo.getFileStreCours()), fvo.getStreFileNm());
            /*
             * long fSize = uFile.length();
             * 
             * if (fSize > 0) {
             */
            if (uFile.exists()) {
                String mimetype = "application/x-msdownload";

                // response.setBufferSize(fSize); // OutOfMemeory 발생
                response.setContentType(mimetype);
                // response.setHeader("Content-Disposition", "attachment; filename=\"" +
                // URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
                setDisposition(fvo.getOrignlFileNm(), request, response);
                // response.setContentLength(fSize);

                /*
                 * FileCopyUtils.copy(in, response.getOutputStream()); in.close();
                 * response.getOutputStream().flush(); response.getOutputStream().close();
                 */
                BufferedInputStream in = null;
                BufferedOutputStream out = null;

                try {
                    if (fvo.getDrmF() == 0) { // 업로드시 drm 미적용 파일일 경우
                        LOGGER.info("File is Decrypted.");
                        in = new BufferedInputStream(new FileInputStream(uFile));
                        out = new BufferedOutputStream(response.getOutputStream());
                        FileCopyUtils.copy(in, out);
                    } else { // 업로드시 drm 적용 파일일 경우
                        LOGGER.info("File is Encrypted.");
                        int resultEncInt = 0;// drmUtil.streamDrmEnc(uFile,
                                             // response.getOutputStream());
                        if (resultEncInt != 0) {
                            // TODO : DRM ENC Error...
                        }
                    }
                } catch (IOException ex) {
                    // 다음 Exception 무시 처리
                    // Connection reset by peer: socket write error
                    BasicLogger.ignore("IO Exception", ex);
                } finally {
                    ResourceCloseHelper.close(in, out);
                }

            } else {
                response.setContentType("application/x-msdownload");

                PrintWriter printwriter = response.getWriter();

                printwriter.println("<html>");
                printwriter.println("<br><br><br><h2>Could not get file name:<br>"
                        + fvo.getOrignlFileNm() + "</h2>");
                printwriter.println(
                        "<br><br><br><center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
                printwriter.println("<br><br><br>&copy; webAccess");
                printwriter.println("</html>");

                printwriter.flush();
                printwriter.close();
            }
        }
    }

    // 맥 유저 DRM 파일 다운로드 시 암호확인 모달 로드
    @RequestMapping(value = "/viewModalPasswordForMacUser.do")
    public String viewModalFileNmModify(Model model, FileVO fileVO) throws Exception {
        // Spring Security 사용자권한 처리
        Boolean isAuthenticated = null;// OfficeUserInfoHelper.isAuthenticated();
        if (!isAuthenticated) {
            model.addAttribute("message", officeMessageSource.getMessage("fail.common.login"));
            return "office/com/logn/OfficeLoginUsr";
        }

        model.addAttribute("atchFileId", fileVO.getAtchFileId());
        model.addAttribute("fileSn", fileVO.getFileSn());

        return "office/com/cmm/fms/ModalPasswordForMacUser";
    }

    @RequestMapping(value = "/internal/FilePreview.do")
    public String cvplFilePreview(@RequestParam Map<String, Object> commandMap,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, Object> map = null;
        String atchFileId = (String) commandMap.get("atchFileId");
        String fileSn = (String) commandMap.get("fileSn");

        LOGGER.info("fileId  ]" + atchFileId + "[");
        LOGGER.info("fileSn  ]" + fileSn + "[");

        // map = pdfDocumentService.getPDFLinkUrl2ByIdSn(atchFileId, fileSn);

        LOGGER.info("urlLink      ]" + map.get("pdfLinkUrl") + "[");
        LOGGER.info("orignlFileNm ]" + map.get("orignlFileNm") + "[");

        if ("none".equals(map.get("pdfLinkUrl"))) {
            LOGGER.error("no_such_file");
            LOGGER.error("fileId   ]" + atchFileId + "[");
            LOGGER.error("fileSn   ]" + fileSn + "[");
            LOGGER.error("ErrorMsg ]" + map.get("orignlFileNm") + "[");
            return "redirect:/pdfjs/web/viewer.html?file=/file/no_such_file.pdf?"
                    + map.get("orignlFileNm");
        } else {
            return "redirect:/pdfjs/web/viewer.html?file=" + map.get("pdfLinkUrl") + "?"
                    + map.get("orignlFileNm");
        }
    }
}
