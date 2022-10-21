package egovframework.com.domain.common.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.com.global.http.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 공통 업로드 API 컨트롤러
 * 
 * @author jkj0411
 *
 */
@RestController
@RequestMapping("/common/upload")
@Api(tags = "공통 - 업로드 API")
public class UploadController {


    Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 파일 목록 리턴
     * 
     * @param fileSeq
     * @return
     */
    @GetMapping("/getFiles")
    @ApiOperation(value = "업로드", notes = "업로드 파일 목록")
    public BaseResponse<Map<String, Object>> getFiles(@ApiParam int fileSeq) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "aa");

        return new BaseResponse<Map<String, Object>>(map);
    }

    /**
     * 파일 업로드 처리
     * 
     * @param multiRequest
     * @return
     * @throws InterruptedException
     */
    @PostMapping("/fileUpload")
    @ApiOperation(value = "업로드처리", notes = "파일 업로드처리")
    public BaseResponse<Map<String, Object>> fileUpload(
            @ApiParam final MultipartHttpServletRequest multiRequest) {
        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        if (!files.isEmpty()) {
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
                MultipartFile file = entry.getValue();
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("temp_atch_file_id", "FILE_000001");

        return new BaseResponse<Map<String, Object>>(map);
    }
}
