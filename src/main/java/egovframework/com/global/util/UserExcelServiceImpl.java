package egovframework.com.global.util;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.domain.main.domain.ExcelTitleType;
import egovframework.com.domain.main.service.MainServiceImpl;
import egovframework.com.global.util.excel.ExcelRead;
import egovframework.com.global.util.excel.ExcelReadOption;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserExcelServiceImpl implements UserExcelService{

	@Autowired
	MainServiceImpl mainServiceImpl;
	
	@Override
	public void excelUpload(File destFile, String[] coloumNm) throws Exception {
	   // TODO Auto-generated method stub
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
       C-related_article	관계법령
       H-GUIDELINE 	지침서*/       
       
       List<LinkedHashMap<String, String>> resultData = new ArrayList<LinkedHashMap<String, String>>(); 
       for(LinkedHashMap<String, String> article: excelContent){
         LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
         
         if(
        		 !(article.get("D")==null 
        		 && article.get("F")==null
        		 && article.get("L")==null
        		 && article.get("N")==null
        		 && article.get("O")==null
        		 && article.get("C")==null
        		 && article.get("H")==null)
        		 &&
        		 !(article.get("D").equals("") 
        		 && article.get("F").equals("")
        		 && article.get("L").equals("")
        		 && article.get("N").equals("")
        		 && article.get("O").equals("")
        		 && article.get("C").equals("")
        		 && article.get("H").equals(""))        		 
         ) {
        	 
        	 if(ExcelTitleType.TITLE1.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE1.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE2.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE2.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE3.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE3.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE4.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE4.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE5.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE5.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE6.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE6.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE7.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE7.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE8.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE8.getCode());//그룹다이디 셋팅
        	 }else if(ExcelTitleType.TITLE9.getName().equals(article.get("D").trim())) {
        		 data.put("A", ExcelTitleType.TITLE9.getCode());//그룹다이디 셋팅
        	 }
        	 
             data.put("D", article.get("D"));//sh_goals
             data.put("F", article.get("F"));
             data.put("L", article.get("L"));
             data.put("N", article.get("N"));
             data.put("O", article.get("O"));
             data.put("C", article.get("C"));
             data.put("H", article.get("H"));
             
             resultData.add(data);        	 
         }else {
        	 System.out.println("데이터없음 : " + article);
         }         

         //System.out.println(article.get("B"));
         //System.out.println(article.get("C"));
         //System.out.println(article.get("D"));
         //System.out.println(article.get("E"));
         //System.out.println(article.get("F"));
         //System.out.println(article.get("G"));
         //DAO.excelUpload(article); 
       }
       
       System.out.println(resultData);
       
       mainServiceImpl.insertEssentialDuty(resultData);
	}
}
