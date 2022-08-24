package egovframework.com.domain.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
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
		
	@PostMapping(value="/excelUpload")
	@ApiOperation(value = "This function save excel upload.",
    notes = "This function save excel upload.")	
    @ApiImplicitParams({
    	@ApiImplicitParam(required = true, name = "excelFile", value = "excel File" ,dataType = "MultipartFile")
    })		
	//public BaseResponse<Map<String, String>> excelUpload(MultipartHttpServletRequest request) throws Exception{
	public BaseResponse<LinkedHashMap<String, String>> excelUpload(
			//@RequestPart("excelFile") MultipartFile excelFile
			@RequestPart(value = "excelFile") MultipartFile excelFile
	) throws Exception{
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
	    //MultipartFile excelFile = request.getFile("excelFile");
	    log.debug("========= 엑셀업로드 ========="+ excelFile);
	    try { 
	        if(excelFile != null && !excelFile.isEmpty()) {
	            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmsss"); 
	            Date time = new Date(); 
	            String fmtDate=format.format(time);
	            
	            //String stordFilePath = GlobalsProperties.getProperty("Globals.fileStorePath");
	            
	            String stordFilePath = "d:/temp";
	            
	            
	            File destFile = new File(stordFilePath + File.separator + fmtDate+"_"+excelFile.getOriginalFilename()); // 파일위치 지정
	            excelFile.transferTo(destFile); // 엑셀파일 생성
	            String[] coloumNm = {"A", "B", "C", "D", "E"
    					, "F", "G", "H", "I", "J"
    					, "K", "L", "M", "N", "O"
    					, "P", "Q", "R", "S", "T"
    					, "U", "V", "W", "X", "Y", "Z"};
	            userExcelService.excelUpload(destFile, coloumNm); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            result.put("code", BaseResponseCode.SUCCESS.getCode());
	            result.put("message", BaseResponseCode.SUCCESS.getMessage());	            
	            return new BaseResponse<LinkedHashMap<String, String>>(result);
	        }else {
	            result.put("code", BaseResponseCode.UNKONWN_ERROR.getCode());
	            result.put("message", BaseResponseCode.UNKONWN_ERROR.getMessage());
	            return new BaseResponse<LinkedHashMap<String, String>>(result);
	        }
	    }catch(Exception e) {
	        e.printStackTrace();
            result.put("code", BaseResponseCode.UNKONWN_ERROR.getCode());
            result.put("message", BaseResponseCode.UNKONWN_ERROR.getMessage());	        
            return new BaseResponse<LinkedHashMap<String, String>>(result);
	    } 
	    
	}
	
}


