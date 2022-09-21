package egovframework.com.global.file.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;

/**
 * FileStorageService 구현체로 서블릿 설치 경로에 파일 저장 (※파일 Storage 결정 시까지 임시로 사용)
 * 
 * @fileName : ServletFileStorageService.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Service
public class ServletFileStorageService implements FileStorageService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletFileStorageService.class);
	
    @Autowired
    private ServletContext servletContext;

    private String realPath;

    /*저장경로*/
    public static final String ROOT_DIR = GlobalsProperties.getProperty("Globals.fileStorePath");
    
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
        String filePath = ROOT_DIR;
        File fileDir = new File(filePath);
        // root directory 없으면 생성
    	if (!fileDir.exists()) {
    		fileDir.mkdirs(); //폴더 생성합니다.
    	}        
        
        // 실제 파일명_현재시간 으로 rename
        StringBuilder sb = new StringBuilder();
        //sb.append(originalFileName.substring(0, originalFileName.indexOf(fileExtension) - 1));
        //sb.append("_").append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
        sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
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
            attachDetail.setFileSize((int) file.getSize());
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
    
    /**
     * 이미지정보 가져오기
     */
    @Override
    public void getImage(String imgPath, HttpServletResponse response) throws Exception{
    	
    	File file = new File(imgPath);
    	if(!file.isFile()){
    		throw new BaseException(BaseResponseCode.DATA_IS_NULL);
    	}
    	
    	FileInputStream fis = null;
    	new FileInputStream(file);
    	
    	BufferedInputStream in = null;
    	ByteArrayOutputStream bStream = null;
    	try {
    		fis = new FileInputStream(file);
    		in = new BufferedInputStream(fis);
    		bStream = new ByteArrayOutputStream();
    		int imgByte;
    		while ((imgByte = in.read()) != -1) {
    			bStream.write(imgByte);
    		}

    		String type = "";
    		String ext = FilenameUtils.getExtension(file.getName());
    		if (ext != null && !"".equals(ext)) {
    			if ("jpg".equals(ext.toLowerCase())) {
    				type = "image/jpeg";
    			} else {
    				type = "image/" + ext.toLowerCase();
    			}

    		} else {
    			LOGGER.debug("Image fileType is null.");
    		}

    		response.setHeader("Content-Type", type);
    		response.setContentLength(bStream.size());

    		bStream.writeTo(response.getOutputStream());

    		response.getOutputStream().flush();
    		response.getOutputStream().close();

    	} catch (Exception e) {
    		LOGGER.debug("{}", e);
    	} finally {
    		if (bStream != null) {
    			try {
    				bStream.close();
    			} catch (Exception est) {
    				LOGGER.debug("IGNORED: {}", est.getMessage());
    			}
    		}
    		if (in != null) {
    			try {
    				in.close();
    			} catch (Exception ei) {
    				LOGGER.debug("IGNORED: {}", ei.getMessage());
    			}
    		}
    		if (fis != null) {
    			try {
    				fis.close();
    			} catch (Exception efis) {
    				LOGGER.debug("IGNORED: {}", efis.getMessage());
    			}
    		}
    	}
    }

}
