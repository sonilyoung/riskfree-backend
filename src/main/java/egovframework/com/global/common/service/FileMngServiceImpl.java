package egovframework.com.global.common.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.AbstractServiceImpl;
import egovframework.com.global.common.dao.FileManageDAO;
import egovframework.com.global.common.domain.FileVO;
import egovframework.com.global.genid.GenIdService;


/**
 * @Class Name : FileMngServiceImpl.java
 * @Description : 파일정보의 관리를 위한 구현 클래스
 * @Modification Information
 *
 *               수정일 수정자 수정내용 ------- ------- ------------------- 2009. 3. 25. 이삼섭 최초생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@Service("FileMngService")
public class FileMngServiceImpl extends AbstractServiceImpl implements FileMngService {

    private GenIdService genIdService;

    @Autowired
    private FileManageDAO fileMngDAO;

    @Resource(name = "FileMngUtil")
    private FileMngUtil fileUtil;

    private String tempDir = System.getProperty("java.io.tmpdir");

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#deleteFileInfs(java.util.List)
     */
    public void deleteFileInfs(List<?> fvoList) throws Exception {
        fileMngDAO.deleteFileInfs(fvoList);
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#insertFileInf(egovframework.com.global.common.service.FileVO)
     */
    public String insertFileInf(FileVO fvo) throws Exception {
        if (StringUtils.isBlank(fvo.getAtchFileId())) {
            String atchFileId = genIdService.selectFileNextId();
            fvo.setAtchFileId(atchFileId);
        }

        fileMngDAO.insertFileInf(fvo);

        return fvo.getAtchFileId();
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#insertFileInfs(java.util.List)
     */
    public String insertFileInfs(List<?> fvoList) throws Exception {
        String atchFileId = "";

        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertFileInfs(fvoList);
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#insertFileInfs(java.util.List)
     */
    public String insertFileInfs(List<?> fvoList, boolean document_flag) throws Exception {
        String atchFileId = "";

        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertFileInfs(fvoList, document_flag);
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 여러 개의 임시 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#insertFileInfs(java.util.List)
     */
    public String insertTmpFileInfs(List<?> fvoList) throws Exception {
        String atchFileId = "";

        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertTmpFileInfs(fvoList);
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    @Override
    public String insertFileDetailInfs(List<?> fvoList, boolean multi_flag) throws Exception {
        String atchFileId = "";
        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertFileDetailInfs(fvoList, multi_flag);
        }
        return atchFileId;
    }

    @Override
    public String insertOnlyFileDetailInfs(List<?> fvoList) throws Exception {
        String atchFileId = "";
        if (fvoList.size() != 0) {
            atchFileId = fileMngDAO.insertOnlyFileDetailInfs(fvoList);
        }
        return atchFileId;
    }

    /**
     * 파일마스터에 대한 정보 조회
     *
     */
    public String selectMasterFileInf(FileVO fvo) throws Exception {
        return fileMngDAO.selectMasterFileInf(fvo);
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileInfs(egovframework.com.global.common.service.FileVO)
     */
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception {
        return fileMngDAO.selectFileInfs(fvo);
    }

    /**
     * 임시 파일에 대한 목록을 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileInfs(egovframework.com.global.common.service.FileVO)
     */
    public List<FileVO> selectTmpFileInfs(FileVO fvo) throws Exception {
        return fileMngDAO.selectTmpFileInfs(fvo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#updateFileInfs(java.util.List)
     */
    public void updateFileInfs(List<?> fvoList) throws Exception {
        // Delete & Insert
        fileMngDAO.updateFileInfs(fvoList);
    }

    /**
     * 여러 개의 임시 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#updateFileInfs(java.util.List)
     */
    public void updateTmpFileInfs(List<?> fvoList) throws Exception {
        // Delete & Insert
        fileMngDAO.updateTmpFileInfs(fvoList);
    }

    /**
     * 한 개의 파일에 대한 상세 정보를 수정한다.
     *
     */
    public void updateFileInf(FileVO file) throws Exception {
        // update
        fileMngDAO.updateFileInf(file);
    }

    /**
     * 하나의 파일을 삭제한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#deleteFileInf(egovframework.com.global.common.service.FileVO)
     */
    public void deleteFileInf(FileVO fvo) throws Exception {
        fileMngDAO.deleteFileInf(fvo);
    }

    public void deleteFileInfByFilename(FileVO fvo) throws Exception {
        fileMngDAO.deleteFileInfByFilename(fvo);
    }

    /**
     * 하나의 임시 파일을 삭제한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#deleteFileInf(egovframework.com.global.common.service.FileVO)
     */
    public void deleteTmpFileInf(FileVO fvo) throws Exception {
        fileMngDAO.deleteTmpFileInf(fvo);
    }

    /**
     * 모든 임시 파일을 삭제한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#deleteFileInf(egovframework.com.global.common.service.FileVO)
     */
    public void allDeleteTmpFileDetail(String atchFileId) throws Exception {
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        // 첨부파일 삭제를 위한 목록 조회
        List<FileVO> deleteFileList = selectTmpFileInfs(fvo);
        for (FileVO delFile : deleteFileList) {
            File tmpDocAttachFile = new File("");
            // new File(PathUtil.getFullPathOfFile(delFile.getFileStreCours()),
            // delFile.getStreFileNm());
            // tmpDocAttachFile.delete();
            if (!tmpDocAttachFile.delete()) {
                Logger.info("Failed to delete file.");
            }
        }
        fileMngDAO.allDeleteTmpFileDetail(fvo);
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileInf(egovframework.com.global.common.service.FileVO)
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
        return fileMngDAO.selectFileInf(fvo);
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileInf(egovframework.com.global.common.service.FileVO)
     */
    public FileVO selectFileInf(String atchFileId, String fileSn) throws Exception {
        FileVO fvo = new FileVO();
        fvo.setAtchFileId(atchFileId);
        fvo.setFileSn(fileSn);

        return fileMngDAO.selectFileInf(fvo);
    }

    /**
     * 임시 파일에 대한 상세정보를 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileInf(egovframework.com.global.common.service.FileVO)
     */
    public FileVO selectTmpFileInf(FileVO fvo) throws Exception {
        return fileMngDAO.selectTmpFileInf(fvo);
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#getMaxFileSN(egovframework.com.global.common.service.FileVO)
     */
    public int getMaxFileSN(FileVO fvo) throws Exception {
        return fileMngDAO.getMaxFileSN(fvo);
    }

    /**
     * 임시 파일 구분자에 대한 최대값을 구한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#getMaxFileSN(egovframework.com.global.common.service.FileVO)
     */
    public int getMaxTmpFileSN(FileVO fvo) throws Exception {
        return fileMngDAO.getMaxTmpFileSN(fvo);
    }

    /**
     * 전체 파일을 삭제한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#deleteAllFileInf(egovframework.com.global.common.service.FileVO)
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception {
        fileMngDAO.deleteAllFileInf(fvo);
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectFileListByFileNm(egovframework.com.global.common.service.FileVO)
     */
    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception {
        List<FileVO> result = fileMngDAO.selectFileListByFileNm(fvo);
        int cnt = fileMngDAO.selectFileListCntByFileNm(fvo);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("resultList", result);
        map.put("resultCnt", Integer.toString(cnt));

        return map;
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @see egovframework.com.global.common.service.FileMngService#selectImageFileList(egovframework.com.global.common.service.FileVO)
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
        return fileMngDAO.selectImageFileList(vo);
    }

    /**
     * FILE_SN 컬럼값을 세팅한다
     *
     * @see egovframework.com.global.common.service.FileMngService#setFileSn(java.util.Map)
     */
    @Override
    public void setFileSn(Map<String, Object> mapForSn) {
        fileMngDAO.setFileSn(mapForSn);
    }

    @Override
    public void alignFileSn(Map<String, Object> mapForSn) {
        fileMngDAO.alignFileSn(mapForSn);
    }

    public Map<String, String> uploadBridge_core(final MultipartHttpServletRequest multiRequest,
            @RequestParam Map params, Login Login) throws Exception {
        System.out.println("");

        String mailTy = "";// Login.getMailTy(); // 1:zimbra, 2:owa
        String zmGwIp = "";// Login.getZmGwIp();
        String zmGwPort = "";// Login.getZmGwPort();
        File binaryFile = null;
        String originalFileName = null;
        Map<String, String> resultMap = new HashMap<String, String>();

        if (tempDir.charAt(tempDir.length() - 1) != File.separatorChar) {
            tempDir += File.separatorChar;
        }

        System.out.println("uploadBridge_core() : tempDir  ]" + tempDir + "[");

        try {
            Map<String, MultipartFile> map = multiRequest.getFileMap();

            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                originalFileName = iterator.next().toString();
                MultipartFile mFile = map.get(originalFileName);
                originalFileName = new String(originalFileName.getBytes(), "UTF-8");
                binaryFile = new File(tempDir + File.separator + originalFileName);
                mFile.transferTo(binaryFile);
                System.out.println(
                        "uploadBridge_core() : UPLOADED ]" + binaryFile.getAbsolutePath() + "[");
            }

            if (binaryFile == null) {
                resultMap.put("RESULT", "1111");
                resultMap.put("DESC", "Cannot create temp dir : " + tempDir);
                return resultMap;
            }

            String gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_upload.do";
            System.out.println("uploadBridge_core() : gurl ]" + gurl + "[");

            String charset = "UTF-8";
            String param = "value";

            String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some
                                                                            // unique random value.
            String CRLF = "\r\n"; // Line separator required by multipart/form-data.

            System.out.println("uploadBridge_core() : Upload URL ]" + gurl + "[");

            URLConnection connection = new URL(gurl).openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=" + boundary);

            InputStream input = null;
            OutputStream output = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);

            // Send normal param.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file1\"").append(CRLF);
            writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
            writer.append(CRLF).append(param).append(CRLF).flush();

            // Send binary file.
            writer.append("--" + boundary).append(CRLF);
            writer.append("Content-Disposition: form-data; name=\"file1\"; filename=\""
                    + originalFileName + "\"").append(CRLF);
            writer.append(
                    "Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName()))
                    .append(CRLF);
            writer.append("Content-Transfer-Encoding: binary").append(CRLF);
            writer.append(CRLF).flush();

            System.out.println("uploadBridge_core() : Send start...");

            // Files.copy(binaryFile.toPath(), output);
            try {
                input = new FileInputStream(binaryFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
            } catch (Exception copyEx) {
                copyEx.printStackTrace();
            } finally {
                input.close();
            }

            System.out.println("uploadBridge_core() : Send end...");

            output.flush(); // Important before continuing with writer!
            writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

            // End of multipart/form-data.
            writer.append("--" + boundary + "--").append(CRLF).flush();

            // Request is lazily fired whenever you need to obtain information about response.
            int responseCode = ((HttpURLConnection) connection).getResponseCode();
            InputStream stream = ((HttpURLConnection) connection).getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }

            ObjectMapper mapper = new ObjectMapper();
            String json = sb.toString();

            resultMap = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

            writer.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("RESULT", "1111");
            resultMap.put("DESC", e.getMessage());
            return resultMap;
        } finally {
            if (binaryFile != null)
                binaryFile.delete();
        }

        System.out.println("uploadBridge_core() : resultMap ]" + resultMap.toString() + "[");

        return resultMap;
    }

    public Map<String, String> downloadBridge_core(final HttpServletRequest request,
            HttpServletResponse response, Login Login) throws Exception {
        String mailTy = "";// Login.getMailTy(); // 1:zimbra, 2:owa
        String zmGwIp = "";// Login.getZmGwIp();
        String zmGwPort = "";// Login.getZmGwPort();


        String gurl = null;
        if ("1".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_download.do?"
                    + request.getQueryString();
        } else if ("2".equals(mailTy)) {
            gurl = zmGwIp + ":" + zmGwPort + "/gwgateway/rdbm_attachment_download_ex.do?"
                    + request.getQueryString();
        }

        // System.out.println("AUTH_TOKEN ::: " + AUTH_TOKEN);
        System.out.println("Download URL ::: " + gurl);
        System.out.println("Temp    Path ::: " + tempDir);

        Map<String, String> resultMap = new HashMap<String, String>();
        String realName = null;
        String fileName = fileUtil.downloadFile(gurl, tempDir);
        String filePath = tempDir + File.separator + fileName;

        try {
            realName = new String(java.util.Base64.getDecoder().decode(fileName.getBytes()));
        } catch (Exception e) {
        }

        // realName = MimeUtility.decodeText(new String(realName.getBytes("8859_1"), "utf8"));

        System.out.println("RealName ::: " + realName);
        System.out.println("Downloaded Path ::: " + filePath);

        try {
            InputStream fis = new FileInputStream(filePath);
            ServletOutputStream outStream = response.getOutputStream();

            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + URLEncoder.encode(realName, "UTF-8").replace("+", "%20") + "\"");

            IOUtil.copyCompletely(fis, outStream);
            outStream.flush();
            fis.close();

            if (fileName != null) {
                new File(filePath).delete();
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }
        return resultMap;
    }
}
