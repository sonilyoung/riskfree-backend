package egovframework.com.global.file.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import egovframework.com.domain.common.domain.CommListWrapper;
import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.parameter.AttachSaveParameter;
import egovframework.com.global.file.parameter.AttachSearchParameter;
import egovframework.com.global.file.service.FileService;
import egovframework.com.global.file.service.FileStorageService;
import egovframework.com.global.file.util.FileUtils;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 파일 관련 Controller
 * 
 * @fileName : FileController.java
 * @author : YeongJun Lee
 * @date : 2022.07.12
 */
@RestController
@RequestMapping("/file")
@Api(tags = "File Upload & Download Management API")
public class FileController {

    private final FileService fileService;
    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileService fileService, FileStorageService fileStorageService) {
        this.fileService = fileService;
        this.fileStorageService = fileStorageService;
    }

    /**
     * 파일 업로드
     * 
     * @param files
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/fileUpload")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file upload", notes = "This function supports file upload and file deletion functions.")
    public BaseResponse<List<AttachDetail>> fileUpload(
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestPart(value = "param", required = false) AttachSaveParameter param)
            throws Exception {
        Long atchFileId = null;
        List<AttachDetail> saveFiles = null;
        List<AttachDetail> deleteFiles = null;
        if (param != null) {
            atchFileId = param.getAtchFileId();
            deleteFiles = param.getDeleteItems();
        }
        if (files != null) {
            saveFiles = new ArrayList<>();
            for (MultipartFile file : files) {
                // 파일 생성
                AttachDetail detail = fileStorageService.createFile(file);
                if (detail != null) {
                    // 기존 파일첨부 아이디가 있는 경우 해당 아이디로 파일 정보 생성
                    if (atchFileId != null) {
                        detail.setAtchFileId(atchFileId);
                    }
                    saveFiles.add(detail);
                }
            }
        }
        // 파일 정보 생성
        fileService.saveFiles(saveFiles, deleteFiles);

        List<AttachDetail> result = saveFiles != null ? saveFiles : new ArrayList<AttachDetail>();
        if (deleteFiles != null) {
            result.addAll(deleteFiles);
        }
        return new BaseResponse<>(result);
    }
    
    /**
     * 파일 다운
     * 
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/fileDown", method = RequestMethod.GET)
	public void fileDown(
			@RequestParam(required = true) Long atchFileId
			,@RequestParam(required = true) Long fileSn
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		
		if(atchFileId == null){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		
		if(fileSn == null){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}				
		
		AttachSearchParameter param = new AttachSearchParameter();
		param.setAtchFileId(atchFileId);
		param.setFileSn(BigDecimal.valueOf(fileSn));
        AttachDetail attachDetail = fileService.getAttachDetail(param);
		request.setAttribute("downFile", attachDetail.getFilePath() + File.separator +  attachDetail.getSaveFileName());
		request.setAttribute("orginFile",  attachDetail.getOriginalFileName());
		request.setAttribute("fileSize",  attachDetail.getFileSize());
		FileUtils.downFile(request, response);		
	} 

    /**
     * 파일 다운로드
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("/fileDownload")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file download", notes = "This function will download the file corresponding to the file ID")
    public void fileDownload(@RequestBody AttachSearchParameter param, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AttachDetail attachDetail = fileService.getAttachDetail(param);
        File file = fileStorageService.getFile(attachDetail);
        String fileName = file.getName();
        String mime = request.getSession().getServletContext().getMimeType(fileName);
        long fileSize = file.length();
        if (!StringUtils.hasText(mime)) {
            mime = "application/octet-stream;";
        }
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.indexOf("MSIE") > -1) {
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"));
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Length", "" + fileSize);
        
        response.setContentType(mime + "; charset=utf-8");
        try (InputStream is = new FileInputStream(file);
                OutputStream os = new BufferedOutputStream(response.getOutputStream());) {
            FileCopyUtils.copy(is, os);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * 첨부 파일 아이디에 해당하는 상세 리스트 조회
     * 
     * @author : YeongJun Lee
     * @param param
     * @return
     */
    @GetMapping("/attachDetailList/{atchFileId}")
    @ApiOperation(value = "file information list", notes = "This function returns a list of information corresponding to the file ID.")
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    public BaseResponse<CommListWrapper<AttachDetail>> getAttachDetailList(
            @PathVariable("atchFileId") long atchFileId) {
        List<AttachDetail> list = fileService.getAttachDetailList(atchFileId);
        CommListWrapper<AttachDetail> listWrapper = new CommListWrapper<>(list);
        return new BaseResponse<>(listWrapper);
    }
}
