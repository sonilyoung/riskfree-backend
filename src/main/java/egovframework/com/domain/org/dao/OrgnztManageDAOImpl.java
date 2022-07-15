package egovframework.com.domain.org.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.org.domain.DutyVO;
import egovframework.com.domain.org.domain.OrgnztVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.global.common.domain.TreeNodeVO;


@Repository
public class OrgnztManageDAOImpl implements OrgnztManageDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace =
            "egovframework.com.domain.portal.logn.mapper.loginMapper";

    public List<OrgnztVO> selectTopInstitutionList() {
        return sqlSession.selectList("OrgnztManageDAO.selectTopInstitutionList");
    }

    public OrgnztVO selectOrgnzt(OrgnztVO orgnzt) {
        return sqlSession.selectOne("OrgnztManageDAO.selectOrgnzt", orgnzt);
    }

    @SuppressWarnings("unchecked")
    public List<OrgnztVO> selectDeptTree(OrgnztVO orgnzt) {
        return sqlSession.selectList("OrgnztManageDAO.selectDeptTree", orgnzt);
    }

    @SuppressWarnings("unchecked")
    public List<OrgnztVO> selectDeptTree(String communityId, String base, int scope) {
        OrgnztVO vo = new OrgnztVO();

        vo.setOrgnztId(communityId);
        vo.setBaseOrgnztId(base);
        vo.setOrgnztLevel(scope);

        return sqlSession.selectList("OrgnztManageDAO.selectDeptTreeWithDepth", vo);
    }

    /**
     * 개요 : 트리 화면을 위한 조직도 목록 조회
     * 
     * @param userVO 조직도 조회 기준이 되는 정보
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<TreeNodeVO> selectOrganizationTreeList(UserVO userVO) {
        return sqlSession.selectList("OrgnztManageDAO.selectOrganizationTreeList", userVO);
    }

    /**
     * 개요 : 직책 목록 조회
     * 
     * @param orgnztId 기준이 되는 부서ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<DutyVO> selectDutyList(String orgnztId) {
        return sqlSession.selectList("OrgnztManageDAO.selectDutyList", orgnztId);
    }

    /**
     * 개요 : 직책 정보 조회
     * 
     * @param dutyId 직책ID
     * @return
     */
    public DutyVO selectDuty(String dutyId) {
        return sqlSession.selectOne("OrgnztManageDAO.selectDuty", dutyId);
    }

    @SuppressWarnings("unchecked")
    public List<OrgnztVO> selectOrgList(String orgId) {
        OrgnztVO vo = new OrgnztVO();

        vo.setOrgnztId(orgId);

        return sqlSession.selectList("OrgnztManageDAO.selectOrgnztList", vo);
    }

    public List<OrgnztVO> selectOrgnztInfoList(String orgId) {
        OrgnztVO vo = new OrgnztVO();

        vo.setOrgnztId(orgId);

        return sqlSession.selectList("OrgnztManageDAO.selectOrgnztInfoList", vo);
    }

    public List<OrgnztVO> selectOrgnztInfoListForPki(String orgId) {
        OrgnztVO vo = new OrgnztVO();

        vo.setOrgnztId(orgId);

        return sqlSession.selectList("OrgnztManageDAO.selectOrgnztInfoListForPki", vo);
    }

    public List<String> selectOutgoingManagerByDBSchema(Map<String, String> param) {
        return sqlSession.selectList("OrgnztManageDAO.selectOutgoingManagerByDBSchema", param);
    }
}
