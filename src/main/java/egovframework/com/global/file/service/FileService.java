package egovframework.com.global.file.service;

import java.util.List;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.parameter.AttachSearchParameter;

/**
 * 파일 관련 Service
 * 
 * @fileName : FileService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
public interface FileService {
    List<AttachDetail> getAttachDetailList(long atchFileId);

    AttachDetail getAttachDetail(AttachSearchParameter param);

    void saveFiles(List<AttachDetail> saveFiles, List<AttachDetail> deleteFiles);

    int deleteAttach(long atchFileId);

    int deleteAttachDetail(AttachSearchParameter param);
}
