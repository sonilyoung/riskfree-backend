package egovframework.com.global.common.dao;

import java.util.List;
import java.util.Map;
import egovframework.com.global.common.domain.FileVO;

/**
 * @Class Name : FileMngDAO.java
 * @Description : 파일정보 관리를 위한 데이터 처리 클래스
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
public interface FileManageDAO {

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List<?> fileList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List<?> fileList, boolean multi_flag) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertTmpFileInfs(List<?> fileList) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileDetailInfs(List<?> fileList, boolean multi_flag);

    public String insertOnlyFileDetailInfs(List<?> fileList);

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param vo
     * @throws Exception
     */
    public void insertFileInf(FileVO vo) throws Exception;

    /**
     * 파일마스터에 대한 정보 조회
     *
     * @param vo
     * @throws Exception
     */
    public String selectMasterFileInf(FileVO vo) throws Exception;

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void updateFileInfs(List<?> fileList) throws Exception;

    /**
     * 여러 개의 임시 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void updateTmpFileInfs(List<?> fileList) throws Exception;

    /**
     * updateFileInf
     *
     * @param file
     * @throws Exception
     */
    public void updateFileInf(FileVO file) throws Exception;

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void deleteFileInfs(List<?> fileList) throws Exception;

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
     * 하나의 임시 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void allDeleteTmpFileDetail(FileVO fvo) throws Exception;

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectFileInfs(FileVO vo) throws Exception;

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectTmpFileInfs(FileVO vo) throws Exception;

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
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception;

    /**
     * 임시 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectTmpFileInf(FileVO fvo) throws Exception;

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
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception;

    /**
     * 파일명 검색에 대한 목록 전체 건수를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int selectFileListCntByFileNm(FileVO fvo) throws Exception;

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception;

    /**
     * FILE_SN 컬럼값을 세팅한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public void setFileSn(Map<String, Object> mapForSn);

    public void alignFileSn(Map<String, Object> mapForSn);
}
