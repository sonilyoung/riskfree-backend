package egovframework.com.global.interceptor;

import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;


import egovframework.com.global.common.service.SecuredObjectService;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class UrlResourceMapFactoryBean implements FactoryBean<LinkedHashMap<RequestMatcher, List<ConfigAttribute>>> {
	
	@Resource(name = "SecuredObjectService")
	private SecuredObjectService securedObjectService;

    public void setSecuredObjectService(SecuredObjectService securedObjectService) {
        this.securedObjectService = securedObjectService;
    }

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourcesMap;

    public void init() throws Exception {
    	resourcesMap = securedObjectService.getRolesAndUrl();
    }
    
    @Override
    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getObject() throws Exception {
        if (resourcesMap == null) {
            init();
        }
        return resourcesMap;
    }

    @Override
	public Class<LinkedHashMap> getObjectType() {
        return LinkedHashMap.class;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
}
