package egovframework.com.global.file.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.global.common.domain.GlobalsProperties;
import egovframework.com.global.file.dao.FileDAO;
import egovframework.com.global.file.domain.Attach;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.parameter.AttachSearchParameter;

/**
 * 파일 관련 Service
 * 
 * @fileName : FileServiceImpl.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileDAO fileDAO;
    private final FileStorageService fileStorageService;
    
    /*저장경로*/
    public static final String ROOT_DIR = GlobalsProperties.getProperty("Globals.fileStorePath");    

    @Autowired
    public FileServiceImpl(FileDAO fileDAO, FileStorageService fileStorageService) {
        this.fileDAO = fileDAO;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<AttachDetail> getAttachDetailList(long atchFileId) {
        return fileDAO.getAttachDetailList(atchFileId);
    }

    @Override
    public AttachDetail getAttachDetail(AttachSearchParameter param) {
        return fileDAO.getAttachDetail(param);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFiles(List<AttachDetail> saveFiles, List<AttachDetail> deleteFiles) {
        int iRslt = 0;
        // 파일 정보 생성
        if (saveFiles != null && !saveFiles.isEmpty()) {
            Long atchFileId = saveFiles.get(0).getAtchFileId();
            // 신규 첨부파일인 경우
            if (atchFileId == null) {
                atchFileId = fileDAO.getAttachFileId();
                Attach attach = new Attach();
                attach.setAtchFileId(atchFileId);
                fileDAO.createAttach(attach);
            }
            for (AttachDetail attachDetail : saveFiles) {
                attachDetail.setAtchFileId(atchFileId);
                iRslt = fileDAO.createAttachDetail(attachDetail);
                attachDetail.setSaved(iRslt > 0);
            }
        }
        // 삭제 파일이 있는 경우
        if (deleteFiles != null && !deleteFiles.isEmpty()) {
            for (AttachDetail attachDetail : deleteFiles) {
                AttachSearchParameter param =
                        AttachSearchParameter.builder().atchFileId(attachDetail.getAtchFileId())
                                .fileSn(attachDetail.getFileSn()).build();
                attachDetail = getAttachDetail(param);
                // DB 정보 삭제
                iRslt = fileDAO.deleteAttachDetail(param);
                if (iRslt > 0) {
                    // 파일 삭제
                    boolean bDeleted = fileStorageService.deleteFile(attachDetail);
                    attachDetail.setDeleted(bDeleted);
                }
            }
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveFilesCopy(AttachDetail saveFiles) {
    	String result = "";
        // 파일 정보 생성
        if (saveFiles != null) {
            Long atchFileId = saveFiles.getAtchFileId();
            // 신규 첨부파일인 경우
            atchFileId = fileDAO.getAttachFileId();
            Attach attach = new Attach();
            attach.setAtchFileId(atchFileId);
            fileDAO.createAttach(attach);
            
        	saveFiles.setAtchFileId(atchFileId);
            fileDAO.createAttachDetail(saveFiles);
            result = String.valueOf(atchFileId);

        }
        return result;
    }    

    /**
     * atchFileId 에 해당하는 전체 파일 삭제
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAttach(long atchFileId) {
        int iRslt = fileDAO.deleteAttach(atchFileId);
        if (iRslt > 0) {
            List<AttachDetail> fileList = getAttachDetailList(atchFileId);
            // DB 정보 삭제
            iRslt += fileDAO.deleteAttachDetailAll(atchFileId);
            // 파일 삭제
            for (AttachDetail attachDetail : fileList) {
                fileStorageService.deleteFile(attachDetail);
            }
        }
        return iRslt;
    }

    /**
     * 특정 파일 하나만 삭제
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAttachDetail(AttachSearchParameter param) {
        AttachDetail attachDetail = getAttachDetail(param);
        // DB 정보 삭제
        int iRslt = fileDAO.deleteAttachDetail(param);
        // 파일 삭제
        fileStorageService.deleteFile(attachDetail);
        return iRslt;
    }

    /**
     * 이미지 path 조회
     */
	@Override
	public AttachDetail getFileInfo(AttachDetail params) {
		return fileDAO.getFileInfo(params);
	}
	
    @Override
    public AttachDetail createFileCopy(String params){
    	AttachDetail attachDetail = null;
    	Long attachFileId = new Long(0);

    	try {
    		attachFileId = Long.parseLong(params);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	if(params!=null && !"".equals(params)) {
    		//파일복사
        	AttachSearchParameter param = new AttachSearchParameter();
    		param.setAtchFileId(attachFileId);
    		param.setFileSn(1);
    		
    		//파일정보
        	attachDetail = fileDAO.getAttachDetail(param);
        	
        	if(attachDetail!=null) {
                String fileExtension = attachDetail.getFileExt();
                String filePath = ROOT_DIR;
       
                // 실제 파일명_현재시간 으로 rename
                StringBuilder sb = new StringBuilder();
                sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))
                        .append(".").append(fileExtension);
                String realFileName = sb.toString();
                try {
                	FileServiceImpl.fileCopy(filePath+File.separator+attachDetail.getSaveFileName(), filePath+File.separator+realFileName);
                    attachDetail.setSaveFileName(realFileName);
                } catch (IllegalStateException e) {
                    throw e;
                }       		
        	}
    	}
        
        return attachDetail;
    }    
    
    public static boolean fileCopy(String inFilePath, String outFilePath) {
        try {
            FileInputStream infile = new FileInputStream(inFilePath);
            FileOutputStream outfile = new FileOutputStream(outFilePath);

            byte[] b = new byte[1024];
            int len;
            while((len = infile.read(b, 0, 1024)) > 0){
                outfile.write(b, 0, len);
            }
            infile.close();
            outfile.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }    
}
