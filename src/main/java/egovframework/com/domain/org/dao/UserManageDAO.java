package egovframework.com.domain.org.dao;

import java.util.HashMap;
import java.util.List;
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
public interface UserManageDAO {

    public Login getByLoginId(String loginId);

    /**
     * 기 등록된 사용자 중 검색조건에 맞는 사용자들의 정보를 데이터베이스에서 읽어와 화면에 출력
     * 
     * @param userUniqId 상세조회대상 업무사용자아이디
     * @return UserVO 사용자 상세정보
     */
    public UserVO selectUser(UserVO vo);

    /**
     * 기 등록된 특정 사용자의 정보를 데이터베이스에서 읽어와 화면에 출력
     * 
     * @param userSearchVO 검색조건
     * @return List 사용자 목록정보
     */
    public List<?> selectUserList(UserDefaultVO userSearchVO);

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserInfList(UserVO userSearchVO);

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserInfListByKeyword(UserVO userSearchVO);

    @SuppressWarnings("unchecked")
    public List<UserVO> selectIdOfAllUserInCompany(UserVO userSearchVO);

    /**
     * 화면에 조회된 사용자의 기본정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * 
     * @param UserVO 사용자 수정정보
     */
    public int updateUser(UserVO UserVO);

    /**
     * 화면에 조회된 사용자가 소유한 권한을 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
     * 
     * @param UserVO 사용자 수정정보
     */
    public int updateDeptAuthority(UserVO UserVO);

    /**
     * 사용자정보 수정시 히스토리 정보를 추가
     * 
     * @param UserVO 사용자 히스토리 정보
     * @return String 히스토리 등록결과
     */
    public int insertUserHistory(UserVO UserVO);

    /**
     * 사용자 암호수정
     * 
     * @param passVO 업무사용자수정정보(비밀번호)
     */
    public int updatePassword(UserVO passVO);

    /**
     * 사용자 결재 비밀번호 수정
     * 
     * @param passVO 사용자수정정보(결재 비밀번호)
     */
    public int updateApprovalPassword(UserVO passVO);

    /**
     * 사용자 상세보기 (연락처에서 사용)
     * 
     * @param vo
     * @return
     */
    public UserVO selectUserDetailInfo(UserVO vo);

    /**
     * 조직도 내 전체 리스트 조회 (email 에서 사용)
     * 
     * @param vo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<UserVO> selectAllUserInCompany(UserVO vo);

    /**
     * 조직도 내 전체 리스트 조회 (mobile 에서 사용)
     * 
     * @param vo
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<UserVO> getAllUserInCompany(UserVO vo);

    /**
     * 부재 설정 조회
     * 
     * @param vo
     * @return
     */
    public UserVO selectUserAbsence(String userUniqId);

    @SuppressWarnings("unchecked")
    public List<UserVO> selectUserListForContact(UserVO vo);

    @SuppressWarnings("unchecked")
    public List<UserVO> selectCompanyUserListForContact(UserVO vo);

    public UserAbsenceVO selectUserAbsenceDetail(String userUniqId);

    public int insertUserAbsence(UserVO UserVO) throws Exception;

    public int updateUserAbsence(UserVO UserVO) throws Exception;

    public String selectCompanyFileSize(Login Login);

    public UserVO syncGetUserInformation(UserVO UserVO);

    public int syncUpdateUserLpwdDt(UserVO UserVO);

    public int checkEmailAdress(String userId);

    public String getCurrentWsId(String userId);

    public String getDDPCodeByUserId(String userId);

    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> getNotifyInfo();

    @SuppressWarnings("unchecked")
    public List<String> getUserIdListByDutyId(String dutyId);

    @SuppressWarnings("unchecked")
    public List<HashMap<String, Object>> searchUsers(HashMap<String, Object> map);

    public int updateSkin(UserVO userVO);

	public void updateLoginTime(String loginId);

}
