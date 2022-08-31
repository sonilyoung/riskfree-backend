package egovframework.com.domain.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.domain.law.domain.DutyBotton;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.service.MainService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.service.FileService;
import egovframework.com.global.file.service.FileStorageService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.global.util.UserExcelService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/common/excel")
public class UserExcelController {

	@Autowired
	private UserExcelService userExcelService;
	
	@Autowired
	private LoginService loginService;	

    @Autowired
    private MainService mainService;
    
    @Autowired
    private FileService fileService;    
    
    @Autowired
    private FileStorageService fileStorageService;
    
	
	//public static final String stordFilePath = "d:/temp";
    public static final String stordFilePath = "/home/jun/apps/riskfree/temp";
    
		
	@PostMapping(value="/excelUpload")
	@ApiOperation(value = "This function save excel upload.",
    notes = "This function save excel upload.")	
    @ApiImplicitParams({
    	@ApiImplicitParam(required = true, name = "excelFile", value = "excel File" ,dataType = "MultipartFile")
    })		
	//public BaseResponse<Map<String, String>> excelUpload(MultipartHttpServletRequest request) throws Exception{
	public BaseResponse<Integer> excelUpload(
			//@RequestPart("excelFile") MultipartFile excelFile
			HttpServletRequest request
			,@RequestPart(value = "excelFile", required = true) MultipartFile excelFile
	) throws Exception{
		//LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	    //MultipartFile excelFile = request.getFile("excelFile");
	    log.debug("========= excelUpload ========="+ excelFile);

    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}	    
	    
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"A", "B", "C", "D", "E"
    					, "F", "G", "H", "I", "J"
    					, "K", "L", "M", "N", "O"
    					, "P", "Q", "R", "S", "T"
    					, "U", "V", "W", "X", "Y", "Z"};
	            int resultCode = userExcelService.excelUpload(destFile, coloumNm, login); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
	            }
	        }else {
	        	return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
	    } 
	    
	}
	
	@PostMapping(value="/relatedRawExcelUpload" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@ApiOperation(value = "This function save excel upload.",
    notes = "This function save excel upload.")	
    @ApiImplicitParams({
    	@ApiImplicitParam(required = true, name = "excelFile", value = "excel File" ,dataType = "MultipartFile")
    })		
	public BaseResponse<Integer> relatedRawExcelUpload(
			HttpServletRequest request
			,@RequestPart(value = "excelFile", required = true) MultipartFile excelFile
			,@RequestPart(value = "lawButtonId", required = true )DutyBotton param
	) throws Exception{
		//LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	    //MultipartFile excelFile = request.getFile("excelFile");
	    log.debug("========= relatedRawExcelUpload ========="+ excelFile);
	    
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}	    
		
		if(param.getLawButtonId() == null || param.getLawButtonId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
	    
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	        	
				//관리차수
	        	Baseline bl = new Baseline();
	        	bl.setCompanyId(login.getCompanyId());
				Baseline baseLineInfo = mainService.getRecentBaseline(bl);	        	
				if(baseLineInfo==null){				
					throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
				}				
				param.setBaselineId(baseLineInfo.getBaselineId());
				param.setCompanyId(login.getCompanyId());
				param.setBaselineId(baseLineInfo.getBaselineId());
	        	param.setInsertId(login.getUserId());//등록자
	        	param.setWorkplaceId(login.getWorkplaceId());//사업장아이디
	        	        	
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"A", "B", "C", "D", "E"
    					, "F", "G", "H", "I", "J"
    					, "K", "L", "M", "N", "O"
    					, "P", "Q", "R", "S", "T"
    					, "U", "V", "W", "X", "Y", "Z"};
	            int resultCode = userExcelService.relatedRawExcelUpload(destFile, coloumNm, param); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
	            }
	        }else {
	        	return new BaseResponse<Integer>(BaseResponseCode.UNKONWN_ERROR);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return new BaseResponse<Integer>(BaseResponseCode.UNKONWN_ERROR);
	    } 
	    
	}	
	
	
	
	//안전작업공사허가서 업로드
	@PostMapping(value="/safeWorkExcelUpload" )
	@ApiOperation(value = "This function save excel upload.",
    notes = "This function save excel upload.")	
    @ApiImplicitParams({
    	@ApiImplicitParam(required = true, name = "excelFile", value = "excel File" ,dataType = "MultipartFile")
    })		
	public BaseResponse<Integer> safeWorkExcelUpload(
			HttpServletRequest request
			,@RequestPart(value = "excelFile", required = true) MultipartFile excelFile
	) throws Exception{
		//LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	    //MultipartFile excelFile = request.getFile("excelFile");
	    log.debug("========= relatedRawExcelUpload ========="+ excelFile);
	    
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}	    
		
		ParamSafeWork param = new ParamSafeWork();
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	        	
	        	param.setUserId(login.getUserId());//등록자
	        	param.setCompanyId(login.getCompanyId());//회사아이디
	        	param.setWorkplaceId(login.getWorkplaceId());//사업장아이디
	        	        	
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"B"};
	            
	         // 파일 정보 생성
	            Long atchFileId = null;
	            List<AttachDetail> saveFiles = null;
	            List<AttachDetail> deleteFiles = null;
                saveFiles = new ArrayList<>();
                // 파일 생성
                AttachDetail detail = fileStorageService.createFile(excelFile);
                if (detail != null) {
                    // 기존 파일첨부 아이디가 있는 경우 해당 아이디로 파일 정보 생성
                    if (atchFileId != null) {
                        detail.setAtchFileId(atchFileId);
                    }
                    saveFiles.add(detail);
                }
                fileService.saveFiles(saveFiles, deleteFiles);
             // 파일 정보 생성
                
                param.setFileId(saveFiles.get(0).getAtchFileId());
	            int resultCode = userExcelService.safeWorkExcelUpload(destFile, coloumNm, param); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.SAVE_ERROR);
	            }
	            
	        }else {
	            return new BaseResponse<Integer>(BaseResponseCode.UNKONWN_ERROR);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
	        return new BaseResponse<Integer>(BaseResponseCode.UNKONWN_ERROR);
	    } 
	    
	}		
}


