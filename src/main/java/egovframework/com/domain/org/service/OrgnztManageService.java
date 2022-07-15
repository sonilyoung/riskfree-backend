package egovframework.com.domain.org.service;

import java.util.List;
import egovframework.com.domain.org.domain.DutyVO;
import egovframework.com.domain.org.domain.OrgnztVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.global.common.domain.TreeNodeVO;

/**
 * 조직 관리를 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author paul
 * @since 2016.02.04
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일     	 수정자          수정내용
 *  -------     --------    ---------------------------
 *  2016.02.04    paul        최초 생성
 *  2017.04.05    suji.h      변수명 정리
 *      </pre>
 */
public interface OrgnztManageService {

    public OrgnztVO getOrgnzt(String orgnztId) throws Exception;

    /*
     * Get department information as a tree type
     */
    public List<OrgnztVO> getDeptTree(String orgnztId) throws Exception;

    /*
     * Show list of departments by tree type
     */
    public List<OrgnztVO> getOrgnztList(String communityID, String base, int scope)
            throws Exception;

    public List<OrgnztVO> getOrgnztList(String orgID) throws Exception;

    public List<OrgnztVO> getOrgnztInfoList(String orgID) throws Exception;

    public List<OrgnztVO> getOrgnztInfoListForPki(String orgID) throws Exception;

    /*
     * Show list of departments by tree type
     */
    public List<OrgnztVO> getExternalOrgnztList(String communityID, String base, int scope,
            String notShowOrgnzt) throws Exception;

    /*
     * Get information from the top level department
     */
    public OrgnztVO getTopOrgnzt(String orgnztId) throws Exception;

    /**
     * 개요 : 트리 화면을 위한 조직도 목록 조회
     * 
     * @param userVO 조직도 조회 기준이 되는 정보
     * @return
     */
    public List<TreeNodeVO> getOrganizationTreeList(UserVO userVO);

    /**
     * 개요 : 직책 목록 조회
     * 
     * @param orgnztId 기준이 되는 부서ID
     * @return
     * @throws Exception
     */
    public List<DutyVO> getDutyList(String orgnztId) throws Exception;

    /**
     * 개요 : 직책 정보 조회
     * 
     * @param dutyId 직책ID
     * @return
     * @throws Exception
     */
    public DutyVO getDutyInfo(String dutyId) throws Exception;
}
