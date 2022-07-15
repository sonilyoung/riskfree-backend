package egovframework.com.domain.portal.system.domain;

import java.util.List;

public class SystemMenu {

    /**
     * 시스템 아이디
     */
    private String systemId;

    /**
     * 시스템 명
     */
    private String systemName;

    /**
     * 사용 여부
     */
    private int useF;

    /**
     * 사용 범위
     */
    private String useScope;

    /**
     * 순번
     */
    private int seq;

    /**
     * 시스템 IDs
     */
    private List<String> systemIds;

    /**
     * 시스템리스트
     */
    private String systems;

    /**
     * 시스템리스트
     */
    private String userSystemId;

    /**
     * display name
     */
    private String systemDisplayName;

    /**
     * URL
     */
    private String url;

    /**
     * 사용자 아이디
     */
    private String esntlId;

    /**
     * 삭제 여부
     */
    private int deleteF;

    /**
     * 수정 여부
     */
    private int editF = 1;

    public List<String> getSystemIds() {
        return systemIds;
    }

    public void setSystemIds(List<String> systemIds) {
        this.systemIds = systemIds;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public int getUseF() {
        return useF;
    }

    public void setUseF(int useF) {
        this.useF = useF;
    }

    public String getUseScope() {
        return useScope;
    }

    public void setUseScope(String useScope) {
        this.useScope = useScope;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getSystems() {
        return systems;
    }

    public void setSystems(String systems) {
        this.systems = systems;
    }

    public String getUserSystemId() {
        return userSystemId;
    }

    public void setUserSystemId(String userSystemId) {
        this.userSystemId = userSystemId;
    }

    public String getEsntlId() {
        return esntlId;
    }

    public void setEsntlId(String esntlId) {
        this.esntlId = esntlId;
    }

    public String getSystemDisplayName() {
        return systemDisplayName;
    }

    public void setSystemDisplayName(String systemDisplayName) {
        this.systemDisplayName = systemDisplayName;
    }

    public String getUrl() {
        return url;
    }

    public int getDeleteF() {
        return deleteF;
    }

    public void setDeleteF(int deleteF) {
        this.deleteF = deleteF;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEditF() {
        return editF;
    }

    public void setEditF(int editF) {
        this.editF = editF;
    }
}
