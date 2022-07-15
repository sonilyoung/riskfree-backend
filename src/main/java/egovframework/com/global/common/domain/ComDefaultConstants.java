package egovframework.com.global.common.domain;


public class ComDefaultConstants {
    // 트리 변경 코드
    /**
     * <p>
     * Sibling Add
     */
    public static final String TREE_FUNC_TYPE_SA = "SA";
    /**
     * <p>
     * Child Add
     */
    public static final String TREE_FUNC_TYPE_CA = "CA";
    /**
     * <p>
     * Delete
     */
    public static final String TREE_FUNC_TYPE_D = "D";
    /**
     * <p>
     * Update
     */
    public static final String TREE_FUNC_TYPE_U = "U";
    /**
     * <p>
     * Sibling Move
     */
    public static final String TREE_FUNC_TYPE_SM = "SM";
    /**
     * <p>
     * Child Move
     */
    public static final String TREE_FUNC_TYPE_CM = "CM";
    /**
     * <p>
     * 한 레벨 하위
     * <p>
     * The node or the entire tree
     */
    public static final int SCOPE_OBJECT = 0;
    /**
     * <p>
     * 한 레벨 하위
     * <p>
     * in the one level
     */
    public static final int SCOPE_ONELEVEL = 1;
    /**
     * <p>
     * 서브 트리
     * <p>
     * in the one level and sub-levels
     */
    public static final int SCOPE_SUBTREE = 2;
    /**
     * <p>
     * 상위로 루트
     * <p>
     * in the tree-based hierarchical path from the base level to the root level
     */
    public static final int SCOPE_PATH = 3;
    // 카테고리 여부 -> 담당자 체크
    /**
     * <p>
     * Category
     */
    public static final String CATEGORY_F_Y = "1";
    /**
     * <p>
     * Not Category
     */
    public static final String CATEGORY_F_N = "2";
    // 상위 여부 -> 담당자 체크
    // - 1 : 상위
    // - 2 : 상위 아님
    /**
     * <p>
     * Top
     */
    public static final String TOP_F_Y = "1";
    /**
     * <p>
     * Not Top
     */
    public static final String TOP_F_N = "2";
    // 여부의 코드값
    /**
     * <p>
     * 참(=여)의 코드값
     * <p>
     * True Code Value
     */
    public static final int FLAG_TRUE = 1;
    /**
     * <p>
     * 거짓(=부)의 코드값
     * <p>
     * False Code Value
     */
    public static final int FLAG_FALSE = 0;
    // 여부의 문자열
    /**
     * <p>
     * 참의 문자열
     * <p>
     * String of True
     */
    public static final String TRUE_STR = "true";
    /**
     * <p>
     * 거짓의 문자열
     * <p>
     * String of False
     */
    public static final String FALSE_STR = "false";
    // NULL값
    /**
     * <p>
     * ID type Null value
     */
    public static final String NULL_ID_STRING = "000000000";
    // 변경 내용
    /**
     * <p>
     * Add
     */
    public static final String ACTION_ADD = "add";
    /**
     * <p>
     * Delete
     */
    public static final String ACTION_DELETE = "delete";
    /**
     * <p>
     * undefined
     */
    public static final String UNDEFINED = "undefined";


}
