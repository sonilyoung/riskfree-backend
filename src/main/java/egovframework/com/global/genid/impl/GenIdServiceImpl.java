package egovframework.com.global.genid.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.com.global.genid.GenIdDAO;
import egovframework.com.global.genid.GenIdService;
import egovframework.com.global.util.OfficeStringUtil;



/**
 * 일반 로그인, 인증서 로그인을 처리하는 비즈니스 인터페이스 클래스
 * 
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력(Modification Information) >>
 * 
 *   수정일      수정자          수정내용
 *  -------    --------    ---------------------------
 *  2009.03.06  박지욱          최초 생성 
 *  2011.08.26  서준식          EsntlId를 이용한 로그인 추가
 *      </pre>
 */
@Service("GenIdService")
public class GenIdServiceImpl implements GenIdService {

    @Autowired
    private GenIdDAO genIdDAO;


    private String strNextId(String key, String pre) throws Exception {
        int nextInt = 0;
        String nextId = "";
        String returnId = "";

        nextInt = genIdDAO.selectNextId(key);
        nextId = Integer.toString(nextInt);
        returnId = pre + OfficeStringUtil.lPad(nextId, (20 - pre.length()), '0');

        return returnId;
    }

    private long longNextId(String key) throws Exception {
        int nextInt = 0;
        nextInt = genIdDAO.selectNextId(key);
        return (long) nextInt;
    }

    private String strNextId(String key) throws Exception {
        int nextInt = 0;
        nextInt = genIdDAO.selectNextId(key);
        return (String) (Integer.toString(nextInt));
    }

    /* E-Approval */

    @Override
    public String selectApprDocNextId() throws Exception {
        return strNextId("DOC_ID", "DOC_");
    }

    @Override
    public String selectApprAtchChngHstryNextId() throws Exception {
        return strNextId("ATCH_C_H_ID", "FILECH_");
    }

    @Override
    public String selectApprAutoSngrNextId() throws Exception {
        return strNextId("SNGR_A_ID", "SNGRA_");
    }

    @Override
    public String selectApprFormNextId() throws Exception {
        return strNextId("FORM_ID", "FORM_");
    }

    @Override
    public String selectApprPDFNextId() throws Exception {
        return strNextId("PDF_ID", "PDF_");
    }

    @Override
    public String selectApprRecpNextId() throws Exception {
        return strNextId("RECP_ID", "RECP_");
    }

    @Override
    public String selectApprDigSigNextId() throws Exception {
        return strNextId("DIG_SIG_ID", "DIGSIG_");
    }

    @Override
    public String selectApprFixedRecpNextId() throws Exception {
        return strNextId("RECP_F_ID", "RECP_F_");
    }

    @Override
    public String selectApprFixedSngrNextId() throws Exception {
        return strNextId("SNGR_F_ID", "SNGR_F_");
    }

    @Override
    public String selectApprSngrChngHstryNextId() throws Exception {
        return strNextId("SNGR_C_H_ID", "SNGRCH_");
    }

    @Override
    public String selectApprSngrHstryNextId() throws Exception {
        return strNextId("SNGR_H_ID", "SNGRH_");
    }

    @Override
    public String selectApprSngrNextId() throws Exception {
        return strNextId("SNGR_ID", "SNGR_");
    }

    @Override
    public String selectApprSngrShareNextId() throws Exception {
        return strNextId("SNGR_S_ID", "SNGRS_");
    }

    /* BBS */
    @Override
    public String selectBBSMstNextId() throws Exception {
        return strNextId("BBS_ID", "BBSMSTR_");
    }

    @Override
    public long selectBBSMsgNextId() throws Exception {
        return longNextId("NTT_ID");
    }

    @Override
    public String selectCommentNextId() throws Exception {
        return strNextId("NTT_ID");
    }

    /* E-Folder */
    @Override
    public String selectEDocFolderNextId() throws Exception {
        return strNextId("FOLDER_ID", "EDCFLD_");
    }

    @Override
    public String selectEDocNextId() throws Exception {
        return strNextId("EDOC_ID", "EDOC_");
    }

    @Override
    public String selectEDocFileNextId() throws Exception {
        return strNextId("EDOC_FILE_ID", "EDFL_");
    }

    @Override
    public String selectEDocSharedFolderNextId() throws Exception {
        return strNextId("SHARFLD_ID", "SHFLD_");
    }

    @Override
    public String selectEDocFolderDocNextId() throws Exception {
        return strNextId("FOLDER_DOC_ID", "FLDDOC_");
    }

    /* Workspace */
    @Override
    public String selectWorkspaceNextId() throws Exception {
        return strNextId("WS_ID", "WS_");
    }

    /* File */
    @Override
    public String selectFileNextId() throws Exception {
        return strNextId("FILE_ID", "FILE_");
    }

    /* Survey */
    @Override
    public String selectSuySurNextId() throws Exception {
        return strNextId("SUR_ID", "SUR_");
    }

    @Override
    public String selectSuyQstNextId() throws Exception {
        return strNextId("QST_ID", "QST_");
    }

    @Override
    public String selectSuyChoNextId() throws Exception {
        return strNextId("CHO_ID", "CHO_");
    }

    @Override
    public String selectSuyResNextId() throws Exception {
        return strNextId("RES_ID", "RES_");
    }

    @Override
    public String selectSuyReseNextId() throws Exception {
        return strNextId("RESE_ID", "RESE_");
    }

    @Override
    public String selectSuyFolderNextId() throws Exception {
        return strNextId("SU_FOLDER_ID", "SUFLD_");
    }

    /* Reservation */
    @Override
    public String selectReResveNextId() throws Exception {
        return strNextId("RESVE_ID", "RESVE_");
    }

    @Override
    public long selectReGroupNextId() throws Exception {
        return longNextId("GROUP_ID");
    }

    @Override
    public String selectReMtgpNextId() throws Exception {
        return strNextId("MTG_PLACE_ID", "MTGP_");
    }

    /* Portal */
    @Override
    public String selectPrtlSystemNextId() throws Exception {
        return strNextId("SYSTEM_ID", "SYSTEM_");
    }

    @Override
    public String selectPrtlPortletNextId() throws Exception {
        return strNextId("PORTLET_ID", "PT_");
    }

    @Override
    public String selectPrtlLinkNextId() throws Exception {
        return strNextId("LINKS_ID", "LINKS_");
    }

    /* Org */
    @Override
    public String selectOrgAbsNextId() throws Exception {
        return strNextId("ABS_ID", "ABS_");
    }

}
