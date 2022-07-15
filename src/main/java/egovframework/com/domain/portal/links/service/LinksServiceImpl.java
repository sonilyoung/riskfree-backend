package egovframework.com.domain.portal.links.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import egovframework.com.domain.portal.links.dao.LinksDAO;
import egovframework.com.domain.portal.links.domain.Links;
import egovframework.com.global.AbstractServiceImpl;


/**
 * Links 관리를 위한 서비스 구현 클래스
 * 
 * @author
 * @since
 * 
 * 
 */
@Service("LinksService")
public class LinksServiceImpl extends AbstractServiceImpl implements LinksService {

    @Autowired
    private LinksDAO linksDAO;

    @Override
    public List<Links> linksList(Links links) throws Exception {
        List<Links> result = linksDAO.selectLinksList(links);
        return result;
    }

    @Override
    public Links linksDetail(Links links) throws Exception {
        return linksDAO.selectLinksDetail(links);
    }

    @Override
    public void insertLinks(Links links) throws Exception {
        linksDAO.insertLinks(links);
    }

    @Override
    public void updateLinks(Links links) throws Exception {
        linksDAO.updateLinks(links);
    }

    @Transactional
    public void deleteLinks(HashMap<String, Object> deleteLinks) throws Exception {
        linksDAO.deleteLinks(deleteLinks);
    }

}
