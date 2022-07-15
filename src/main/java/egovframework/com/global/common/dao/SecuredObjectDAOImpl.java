package egovframework.com.global.common.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Repository;

@Repository
public class SecuredObjectDAOImpl implements SecuredObjectDAO {

    /**
     * url 형식인 보호자원 - Role 맵핑정보를 조회하는 default 쿼리이다.
     */
    public static final String DEF_ROLES_AND_URL_QUERY =
            "SELECT a.ROLE_PTTRN url, b.AUTHOR_CODE authority"
                    + "	FROM cs_se_role a, cs_se_authority_role_relate b"
                    + "	WHERE a.ROLE_CODE = b.ROLE_CODE"
                    + "		AND a.ROLE_TY = 'url'  ORDER BY a.ROLE_SORT";

    /**
     * method 형식인 보호자원 - Role 맵핑정보를 조회하는 default 쿼리이다.
     */
    public static final String DEF_ROLES_AND_METHOD_QUERY =
            "SELECT a.ROLE_PTTRN method, b.AUTHOR_CODE authority"
                    + "	FROM cs_se_role a, cs_se_authority_role_relate b"
                    + "	WHERE a.ROLE_CODE = b.ROLE_CODE"
                    + "		AND a.ROLE_TY = 'method'  ORDER BY a.ROLE_SORT";

    /**
     * pointcut 형식인 보호자원 - Role 맵핑정보를 조회하는 default 쿼리이다.
     */
    public static final String DEF_ROLES_AND_POINTCUT_QUERY =
            "SELECT a.ROLE_PTTRN pointcut, b.AUTHOR_CODE authority"
                    + "	FROM cs_se_role a, cs_se_authority_role_relate b"
                    + "	WHERE a.ROLE_CODE = b.ROLE_CODE"
                    + "		AND a.ROLE_TY = 'pointcut'  ORDER BY a.ROLE_SORT";

    /**
     * 매 request 마다 best matching url 보호자원 - Role 맵핑정보를 얻기위한 default 쿼리이다. (Oracle 10g specific)
     */
    public static final String DEF_REGEX_MATCHED_REQUEST_MAPPING_QUERY_ORACLE10G =
            "SELECT a.resource_pattern uri, b.authority authority"
                    + "	FROM COMTNSECURED_RESOURCES a, COMTNSECURED_RESOURCES_ROLE b"
                    + "	WHERE a.resource_id = b.resource_id"
                    + "		AND a.resource_type = 'url'";

    /**
     * Role 의 계층(Hierarchy) 관계를 조회하는 default 쿼리이다.
     */
    public static final String DEF_HIERARCHICAL_ROLES_QUERY =
            "SELECT a.CHLDRN_ROLE child, a.PARNTS_ROLE parent"
                    + "	FROM cs_se_role_hierarchy a LEFT JOIN cs_se_role_hierarchy b on (a.CHLDRN_ROLE = b.PARNTS_ROLE)";

    private String sqlRolesAndUrl;

    private String sqlRolesAndMethod;

    private String sqlRolesAndPointcut;

    private String sqlRegexMatchedRequestMapping;

    private String sqlHierarchicalRoles;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public SecuredObjectDAOImpl() {
        this.sqlRolesAndUrl = DEF_ROLES_AND_URL_QUERY;
        this.sqlRolesAndMethod = DEF_ROLES_AND_METHOD_QUERY;
        this.sqlRolesAndPointcut = DEF_ROLES_AND_POINTCUT_QUERY;
        this.sqlRegexMatchedRequestMapping = DEF_REGEX_MATCHED_REQUEST_MAPPING_QUERY_ORACLE10G;
        this.sqlHierarchicalRoles = DEF_HIERARCHICAL_ROLES_QUERY;
    }

    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    /**
     * 롤에 대한 URL 정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlRolesAndUrl() {
        return sqlRolesAndUrl;
    }

    /**
     * 롤에대한 URL 정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlRolesAndUrl
     */
    public void setSqlRolesAndUrl(String sqlRolesAndUrl) {
        this.sqlRolesAndUrl = sqlRolesAndUrl;
    }

    /**
     * 롤에 대한 메소드 정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlRolesAndMethod() {
        return sqlRolesAndMethod;
    }

    /**
     * 롤에 대한 메소드 정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlRolesAndMethod
     */
    public void setSqlRolesAndMethod(String sqlRolesAndMethod) {
        this.sqlRolesAndMethod = sqlRolesAndMethod;
    }

    /**
     * 롤에 대한 포인트컷 정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlRolesAndPointcut() {
        return sqlRolesAndPointcut;
    }

    /**
     * 롤에 대한 포인트컷 정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlRolesAndPointcut
     */
    public void setSqlRolesAndPointcut(String sqlRolesAndPointcut) {
        this.sqlRolesAndPointcut = sqlRolesAndPointcut;
    }

    /**
     * 매핑 정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlRegexMatchedRequestMapping() {
        return sqlRegexMatchedRequestMapping;
    }

    /**
     * 매핑 정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlRegexMatchedRequestMapping
     */
    public void setSqlRegexMatchedRequestMapping(String sqlRegexMatchedRequestMapping) {
        this.sqlRegexMatchedRequestMapping = sqlRegexMatchedRequestMapping;
    }

    /**
     * 롤 계층구조 정보를 가져오는 SQL을 얻어온다.
     * 
     * @return
     */
    public String getSqlHierarchicalRoles() {
        return sqlHierarchicalRoles;
    }

    /**
     * 롤 계층구조 정보를 가져오는 SQL을 설정한다.
     * 
     * @param sqlHierarchicalRoles
     */
    public void setSqlHierarchicalRoles(String sqlHierarchicalRoles) {
        this.sqlHierarchicalRoles = sqlHierarchicalRoles;
    }

    /**
     * 리소스 유형에 대한 할당된 롤 정보를 가져온다.
     *
     * @param resourceType
     * @return
     * @throws Exception
     */
    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources(String resourceType,
            String requestMatcherType) throws Exception {

        LinkedHashMap<Object, List<ConfigAttribute>> resourcesMap =
                new LinkedHashMap<Object, List<ConfigAttribute>>();

        String sqlRolesAndResources;
        boolean isResourcesUrl = true;

        if ("method".equals(resourceType)) {
            sqlRolesAndResources = getSqlRolesAndMethod();
            isResourcesUrl = false;
        } else if ("pointcut".equals(resourceType)) {
            sqlRolesAndResources = getSqlRolesAndPointcut();
            isResourcesUrl = false;
        } else {
            sqlRolesAndResources = getSqlRolesAndUrl();
        }

        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate
                .queryForList(sqlRolesAndResources, new HashMap<String, String>());

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        String preResource = null;
        String presentResourceStr;
        Object presentResource;
        while (itr.hasNext()) {
            tempMap = itr.next();

            presentResourceStr = (String) tempMap.get(resourceType);
            presentResource = isResourcesUrl ? new AntPathRequestMatcher(presentResourceStr)
                    : presentResourceStr;

            List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();

            // 이미 requestMap 에 해당 Resource 에 대한 Role 이 하나 이상 맵핑되어 있었던 경우,
            // sort_order 는 resource(Resource) 에 대해 매겨지므로 같은 Resource 에 대한 Role 맵핑은 연속으로 조회됨.
            // 해당 맵핑 Role List (SecurityConfig) 의 데이터를 재활용하여 새롭게 데이터 구축
            if (preResource != null && presentResourceStr.equals(preResource)) {
                List<ConfigAttribute> preAuthList = resourcesMap.get(presentResource);
                Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();
                while (preAuthItr.hasNext()) {
                    SecurityConfig tempConfig = (SecurityConfig) preAuthItr.next();
                    configList.add(tempConfig);
                }
            }

            configList.add(new SecurityConfig((String) tempMap.get("authority")));

            // 만약 동일한 Resource 에 대해 한개 이상의 Role 맵핑 추가인 경우
            // 이전 resourceKey 에 현재 새로 계산된 Role 맵핑 리스트로 덮어쓰게 됨.
            resourcesMap.put(presentResource, configList);

            // 이전 resource 비교위해 저장
            preResource = presentResourceStr;
        }

        return resourcesMap;
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndUrl() throws Exception {
        return getRolesAndResources("url", null);
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndMethod() throws Exception {
        return getRolesAndResources("method", null);
    }

    public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndPointcut() throws Exception {
        return getRolesAndResources("pointcut", null);
    }

    public List<ConfigAttribute> getRegexMatchedRequestMapping(String url) throws Exception {

        // best regex matching - best 매칭된 Uri 에 따른 Role List 조회,
        // DB 차원의 정규식 지원이 있는 경우 사용 (ex. hsqldb custom function, Oracle 10g regexp_like 등)
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("url", url);
        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate
                .queryForList(getSqlRegexMatchedRequestMapping(), paramMap);

        Iterator<Map<String, Object>> itr = resultList.iterator();
        Map<String, Object> tempMap;
        List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();
        // 같은 Uri 에 대한 Role 맵핑이므로 무조건 configList 에 add 함
        while (itr.hasNext()) {
            tempMap = itr.next();
            configList.add(new SecurityConfig((String) tempMap.get("authority")));
        }

        if (configList.size() > 0) {

        }

        return configList;
    }

    public String getHierarchicalRoles() throws Exception {

        List<Map<String, Object>> resultList = this.namedParameterJdbcTemplate
                .queryForList(getSqlHierarchicalRoles(), new HashMap<String, String>());

        Iterator<Map<String, Object>> itr = resultList.iterator();
        StringBuffer concatedRoles = new StringBuffer();
        Map<String, Object> tempMap;
        while (itr.hasNext()) {
            tempMap = itr.next();
            concatedRoles.append(tempMap.get("child"));
            concatedRoles.append(" > ");
            concatedRoles.append(tempMap.get("parent"));
            concatedRoles.append("\n");
        }

        return concatedRoles.toString();
    }
}
