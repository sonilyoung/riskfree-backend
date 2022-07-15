package egovframework.com.global.util.fcc.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import egovframework.com.global.util.WebUtil;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @Class Name  : FileUploadUtil.java
 * @Description : Spring 기반 File Upload 유틸리티
 * @Modification Information
 *
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.08.26       한성곤                  최초 생성
 *
 * @author 공통컴포넌트 개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see
 */
public class OfficeFileUploadUtil extends OfficeFormBasedFileUtil {
	/**
	 * 파일을 Upload 처리한다.
	 *
	 * @param request
	 * @param where
	 * @param maxFileSize
	 * @return
	 * @throws Exception
	 */
	public static List<OfficeFormBasedFileVo> uploadFiles(HttpServletRequest request, String where, long maxFileSize) throws Exception {
		List<OfficeFormBasedFileVo> list = new ArrayList<OfficeFormBasedFileVo>();

		MultipartHttpServletRequest mptRequest = (MultipartHttpServletRequest) request;
		Iterator<?> fileIter = mptRequest.getFileNames();

		while (fileIter.hasNext()) {
			MultipartFile mFile = mptRequest.getFile((String) fileIter.next());

			OfficeFormBasedFileVo vo = new OfficeFormBasedFileVo();

			String tmp = mFile.getOriginalFilename();

			if (tmp.lastIndexOf("\\") >= 0) {
				tmp = tmp.substring(tmp.lastIndexOf("\\") + 1);
			}

			vo.setFileName(tmp);
			vo.setContentType(mFile.getContentType());
			vo.setServerSubPath(getTodayString());
			vo.setPhysicalName(getPhysicalFileName());
			vo.setSize(mFile.getSize());

			if (tmp.lastIndexOf(".") >= 0) {
				vo.setPhysicalName(vo.getPhysicalName()); // 2012.11 KISA 보안조치
			}

			if (mFile.getSize() > 0) {
				InputStream is = null;

				try {
					is = mFile.getInputStream();
					saveFile(is, new File(WebUtil.filePathBlackList(where + SEPERATOR + vo.getServerSubPath() + SEPERATOR + vo.getPhysicalName())));
				} finally {
					if (is != null) {
						is.close();
					}
				}
				list.add(vo);
			}
		}

		return list;
	}
}
