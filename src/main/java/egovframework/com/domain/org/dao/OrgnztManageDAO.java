package egovframework.com.domain.org.dao;

import java.util.List;
import java.util.Map;
import egovframework.com.domain.org.domain.DutyVO;
import egovframework.com.domain.org.domain.OrgnztVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.global.common.domain.TreeNodeVO;


public interface OrgnztManageDAO {

    public List<OrgnztVO> selectTopInstitutionList();

    public OrgnztVO selectOrgnzt(OrgnztVO orgnzt);

    public List<OrgnztVO> selectDeptTree(OrgnztVO orgnzt);

    public List<OrgnztVO> selectDeptTree(String communityId, String base, int scope);

    /**
     * 개요 : 트리 화면을 위한 조직도 목록 조회
     * 
     * @param userVO 조직도 조회 기준이 되는 정보
     * @return
     */
    public List<TreeNodeVO> selectOrganizationTreeList(UserVO userVO);

    /**
     * 개요 : 직책 목록 조회
     * 
     * @param orgnztId 기준이 되는 부서ID
     * @return
     */
    public List<DutyVO> selectDutyList(String orgnztId);

    /**
     * 개요 : 직책 정보 조회
     * 
     * @param dutyId 직책ID
     * @return
     */
    public DutyVO selectDuty(String dutyId);

    public List<OrgnztVO> selectOrgList(String orgId);

    public List<OrgnztVO> selectOrgnztInfoList(String orgId);

    public List<OrgnztVO> selectOrgnztInfoListForPki(String orgId);

    public List<String> selectOutgoingManagerByDBSchema(Map<String, String> param);
}
