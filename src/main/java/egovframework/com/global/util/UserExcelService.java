package egovframework.com.global.util;

import java.io.File;

import org.springframework.stereotype.Service;

import egovframework.com.domain.law.domain.DutyBotton;


@Service
public interface UserExcelService {
	public void excelUpload(File destFile, String[] coloumNm) throws Exception;
	
	public void relatedRawExcelUpload(File destFile, String[] coloumNm, DutyBotton vo) throws Exception;
	
}
