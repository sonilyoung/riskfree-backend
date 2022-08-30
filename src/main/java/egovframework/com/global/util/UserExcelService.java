package egovframework.com.global.util;

import java.io.File;

import org.springframework.stereotype.Service;

import egovframework.com.domain.law.domain.DutyBotton;
import egovframework.com.domain.main.domain.ParamSafeWork;
import egovframework.com.domain.portal.logn.domain.Login;


@Service
public interface UserExcelService {
	public void excelUpload(File destFile, String[] coloumNm, Login login) throws Exception;
	
	public void relatedRawExcelUpload(File destFile, String[] coloumNm, DutyBotton vo) throws Exception;

	public void safeWorkExcelUpload(File destFile, String[] coloumNm, ParamSafeWork vo) throws Exception;
	
}
