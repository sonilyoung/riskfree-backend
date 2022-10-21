package egovframework.com.global.util;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.domain.main.domain.MainExcelData;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.relatedlaw.domain.DutyBotton;


@Service
public interface UserExcelService {
	public int excelUpload(File destFile, String[] coloumNm, Login login, MultipartFile excelFile) throws Exception;
	
	public int relatedRawExcelUpload(File destFile, String[] coloumNm, DutyBotton vo, MultipartFile excelFile) throws Exception;

	public int safeWorkExcelUpload(File destFile, String[] coloumNm, ParamSafeWork vo, MultipartFile excelFile) throws Exception;
	
	public int userDutyExcel(File destFile, String[] coloumNm, MainExcelData vo, MultipartFile excelFile) throws Exception;
}
