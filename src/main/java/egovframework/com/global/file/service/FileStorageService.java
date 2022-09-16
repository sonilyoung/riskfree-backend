package egovframework.com.global.file.service;

import java.io.File;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import egovframework.com.global.file.domain.AttachDetail;

/**
 * 파일 Storage 관련 Service
 * 
 * @fileName : FileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
public interface FileStorageService {
    File getFile(AttachDetail attachDetail);

    AttachDetail createFile(MultipartFile file) throws Exception;

    boolean deleteFile(AttachDetail attachDetail);
    
    void getImage(String filePath, HttpServletResponse response) throws Exception;
}
