package egovframework.com.global.util;

import java.io.File;

import org.springframework.stereotype.Service;


@Service
public interface UserExcelService {
	public void excelUpload(File destFile, String[] coloumNm) throws Exception;
}
