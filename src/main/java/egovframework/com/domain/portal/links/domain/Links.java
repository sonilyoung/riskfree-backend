package egovframework.com.domain.portal.links.domain;

public class Links {

    /* */
    private String linksId;
    /* */
    private String linksName;
    /* */
    private String url;
    /* */
    private String orgnztId;
    /* */
    private int displayOrder;

    private int useF;

    public String getLinksId() {
        return linksId;
    }

    public void setLinksId(String linksId) {
        this.linksId = linksId;
    }

    public String getLinksName() {
        return linksName;
    }

    public void setLinksName(String linksName) {
        this.linksName = linksName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrgnztId() {
        return orgnztId;
    }

    public void setOrgnztId(String orgnztId) {
        this.orgnztId = orgnztId;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public int getUseF() {
        return useF;
    }

    public void setUseF(int useF) {
        this.useF = useF;
    }

}
