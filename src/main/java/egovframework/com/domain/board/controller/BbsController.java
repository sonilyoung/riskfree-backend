package egovframework.com.domain.board.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.board.domain.BbsMssg;
import egovframework.com.domain.board.domain.BbsMssgBody;
import egovframework.com.domain.board.parameter.BbsMssgBodyParameter;
import egovframework.com.domain.board.parameter.BbsMssgSearchParameter;
import egovframework.com.domain.board.service.BbsService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/bboard")
@Api(tags = "BBS (Bulletin Board System) Management API")
public class BbsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BbsController.class);

    @Autowired
    private BbsService bbsService;

    @PostMapping("/{bbsBoardId}/listCount")
    @ApiOperation(value = "Number of BBS messages of the bbsBoardId.",
            notes = "This function returns the number of BBS messages of the bbsBoardId.")
    @ApiImplicitParams({@ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board",
            dataType = "long")})
    public BaseResponse<Long> getMssgCount(HttpServletRequest request,
            @PathVariable Long bbsBoardId,
            @ApiParam @RequestBody BbsMssgSearchParameter parameter) {
        parameter.setBbsBoardId(bbsBoardId);
        return new BaseResponse<Long>(bbsService.getMssgListCount(parameter));
    }

    @PostMapping("/{bbsBoardId}/list")
    @ApiOperation(value = "List of BBS message headers of the bbsBoardId.",
            notes = "This function returns the list of BBS message headers of the bbsBoardId.")
    @ApiImplicitParams({@ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board",
            dataType = "long")})
    public BaseResponse<List<BbsMssg>> getMssgList(HttpServletRequest request,
            @PathVariable Long bbsBoardId,
            @ApiParam @RequestBody BbsMssgSearchParameter parameter) {
        parameter.setBbsBoardId(bbsBoardId);
        return new BaseResponse<List<BbsMssg>>(bbsService.getMssgList(parameter));
    }

    @GetMapping("/{bbsBoardId}/messages/{bbsMssgId}")
    @ApiOperation(value = "Get the content of the bbsMssgId.",
            notes = "This function returns the content of the bbsMssgId.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board",
                    dataType = "long"),
            @ApiImplicitParam(name = "bbsMssgId", value = "Id of the BBS message",
                    dataType = "long")})
    public BaseResponse<BbsMssgBody> getMssg(HttpServletRequest request,
            @PathVariable Long bbsBoardId, @PathVariable Long bbsMssgId) {
        BbsMssgBody bbsMssgBody = bbsService.getMssg(bbsBoardId, bbsMssgId);
        if (bbsMssgBody == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "bbsBoardId (" + bbsBoardId + ")", "bbsMssgId (" + bbsMssgId + ")"});
        }
        return new BaseResponse<BbsMssgBody>(bbsMssgBody);
    }

    @PutMapping("/{bbsBoardId}/messages")
    @ApiOperation(value = "Add a new BBS message.", notes = "This function adds a new BBS message.")
    public BaseResponse<Long> createMssg(HttpServletRequest request, @PathVariable Long bbsBoardId,
            @ApiParam @RequestBody BbsMssgBodyParameter parameter) {
        if (StringUtils.isEmpty(bbsBoardId)) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시판 ID"});
        }
        if (StringUtils.isEmpty(parameter.getBbsTitle())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 제목"});
        }
        if (StringUtils.isEmpty(parameter.getBbsContent())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시물 본문"});
        }
        parameter.setBbsBoardId(bbsBoardId);
        bbsService.insertMssg(parameter);
        return new BaseResponse<Long>(parameter.getBbsMssgId());
    }

    @PatchMapping("/{bbsBoardId}/messages/{bbsMssgId}")
    @ApiOperation(value = "Update the BBS message.",
            notes = "This function updates the specified BBS message.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board",
                    dataType = "long"),
            @ApiImplicitParam(name = "bbsMssgId", value = "Id of the BBS message",
                    dataType = "long")})
    public BaseResponse<Long> modifyMssg(HttpServletRequest request, @PathVariable Long bbsBoardId,
            @PathVariable Long bbsMssgId, @ApiParam @RequestBody BbsMssgBodyParameter parameter) {
        BbsMssgBody bbsMssgBody = bbsService.getMssg(bbsBoardId, bbsMssgId);
        if (bbsMssgBody == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "bbsBoardId (" + bbsBoardId + ")", "bbsMssgId (" + bbsMssgId + ")"});
        } else {
            if (bbsBoardId == null) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시판 ID"});
            }
            if (StringUtils.isEmpty(parameter.getBbsTitle())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 제목"});
            }
            if (StringUtils.isEmpty(parameter.getBbsContent())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"게시물 본문"});
            }
            parameter.setBbsBoardId(bbsBoardId);
            parameter.setBbsMssgId(bbsMssgId);
            bbsService.updateMssg(parameter);
        }
        return new BaseResponse<Long>(bbsMssgId);
    }

    @DeleteMapping("/{bbsBoardId}/messages/{bbsMssgId}")
    @ApiOperation(value = "Delete the message.",
            notes = "This function deletes the specified message.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bbsBoardId", value = "Id of the BBS board",
                    dataType = "long"),
            @ApiImplicitParam(name = "bbsMssgId", value = "Id of the BBS message",
                    dataType = "long")})
    public BaseResponse<Boolean> deleteMssg(HttpServletRequest request,
            @PathVariable Long bbsBoardId, @PathVariable Long bbsMssgId) {
        BbsMssgBody bbsMssgBody = bbsService.getMssg(bbsBoardId, bbsMssgId);
        if (bbsMssgBody == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] {
                    "bbsBoardId (" + bbsBoardId + ")", "bbsMssgId (" + bbsMssgId + ")"});
        }
        bbsService.deleteMssg(bbsBoardId, bbsMssgId);
        return new BaseResponse<Boolean>(true);
    }

    @PostMapping("/{bbsBoardId}/messages/deleteArr")
    @ApiOperation(value = "Delete messages by the list",
            notes = "This function deletes the specified messages by the list.")
    public BaseResponse<Boolean> deleteArrMssg(HttpServletRequest request,
            @PathVariable Long bbsBoardId, @RequestParam(value = "deleteList") String deleteList) {
        if (StringUtils.isEmpty(bbsBoardId)) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR, new String[] {"게시판 ID"});
        }
        if (ObjectUtils.isEmpty(deleteList)) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR, new String[] {"삭제할 정보가 없습니다."});
        }

        try {

            String[] deleteSplit = deleteList.split(",");
            List<Long> bbsMssgList = new ArrayList<Long>();

            for (int i = 0; i < deleteSplit.length; i++) {
                bbsMssgList.add(Long.parseLong(deleteSplit[i]));
            }
            bbsService.deleteArrMssg(bbsBoardId, bbsMssgList);
            return new BaseResponse<Boolean>(true);
        } catch (Exception e) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"삭제 중에 오류가 발행했습니다. (" + e.getMessage() + ")"});
        }
    }
}
