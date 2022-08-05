package egovframework.com.global.file.dao;

import java.util.List;
import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import egovframework.com.global.file.domain.Attach;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.parameter.AttachSearchParameter;

/**
 * 파일 관련 DAO
 * 
 * @fileName : FileDAOImpl.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Repository
public class FileDAOImpl implements FileDAO {
    @Inject
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.global.file.dao.FileDAO";

    @Override
    public List<AttachDetail> getAttachDetailList(long atchFileId) {
        return sqlSession.selectList(Namespace + ".getAttachDetailList", atchFileId);
    }

    @Override
    public AttachDetail getAttachDetail(AttachSearchParameter param) {
        return sqlSession.selectOne(Namespace + ".getAttachDetail", param);
    }

    @Override
    public long getAttachFileId() {
        return sqlSession.selectOne(Namespace + ".getAttachFileId");
    }

    @Override
    public int createAttach(Attach param) {
        return sqlSession.insert(Namespace + ".createAttach", param);
    }

    @Override
    public int createAttachDetail(AttachDetail param) {
        return sqlSession.insert(Namespace + ".createAttachDetail", param);
    }

    @Override
    public int deleteAttach(long atchFileId) {
        return sqlSession.delete(Namespace + ".deleteAttach", atchFileId);
    }

    @Override
    public int deleteAttachDetail(AttachSearchParameter param) {
        return sqlSession.delete(Namespace + ".deleteAttachDetail", param);
    }

    @Override
    public int deleteAttachDetailAll(long atchFileId) {
        return sqlSession.delete(Namespace + ".deleteAttachDetailAll", atchFileId);
    }
}
