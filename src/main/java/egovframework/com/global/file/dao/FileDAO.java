package egovframework.com.global.file.dao;

import java.util.List;
import egovframework.com.global.file.domain.Attach;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.parameter.AttachSearchParameter;

/**
 * 파일 관련 DAO
 * 
 * @fileName : FileDAO.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
public interface FileDAO {

    List<AttachDetail> getAttachDetailList(long atchFileId);

    AttachDetail getAttachDetail(AttachSearchParameter param);

    long getAttachFileId();

    int createAttach(Attach param);

    int createAttachDetail(AttachDetail param);

    int deleteAttach(long atchFileId);

    int deleteAttachDetail(AttachSearchParameter param);

    int deleteAttachDetailAll(long atchFileId);
}
