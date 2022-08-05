package egovframework.com.global.file.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import egovframework.com.global.file.domain.AttachDetail;

/**
 * FileStorageService 구현체로 서블릿 설치 경로에 파일 저장 (※파일 Storage 결정 시까지 임시로 사용)
 * 
 * @fileName : ServletFileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Service
public class ServletFileStorageService implements FileStorageService {
    @Autowired
    private ServletContext servletContext;

    private String realPath;

    /** */
    public static final String ROOT_DIR = "file";

    @PostConstruct
    public void initialize() {
        this.realPath = servletContext.getRealPath("/");
    }

    @Override
    public AttachDetail createFile(MultipartFile file) throws Exception {
        AttachDetail attachDetail = null;
        File newFile = null;
        String originalFileName = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        String filePath = realPath + ROOT_DIR;
        File fileDir = new File(filePath);
        // root directory 없으면 생성
        if (!fileDir.isDirectory()) {
            fileDir.mkdir();
        }
        // 실제 파일명_현재시간 으로 rename
        StringBuilder sb = new StringBuilder();
        sb.append(originalFileName.substring(0, originalFileName.indexOf(fileExtension) - 1));
        sb.append("_").append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
                .append(".").append(fileExtension);
        String realFileName = sb.toString();
        try {
            newFile = new File(filePath, realFileName);
            file.transferTo(newFile);
            attachDetail = new AttachDetail();
            attachDetail.setFileExt(fileExtension);
            attachDetail.setFilePath(filePath);
            attachDetail.setOriginalFileName(originalFileName);
            attachDetail.setSaveFileName(realFileName);
            attachDetail.setFileSize(new BigDecimal(file.getSize()));
        } catch (IllegalStateException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        return attachDetail;
    }

    @Override
    public boolean deleteFile(AttachDetail attachDetail) {
        File file = getFile(attachDetail);
        boolean bDeleted = false;
        if (file.exists()) {
            bDeleted = file.delete();
        }
        return bDeleted;
    }

    @Override
    public File getFile(AttachDetail attachDetail) {
        File file = new File(attachDetail.getFilePath(), attachDetail.getSaveFileName());
        return file;
    }
}
