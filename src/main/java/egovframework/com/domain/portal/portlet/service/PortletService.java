package egovframework.com.domain.portal.portlet.service;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.portlet.domain.Portlet;

/**
 * 포틀릿 관리를 위한 서비스 인터페이스 클래스
 * 
 * @author 박선희
 * @since 2018.08.14
 * 
 */
public interface PortletService {

    /**
     * 포틀릿 목록 조회
     */
    public List<Portlet> getPortletList(Portlet portlet) throws Exception;

    /**
     * 포틀릿 상세 조회
     */
    public Portlet getPortlet(String id) throws Exception;

    /**
     * 포틀릿 등록
     */
    public void insertPortlet(Portlet portlet, String companyId) throws Exception;

    /**
     * 포틀릿 수정
     */
    public void updatePortlet(Portlet portlet) throws Exception;

    /**
     * 포틀릿 목록 조회 by 사용자
     */
    public List<Portlet> getUserPortletList(String userId) throws Exception;

    /**
     * 포틀릿 목록 조회 by 사용자
     */
    public List<Portlet> getUserPortletList_4(String userId) throws Exception;

    /**
     * 포틀릿 삭제
     */
    public void deletePortlet(HashMap<String, Object> deletePortlets) throws Exception;

}
