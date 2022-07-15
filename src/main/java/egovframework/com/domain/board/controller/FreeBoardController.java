package egovframework.com.domain.board.controller;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import egovframework.com.domain.board.domain.FreeBoard;
import egovframework.com.domain.board.parameter.FreeBoardParameter;
import egovframework.com.domain.board.parameter.FreeBoardSearchParameter;
import egovframework.com.domain.board.service.FreeBoardService;
import egovframework.com.global.http.BaseResponse;
import egovframework.com.global.http.BaseResponseCode;
import egovframework.com.global.http.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 게시판 API 컨트롤러
 * 
 * @author jkj0411
 *
 */
@RestController
@RequestMapping("/board/free")
@Api(tags = "게시판API")
public class FreeBoardController {

    @Autowired
    private FreeBoardService freeBoardService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 목록 갯수
     * 
     * @param parameter
     * @return
     */
    @PostMapping("/listCount")
    @ApiOperation(value = "목록갯수", notes = "게시물 목록 갯수")
    public BaseResponse<Long> getListCount(
            @ApiParam @RequestBody FreeBoardSearchParameter parameter) {
        Long listCount = freeBoardService.getListCount(parameter);
        return new BaseResponse<Long>(listCount);
    }

    /**
     * 목록 리턴
     * 
     * @param parameter
     * @return
     */
    @PostMapping("/list")
    @ApiOperation(value = "목록", notes = "게시물 목록")
    public BaseResponse<List<FreeBoard>> getList(
            @ApiParam @RequestBody FreeBoardSearchParameter parameter) {
        return new BaseResponse<List<FreeBoard>>(freeBoardService.getList(parameter));
    }

    /**
     * 엑셀용 목록 리턴
     * 
     * @param parameter
     * @return
     */
    @PostMapping("/listExcel")
    @ApiOperation(value = "엑셀용 목록", notes = "엑셀용 게시물 목록")
    public BaseResponse<List<FreeBoard>> getListExcel(
            @ApiParam @RequestBody FreeBoardSearchParameter parameter) {
        return new BaseResponse<List<FreeBoard>>(freeBoardService.getListExcel(parameter));
    }

    /**
     * 조회
     * 
     * @param boardSeq
     * @return
     */
    @PostMapping("/{boardSeq}")
    @ApiOperation(value = "상세조회", notes = "게시물 번호에 해당하는 상세정보를 조회할수 있습니다.")
    @ApiImplicitParams({@ApiImplicitParam(name = "boardSeq", value = "게시물번호", example = "1",
            dataType = "long")})
    public BaseResponse<FreeBoard> get(@PathVariable Long boardSeq) {
        FreeBoard board = freeBoardService.get(boardSeq);
        // null 처리
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL,
                    new String[] {"게시물(" + boardSeq + ")"});
        }
        return new BaseResponse<FreeBoard>(board);
    }

    /**
     * 등록 처리
     * 
     * @param parameter
     * @return
     */
    @PutMapping("/create")
    @ApiOperation(value = "등록", notes = "게시물 등록")
    @ApiImplicitParams({@ApiImplicitParam(name = "title", value = "게시물 제목", example = "제목"),
            @ApiImplicitParam(name = "contents", value = "게시물 내용", example = "내용")})
    public BaseResponse<Long> create(FreeBoardParameter parameter) {

        // 제목 필수 체크
        if (StringUtils.isEmpty(parameter.getTitle())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"title", "제목"});
        }
        // 내용 필수 체크
        if (StringUtils.isEmpty(parameter.getContents())) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"contents", "내용"});
        }
        // 타입 필수 체크
        if (parameter.getBoardType() == null) {
            throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                    new String[] {"boartType", "타입"});
        }
        freeBoardService.insert(parameter);
        return new BaseResponse<Long>(parameter.getBoardSeq());
    }

    /**
     * 수정 처리
     * 
     * @param boardSeq
     * @param parameter
     * @return
     */
    @PutMapping("/modify/{boardSeq}")
    @ApiOperation(value = "수정", notes = "게시물 수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1",
                    dataType = "long"),
            @ApiImplicitParam(name = "title", value = "게시물 제목", example = "제목"),
            @ApiImplicitParam(name = "contents", value = "게시물 내용", example = "내용")})
    public BaseResponse<Long> modify(@PathVariable Long boardSeq, FreeBoardParameter parameter) {

        FreeBoard board = freeBoardService.get(boardSeq);
        if (board == null) {
            throw new BaseException(BaseResponseCode.SAVE_ERROR);
        } else {
            // seq 필수 체크
            if (parameter.getBoardSeq() == null) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"boardSeq", "게시물번호"});
            }
            // 제목 필수 체크
            if (StringUtils.isEmpty(parameter.getTitle())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"title", "제목"});
            }
            // 내용 필수 체크
            if (StringUtils.isEmpty(parameter.getContents())) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"contents", "내용"});
            }
            // 타입 필수 체크
            if (parameter.getBoardType() == null) {
                throw new BaseException(BaseResponseCode.INPUT_CHECK_ERROR,
                        new String[] {"boartType", "타입"});
            }
            freeBoardService.update(parameter);
        }
        return new BaseResponse<Long>(boardSeq);
    }

    /**
     * 삭제처리
     * 
     * @param boardSeq
     * @return
     */
    @DeleteMapping("/delete/{boardSeq}")
    @ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제 처리")
    @ApiImplicitParams({@ApiImplicitParam(name = "boardSeq", value = "게시물번호", example = "1",
            dataType = "long")})
    public BaseResponse<Boolean> delete(@PathVariable Long boardSeq) {
        FreeBoard board = freeBoardService.get(boardSeq);
        if (board == null) {
            throw new BaseException(BaseResponseCode.DELETE_ERROR,
                    new String[] {"게시물(" + boardSeq + ")"});
        }
        freeBoardService.delete(boardSeq);
        return new BaseResponse<Boolean>(true);
    }

    @PostMapping("/deleteArr")
    @ApiOperation(value = "삭제 처리", notes = "게시물 번호[배열]에 해당하는 정보를 삭제 처리")
    public BaseResponse<Boolean> deleteArr(@RequestParam(value = "deleteList") String deleteList) {
        if (!ObjectUtils.isEmpty(deleteList)) {
            String[] paramBoardSeqs = deleteList.split(",");
            List<Long> boardSeqs = new ArrayList<Long>();

            for (int i = 0; i < paramBoardSeqs.length; i++) {
                boardSeqs.add(Long.parseLong(paramBoardSeqs[i]));
            }
            freeBoardService.deleteArr(boardSeqs);
            return new BaseResponse<Boolean>(true);
        } else {
            throw new BaseException(BaseResponseCode.DELETE_ERROR, new String[] {"게시물()"});
        }

    }
}
