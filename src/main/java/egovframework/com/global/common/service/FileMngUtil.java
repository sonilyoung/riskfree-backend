package egovframework.com.global.common.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.domain.org.service.UserManageService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.common.domain.FileVO;
import egovframework.com.global.common.domain.Globals;
import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.genid.GenIdService;
import egovframework.com.global.util.ResourceCloseHelper;
import egovframework.com.global.util.WebUtil;
import egovframework.com.global.util.fcc.service.OfficeStringUtil;

/**
 * @Class Name : FileMngUtil.java
 * @Description : 첨부파일 업로드, 다운로드 처리 관련 유틸리티
 * @Modification Information
 *
 *               수정일 수정자 수정내용 ------- -------- ---------------------------- 2009.02.13 이삼섭 최초 생성
 *               2011.08.09 서준식 utl.fcc패키지와 Dependency제거를 위해 getTimeStamp()메서드 추가 2017.06.01 suji.h
 *               공통 File처리를 위한 deleteFiles(), uploadInsertFiles(), uploadUpdateFiles()메서드 추가
 * 
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 02. 13
 * @version 1.0
 * @see
 *
 */
@Component("FileMngUtil")
public class FileMngUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileMngUtil.class);

    public static final int BUFF_SIZE = 2048;

    private GenIdService genIdService;

    @Resource(name = "FileMngService")
    private FileMngService fileMngService;

    @Autowired
    private UserManageService userService;

    /**
     * 첨부파일에 대한 목록 정보를 취득한다.
     * 
     * @param files
     * @param KeyStr
     * @param fileKeyParam
     * @param atchFileId
     * @param storePath
     * @param fileName
     * @return
     * @throws Exception
     */
    public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr,
            int fileKeyParam, String atchFileId, String storePath, String fileName,
            Boolean multiFlag) throws Exception {
        Login Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
        return _parseFileInf(files, KeyStr, fileKeyParam, atchFileId, storePath, fileName,
                multiFlag, Login);
    }

    public List<FileVO> parseFileInfMbl(Map<String, MultipartFile> files, String KeyStr,
            int fileKeyParam, String atchFileId, String storePath, String fileName,
            Boolean multiFlag, Login Login) throws Exception {
        return _parseFileInf(files, KeyStr, fileKeyParam, atchFileId, storePath, fileName,
                multiFlag, Login);
    }

    public List<FileVO> _parseFileInf(Map<String, MultipartFile> files, String KeyStr,
            int fileKeyParam, String atchFileId, String storePath, String fileName,
            Boolean multiFlag, Login Login) throws Exception {
        int fileKey = fileKeyParam;
        String storePathString = "";
        String atchFileIdString = "";
        String companyId = "";
        List<FileVO> result = new ArrayList<FileVO>();

        if (files == null) {
            FileVO fileId = new FileVO();
            fileId.setAtchFileId(atchFileIdString);
            result.add(0, fileId);
            return result;
        }

        try {
            // jojo UserVO user = userService.getUser(Login.getUserUniqId());
            companyId = "";// Login.getCompanyId(); // user.getCompanyId().toString();
            // String strMaxAllowAttachSize = userService.selectCompanyFileSize(Login);
        } catch (ClassCastException ccs) {
            companyId = "ORGNZT_0000000000006"; // By David for TMS
        }

        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        String filePath = "";
        FileVO fvo;
        while (itr.hasNext()) {
            if ("".equals(storePath) || storePath == null) {
                storePathString = GlobalsProperties.getProperty(Globals.UPLOAD_FILE_PATH);
            } else {
                storePathString = GlobalsProperties.getProperty(storePath);
            }

            storePathString = companyId + storePathString;// + PathUtil.calcPath();

            if ("".equals(atchFileId) || atchFileId == null) {
                atchFileIdString = genIdService.selectFileNextId();
            } else {
                atchFileIdString = atchFileId;
            }

            String fileModData = "";
            if (StringUtils.isNotBlank(fileName)) {
                fileModData = OfficeStringUtil.modStr(fileName).toString();
            } else {
                fileModData = OfficeStringUtil.modStr(atchFileIdString).toString();
            }

            if (StringUtils.isNotBlank(fileModData)) {
                storePathString = storePathString + fileModData + File.separator;
            }

            File saveFolder = new File("");
            // new File(PathUtil.getFullPathOfFile(WebUtil.filePathBlackList(storePathString)));
            if (!saveFolder.exists() || saveFolder.isFile()) {
                saveFolder.mkdirs();
            }

            Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            String orginFileName = file.getOriginalFilename();

            LOGGER.info("*********** BEFORE orginFileName ]" + orginFileName + "[");
            if (orginFileName.indexOf('|') > 0) {
                orginFileName = orginFileName.substring(47, orginFileName.length());
                LOGGER.info("*********** AFTER  orginFileName ]" + orginFileName + "[");
            }

            // --------------------------------------
            // 원 파일명이 없는 경우 처리
            // (첨부가 되지 않은 input file type)
            // --------------------------------------
            if ("".equals(orginFileName)) {
                continue;
            }
            //// ------------------------------------

            int index = orginFileName.lastIndexOf(".");
            String fileExt = orginFileName.substring(index + 1);
            String newName = "";
            if (StringUtils.isNotBlank(fileName)) {
                newName = fileName + "_" + fileKey + "_att";
            } else if (StringUtils.isNotBlank(KeyStr)) {
                newName = KeyStr + getTimeStamp() + "_" + fileKey + "_att";
            } else {
                newName = atchFileIdString + "_" + fileKey + "_att";
            }
            long size = file.getSize();

            fvo = new FileVO();
            if (!"".equals(orginFileName)) {
                filePath = saveFolder.getPath() + File.separator + newName;

                // DRM적용
                // int drmChkInt = drmUtil.streamDrmFileCheck(file);
                int drmChkInt = -1;
                if (drmChkInt == 0) { // 0:Drm Encrypt File
                    // DRM Decrypt
                    fvo.setDrmF(1);
                    int resultDecInt = -1;// drmUtil.streamDrmDec(file,
                                          // WebUtil.filePathBlackList(filePath));
                    if (resultDecInt != 0) {
                        // Error...
                        // TODO : DRM DEC Error...
                    }
                } else { // 그 외 : Original File, 또는 에러
                    fvo.setDrmF(0);
                    file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
                }
            }

            fvo.setFileExtsn(fileExt);
            fvo.setFileStreCours(saveFolder.getPath());
            fvo.setFileMg(Long.toString(size));
            fvo.setOrignlFileNm(orginFileName);
            fvo.setStreFileNm(newName);
            fvo.setAtchFileId(atchFileIdString);
            fvo.setFileSn(String.valueOf(fileKey));

            result.add(fvo);

            fileKey++;
        }

        return result;
    }

    public List<FileVO> parseFileInf_multi(Map<String, MultipartFile> files, String KeyStr,
            int fileKeyParam, String atchFileId, String storePath, String fileName,
            Boolean multiFlag) throws Exception {
        int fileKey = fileKeyParam;
        String storePathString = "";
        String atchFileIdString = "";
        String companyId = "";
        long lmaxAllowAttachSize = 0;
        Login Login = null;


        List<FileVO> result = new ArrayList<FileVO>();
        if (files == null) {
            FileVO fileId = new FileVO();
            fileId.setAtchFileId(atchFileIdString);
            result.add(0, fileId);

            return result;
        }

        try {
            Login = null;// (Login) OfficeUserInfoHelper.getAuthenticatedUser();
            // UserVO user = userService.getUser(Login.getUserUniqId());
            companyId = "";// Login.getCompanyId(); // .getCompanyId().toString();
        } catch (ClassCastException ccs) {
            companyId = "ORGNZT_0000000000006"; // By David for TMS
            Login = new Login();
            // Login.setCompanyId(companyId);
            /*
             * 2019-07-06 22:22:40,565 ERROR [egovframework.com.domain.tms.service.InnerTmsService]
             * java.lang.String cannot be cast to egovframework.com.domain.portal.logn.service.Login
             * java.lang.ClassCastException: java.lang.String cannot be cast to
             * egovframework.com.domain.portal.logn.service.Login
             */
        }

        String strMaxAllowAttachSize = null;// userService.selectCompanyFileSize(Login);
        lmaxAllowAttachSize = Long.parseLong(strMaxAllowAttachSize) * 1024 * 1024;

        if ("".equals(storePath) || storePath == null) {
            storePathString = GlobalsProperties.getProperty(Globals.UPLOAD_FILE_PATH);
        } else {
            storePathString = GlobalsProperties.getProperty(storePath);
        }

        storePathString = companyId + storePathString;// + PathUtil.calcPath();
        LOGGER.info("*********** storePathString ]" + storePathString + "[");

        // 다중 파일 작성 시 반복문 안에 존재하는 코드 반복문 밖으로 꺼냄
        // 이유는 반복문 안에 있을 시 atchFileId 값이 증가하여
        // DAO 처리 과정에서 에러가 발생함.
        if ("".equals(atchFileId) || atchFileId == null) {
            atchFileIdString = genIdService.selectFileNextId();
        } else {
            atchFileIdString = atchFileId;
        }
        LOGGER.info("*********** atchFileIdString ]" + atchFileIdString + "[");

        String fileModData = "";
        if (StringUtils.isNotBlank(fileName)) {
            fileModData = OfficeStringUtil.modStr(fileName).toString();
        } else {
            fileModData = OfficeStringUtil.modStr(atchFileIdString).toString();
        }

        if (StringUtils.isNotBlank(fileModData)) {
            storePathString = storePathString + fileModData + File.separator;
        }
        LOGGER.info("*********** storePathString ]" + storePathString + "[");

        File saveFolder = new File("");
        // new File(PathUtil.getFullPathOfFile(WebUtil.filePathBlackList(storePathString)));
        if (!saveFolder.exists() || saveFolder.isFile()) {
            saveFolder.mkdirs();
        }

        Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
        MultipartFile file;
        String filePath = "";
        FileVO fvo;
        while (itr.hasNext()) {
            Entry<String, MultipartFile> entry = itr.next();

            file = entry.getValue();
            String orginFileName = file.getOriginalFilename();

            // --------------------------------------
            // 원 파일명이 없는 경우 처리
            // (첨부가 되지 않은 input file type)
            // --------------------------------------
            if ("".equals(orginFileName)) {
                continue;
            }
            //// ------------------------------------

            int index = orginFileName.lastIndexOf(".");
            String fileExt = orginFileName.substring(index + 1);
            String newName = "";
            if (StringUtils.isNotBlank(fileName)) {
                newName = fileName + "_" + fileKey + "_att";
            } else if (StringUtils.isNotBlank(KeyStr)) {
                newName = KeyStr + getTimeStamp() + "_" + fileKey + "_att";
            } else {
                newName = orginFileName;
            }
            LOGGER.info("*********** newName ]" + newName + "[");

            long size = file.getSize();

            // By David 19.06.20
            if (size > lmaxAllowAttachSize) {
                continue;
            }

            fvo = new FileVO();
            if (!"".equals(orginFileName)) {
                filePath = saveFolder.getPath() + File.separator + newName;
                LOGGER.info("*********** filePath ]" + filePath + "[");
                LOGGER.info("*********** WebUtil.filePathBlackList(filePath) ]"
                        + WebUtil.filePathBlackList(filePath) + "[");

                // DRM적용
                int drmChkInt = -1;// drmUtil.streamDrmFileCheck(file);
                if (drmChkInt == 0) { // 0:Drm Encrypt File
                    // DRM Decrypt
                    fvo.setDrmF(1);
                    int resultDecInt = -1;// drmUtil.streamDrmDec(file,
                                          // WebUtil.filePathBlackList(filePath));
                    if (resultDecInt != 0) {
                        // Error...
                        // TODO : DRM DEC Error...
                    }
                } else { // 그 외 : Original File, 또는 에러
                    fvo.setDrmF(0);
                    file.transferTo(new File(WebUtil.filePathBlackList(filePath)));
                }
            }

            LOGGER.info("*********** fileExt            ]" + fileExt + "[");
            LOGGER.info("*********** saveFolder.getPath ]" + saveFolder.getPath() + "[");
            LOGGER.info("*********** orginFileName      ]" + orginFileName + "[");
            LOGGER.info("*********** newName            ]" + newName + "[");
            LOGGER.info("*********** atchFileIdString   ]" + atchFileIdString + "[");
            LOGGER.info("*********** fileKey            ]" + fileKey + "[");

            fvo.setFileExtsn(fileExt);
            fvo.setFileStreCours(saveFolder.getPath());
            fvo.setFileMg(Long.toString(size));
            fvo.setOrignlFileNm(orginFileName);
            fvo.setStreFileNm(newName);
            fvo.setAtchFileId(atchFileIdString);
            fvo.setFileSn(String.valueOf(fileKey));

            result.add(fvo);

            fileKey++;
        }

        return result;
    }

    /**
     * 첨부파일을 서버에 저장한다. 호출하는 부분이 없는 걸 보니 사용하지 않는다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeUploadedFile(MultipartFile file, String newName, String stordFilePath)
            throws Exception {
        InputStream stream = null;
        OutputStream bos = null;

        try {
            stream = file.getInputStream();
            File cFile = new File(stordFilePath);

            if (!cFile.isDirectory()) {
                boolean _flag = cFile.mkdir();
                if (!_flag) {
                    throw new IOException("Directory creation Failed ");
                }
            }

            String filePath = stordFilePath + File.separator + newName;

            // DRM적용
            // TODO : DRM FileChk 부분 확인. - pass
            int drmChkInt = -1;// drmUtil.streamDrmFileCheck(file);
            if (drmChkInt == 0) { // 0:Drm Encrypt File, 1:Original File
                // DRM Decrypt
                int resultDecInt = -1;// drmUtil.streamDrmDec(file,
                                      // WebUtil.filePathBlackList(filePath));
                if (resultDecInt != 0) {
                    // Error...
                    // TODO : DRM DEC Error...
                }
            } else if (drmChkInt == 1) { // 0:Drm Encrypt File, 1:Original File
                bos = new FileOutputStream(filePath);
                int bytesRead = 0;
                byte[] buffer = new byte[BUFF_SIZE];
                while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            } else {
                // TODO : DRM FileChk Error...
                // Temporary...
                // drmUtil.streamDrmDec(file, WebUtil.filePathBlackList(filePath));
            }
        } finally {
            ResourceCloseHelper.close(bos, stream);
        }
    }

    /**
     * 첨부로 등록된 파일을 서버에 업로드한다. 호출하는 부분이 없는 걸 보니 사용하지 않는다.
     * 
     * @param file
     * @return
     * @throws Exception
     */
    public HashMap<String, String> uploadFile(MultipartFile file) throws Exception {
        HashMap<String, String> map = new HashMap<String, String>();
        // Write File 이후 Move File????
        String newName = "";
        String stordFilePath = GlobalsProperties.getProperty("Globals.uploadFileStorePath");
        String orginFileName = file.getOriginalFilename();

        int index = orginFileName.lastIndexOf(".");
        // String fileName = orginFileName.substring(0, _index);
        String fileExt = orginFileName.substring(index + 1);
        long size = file.getSize();

        // newName 은 Naming Convention에 의해서 생성
        newName = getTimeStamp(); // 2012.11 KISA 보안조치
        writeFile(file, newName, stordFilePath);
        // storedFilePath는 지정
        map.put(Globals.ORIGIN_FILE_NM, orginFileName);
        map.put(Globals.UPLOAD_FILE_NM, newName);
        map.put(Globals.FILE_EXT, fileExt);
        map.put(Globals.FILE_PATH, stordFilePath);
        map.put(Globals.FILE_SIZE, String.valueOf(size));

        return map;
    }

    /**
     * 파일을 실제 물리적인 경로에 생성한다.
     * 
     * @param file
     * @param newName
     * @param stordFilePath
     * @throws Exception
     */
    protected void writeFile(MultipartFile file, String newName, String stordFilePath)
            throws Exception {
        InputStream stream = null;
        OutputStream bos = null;

        try {
            stream = file.getInputStream();
            File cFile = new File(WebUtil.filePathBlackList(stordFilePath));
            if (!cFile.isDirectory())
                cFile.mkdir();

            String filePath = stordFilePath + File.separator + newName;

            // DRM적용
            // TODO : DRM FileChk 부분 확인. - pass
            int drmChkInt = -1;// drmUtil.streamDrmFileCheck(file);
            if (drmChkInt == 0) { // 0:Drm Encrypt File, 1:Original File
                // DRM Decrypt
                int resultDecInt = -1;// drmUtil.streamDrmDec(file,
                                      // WebUtil.filePathBlackList(filePath));
                if (resultDecInt != 0) {
                    // Error...
                    // TODO : DRM DEC Error...
                }
            } else if (drmChkInt == 1) { // 0:Drm Encrypt File, 1:Original File
                bos = new FileOutputStream(WebUtil.filePathBlackList(filePath));
                int bytesRead = 0;
                byte[] buffer = new byte[BUFF_SIZE];
                while ((bytesRead = stream.read(buffer, 0, BUFF_SIZE)) != -1) {
                    bos.write(buffer, 0, bytesRead);
                }
            } else {
                // TODO : DRM FileChk Error...
                // Temporary...
                // drmUtil.streamDrmDec(file, WebUtil.filePathBlackList(filePath));
            }
        } finally {
            ResourceCloseHelper.close(bos, stream);
        }
    }

    /**
     * 서버의 파일을 다운로드한다.
     * 
     * @param request
     * @param response
     * @throws Exception
     */
    public static boolean downFile(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean isSuccess = false;

        String downFileName = request.getAttribute("downFile") == null ? ""
                : (String) request.getAttribute("downFile");
        if (downFileName == null || "".equals(downFileName))
            downFileName = request.getParameter("downFile") == null ? ""
                    : request.getParameter("downFile");
        String orgFileName = request.getAttribute("orgFileName") == null ? ""
                : (String) request.getAttribute("orgFileName");
        if (orgFileName == null || "".equals(orgFileName))
            orgFileName = request.getParameter("orgFileName") == null ? ""
                    : request.getParameter("orgFileName");
        orgFileName = orgFileName.replaceAll("\r", "").replaceAll("\n", "");

        File file = new File(WebUtil.filePathBlackList(downFileName));
        if (!file.exists()) {
            LOGGER.info("The File is not existed.");
            throw new FileNotFoundException(downFileName);
        }
        if (!file.isFile()) {
            LOGGER.info("The File is not a file.");
            throw new FileNotFoundException(downFileName);
        }

        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition:",
                "attachment; filename=" + new String(orgFileName.getBytes(), "UTF-8"));
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");

        BufferedInputStream fin = null;
        BufferedOutputStream outs = null;
        byte[] buffer = new byte[BUFF_SIZE]; // buffer size 2K.
        try {
            /*
             * TODO : DRM FileChk 부분 확인. - pass DrmUtil anotherDrmUtil = new DrmUtil(); int
             * drmChkInt = anotherDrmUtil.streamDrmFileCheck(file); if (drmChkInt == 0) { // 0:Drm
             * Encrypt File, 1:Original File LOGGER.info("File is Encrypted."); fin = new
             * BufferedInputStream(new FileInputStream(file)); outs = new
             * BufferedOutputStream(response.getOutputStream()); int read = 0; while ((read =
             * fin.read(buffer)) != -1) { outs.write(buffer, 0, read); } outs.flush(); } else if
             * (drmChkInt == 1) { // 0:Drm Encrypt File, 1:Original File
             * LOGGER.info("File is Not Encrypted."); int resultEncInt =
             * anotherDrmUtil.streamDrmEnc(file, response.getOutputStream()); if (resultEncInt != 0)
             * { // TODO : DRM ENC Error... } } else { // TODO : DRM FileChk Error... //
             * Temporary... anotherDrmUtil.streamDrmEnc(file, response.getOutputStream()); }
             */
            isSuccess = true;
        } finally {
            ResourceCloseHelper.close(outs, fin);
        }

        return isSuccess;
    }

    /**
     * 서버 파일에 대하여 다운로드를 처리한다.
     * 
     * @param response
     * @param downFileName 파일저장 경로가 포함된 형태
     * @param orgFileName
     * @throws Exception
     */
    public boolean downFile(HttpServletResponse response, String downFileName, String orgFileName)
            throws Exception {
        boolean isSuccess = false;

        File file = new File(downFileName);
        if (!file.exists())
            throw new FileNotFoundException(downFileName);
        if (!file.isFile())
            throw new FileNotFoundException(downFileName);

        int fSize = (int) file.length();
        /*
         * if (fSize > 0) {
         */
        if (file.exists()) {
            BufferedInputStream in = null;
            try {
                String mimetype = "application/x-msdownload";
                response.setContentType(mimetype);
                response.setHeader("Content-Disposition:", "attachment; filename=" + orgFileName);
                response.setContentLength(fSize);

                // TODO : DRM FileChk 부분 확인. - pass
                int drmChkInt = -1;// drmUtil.streamDrmFileCheck(file);
                if (drmChkInt == 0) { // 0:Drm Encrypt File, 1:Original File
                    LOGGER.info("File is Encrypted.");
                    in = new BufferedInputStream(new FileInputStream(file));
                    FileCopyUtils.copy(in, response.getOutputStream());
                } else if (drmChkInt == 1) { // 0:Drm Encrypt File, 1:Original File
                    LOGGER.info("File is Not Encrypted.");
                    int resultEncInt = -1;// drmUtil.streamDrmEnc(file, response.getOutputStream());
                    if (resultEncInt != 0) {
                        // TODO : DRM ENC Error...
                    }
                } else {
                    // TODO : DRM FileChk Error...
                    // Temporary...
                    // drmUtil.streamDrmEnc(file, response.getOutputStream());
                }
                isSuccess = true;
            } finally {
                ResourceCloseHelper.close(in);
            }
        }

        return isSuccess;
    }

    /**
     * 공통 컴포넌트 utl.fcc 패키지와 Dependency제거를 위해 내부 메서드로 추가 정의함 응용어플리케이션에서 고유값을 사용하기 위해
     * 시스템에서17자리의TIMESTAMP값을 구하는 기능
     * 
     * @param
     * @return Timestamp 값
     * @see
     */
    private static String getTimeStamp() {
        String rtnStr = null;

        // 문자열로 변환하기 위한 패턴 설정(년도-월-일 시:분:초:초(자정이후 초))
        String pattern = "yyyyMMddhhmmssSSS";
        SimpleDateFormat sdfCurrent = new SimpleDateFormat(pattern, Locale.KOREA);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        rtnStr = sdfCurrent.format(ts.getTime());

        return rtnStr;
    }

    /*
     * keyStr : 생성되는 파일명(물리적)의 Key로 첨부파일이 여러개 일 경우 삽입 ex)BBS_ fileKeyParam : 파일 순번으로 보통 0으로 하면 다중일
     * 경우 +1된다 atachFileId : 첨부파일 고유 ID로 보통 수정시 사용한다. storePath : 저장경로로 전역변수 참고한다. fileName : 만약
     * 파일이름(물리적)을 따로 지정하고 싶을 때 내용 삽입하되 첨부파일이 하나일 경우 사용한다.
     */
    /**
     * 첨부파일 정보 DB 삭제
     * 
     * @param delFiles
     * @param atchFileId
     * @throws Exception
     */
    public List<FileVO> deleteFiles(String[] delFiles, String atchFileId) throws Exception {
        List<FileVO> delFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        if (delFiles != null) {
            for (int i = 0; i < delFiles.length; i++) {
                fvo.setFileSn(delFiles[i]);
                fvo = fileMngService.selectFileInf(fvo);
                delFileList.add(fvo);
                fileMngService.deleteFileInf(fvo);
            }
            int fileCnt = fileMngService.selectFileInfs(fvo).size();
            if (fileCnt == 0) {
                fileMngService.deleteAllFileInf(fvo);
            }
        }
        return delFileList;
    }

    public void deleteFilesByFilename(String[] delFiles, String atchFileId) throws Exception {
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        if (delFiles != null) {
            for (int i = 0; i < delFiles.length; i++) {
                fvo.setOrignlFileNm(delFiles[i]);
                fileMngService.deleteFileInfByFilename(fvo);
            }
            int fileCnt = fileMngService.selectFileInfs(fvo).size();
            if (fileCnt == 0) {
                fileMngService.deleteAllFileInf(fvo);
            }
        }
        return;
    }

    /**
     * 첨부파일 정보 DB 삭제
     * 
     * @param delFiles
     * @param atchFileId
     * @throws Exception
     */
    public List<FileVO> deleteTmpFiles(String[] delFiles, String atchFileId) throws Exception {
        List<FileVO> delFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        if (delFiles != null) {
            for (int i = 0; i < delFiles.length; i++) {
                fvo.setFileSn(delFiles[i]);
                fvo = fileMngService.selectTmpFileInf(fvo);
                delFileList.add(fvo);
                fileMngService.deleteTmpFileInf(fvo);
            }
        }
        return delFileList;
    }

    /**
     * 첨부파일 업로드 및 관련 정보 DB 삽입
     * 
     * @param multiRequest
     * @param keyStr
     * @param storePath
     * @param fileName
     * @param multiFlag
     * @param useFlag
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadInsertFiles(MultipartHttpServletRequest multiRequest, String keyStr,
            String storePath, String fileName, Boolean multiFlag, Boolean useFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();

        try {
            final Map<String, MultipartFile> files = multiRequest.getFileMap();

            if (!files.isEmpty()) {
                addFileList = parseFileInf(files, keyStr, 0, "", storePath, fileName, multiFlag);
                fileMngService.insertFileInfs(addFileList, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return addFileList;
    }

    public List<FileVO> uploadInsertFilesMbl(MultipartHttpServletRequest multiRequest,
            String keyStr, String storePath, String fileName, Boolean multiFlag, Boolean useFlag,
            Login Login) throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();

        try {
            final Map<String, MultipartFile> files = multiRequest.getFileMap();

            if (!files.isEmpty()) {
                addFileList = parseFileInfMbl(files, keyStr, 0, "", storePath, fileName, multiFlag,
                        Login);
                fileMngService.insertFileInfs(addFileList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return addFileList;
    }

    public List<FileVO> uploadInsertFilesPDF(MultipartHttpServletRequest multiRequest,
            String keyStr, String storePath, String fileName, Boolean multiFlag, Boolean useFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();

        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        if (!files.isEmpty()) {
            // addFileList = parseFileInf(files, keyStr, 0, "", storePath, fileName, multiFlag);
            addFileList = parseFileInf_multi(files, keyStr, 0, "", storePath, fileName, multiFlag);

            /*
             * int idx; for (int i = 0; i < addFileList.size(); i++ ) { idx =
             * Integer.parseInt(addFileList.get(i).getFileSn());
             * addFileList.get(i).setFileSn((addFileList.size() - idx - 1) + ""); }
             */

            fileMngService.insertFileInfs(addFileList);
        }

        return addFileList;
    }

    public List<FileVO> uploadInsertFiles_multi(MultipartHttpServletRequest multiRequest,
            String keyStr, String storePath, String fileName, Boolean multiFlag, Boolean useFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();

        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        if (!files.isEmpty()) {
            addFileList = parseFileInf_multi(files, keyStr, 0, "", storePath, fileName, multiFlag);
            fileMngService.insertFileInfs(addFileList, true);
        }

        return addFileList;
    }

    /**
     * 첨부파일 업로드 및 관련 정보 DB 삽입
     * 
     * @param multiRequest
     * @param keyStr
     * @param storePath
     * @param fileName
     * @param multiFlag
     * @param useFlag
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadInsertFiles(MultipartHttpServletRequest multiRequest, String keyStr,
            int fileSn, String atchFileId, String storePath, String fileName, Boolean multiFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (files != null && !files.isEmpty()) {
            addFileList =
                    parseFileInf(files, keyStr, fileSn, atchFileId, storePath, fileName, multiFlag);
            if (fileSn == 0)
                fileMngService.insertFileInfs(addFileList);
            else
                fileMngService.updateFileInfs(addFileList);
        }

        return addFileList;
    }

    /**
     * 첨부파일 업로드 및 관련 정보 DB 삽입
     * 
     * @param multiRequest
     * @param keyStr
     * @param storePath
     * @param fileName
     * @param multiFlag
     * @param useFlag
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadInsertTmpFiles(MultipartHttpServletRequest multiRequest,
            String keyStr, String storePath, String fileName, Boolean multiFlag) throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();

        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        if (!files.isEmpty()) {
            addFileList = parseFileInf(files, keyStr, 0, "", storePath, fileName, multiFlag);
            fileMngService.insertTmpFileInfs(addFileList);
        }

        return addFileList;
    }

    /**
     * 첨부파일이 수정된 경우 재업로드 및 관련 정보 DB 수정
     * 
     * @param multiRequest
     * @param keyStr
     * @param atchFileId
     * @param storePath
     * @param fileName
     * @param multiFlag
     * @param useFlag 임시저장된 첨부파일 여부
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadUpdateFiles(MultipartHttpServletRequest multiRequest, String keyStr,
            String atchFileId, String storePath, String fileName, Boolean multiFlag,
            Boolean useFlag) throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            int cnt = fileMngService.getMaxFileSN(fvo);
            addFileList =
                    parseFileInf(files, keyStr, cnt, atchFileId, storePath, fileName, multiFlag);
            fileMngService.updateFileInfs(addFileList);
        }

        return addFileList;
    }

    public List<FileVO> uploadUpdateFilesPDF(MultipartHttpServletRequest multiRequest,
            String keyStr, String atchFileId, String storePath, String fileName, Boolean multiFlag,
            Boolean useFlag) throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            int cnt = fileMngService.getMaxFileSN(fvo);
            addFileList =
                    parseFileInf(files, keyStr, cnt, atchFileId, storePath, fileName, multiFlag);
            /*
             * int idx; for (int i = 0; i < addFileList.size(); i++ ) { idx =
             * Integer.parseInt(addFileList.get(i).getFileSn());
             * 
             * LOGGER.info("addFileList.size ]" + addFileList.size() + "["); LOGGER.info("idx ]" +
             * idx + "[");
             * 
             * addFileList.get(i).setFileSn((addFileList.size() - idx - 1) + ""); }
             */
            fileMngService.updateFileInfs(addFileList);
        }

        return addFileList;
    }

    /**
     * 첨부파일이 수정된 경우 재업로드 및 관련 정보 DB 수정
     * 
     * @param multiRequest
     * @param keyStr
     * @param atchFileId
     * @param storePath
     * @param fileName
     * @param multiFlag
     * @param useFlag 임시저장된 첨부파일 여부
     * @return
     * @throws Exception
     */
    public List<FileVO> uploadUpdateTmpFiles(MultipartHttpServletRequest multiRequest,
            String keyStr, String atchFileId, String storePath, String fileName, Boolean multiFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            int cnt = fileMngService.getMaxTmpFileSN(fvo);
            addFileList =
                    parseFileInf(files, keyStr, cnt, atchFileId, storePath, fileName, multiFlag);
            fileMngService.updateTmpFileInfs(addFileList);
        }

        return addFileList;
    }

    public List<FileVO> uploadUpdateTmpFiles_multi(MultipartHttpServletRequest multiRequest,
            String keyStr, String atchFileId, String storePath, String fileName, Boolean multiFlag)
            throws Exception {
        List<FileVO> addFileList = new ArrayList<FileVO>();
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);

        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            int cnt = fileMngService.getMaxTmpFileSN(fvo);
            addFileList = parseFileInf_multi(files, keyStr, cnt, atchFileId, storePath, fileName,
                    multiFlag);
            fileMngService.updateTmpFileInfs(addFileList);
        }

        return addFileList;
    }

    private static final int BUFFER_SIZE = 4096;

    /**
     * Downloads a file from a URL
     * 
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    public static String downloadFile(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();
        String fileName = null, savedName = null;

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {

            String disposition = httpConn.getHeaderField("Content-Disposition");
            String contentType = httpConn.getContentType();
            int contentLength = httpConn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10, disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1, fileURL.length());
            }

            try {
                fileName = URLDecoder.decode(fileName, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            savedName = new String(Base64.getEncoder().encode(fileName.getBytes()));

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);
            System.out.println("savedName = " + savedName);


            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = saveDir + File.separator + savedName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");

        } else {
            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();

        return savedName;
    }

}
