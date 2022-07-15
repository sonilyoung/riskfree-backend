package egovframework.com.domain.portal.portlet.domain;

import java.util.List;

public class Portlet {

    private List<Portlet> portletList;

    /**
     * 포틀릿 아이디
     */
    private String portletId;

    /**
     * 포틀릿 명
     */
    private String portletName;

    /**
     * 포틀릿 표출명
     */
    private String portletDisplayName;

    /**
     * 사용 여부
     */
    private int useF;

    /**
     * 포틀릿 IDs
     */
    private List<String> portletIds;

    /**
     * URL
     */
    private String url;

    /**
     * 시스템 아이디
     */
    private String systemId;

    /**
     * 시스템 명
     */
    private String systemName;

    /**
     * 시스템 리스트
     */
    private String systems;

    /**
     * 사용자 아이디
     */
    private String esntlId;

    /**
     * 표출 순서
     */
    private int displayOrder;

    /**
     * 표출 유형
     */
    private String displayTypeCd;

    /**
     * 포틀릿 리스트
     */
    private String portlets;

    /**
     * 표출_1
     */
    private String portlet1;

    /**
     * 표출_2
     */
    private String portlet2;

    /**
     * 표출_3
     */
    private String portlet3;

    /**
     * 표출_4
     */
    private String portlet4;

    /**
     * 표출_5
     */
    private String portlet5;

    /**
     * 표출_6
     */
    private String portlet6;

    /**
     * 표출_7
     */
    private String portlet7;

    /**
     * 표출명_1
     */
    private String portletName1;

    /**
     * 표출명_2
     */
    private String portletName2;

    /**
     * 표출명_3
     */
    private String portletName3;

    /**
     * 표출명_4
     */
    private String portletName4;

    /**
     * 표출명_5
     */
    private String portletName5;

    /**
     * 표출명_6
     */
    private String portletName6;

    /**
     * 표출명_7
     */
    private String portletName7;

    /**
     * URL_1
     */
    private String url1;

    /**
     * URL_2
     */
    private String url2;

    /**
     * URL_3
     */
    private String url3;

    /**
     * URL_4
     */
    private String url4;

    /**
     * URL_5
     */
    private String url5;

    /**
     * URL_6
     */
    private String url6;

    /**
     * URL_7
     */
    private String url7;

    /**
     * 삭제 여부
     */
    private int deleteF;

    /**
     * 수정 여부
     */
    private int editF = 1;

    public List<Portlet> getPortletList() {
        return portletList;
    }

    public void setPortletList(List<Portlet> portletList) {
        this.portletList = portletList;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    private String userPortletId;

    public String getUserPortletId() {
        return userPortletId;
    }

    public void setUserPortletId(String userPortletId) {
        this.userPortletId = userPortletId;
    }

    public String getPortletId() {
        return portletId;
    }

    public void setPortletId(String portletId) {
        this.portletId = portletId;
    }

    public String getPortletName() {
        return portletName;
    }

    public void setPortletName(String portletName) {
        this.portletName = portletName;
    }

    public String getPortletDisplayName() {
        return portletDisplayName;
    }

    public void setPortletDisplayName(String portletDisplayName) {
        this.portletDisplayName = portletDisplayName;
    }

    public int getUseF() {
        return useF;
    }

    public void setUseF(int useF) {
        this.useF = useF;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getDisplayTypeCd() {
        return displayTypeCd;
    }

    public void setDisplayTypeCd(String displayTypeCd) {
        this.displayTypeCd = displayTypeCd;
    }

    public String getPortlet1() {
        return portlet1;
    }

    public void setPortlet1(String portlet1) {
        this.portlet1 = portlet1;
    }

    public String getPortlet2() {
        return portlet2;
    }

    public void setPortlet2(String portlet2) {
        this.portlet2 = portlet2;
    }

    public String getPortlet3() {
        return portlet3;
    }

    public void setPortlet3(String portlet3) {
        this.portlet3 = portlet3;
    }

    public String getPortlet4() {
        return portlet4;
    }

    public void setPortlet4(String portlet4) {
        this.portlet4 = portlet4;
    }

    public String getPortlet5() {
        return portlet5;
    }

    public void setPortlet5(String portlet5) {
        this.portlet5 = portlet5;
    }

    public String getPortlet6() {
        return portlet6;
    }

    public void setPortlet6(String portlet6) {
        this.portlet6 = portlet6;
    }

    public String getPortlet7() {
        return portlet7;
    }

    public void setPortlet7(String portlet7) {
        this.portlet7 = portlet7;
    }

    public String getPortletName1() {
        return portletName1;
    }

    public void setPortletName1(String portletName1) {
        this.portletName1 = portletName1;
    }

    public String getPortletName2() {
        return portletName2;
    }

    public void setPortletName2(String portletName2) {
        this.portletName2 = portletName2;
    }

    public String getPortletName3() {
        return portletName3;
    }

    public void setPortletName3(String portletName3) {
        this.portletName3 = portletName3;
    }

    public String getPortletName4() {
        return portletName4;
    }

    public void setPortletName4(String portletName4) {
        this.portletName4 = portletName4;
    }

    public String getPortletName5() {
        return portletName5;
    }

    public void setPortletName5(String portletName5) {
        this.portletName5 = portletName5;
    }

    public String getPortletName6() {
        return portletName6;
    }

    public void setPortletName6(String portletName6) {
        this.portletName6 = portletName6;
    }

    public String getPortletName7() {
        return portletName7;
    }

    public void setPortletName7(String portletName7) {
        this.portletName7 = portletName7;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url4) {
        this.url4 = url4;
    }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url5) {
        this.url5 = url5;
    }

    public String getUrl6() {
        return url6;
    }

    public void setUrl6(String url6) {
        this.url6 = url6;
    }

    public String getUrl7() {
        return url7;
    }

    public void setUrl7(String url7) {
        this.url7 = url7;
    }

    public String getEsntlId() {
        return esntlId;
    }

    public void setEsntlId(String esntlId) {
        this.esntlId = esntlId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public List<String> getPortletIds() {
        return portletIds;
    }

    public void setPortletIds(List<String> portletIds) {
        this.portletIds = portletIds;
    }

    public String getSystems() {
        return systems;
    }

    public void setSystems(String systems) {
        this.systems = systems;
    }

    public int getDeleteF() {
        return deleteF;
    }

    public void setDeleteF(int deleteF) {
        this.deleteF = deleteF;
    }

    public int getEditF() {
        return editF;
    }

    public void setEditF(int editF) {
        this.editF = editF;
    }

    public String getPortlets() {
        return portlets;
    }

    public void setPortlets(String portlets) {
        this.portlets = portlets;
    }
}
