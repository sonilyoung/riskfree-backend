package egovframework.com.global.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.domain.main.domain.ExcelSafeWorkType;
import egovframework.com.domain.main.domain.ExcelTitleType;
import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.main.service.MainServiceImpl;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;
import egovframework.com.domain.relatedlaw.service.RelatedLawServiceImpl;
import egovframework.com.global.file.domain.AttachDetail;
import egovframework.com.global.file.service.FileService;
import egovframework.com.global.file.service.FileStorageService;
import egovframework.com.global.http.exception.BaseException;
import egovframework.com.global.util.excel.ExcelRead;
import egovframework.com.global.util.excel.ExcelReadOption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserExcelServiceImpl implements UserExcelService{

	@Autowired
	MainServiceImpl mainServiceImpl;
	
	@Autowired
	RelatedLawServiceImpl relatedLawServiceImpl;
	
    @Autowired
    private FileService fileService;    
    
    @Autowired
    private FileStorageService fileStorageService;	
	
	@Override
	public int excelUpload(File destFile, String[] coloumNm, Login login, MultipartFile excelFile) throws Exception {
	   // TODO Auto-generated method stub
		
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
        Long fileId = saveFiles.get(0).getAtchFileId();		
        //파일 정보 생성		
        
       //버전확인
       MainExcelData versionInfo = mainServiceImpl.getEssentialDutyVersion();
        
		
	   int result = 0;
	   boolean addFlag = true;
       ExcelReadOption excelReadOption = new ExcelReadOption();
       excelReadOption.setFilePath(destFile.getAbsolutePath()); //파일경로 추가
       excelReadOption.setOutputColumns(coloumNm); //추출할 컬럼명 추가
       excelReadOption.setStartRow(2); //시작행(헤더부분 제외)
        
       List<LinkedHashMap<String, String>>excelContent  = ExcelRead.read(excelReadOption);
            
       /*D - SH_GOALS 필수의무조치내역
       F -detailed_items 의무조치별상세점검항목
       L-sh_goal	점검서류목록
       N-duty_cycle	이행주기
       O-duty_assigned	준수대상
       C-related_excelData	관계법령
       H-GUIDELINE 	지침서*/       
       
       List<LinkedHashMap<String, String>> resultData = new ArrayList<LinkedHashMap<String, String>>(); 
       for(LinkedHashMap<String, String> excelData: excelContent){
         LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
         
         if(
        		 !(excelData.get("D")==null 
        		 && excelData.get("F")==null
        		 && excelData.get("L")==null
        		 && excelData.get("N")==null
        		 && excelData.get("O")==null
        		 && excelData.get("C")==null
        		 && excelData.get("H")==null)
        		 &&
        		 !(excelData.get("D").equals("") 
        		 && excelData.get("F").equals("")
        		 && excelData.get("L").equals("")
        		 && excelData.get("N").equals("")
        		 && excelData.get("O").equals("")
        		 && excelData.get("C").equals("")
        		 && excelData.get("H").equals(""))        		 
         ) {
        	 
        	 if(ExcelTitleType.TITLE1.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE1.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE2.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE2.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE3.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE3.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE4.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE4.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE5.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE5.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE6.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE6.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE7.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE7.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE8.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE8.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE9.getName().equals(excelData.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE9.getCode());//그룹다이디 셋팅
        	 }else {
        		 addFlag = false;
        	 }
        	 
             data.put("D", excelData.get("D"));//sh_goals
             data.put("F", excelData.get("F"));
             data.put("L", excelData.get("L"));
             data.put("N", excelData.get("N"));
             data.put("O", excelData.get("O"));
             data.put("C", excelData.get("C"));
             data.put("H", excelData.get("H"));
             
             data.put("articleVersion", String.valueOf(versionInfo.getArticleVersion()+1));//버전정보
             data.put("attachFileId", String.valueOf(fileId));//파일아이디
             
             if(addFlag) {
            	 resultData.add(data);        	 
             }
             addFlag = true;       	 
         }else {
        	 log.debug("데이터없음 : " + excelData);
         }         

       }
       
       log.debug("excel : " + resultData);
       
       result = mainServiceImpl.insertEssentialDuty(resultData, login);
       
       return result;
	}
	
	
	@Transactional
	public int relatedRawExcelUpload(File destFile, String[] coloumNm, DutyBotton vo, MultipartFile excelFile) throws Exception {
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
        vo.setAttachId(saveFiles.get(0).getAtchFileId());		
        //파일 정보 생성
        
       //버튼에 파일아이디 업데이트
       relatedLawServiceImpl.updateButtonAttachId(vo);
        
	   // TODO Auto-generated method stub
	   int result = 0;
	   boolean addFlag = true;
       ExcelReadOption excelReadOption = new ExcelReadOption();
       excelReadOption.setFilePath(destFile.getAbsolutePath()); //파일경로 추가
       excelReadOption.setOutputColumns(coloumNm); //추출할 컬럼명 추가
       excelReadOption.setStartRow(3); //시작행(헤더부분 제외)
        
       List<LinkedHashMap<String, String>>excelContent  = ExcelRead.read(excelReadOption);
       List<LinkedHashMap<String, String>> resultData = new ArrayList<LinkedHashMap<String, String>>();
       
       for(LinkedHashMap<String, String> excelData: excelContent){
         LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
        	 
         if(
        		 !(excelData.get("B")==null 
        		 && excelData.get("C")==null
        		 && excelData.get("D")==null
        		 && excelData.get("E")==null
        		 && excelData.get("F")==null
        		 && excelData.get("G")==null
        		 && excelData.get("H")==null
        		 && excelData.get("I")==null)
        		 &&
        		 !(excelData.get("B").equals("") 
        		 && excelData.get("C").equals("")
        		 && excelData.get("D").equals("")
        		 && excelData.get("E").equals("")
        		 && excelData.get("F").equals("")
        		 && excelData.get("G").equals("")
        		 && excelData.get("H").equals("")
        		 && excelData.get("I").equals(""))
         ) {         
	         data.put("B", excelData.get("B"));//관련법령
	         data.put("C", excelData.get("C"));//항목
	         data.put("D", excelData.get("D"));//중대재해처벌법시행령
	         data.put("E", excelData.get("E"));//위반법조항
	         data.put("F", excelData.get("F"));//위반행위
	         data.put("G", excelData.get("G"));//세부내용1
	         data.put("H", excelData.get("H"));//세부내용2
	         data.put("I", excelData.get("I"));//근거법조문
	         data.put("J", excelData.get("J"));//1차위반
	         data.put("K", excelData.get("K"));//2차위반
	         data.put("L", excelData.get("L"));//3차위반
    	 }else {
    		 addFlag = false;
    	 }
         data.put("A", String.valueOf(vo.getLawButtonId()));//버튼아이디 셋팅
         data.put("W", String.valueOf(vo.getCompanyId()));//회사아이디
         data.put("X", String.valueOf(vo.getWorkplaceId()));//사업장아이디
         data.put("Y", String.valueOf(vo.getBaselineId()));//차수아이디
         data.put("Z", String.valueOf(vo.getInsertId()));//등록자
         
         if(addFlag) {
        	 resultData.add(data);        	 
         }
         addFlag = true;         
       }
       
       log.debug("excel : " + resultData);
       
       result = relatedLawServiceImpl.insertRelatedRaw(resultData, vo);
       return result;
	}	
	
	
	//안전작업공사허가서 업로드
	@Transactional
	public int safeWorkExcelUpload(File destFile, String[] coloumNm, ParamSafeWork vo, MultipartFile excelFile) throws Exception, BaseException {
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
        vo.setFileId(saveFiles.get(0).getAtchFileId());		
        // 파일 정보 생성
        
		
	   // TODO Auto-generated method stub
	   int result = 0;
	   boolean addFlag = true;
       ExcelReadOption excelReadOption = new ExcelReadOption();
       excelReadOption.setFilePath(destFile.getAbsolutePath()); //파일경로 추가
       excelReadOption.setOutputColumns(coloumNm); //추출할 컬럼명 추가
       //excelReadOption.setStartRow(3); //시작행(헤더부분 제외)
        
       List<LinkedHashMap<String, String>>excelContent  = ExcelRead.read(excelReadOption);
       List<LinkedHashMap<String, String>> resultData = new ArrayList<LinkedHashMap<String, String>>();
       
       for(LinkedHashMap<String, String> excelData: excelContent){
         LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
        	 
         if(excelData.get("B")!=null && !excelData.get("B").equals("")) {  
        	 
        	 //화기,밀폐,정전,굴착,방사선,고소,중장비
        	 if(ExcelSafeWorkType.TITLE1.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE1.getCode());//화기
        	 } else if(ExcelSafeWorkType.TITLE2.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE2.getCode());//밀폐
        	 } else if(ExcelSafeWorkType.TITLE3.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE3.getCode());//정전
        	 } else if(ExcelSafeWorkType.TITLE4.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE4.getCode());//굴착
        	 } else if(ExcelSafeWorkType.TITLE5.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE5.getCode());//방사선        		 
        	 } else if(ExcelSafeWorkType.TITLE6.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE6.getCode());//고소
        	 } else if(ExcelSafeWorkType.TITLE7.getName().equals(excelData.get("B").trim())) {
        		 data.put("B", ExcelSafeWorkType.TITLE7.getCode());//중장비
        	 }else {
        		 addFlag = false;
        	 }
        	 
             data.put("companyId", String.valueOf(vo.getCompanyId()));//회사아이디
             data.put("workplaceId", String.valueOf(vo.getWorkplaceId()));//사업장아이디
             data.put("attachId", String.valueOf(vo.getFileId()));//파일아이디
             data.put("userId", String.valueOf(vo.getUserId()));//등록자
             
             if(addFlag) {
            	 resultData.add(data);        	 
             }
             addFlag = true;
         }
         log.debug("excel : " + data);
       }
       
       if(resultData.size()>0) {
    	   result = mainServiceImpl.insertSafeWorkExcelUpload(resultData, vo);
       }
       return result;
	}	
}
