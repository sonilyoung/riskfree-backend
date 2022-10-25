package egovframework.com.domain.excel;

import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
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
import egovframework.com.domain.relatedlaw.dao.RelatedLawDAO;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.domain.RelatedLaw;
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
	
	@Autowired
	private RelatedLawDAO rlRepository;	
    
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
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
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
	
	
    /**
     * 관계법령업데이트
     * 
     * @param param
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/relatedRawExcel", method = RequestMethod.GET)
    @SkipAuth(skipAuthLevel = SkipAuthLevel.SKIP_AUTHORIZATION)
    @ApiOperation(value = "file Down", notes = "file Down")	
	public void relatedRawExcel(
			@RequestParam(required = true) Long companyId
			, @RequestParam(required = true) Long workplaceId
			, @RequestParam(required = true) Long baselineId
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
		
		//복사할 관계법령에 의무이행의 관리의 조치
		RelatedLaw rl = new RelatedLaw();
		rl.setCompanyId(companyId);
		rl.setWorkplaceId(workplaceId);
		rl.setTargetBaselineId(baselineId);		
		List<RelatedLaw> resultList = rlRepository.getRelatedRawCopyData(rl);
		
        // Header
        row = sheet.createRow(rowNum++);
        for(int i=0; i<13; i++) {
        	cell = row.createCell(i);
        	cell.setCellStyle(xssfWb);
        	if(i==0) {
        		cell.setCellValue("no");        		
        	}else if(i==1) {
        		cell.setCellValue("관계법령");
        	}else if(i==2) {
        		cell.setCellValue("항목");
        	}else if(i==3) {
        		cell.setCellValue("중처법시행령");
        	}else if(i==4) {
        		cell.setCellValue("위반법조항");
        	}else if(i==5) {
        		cell.setCellValue("위반행위");
        	}else if(i==6) {
        		cell.setCellValue("세부내용1");
        	}else if(i==7) {
        		cell.setCellValue("세부내용2");
        	}else if(i==8) {
        		cell.setCellValue("근거 법조문");
        	}else if(i==9) {
        		cell.setCellValue("1차위반");
        	}else if(i==10) {
        		cell.setCellValue("2차위반");
        	}else if(i==11) {
        		cell.setCellValue("3차위반");      
        	}else if(i==12) {
        		cell.setCellValue("관리상의 조치 내역");      
        	}
        }
        
        
		for(int i=0; i<resultList.size(); i++) {		
            row = sheet.createRow(rowNum++);
            //row.setHeight((short)1200);
            
            
            cell = row.createCell(0);//no
            cell.setCellValue(resultList.get(i).getDutyImproveId());
            cell = row.createCell(1);//관계법령
            cell.setCellValue(resultList.get(i).getRelatedArticle());
            cell = row.createCell(2);//항목
            cell.setCellValue(resultList.get(i).getArticleItem());
            cell = row.createCell(3);//중처법시행령
            cell.setCellValue(resultList.get(i).getSeriousAccdntDecree());
            cell = row.createCell(4);//위반법조항
            cell.setCellValue(resultList.get(i).getViolatedArticle());
            cell = row.createCell(5);//위반행위
            cell.setCellValue(resultList.get(i).getViolatedActivity());
            cell = row.createCell(6);//세부내용1
            cell.setCellValue(resultList.get(i).getViolationDetail1());
            cell = row.createCell(7);//세부내용2
            cell.setCellValue(resultList.get(i).getViolationDetail2());
            cell = row.createCell(8);//근거 법조문
            cell.setCellValue(resultList.get(i).getBaseArticle());
            cell = row.createCell(9);//1차위반
            cell.setCellValue(resultList.get(i).getStPenalty1());
            cell = row.createCell(10);//2차위반
            cell.setCellValue(resultList.get(i).getStPenalty2());
            cell = row.createCell(11);//3차위반
            cell.setCellValue(resultList.get(i).getStPenalty2());
            cell = row.createCell(12);//관리상의 조치 내역
            cell.setCellValue(resultList.get(i).getAcctionCn());
            
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (Math.min(255 * 256, sheet.getColumnWidth(i) + 2400)));            

		}
		
        // 셀 너비 설정
        //for (int i=0; i<=12; i++){ 
        	//sheet.autoSizeColumn(i);
        	//sheet.setColumnWidth(i, (Math.min(255 * 256, sheet.getColumnWidth(i) + 1200)));
        	//sheet.setDefaultRowHeight(Math.min(255 * 256, sheet.getColumnWidth(i) + 1200));
        //}		
		
		
        // 컨텐츠 타입과 파일명 지정
		//xls확장자로 다운로드   
		String tempName = "관계법령의무이행조치";
		response.setContentType("ms-vnd/excel");
		//response.setContentType("application/x-msdownload;charset=utf-8");
		String fileName = URLEncoder.encode(tempName, ("UTF-8"));
		response.setHeader("Set-Cookie", "fileDownloadToken=Y; path=/");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");         
        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
		//FileUtils.downFile(request, response);
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
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
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
	@RequestMapping(value = "/getUserDutyExcel", method = RequestMethod.GET)
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
        for(int i=0; i<13; i++) {
        	cell = row.createCell(i);
        	cell.setCellStyle(xssfWb);
        	if(i==0) {
        		cell.setCellValue("조항");        		
        	}else if(i==1) {
        		cell.setCellValue("내용");
        	}else if(i==2) {
        		cell.setCellValue("체크리스트");
        	}else if(i==3) {
        		cell.setCellValue("가이드라인");
        	}else if(i==4) {
        		cell.setCellValue("증빙서류");
        	}else if(i==5) {
        		cell.setCellValue("이행 및 점검주기");
        	}else if(i==6) {
        		cell.setCellValue("이행대상");
        	}else if(i==7) {
        		cell.setCellValue("articleNo");
        	}else if(i==8) {
        		cell.setCellValue("그룹아이디");
        	}else if(i==9) {
        		cell.setCellValue("법령코드");
        	}else if(i==10) {
        		cell.setCellValue("파일아이디");
        	}else if(i==11) {
        		cell.setCellValue("평가"); //엑셀내용      
        	}else if(i==12) {
        		cell.setCellValue("매니저체크"); //매니저체크      
        	}
        	
        }
        
        
		for(int i=0; i<resultList.size(); i++) {		
            row = sheet.createRow(rowNum++);
            //row.setHeight((short)1200);
            
            
            cell = row.createCell(0);//조항
            cell.setCellValue(resultList.get(i).getRelatedArticle());
            cell = row.createCell(1);//내용
            cell.setCellValue(resultList.get(i).getShGoals());
            cell = row.createCell(2);//체크리스트
            cell.setCellValue(resultList.get(i).getDetailedItems());
            
            
            
            cell = row.createCell(3);//가이드라인
            //to enable newlines you need set a cell styles with wrap=true
            CellStyle cs = wb.createCellStyle();
            cs.setWrapText(true);
            cs.setVerticalAlignment((short)1);	// 세로 정렬 중단
            cell.setCellStyle(cs);

            //increase row height to accomodate two lines of text
            row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

            //adjust column width to fit the content
            sheet.autoSizeColumn((short)2); 
            row.setHeight((short)-1);              
            cell.setCellValue(resultList.get(i).getGuideline());
            
            
            
            
            cell = row.createCell(4);//증빙서류
            //to enable newlines you need set a cell styles with wrap=true
            cs.setWrapText(true);
            cell.setCellStyle(cs);

            //increase row height to accomodate two lines of text
            row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

            //adjust column width to fit the content
            sheet.autoSizeColumn((short)2); 
            row.setHeight((short)-1);              
            cell.setCellValue(resultList.get(i).getShGoal());
        	
            
            
            cell = row.createCell(5);//이행 및 점검주기
            //to enable newlines you need set a cell styles with wrap=true
            cs.setWrapText(true);
            cs.setVerticalAlignment((short)1);	// 세로 정렬 중단
            cell.setCellStyle(cs);

            //increase row height to accomodate two lines of text
            row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

            //adjust column width to fit the content
            sheet.autoSizeColumn((short)2); 
            row.setHeight((short)-1);              
            cell.setCellValue(resultList.get(i).getDutyCycle());
            
            
            
            
            
            cell = row.createCell(6);//이행대상
            //to enable newlines you need set a cell styles with wrap=true
            cs.setWrapText(true);
            cs.setVerticalAlignment((short)1);	// 세로 정렬 중단
            cell.setCellStyle(cs);

            //increase row height to accomodate two lines of text
            row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

            //adjust column width to fit the content
            sheet.autoSizeColumn((short)2); 
            row.setHeight((short)-1);                    
            cell.setCellValue(resultList.get(i).getDutyAssigned());
            //////////
            
            
            cell = row.createCell(7);//articleNo            
            cell.setCellValue(resultList.get(i).getArticleNo());
            
            cell = row.createCell(8);//그룹아이디
            cell.setCellValue(resultList.get(i).getGroupId());
            
            cell = row.createCell(9);//법령코드
            
            if(resultList.get(i).getArticleCd()!=null) {
            	cell.setCellValue(resultList.get(i).getArticleCd());
            }
              
            cell = row.createCell(10);//파일아이디
            if(resultList.get(i).getFileId()!=null) {
            	cell.setCellValue(resultList.get(i).getFileId());
            }
            
            cell = row.createCell(11);//평가
            if(resultList.get(i).getEvaluation()!=null) {
            	cell.setCellValue(resultList.get(i).getEvaluation());
            }
            
            cell = row.createCell(12);//매니저체크
            if(resultList.get(i).getManagerChecked()!=null) {
            	cell.setCellValue(resultList.get(i).getManagerChecked());
            }
            
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, (Math.min(255 * 256, sheet.getColumnWidth(i) + 2400)));            

		}
		
        // 셀 너비 설정
        //for (int i=0; i<=12; i++){ 
        	//sheet.autoSizeColumn(i);
        	//sheet.setColumnWidth(i, (Math.min(255 * 256, sheet.getColumnWidth(i) + 1200)));
        	//sheet.setDefaultRowHeight(Math.min(255 * 256, sheet.getColumnWidth(i) + 1200));
        //}		
		
		
        // 컨텐츠 타입과 파일명 지정
		//xls확장자로 다운로드   
		String tempName = "필수의무조치내역";
		response.setContentType("ms-vnd/excel");
		//response.setContentType("application/x-msdownload;charset=utf-8");
		String fileName = URLEncoder.encode(tempName, ("UTF-8"));
		response.setHeader("Set-Cookie", "fileDownloadToken=Y; path=/");
		response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");         
        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
        response.getOutputStream().flush();
        response.getOutputStream().close();
		//FileUtils.downFile(request, response);
	} 	
	
    //필수의무조치내역 엑셀업로드
	@PostMapping(value="/userDutyExcel" , consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@ApiOperation(value = "This function save userDutyExcel upload.",
    notes = "This function save excel upload.")	
    @ApiImplicitParams({
    	@ApiImplicitParam(required = true, name = "excelFile", value = "excel File" ,dataType = "MultipartFile")
    })		
	public BaseResponse<Integer> userDutyExcel(
			HttpServletRequest request
			,@RequestPart(value = "excelFile", required = true) MultipartFile excelFile
			,@RequestPart(value = "params", required = true )MainExcelData param
	) throws Exception{		
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}	    
		if(param.getWorkplaceId() == 0){				
	           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
	                    new String[] {"workplaceId", "workplaceId 사업장아이디"});
		}		
		
		if(param.getBaselineId() == 0){				
           throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"baselineId", "baselineId 차수"});
		}		
		
        String originalFileName = excelFile.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFileName);
        if(!fileExtension.toUpperCase().equals("XLS") && !fileExtension.toUpperCase().equals("XLSX")) {
        	return new BaseResponse<Integer>(BaseResponseCode.EXTENSION_ERROR);
        }
        
		//관리차수
    	Baseline bl = new Baseline();
    	bl.setCompanyId(login.getCompanyId());
    	bl.setBaselineId(param.getBaselineId());
		Baseline baseLineInfo = mainService.getBaseline(bl);
		if(baseLineInfo==null){				
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, BaseResponseCode.DATA_IS_NULL.getMessage());	
		}else {
			param.setBaselineId(baseLineInfo.getBaselineId());
			param.setBaselineStart(baseLineInfo.getBaselineStart()); 
			param.setBaselineEnd(baseLineInfo.getBaselineEnd()); 
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
	            int resultCode = userExcelService.userDutyExcel(destFile, coloumNm, param, excelFile); // service단 호출
	            destFile.delete(); // 업로드된 엑셀파일 삭제
	            
	            if(resultCode>0) {
		            return new BaseResponse<Integer>(BaseResponseCode.SAVE_SUCCESS, BaseResponseCode.SAVE_SUCCESS.getMessage());
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
}


