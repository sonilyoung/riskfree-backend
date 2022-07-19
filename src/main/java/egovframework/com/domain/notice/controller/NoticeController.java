package egovframework.com.domain.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.notice.domain.Notice;
import egovframework.com.domain.notice.parameter.NoticeParameter;
import egovframework.com.domain.notice.parameter.NoticeSearchParameter;
import egovframework.com.domain.notice.service.NoticeService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 공지사항 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/notices")
@Api(tags = "Notices Management API")
public class NoticeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;
    
    /**
     * 공지사항 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Notice>
     */
    @GetMapping("/{companyId}/messages")
    @ApiOperation(value = "List of notices message of the companyId.", notes = "This function returns the list of notices message of the companyId.")
    @ApiImplicitParams({@ApiImplicitParam(name = "companyId", value = "Id of the company", dataType = "long")})
    public BaseResponse<List<Notice>> getNoticeList(HttpServletRequest request,
            @PathVariable Long companyId,
            @ApiParam @RequestBody NoticeSearchParameter parameter) {
        parameter.setCompanyId(companyId);
        return new BaseResponse<List<Notice>>(noticeService.getNoticeList(parameter));
    }
    

    /**
     * 공지사항 등록
     * 
     * @param param
     * @return 
     */
    @PostMapping("/{companyId}/messages")
    @ApiOperation(value = "Add a new Notice.", notes = "This function adds a new Notice message.")
    public BaseResponse<Long> createMssg(HttpServletRequest request, @PathVariable Long companyId,
            @ApiParam @RequestBody NoticeParameter parameter) {
        if (StringUtils.hasText(parameter.getTitle())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 제목"});
        }
        
        if (StringUtils.hasText(parameter.getContent())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 본문"});
        }
        
        if (StringUtils.hasText(parameter.getImprotCd())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 중요여부"});
        }
        
        parameter.setCompanyId(companyId);
        noticeService.insertNotice(parameter);
        return new BaseResponse<Long>(parameter.getNoticeId());
    }
    
    /**
     * 공지사항 상세보기
     * 
     * @param param
     * @return 
     */
    @GetMapping("/{companyId}/messages/{noticeId}")
    @ApiOperation(value = "Get the content of the noticeId.", notes = "This function returns the content of the noticeId.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "Id of the companyId",dataType = "long"),
            @ApiImplicitParam(name = "noticeId", value = "Id of the noticeId",  dataType = "long")})
    public BaseResponse<Notice> getNotice(HttpServletRequest request,
            @PathVariable Long companyId, @PathVariable Long noticeId) {
    	
    	Notice notice = noticeService.getNotice(companyId, noticeId);
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + companyId + ")", "noticeId (" + noticeId + ")"});
        } else {
        	// 조회수 증가
        	int viewCount = noticeService.updateViewCount(notice);
        	notice.setViewCnt(viewCount);
        }
        
        return new BaseResponse<Notice>(notice);
    }

    /**
     * 공지사항 수정
     * 
     * @param param
     * @return 
     */
    @PutMapping("/{companyId}/messages/{noticeId}")
    @ApiOperation(value = "Update the Notice message.", notes = "This function updates the specified Notice message.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board", dataType = "long"),
            @ApiImplicitParam(name = "bbsMssgId", value = "Id of the BBS message", dataType = "long")})
    public BaseResponse<Long> modifyMssg(HttpServletRequest request, @PathVariable Long companyId,
            @PathVariable Long noticeId, @ApiParam @RequestBody NoticeParameter parameter) {
    	Notice notice = noticeService.getNotice(companyId, noticeId);
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + companyId + ")", "noticeId (" + noticeId + ")"});
        } else {
            if (noticeId == null) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시판 ID"});
            }
            if (StringUtils.hasText(parameter.getTitle())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 제목"});
            }
            if (StringUtils.hasText(parameter.getContent())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 본문"});
            }
            parameter.setCompanyId(companyId);
            parameter.setNoticeId(noticeId);
            noticeService.updateNotice(parameter);
        }
        return new BaseResponse<Long>(noticeId);
    }
    
    /**
     * 공지사항 삭제
     * 
     * @param companyId, noticeId
     * @return 
     */
    @DeleteMapping("/{companyId}/messages/{noticeId}")
    @ApiOperation(value = "Delete the message.", notes = "This function deletes the specified message.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board", dataType = "long"),
            @ApiImplicitParam(name = "bbsMssgId", value = "Id of the BBS message", dataType = "long")})
    public BaseResponse<Boolean> deleteNotice(HttpServletRequest request,
            @PathVariable Long companyId, @PathVariable Long noticeId) {
        Notice notice = noticeService.getNotice(companyId, noticeId);
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + companyId + ")", "noticeId (" + noticeId + ")"});
        }
        noticeService.deleteNotice(companyId, noticeId);
        return new BaseResponse<Boolean>(true);
    }

//    @PostMapping("/{bbsBoardId}/messages/deleteArr")
//    @ApiOperation(value = "Delete messages by the list",
//            notes = "This function deletes the specified messages by the list.")
//    public BaseResponse<Boolean> deleteArrMssg(HttpServletRequest request,
//            @PathVariable Long bbsBoardId, @RequestParam(value = "deleteList") String deleteList) {
//        if (StringUtils.isEmpty(bbsBoardId)) {
//            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시판 ID"});
//        }
//        if (ObjectUtils.isEmpty(deleteList)) {
//            throw new BaseException(BaseResponseCode.DELETE_ERROR, new String[] {"삭제할 정보가 없습니다."});
//        }
//
//        try {
//
//            String[] deleteSplit = deleteList.split(",");
//            List<Long> bbsMssgList = new ArrayList<Long>();
//
//            for (int i = 0; i < deleteSplit.length; i++) {
//                bbsMssgList.add(Long.parseLong(deleteSplit[i]));
//            }
//            bbsService.deleteArrMssg(bbsBoardId, bbsMssgList);
//            return new BaseResponse<Boolean>(true);
//        } catch (Exception e) {
//            throw new BaseException(BaseResponseCode.DELETE_ERROR,
//                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
//        }
//    }
}