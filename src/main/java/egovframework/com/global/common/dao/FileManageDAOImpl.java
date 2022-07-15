package egovframework.com.global.common.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
@Repository
public class FileManageDAOImpl implements FileManageDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "";

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List<?> fileList) throws Exception {
        String atchFileId = "";
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            atchFileId = vo.getAtchFileId();
            sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
            }
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileInfs(List<?> fileList, boolean multi_flag) throws Exception {
        String atchFileId = "";
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            if (multi_flag) {
                atchFileId = vo.getAtchFileId();
                sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);
            }

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                if (!multi_flag) {
                    sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);
                }
                sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
            }
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertTmpFileInfs(List<?> fileList) throws Exception {
        String atchFileId = "";
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            atchFileId = vo.getAtchFileId();
            sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                sqlSession.insert(Namespace + "FileManageDAO.insertTmpFileDetail", vo);
            }
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 여러 개의 파일에 대한 정보(상세)를 등록한다.
     *
     * @param fileList
     * @return
     * @throws Exception
     */
    public String insertFileDetailInfs(List<?> fileList, boolean multi_flag) {
        String atchFileId = "";
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            atchFileId = vo.getAtchFileId();
            if (multi_flag) {
                sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);
            }

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                if (!multi_flag) {
                    sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);
                }
                sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
            }
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    public String insertOnlyFileDetailInfs(List<?> fileList) {
        String atchFileId = "";
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            atchFileId = vo.getAtchFileId();

            /*
             * if (multi_flag) { // insert("FileManageDAO.insertFileMaster", vo); }
             */

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                /*
                 * if (! multi_flag) { insert("FileManageDAO.insertFileMaster", vo); }
                 */
                sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
            }
        }
        if (atchFileId == "") {
            atchFileId = null;
        }
        return atchFileId;
    }

    /**
     * 하나의 파일에 대한 정보(속성 및 상세)를 등록한다.
     *
     * @param vo
     * @throws Exception
     */
    public void insertFileInf(FileVO vo) throws Exception {
        sqlSession.insert(Namespace + "FileManageDAO.insertFileMaster", vo);
        sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
    }

    /**
     * 파일마스터에 대한 정보 조회
     *
     * @param vo
     * @throws Exception
     */
    public String selectMasterFileInf(FileVO vo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.selectMasterFileInf", vo);
    }

    /**
     * 여러 개의 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void updateFileInfs(List<?> fileList) throws Exception {
        if (fileList.size() != 0) {
            FileVO vo = (FileVO) fileList.get(0);
            sqlSession.update(Namespace + "FileManageDAO.updateFileMaster", vo);

            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                sqlSession.update(Namespace + "FileManageDAO.updateFileMaster", vo);
                sqlSession.insert(Namespace + "FileManageDAO.insertFileDetail", vo);
            }
        }
    }

    /**
     * 여러 개의 임시 파일에 대한 정보(속성 및 상세)를 수정한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void updateTmpFileInfs(List<?> fileList) throws Exception {
        if (fileList.size() != 0) {
            FileVO vo = null; // (FileVO) fileList.get(0);
            Iterator<?> iter = fileList.iterator();
            while (iter.hasNext()) {
                vo = (FileVO) iter.next();
                sqlSession.insert(Namespace + "FileManageDAO.insertTmpFileDetail", vo);
            }
        }
    }

    /**
     * updateFileInf
     *
     * @param file
     * @throws Exception
     */
    public void updateFileInf(FileVO file) throws Exception {
        sqlSession.update(Namespace + "FileManageDAO.updateFileDetail", file);
    }

    /**
     * 여러 개의 파일을 삭제한다.
     *
     * @param fileList
     * @throws Exception
     */
    public void deleteFileInfs(List<?> fileList) throws Exception {
        Iterator<?> iter = fileList.iterator();
        FileVO vo;
        while (iter.hasNext()) {
            vo = (FileVO) iter.next();

            sqlSession.delete(Namespace + "FileManageDAO.deleteFileDetail", vo);
        }
    }

    /**
     * 하나의 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteFileInf(FileVO fvo) throws Exception {
        sqlSession.delete(Namespace + "FileManageDAO.deleteFileDetail", fvo);
    }

    public void deleteFileInfByFilename(FileVO fvo) throws Exception {
        sqlSession.delete(Namespace + "FileManageDAO.deleteFileDetailByFilename", fvo);
    }

    /**
     * 하나의 임시 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteTmpFileInf(FileVO fvo) throws Exception {
        sqlSession.delete(Namespace + "FileManageDAO.deleteTmpFileDetail", fvo);
    }

    /**
     * 하나의 임시 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void allDeleteTmpFileDetail(FileVO fvo) throws Exception {
        sqlSession.delete(Namespace + "FileManageDAO.allDeleteTmpFileDetail", fvo);
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectFileInfs(FileVO vo) throws Exception {
        return sqlSession.selectList(Namespace + "FileManageDAO.selectFileList", vo);
    }

    /**
     * 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectTmpFileInfs(FileVO vo) throws Exception {
        return sqlSession.selectList(Namespace + "FileManageDAO.selectTmpFileList", vo);
    }

    /**
     * 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxFileSN(FileVO fvo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.getMaxFileSN", fvo);
    }

    /**
     * 임시 파일 구분자에 대한 최대값을 구한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int getMaxTmpFileSN(FileVO fvo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.getMaxTmpFileSN", fvo);
    }

    /**
     * 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectFileInf(FileVO fvo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.selectFileInf", fvo);
    }

    /**
     * 임시 파일에 대한 상세정보를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public FileVO selectTmpFileInf(FileVO fvo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.selectTmpFileInf", fvo);
    }

    /**
     * 전체 파일을 삭제한다.
     *
     * @param fvo
     * @throws Exception
     */
    public void deleteAllFileInf(FileVO fvo) throws Exception {
        sqlSession.update(Namespace + "FileManageDAO.deleteFileMaster", fvo);
    }

    /**
     * 파일명 검색에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception {
        return sqlSession.selectList(Namespace + "FileManageDAO.selectFileListByFileNm", fvo);
    }

    /**
     * 파일명 검색에 대한 목록 전체 건수를 조회한다.
     *
     * @param fvo
     * @return
     * @throws Exception
     */
    public int selectFileListCntByFileNm(FileVO fvo) throws Exception {
        return sqlSession.selectOne(Namespace + "FileManageDAO.selectFileListCntByFileNm", fvo);
    }

    /**
     * 이미지 파일에 대한 목록을 조회한다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<FileVO> selectImageFileList(FileVO vo) throws Exception {
        return sqlSession.selectList(Namespace + "FileManageDAO.selectImageFileList", vo);
    }

    /**
     * FILE_SN 컬럼값을 세팅한다
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public void setFileSn(Map<String, Object> mapForSn) {
        sqlSession.update(Namespace + "FileManageDAO.setFileSn", mapForSn);
    }

    public void alignFileSn(Map<String, Object> mapForSn) {
        sqlSession.update(Namespace + "FileManageDAO.alignFileSn", mapForSn);
    }

}
