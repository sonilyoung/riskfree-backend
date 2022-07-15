package egovframework.com.global.util.upload.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 업로드 정보를 관리하기 위한 VO 클래스
 * @author paul
 * @since 2016.02.04
 * @version 1.0
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일     	     수정자            수정내용
 *  -------       --------    ---------------------------
 *   2016.02.04    paul        최초 생성
 *   2017.04.05    suji.h      변수명 정리
 * </pre>
 */
public class UploadItemVO {

	private String name;
	
	private CommonsMultipartFile fileData;
	
	private String savedFilename;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

	public String getSavedFilename() {
		return savedFilename;
	}

	public void setSavedFilename(String savedFilename) {
		this.savedFilename = savedFilename;
	}

	public String getExtension() {
		if (StringUtils.isBlank(name)) {
			return "";
		}
		int dotPos = StringUtils.lastIndexOf(name, ".");
		return StringUtils.substring(name, dotPos);
	}
}