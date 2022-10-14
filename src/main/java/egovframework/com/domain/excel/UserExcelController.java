package egovframework.com.domain.excel;

import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.domain.main.controller.MainController;
import egovframework.com.domain.main.domain.Baseline;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.service.MainService;
import egovframework.com.domain.main.service.MainServiceImpl;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.global.OfficeMessageSource;
import egovframework.com.global.annotation.SkipAuth;
import egovframework.com.global.authorization.SkipAuthLevel;
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
    
	@Autowired
	MainServiceImpl mainServiceImpl;    
    
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
	            
	            if(resultCode>0) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS);
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	            }
	        }else {
	        	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL);
	        }
	    }catch(Exception e) {
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
	    }catch(Exception e) {
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
		
		if("001".equals(login.getRoleCd())) {
			return new BaseResponse<Integer>(BaseResponseCode.AUTH_ERROR, OfficeMessageSource.getMessage("auth.error"));
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
	            String[] coloumNm = {"B","C","D","E","F","G","H"};
	            
	            log.info("safeWorkExcelUpload param : {}"+  param);
	            int resultCode = userExcelService.safeWorkExcelUpload(destFile, coloumNm, param, excelFile); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode==1) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
	            }else {
	            	return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, BaseResponseCode.DATA_IS_NULL.getMessage());
	            }
	            
	        }else {
	            return new BaseResponse<Integer>(BaseResponseCode.DATA_IS_NULL, BaseResponseCode.DATA_IS_NULL.getMessage());
	        }
	    }catch(Exception e) {
	       LOGGER.error("error:", e);
	       return new BaseResponse<Integer>(BaseResponseCode.EXCEL_TYPE, BaseResponseCode.EXCEL_TYPE.getMessage());
	    } 
	    
	}		
	
	
    /**
     * 필수 9개항목 파일 다운
     * 
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/getEssentialDutyExcel", method = RequestMethod.GET)
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file Down", notes = "file Down")	
	public void fileDown(
			@RequestParam(required = true) Long workplaceId
			,@RequestParam(required = true) Long baselineId
			, HttpServletRequest request
			, HttpServletResponse response) throws Exception {
		
		if(workplaceId == 0){				
	           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"workplaceId", "workplaceId 사업장아이디"});
		}
			
		if(baselineId == 0){				
           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"baselineId", "baselineId 차수"});
		}	
		
		
        //Workbook wb = new XSSFWorkbook();
        //Sheet sheet = wb.createSheet("첫번째 시트");
        
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("첫번째 시트");        
        
        Row row = null;
        Cell cell = null;
        int rowNum = 0;	
        
        
		//스타일 설정
		CellStyle xssfWb = wb.createCellStyle();
		 
		//정렬
		xssfWb.setAlignment(CellStyle.ALIGN_CENTER);
		xssfWb.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		//테두리 라인
		xssfWb.setBorderRight(HSSFCellStyle.BORDER_THIN);
		xssfWb.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		xssfWb.setBorderTop(HSSFCellStyle.BORDER_THIN);
		xssfWb.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		
		//배경색
		//xssfWb.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		xssfWb.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		xssfWb.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);   
		
		//폰트
		XSSFFont font = wb.createFont();
		font.setFontName("맑은고딕");
		font.setBold(true);
		xssfWb.setFont(font);
		
        MainExcelData param = new MainExcelData();
		param.setWorkplaceId(workplaceId);
		param.setBaselineId(baselineId);
		List<MainExcelData> resultList = mainServiceImpl.getEssentialDutyUserCopyData(param);
		
		
		
        // Header
        row = sheet.createRow(rowNum++);
        for(int i=0; i<18; i++) {
        	cell = row.createCell(i);
        	cell.setCellStyle(xssfWb);
        	if(i==0) {
        		cell.setCellValue("조항");        		
        	}else if(i==1) {
        		cell.setCellValue("내용1");
        	}else if(i==2) {
        		cell.setCellValue("조항");
        	}else if(i==3) {
        		cell.setCellValue("내용2");
        	}else if(i==4) {
        		cell.setCellValue("구분");
        	}else if(i==5) {
        		cell.setCellValue("체크리스트");
        	}else if(i==6) {
        		cell.setCellValue("체크포인트");
        	}else if(i==7) {
        		cell.setCellValue("guide");
        	}else if(i==8) {
        		cell.setCellValue("규정");
        	}else if(i==9) {
        		cell.setCellValue("문서");
        	}else if(i==10) {
        		cell.setCellValue("면담");
        	}else if(i==11) {
        		cell.setCellValue("증빙서류");
        	}else if(i==12) {
        		cell.setCellValue("관련 법령 및 가이드");
        	}else if(i==13) {
        		cell.setCellValue("이행 및 점검주기");
        	}else if(i==14) {
        		cell.setCellValue("이행 대상");
        	}else if(i==16) {
        		cell.setCellValue("평가항목");
        	}else if(i==16) {
        		cell.setCellValue("파일아이디");
        	}else if(i==17) {
        		cell.setCellValue("관계법령 체크");
        	}
        	
        }
        
        
		for(int i=0; i<resultList.size(); i++) {		
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(resultList.get(i).getArticleNo());
		}
		
        // 셀 너비 설정
        for (int i=0; i<=12; i++){ 
        	sheet.autoSizeColumn(i);
        	sheet.setColumnWidth(i, (sheet.getColumnWidth(i))+(short)2048);
        }		
		
		
        // 컨텐츠 타입과 파일명 지정
        response.setContentType("ms-vnd/excel");
        //response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Disposition", "attachment;filename=example.xlsx");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();		
		//FileUtils.downFile(request, response);		
	} 	
}


