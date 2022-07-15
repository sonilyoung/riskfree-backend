package egovframework.com.global.common.domain;

import java.io.Serializable;

public class TreeNodeVO implements Serializable {

    private static final long serialVersionUID = -20160202181700L;

    private String id;

    private String text;

    private String parent_id;

    private int sorder;

    private String children;

    // contact에서 트리 아이콘을 바꾸기 위해 사용
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public int getSorder() {
        return sorder;
    }

    public void setSorder(int sorder) {
        this.sorder = sorder;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    /************* jstree end *******************/

    /**
     * Node Id
     */
    private String nodeId;

    /**
     * Parent node id
     */
    private String parentId;

    /**
     * Node name
     */
    private String nodeNm;

    /**
     * Node Level
     */
    private String level;

    /**
     * Display sequence in same level
     */
    private int seq;

    /**
     * Return id of node
     * 
     * @return nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * Set id of node
     * 
     * @param id
     */
    public void setNodeId(String id) {
        nodeId = id;
    }


    /**
     * Return id of parent node
     * 
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * Set parent id
     * 
     * @param id
     */
    public void setParentId(String id) {
        parentId = id;
    }

    /**
     * Return node name
     * 
     * @return nodeNm
     */
    public String getNodeNm() {
        return nodeNm;
    }

    /**
     * Set node name
     * 
     * @param name
     */
    public void setNodeNm(String name) {
        nodeNm = name;
    }

    /**
     * Return level of node
     * 
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Set level of node
     * 
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Return display sequence of node in the depth
     * 
     * @return seq
     */
    public int getSeq() {
        return seq;
    }

    /**
     * Set display sequence of node
     * 
     * @param seq
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

}
