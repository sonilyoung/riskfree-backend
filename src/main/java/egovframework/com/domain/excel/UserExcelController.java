package egovframework.com.domain.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.domain.main.controller.MainController;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.service.MainService;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.common.domain.GlobalsProperties;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
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
    
    public static final String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	
    //필수의무조치내역 엑셀업로드
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
		
        String originalFileName = excelFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        if(!fileExtension.toUpperCase().equals("XLS") && !fileExtension.toUpperCase().equals("XLSX")) {
        	return new BaseResponse<Integer>(BaseResponseCode.EXTENSION_ERROR);
        }
	    
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            File fileDir = new File(stordFilePath);
	            // root directory 없으면 생성
	        	if (!fileDir.exists()) {
	        		fileDir.mkdirs(); //폴더 생성합니다.
	        	}             
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"A", "B", "C", "D", "E"
    					, "F", "G", "H", "I", "J"
    					, "K", "L", "M", "N", "O"
    					, "P", "Q", "R", "S", "T"
    					, "U", "V", "W", "X", "Y", "Z"};
	            
	            log.info("excelUpload param : {}"+  login);
	            int resultCode = userExcelService.excelUpload(destFile, coloumNm, login, excelFile); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	            }
	        }else {
	        	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	        }
	    }catch(BaseException e) {
	       LOGGER.error("error:", e);
	        return new BaseResponse<Integer>(BaseResponseCode.EXCEL_TYPE, BaseResponseCode.EXCEL_TYPE.getMessage());
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
	    log.info("========= relatedRawExcelUpload ========="+ excelFile);
	    
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}	   
		
        String originalFileName = excelFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        if(!fileExtension.toUpperCase().equals("XLS") && !fileExtension.toUpperCase().equals("XLSX")) {
        	return new BaseResponse<Integer>(BaseResponseCode.EXTENSION_ERROR);
        }		
		
		if(param.getLawButtonId() == null || param.getLawButtonId()==0){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}		
		log.info("ButtonId : {}"+  param.getLawButtonId());
		
		//관리차수
		Baseline bl = new Baseline();
		bl.setCompanyId(login.getCompanyId());
		Baseline baseLineInfo = mainService.getRecentBaseline(bl);
		log.info("baseLineInfo : {}"+  baseLineInfo);
		
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.PARAMS_ERROR);	
		}
		
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	        	
				param.setBaselineId(baseLineInfo.getBaselineId());
				param.setCompanyId(login.getCompanyId());
				param.setBaselineId(baseLineInfo.getBaselineId());
	        	param.setInsertId(login.getUserId());//등록자
	        	param.setWorkplaceId(login.getWorkplaceId());//사업장아이디
	        	        	
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            File fileDir = new File(stordFilePath);
	            // root directory 없으면 생성
	        	if (!fileDir.exists()) {
	        		fileDir.mkdirs(); //폴더 생성합니다.
	        	}        		            
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"A", "B", "C", "D", "E"
    					, "F", "G", "H", "I", "J"
    					, "K", "L", "M", "N", "O"
    					, "P", "Q", "R", "S", "T"
    					, "U", "V", "W", "X", "Y", "Z"};
	            
	            
	            log.info("relatedRawExcelUpload param : {}"+  param);
                int resultCode = userExcelService.relatedRawExcelUpload(destFile, coloumNm, param, excelFile); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else if(resultCode==9001) {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, OfficeMessageSource.getMessage("baseline.nodata"));
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	            }	            
	        }else {
	        	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	        }
	    }catch(BaseException e) {
	       LOGGER.error("error:", e);
	       return new BaseResponse<Integer>(BaseResponseCode.EXCEL_TYPE, BaseResponseCode.EXCEL_TYPE.getMessage());
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
		
        String originalFileName = excelFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        if(!fileExtension.toUpperCase().equals("XLS") && !fileExtension.toUpperCase().equals("XLSX")) {
        	return new BaseResponse<Integer>(BaseResponseCode.EXTENSION_ERROR);
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
	            File fileDir = new File(stordFilePath);
	            // root directory 없으면 생성
	        	if (!fileDir.exists()) {
	        		fileDir.mkdirs(); //폴더 생성합니다.
	        	}                    
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"B"};
	            
	            log.info("safeWorkExcelUpload param : {}"+  param);
	            int resultCode = userExcelService.safeWorkExcelUpload(destFile, coloumNm, param, excelFile); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
	            }else if(resultCode==9001) {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, OfficeMessageSource.getMessage("baseline.nodata"));
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, BaseResponseCode.DATA_IS_NULL.getMessage());
	            }
	            
	        }else {
	            return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, BaseResponseCode.DATA_IS_NULL.getMessage());
	        }
	    }catch(BaseException e) {
	       LOGGER.error("error:", e);
	       return new BaseResponse<Integer>(BaseResponseCode.EXCEL_TYPE, BaseResponseCode.EXCEL_TYPE.getMessage());
	    } 
	    
	}		
}


