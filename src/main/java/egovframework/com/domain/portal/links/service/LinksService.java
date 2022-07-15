package egovframework.com.domain.portal.links.service;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.links.domain.Links;


/**
 * Links 관리를 위한 서비스 인터페이스 클래스
 * 
 * @author
 * @since
 * 
 */
public interface LinksService {

    /**
     * 링크 목록 조회
     */
    public List<Links> linksList(Links links) throws Exception;

    /**
     * 링크 상세 조회
     */
    public Links linksDetail(Links links) throws Exception;

    /**
     * 링크 등록
     */
    public void insertLinks(Links links) throws Exception;

    /**
     * 링크 수정
     */
    public void updateLinks(Links links) throws Exception;

    /**
     * 링크 삭제
     */
    public void deleteLinks(HashMap<String, Object> deleteLinks) throws Exception;

}
