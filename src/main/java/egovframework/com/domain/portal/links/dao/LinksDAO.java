package egovframework.com.domain.portal.links.dao;

import java.util.HashMap;
import java.util.List;
import egovframework.com.domain.portal.links.domain.Links;

/**
 * Links 관리를 위한 데이터 접근 클래스
 * 
 * @author
 * @since
 * 
 */
public interface LinksDAO {

    @SuppressWarnings("unchecked")
    public List<Links> selectLinksList(Links links) throws Exception;

    public Links selectLinksDetail(Links linksd) throws Exception;

    public int insertLinks(Links links);

    public int updateLinks(Links links);

    public int deleteLinks(HashMap<String, Object> deleteLinks);
}
