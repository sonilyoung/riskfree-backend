package egovframework.com.domain.notice.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import egovframework.com.domain.portal.logn.domain.Login;
import egovframework.com.domain.portal.logn.service.LoginService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

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
    
    @Autowired
    private LoginService loginService;
    
    /**
     * 공지사항 목록 및 총 갯수 리턴
     * 
     * @param param
     * @return List<Notice>
     */
    @PostMapping("/select")
    @ApiOperation(value = "List of notices message of the companyId.", notes = "This function returns the list of notices message of the companyId.")
    public BaseResponse<List<Notice>> getNoticeList(HttpServletRequest request, @RequestBody NoticeSearchParameter parameter) {
        
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
		
		parameter.setCompanyId(login.getCompanyId());
    	
    	return new BaseResponse<List<Notice>>(noticeService.getNoticeList(parameter));
    }
    

    /**
     * 공지사항 등록
     * 
     * @param param
     * @return 
     */
    @PostMapping("/insert")
    @ApiOperation(value = "Add a new Notice.", notes = "This function adds a new Notice message.")
    public BaseResponse<Long> createMssg(HttpServletRequest request, @RequestBody NoticeParameter parameter) {
        
    	if (!StringUtils.hasText(parameter.getTitle())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 제목"});
        }
        
        if (!StringUtils.hasText(parameter.getContent())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 본문"});
        }
        
        if (!StringUtils.hasText(parameter.getImportCd())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 중요여부"});
        }
        
        Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
        
        parameter.setInsertId(login.getUserId());
        parameter.setUpdateId(login.getUserId());
        parameter.setCompanyId(login.getCompanyId());
        parameter.setWorkplaceId(login.getWorkplaceId());
        noticeService.insertNotice(parameter);
        return new BaseResponse<Long>(parameter.getNoticeId());
    }
    
    /**
     * 공지사항 상세보기
     * 
     * @param param
     * @return 
     */
    @PostMapping("/view")
    @ApiOperation(value = "Get the content of the noticeId.", notes = "This function returns the content of the noticeId.")
    public BaseResponse<Notice> getNotice(HttpServletRequest request, HttpServletResponse response, Long noticeId) {
    	
    	Login login = loginService.getLoginInfo(request);
		if (login == null) {
			throw new BaseException(BaseResponseCode.AUTH_FAIL);
		}
    	
    	Notice notice = noticeService.getNotice(login.getCompanyId(), noticeId);
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + login.getCompanyId() + ")", "noticeId (" + noticeId + ")"});
        } else {
        	// 조회수 증가(중복 증가 방지)
        	Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                	LOGGER.debug("cookie.getName " + cookie.getName());
                	LOGGER.debug("cookie.getValue " + cookie.getValue());

                    if (!cookie.getValue().contains(noticeId.toString())) {
                        cookie.setValue(cookie.getValue() + "_" + noticeId.toString());
                        cookie.setMaxAge(60 * 60 * 2);  /* 쿠키 시간 */
                        response.addCookie(cookie);
                        noticeService.updateViewCount(notice);
                        notice.setViewCnt(notice.getViewCnt());
                    }
                }
            } else {
                Cookie newCookie = new Cookie("visitCookie", noticeId.toString());
                newCookie.setMaxAge(60 * 60 * 2);
                response.addCookie(newCookie);
                noticeService.updateViewCount(notice);
                notice.setViewCnt(notice.getViewCnt());
            }
        }
        
        return new BaseResponse<Notice>(notice);
    }

    /**
     * 공지사항 수정
     * 
     * @param param
     * @return 
     */
    @PostMapping("/update")
    @ApiOperation(value = "Update the Notice message.", notes = "This function updates the specified Notice message.")
    public BaseResponse<Long> modifyMssg(HttpServletRequest request, @RequestBody NoticeParameter parameter) {
    	
    	Login login = loginService.getLoginInfo(request);
 		if (login == null) {
 			throw new BaseException(BaseResponseCode.AUTH_FAIL);
 		}
 		
 		parameter.setCompanyId(login.getCompanyId());
 		parameter.setWorkplaceId(login.getWorkplaceId());
        parameter.setUpdateId(login.getUserId());
    	
    	Notice notice = noticeService.getNotice(parameter.getCompanyId(), parameter.getNoticeId());
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + parameter.getCompanyId() + ")", "noticeId (" + parameter.getNoticeId() + ")"});
        } else {
            if (parameter.getNoticeId() == null) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시판 ID"});
            }
            if (!StringUtils.hasText(parameter.getTitle())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 제목"});
            }
            if (!StringUtils.hasText(parameter.getContent())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 본문"});
            }
            
            
            noticeService.updateNotice(parameter);
        }
        return new BaseResponse<Long>(parameter.getNoticeId());
    }
    
    /**
     * 공지사항 삭제
     * 
     * @param companyId, noticeId
     * @return 
     */
    @PostMapping("/delete")
    @ApiOperation(value = "Delete the message.", notes = "This function deletes the specified message.")
    public BaseResponse<Boolean> deleteNotice(HttpServletRequest request, Long noticeId) {
        
    	Login login = loginService.getLoginInfo(request);
 		if (login == null) {
 			throw new BaseException(BaseResponseCode.AUTH_FAIL);
 		}
 		
    	Notice notice = noticeService.getNotice(login.getCompanyId(), noticeId);
        if (notice == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "companyId (" + login.getCompanyId() + ")", "noticeId (" + noticeId + ")"});
        }
        noticeService.deleteNotice(login.getCompanyId(), noticeId);
        return new BaseResponse<Boolean>(true);
    }

}
