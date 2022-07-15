/*
 * CKEditor image upload module for Java. Copyright guavatak
 * (https://github.com/guavatak/ckeditor-upload-filter-java)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @author guavatak (https://github.com/guavatak/ckeditor-upload-filter-java)
 */
package egovframework.com.global.util.wed.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.util.sim.service.OfficeFileScrty;

/**
 * Created by guava on 1/20/14. 이미지 저장 처리 클래스
 * 
 * @author guavatak
 * @since 2014.12.04
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일        수정자       수정내용
 *  -------       --------    ---------------------------
 *   2014.12.04	표준프레임워크	최초 적용 (패키지 변경 및 소스 정리)
 *      </pre>
 */
public class CkImageSaver {
    private static final Log log = LogFactory.getLog(CkFilter.class);

    private static final String FUNC_NO = "CKEditorFuncNum";

    private String imageBaseDir;
    private String imageDomain;
    private String[] allowFileTypeArr;

    private FileSaveManager fileSaveManager;

    public CkImageSaver(String imageDomain, String[] allowFileTypeArr, String saveManagerClass) {
        /*
         * this.imageBaseDir = imageBaseDir; if (imageBaseDir.endsWith("/")) {
         * StringUtils.removeEnd(imageBaseDir, "/"); } if (imageBaseDir.endsWith("\\")) {
         * StringUtils.removeEnd(imageBaseDir, "\\"); }
         */

        this.imageDomain = imageDomain;
        if (imageDomain.endsWith("/")) {
            StringUtils.removeEnd(imageDomain, "/");
        }

        this.allowFileTypeArr = allowFileTypeArr;

        if (StringUtils.isBlank(saveManagerClass)) {
            fileSaveManager = new DefaultFileSaveManager();
        } else {
            try {
                Class<?> klass = Class.forName(saveManagerClass);
                fileSaveManager = (FileSaveManager) klass.newInstance();
            } catch (ClassNotFoundException e) {
                log.error(e);
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                log.error(e);
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                log.error(e);
                throw new RuntimeException(e);
            }
        }
    }

    public void saveAndReturnUrlToClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Parse the request
        try {
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> /* FileItem */ items = upload.parseRequest(request);
            // We upload just one file at the same time
            FileItem uplFile = items.get(0);

            String relUrl = null;

            // GET context path
            // String contextPath = request.getContextPath();
            String contextPath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + request.getContextPath();

            HttpSession session = request.getSession();
            Login Login = (Login) session.getAttribute("Login");

            /*
             * PathUtil pathUtil = new PathUtil(); imageBaseDir =
             * pathUtil.getBaseDirWithCompanyId();
             * 
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

            if (isAllowFileType(FilenameUtils.getName(uplFile.getName()))) {
                relUrl = fileSaveManager.saveFile(uplFile, imageBaseDir, contextPath + imageDomain,
                        appDir);
            }

            printClient(request, response, relUrl);

        } catch (FileUploadException e) {
            log.error(e);
        }
    }

    protected boolean isAllowFileType(String fileName) {
        boolean isAllow = false;
        if (allowFileTypeArr != null && allowFileTypeArr.length > 0) {
            for (String allowFileType : allowFileTypeArr) {
                if (StringUtils.equalsIgnoreCase(allowFileType,
                        StringUtils.substringAfterLast(fileName, "."))) {
                    isAllow = true;
                    break;
                }
            }
        } else {
            isAllow = true;
        }

        return isAllow;
    }

    public void returnEncodeDataToClient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Parse the request
        try {
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            List<FileItem> /* FileItem */ items = upload.parseRequest(request);
            // We upload just one file at the same time
            FileItem uplFile = items.get(0);

            StringBuilder dataUrl = new StringBuilder();

            if (isAllowFileType(FilenameUtils.getName(uplFile.getName()))) {
                String encodeData = "";
                try {
                    encodeData = OfficeFileScrty.encodeBinary(uplFile.get());
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

                if (StringUtils.isNotBlank(encodeData)) {
                    dataUrl.append("data:");
                    dataUrl.append(uplFile.getContentType());
                    dataUrl.append(";base64,");
                    dataUrl.append(encodeData);
                }
            }

            printClient(request, response, dataUrl.toString());

        } catch (FileUploadException e) {
            log.error(e);
        }
    }

    public void printClient(HttpServletRequest request, HttpServletResponse response,
            String dataUrl) throws IOException {
        String errorMessage = null;
        if (StringUtils.isBlank(dataUrl)) {
            errorMessage = "Restricted Image Format";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<script type=\"text/javascript\">\n");
        // Compressed version of the document.domain automatic fix script.
        // The original script can be found at [fckeditor_dir]/_dev/domain_fix_template.js
        // sb.append("(function(){var d=document.domain;while (true){try{var
        // A=window.parent.document.domain;break;}catch(e) {};d=d.replace(/.*?(?:\\.|$)/,'');if
        // (d.length==0) break;try{document.domain=d;}catch (e){break;}}})();\n");
        sb.append("window.parent.CKEDITOR.tools.callFunction(")
                .append(request.getParameter(FUNC_NO)).append(", '");
        if (StringUtils.isNotBlank(dataUrl)) {
            sb.append(dataUrl);
        }
        if (errorMessage != null) {
            sb.append("', '").append(errorMessage);
        }
        sb.append("');\n </script>");

        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();

        out.print(sb.toString());
        out.flush();
        out.close();
    }



}
