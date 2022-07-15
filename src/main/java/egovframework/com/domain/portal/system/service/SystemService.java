package egovframework.com.domain.portal.system.service;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.system.domain.SystemMenu;

/**
 * 시스템메뉴 관리를 위한 서비스 인터페이스 클래스
 * 
 * @author 박선희
 * @since 2018.08.13
 * 
 */
public interface SystemService {

    /**
     * 시스템 목록 조회
     */
    public List<SystemMenu> getSystemList() throws Exception;

    /**
     * 시스템 상세 조회
     */
    public SystemMenu getSystem(String id) throws Exception;

    /**
     * 시스템 등록
     */
    public void insertSystem(SystemMenu system, String companyId) throws Exception;

    /**
     * 시스템 수정
     */
    public void updateSystem(SystemMenu system) throws Exception;

    /**
     * 시스템 목록 조회 by 사용자
     */
    public List<SystemMenu> getSystemListByUserId(String userId) throws Exception;

    /**
     * 시스템 삭제
     */
    public void deleteSystem(HashMap<String, Object> deleteSystems) throws Exception;


}
