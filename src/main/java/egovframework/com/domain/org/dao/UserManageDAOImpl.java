package egovframework.com.domain.org.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.domain.org.domain.UserAbsenceVO;
import egovframework.com.domain.org.domain.UserDefaultVO;
import egovframework.com.domain.org.domain.UserVO;
import egovframework.com.domain.portal.logn.domain.Login;


/**
 * 사용자관리에 관한 데이터 접근 클래스를 정의한다.
 * 
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  조재영          최초 생성
 *
 *      </pre>
 */
@Repository
public class UserManageDAOImpl implements UserManageDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.org.dao.UserManageDAO";

    public Login getByLoginId(String loginId) {
        return sqlSession.selectOne(Namespace + ".getByLoginId", loginId);
    }

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에 출력
     * 
     * @param userUniqId 상세조회대상 업무사용자아이디
     * @return UserVO 사용자 상세정보
     */
    public UserVO selectUser(UserVO vo) {
        UserVO user = (UserVO) sqlSession.selectOne("userManageDAO.selectUser", vo);
        if (user != null) {
            return user;
        } else {
            return new UserVO();
        }
    }

    /**
     * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
     * 
     * @param userSearchVO 검색조건
     * @return List 사용자 목록정보
     */
    public List<?> selectUserList(UserDefaultVO userSearchVO) {
        return sqlSession.selectList("userManageDAO.selectUserList", userSearchVO);
    }

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserInfList(UserVO userSearchVO) {
        return sqlSession.selectList("userManageDAO.selectUserInfList", userSearchVO);
    }

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserInfListByKeyword(UserVO userSearchVO) {
        return sqlSession.selectList("userManageDAO.selectUserInfListByKeyword", userSearchVO);
    }

    @SuppressWarnings("unchecked")
    public List<UserVO> selectIdOfAllUserInCompany(UserVO userSearchVO) {
        return sqlSession.selectList("userManageDAO.selectIdOfAllUserInCompany", userSearchVO);
    }

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * 
     * @param UserVO 사용자 수정정보
     */
    public int updateUser(UserVO UserVO) {
        return sqlSession.update("userManageDAO.updateUser", UserVO);
    }

    /**
     * 화면에 조회된 사용자가 소유한 권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * 
     * @param UserVO 사용자 수정정보
     */
    public int updateDeptAuthority(UserVO UserVO) {
        return sqlSession.update("userManageDAO.updateDeptAuthority", UserVO);
    }

    /**
     * 사용자정보 수정시 히스토리 정보를 추가
     * 
     * @param UserVO 사용자 히스토리 정보
     * @return String 히스토리 등록결과
     */
    public int insertUserHistory(UserVO UserVO) {
        return sqlSession.insert("userManageDAO.insertUserHistory", UserVO);
    }

    /**
     * 사용자 암호수정
     * 
     * @param passVO 업무사용자수정정보(비밀번호)
     */
    public int updatePassword(UserVO passVO) {
        return sqlSession.update("userManageDAO.updatePassword", passVO);
    }

    /**
     * 사용자 결재 비밀번호 수정
     * 
     * @param passVO 사용자수정정보(결재 비밀번호)
     */
    public int updateApprovalPassword(UserVO passVO) {
        return sqlSession.update("userManageDAO.updateApprovalPassword", passVO);
    }

    /**
     * 사용자 상세보기 (연락처에서 사용)
     * 
     * @param vo
     * @return
     */
    public UserVO selectUserDetailInfo(UserVO vo) {
        return sqlSession.selectOne("userManageDAO.selectUser", vo);
    }

    /**
     * 조직도 내 전체 리스트 조회 (email 에서 사용)
     * 
     * @param vo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<UserVO> selectAllUserInCompany(UserVO vo) {
        return sqlSession.selectList("userManageDAO.selectAllUserInCompany", vo);
    }

    /**
     * 조직도 내 전체 리스트 조회 (mobile 에서 사용)
     * 
     * @param vo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<UserVO> getAllUserInCompany(UserVO vo) {
        return sqlSession.selectList("userManageDAO.getAllUserInCompany", vo);
    }

    /**
     * 부재 설정 조회
     * 
     * @param vo
     * @return
     */
    public UserVO selectUserAbsence(String userUniqId) {
        return sqlSession.selectOne("userManageDAO.selectUserAbsence", userUniqId);
    }

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserListForContact(UserVO vo) {
        return sqlSession.selectList("userManageDAO.selectUserListForContact", vo);
    }

    @SuppressWarnings("unchecked")
    public List<UserVO> selectCompanyUserListForContact(UserVO vo) {
        return sqlSession.selectList("userManageDAO.selectCompanyUserListForContact", vo);
    }

    public UserAbsenceVO selectUserAbsenceDetail(String userUniqId) {
        return sqlSession.selectOne("userManageDAO.selectUserAbsenceDetail", userUniqId);
    }

    public int insertUserAbsence(UserVO UserVO) throws Exception {
        return sqlSession.insert("userManageDAO.insertUserAbsence", UserVO);
    }

    public int updateUserAbsence(UserVO UserVO) throws Exception {
        return sqlSession.update("userManageDAO.updateUserAbsence", UserVO);
    }

    public String selectCompanyFileSize(Login Login) {
        return sqlSession.selectOne("userManageDAO.selectCompanyFileSize", Login);
    }

    public UserVO syncGetUserInformation(UserVO UserVO) {
        return sqlSession.selectOne("userManageDAO.syncGetUserInformation", UserVO);
    }

    public int syncUpdateUserLpwdDt(UserVO UserVO) {
        return sqlSession.update("userManageDAO.syncUpdateUserLpwdDt", UserVO);
    }

    public int checkEmailAdress(String userId) {
        return sqlSession.selectOne("userManageDAO.checkEmailAdress", userId);
    }

    public String getCurrentWsId(String userId) {
        return sqlSession.selectOne("userManageDAO.getCurrentWsId", userId);
    }

    public String getDDPCodeByUserId(String userId) {
        return sqlSession.selectOne("userManageDAO.getDDPCodeByUserID", userId);
    }

    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> getNotifyInfo() {
        return sqlSession.selectList("userManageDAO.getNotifyInfo");
    }

    @SuppressWarnings("unchecked")
    public List<String> getUserIdListByDutyId(String dutyId) {
        return sqlSession.selectList("userManageDAO.getUserIdListByDutyId", dutyId);
    }

    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> searchUsers(HashMap<String, Object> map) {
        return sqlSession.selectList("userManageDAO.searchUsers", map);
    }

    public int updateSkin(UserVO userVO) {
        return sqlSession.update("userManageDAO.updateSkin", userVO);
    }
}
