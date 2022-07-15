package egovframework.com.global.genid;

/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *  
 * <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성 
 *  2011.08.26  서준식          EsntlId를 이용한 로그인 추가
 *  </pre>
 */
public interface GenIdService {

	public String selectApprDocNextId() throws Exception;
	
	public String selectApprAtchChngHstryNextId() throws Exception;
	
	public String selectApprAutoSngrNextId() throws Exception;
	
	public String selectApprFormNextId() throws Exception;
	
	public String selectApprPDFNextId() throws Exception;
	
	public String selectApprRecpNextId() throws Exception;
	
	public String selectApprFixedRecpNextId() throws Exception;
	
	public String selectApprDigSigNextId() throws Exception;
	
	public String selectApprFixedSngrNextId() throws Exception;
	
	public String selectApprSngrChngHstryNextId() throws Exception;
	
	public String selectApprSngrHstryNextId() throws Exception;
	
	public String selectApprSngrNextId() throws Exception;
	
	public String selectApprSngrShareNextId() throws Exception;
	
	public String selectBBSMstNextId() throws Exception;
	
	public long selectBBSMsgNextId() throws Exception;
	
	public String selectCommentNextId() throws Exception;
	
	public String selectEDocFolderNextId() throws Exception;
	
	public String selectEDocNextId() throws Exception;
	
	public String selectEDocFileNextId() throws Exception;
	
	public String selectEDocSharedFolderNextId() throws Exception;
	
	public String selectEDocFolderDocNextId() throws Exception;
	
	public String selectWorkspaceNextId() throws Exception;
	
	public String selectFileNextId() throws Exception;
	
	public String selectSuySurNextId() throws Exception;
	
	public String selectSuyQstNextId() throws Exception;

	public String selectSuyChoNextId() throws Exception;
	
	public String selectSuyResNextId() throws Exception;
	
	public String selectSuyReseNextId() throws Exception;
	
	public String selectSuyFolderNextId() throws Exception;
	
	public String selectReResveNextId() throws Exception;
	
	public long selectReGroupNextId() throws Exception;
		
	public String selectReMtgpNextId() throws Exception;
	
	public String selectPrtlSystemNextId() throws Exception;
	
	public String selectPrtlPortletNextId() throws Exception;
	
	public String selectPrtlLinkNextId() throws Exception;
	
	public String selectOrgAbsNextId() throws Exception;
}
