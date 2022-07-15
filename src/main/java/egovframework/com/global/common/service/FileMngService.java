package egovframework.com.global.common.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.global.common.domain.FileVO;

/**
 * @Class Name : FileMngService.java
 * @Description : 파일정보의 관리를 위한 서비스 인터페이스
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
public interface FileMngService {

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectFileInfs(FileVO fvo) throws Exception;

    /**
     * 파일마스터에 대한 정보 조회
     *
     */
    public String selectMasterFileInf(FileVO fvo) throws Exception;

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectTmpFileInfs(FileVO fvo) throws Exception;

    /**
     * 임시 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectTmpFileInf(FileVO fvo) throws Exception;

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvo
     * @throws Exception
     */
    public String insertFileInf(FileVO fvo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public String insertFileInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public String insertFileInfs(List<?> fvoList, boolean document_flag) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public String insertTmpFileInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(상세)를 등록한다.
     *
     * @param fvoList
     * @param masterFlag
     * @throws Exception
     */
    public String insertFileDetailInfs(List<?> fvoList, boolean masterFlag) throws Exception;

    public String insertOnlyFileDetailInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void updateFileInfs(List<?> fvoList) throws Exception;

    /**
     * 여러 개의 임시 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void updateTmpFileInfs(List<?> fvoList) throws Exception;

    /**
     * 한 개의 파일에 대한 상세 정보를 수정한다.
     * 
     * @param file
     * @throws Exception
     */
    public void updateFileInf(FileVO file) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fvoList
     * @throws Exception
     */
    public void deleteFileInfs(List<?> fvoList) throws Exception;

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(FileVO fvo) throws Exception;

    public void deleteFileInfByFilename(FileVO fvo) throws Exception;

    /**
     * 하나의 임시 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteTmpFileInf(FileVO fvo) throws Exception;

    /**
     * 모든 임시 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void allDeleteTmpFileDetail(String atchFileId) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception;

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param atchFileId Attchment File ID
     * @param fileSn Attchment File Seq
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(String atchFileId, String fileSn) throws Exception;

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(FileVO fvo) throws Exception;

    /**
     * 임시 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxTmpFileSN(FileVO fvo) throws Exception;

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectFileListByFileNm(FileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception;

    /**
     * FILE_SN 컬럼값을 세팅한다
     *
     * @param map
     * @return
     * @throws Exception
     */
    public void setFileSn(Map<String, Object> mapForSn);

    public void alignFileSn(Map<String, Object> mapForSn);

    public Map<String, String> uploadBridge_core(final MultipartHttpServletRequest multiRequest,
            @RequestParam Map params, Login Login) throws Exception;

    public Map<String, String> downloadBridge_core(final HttpServletRequest request,
            HttpServletResponse response, Login Login) throws Exception;
}
