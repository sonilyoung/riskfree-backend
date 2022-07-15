package egovframework.com.global.common.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import egovframework.com.domain.portal.logn.domain.Login;


@Service("EmailMngService")
public class EmailMngServiceImpl implements EmailMngService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailMngServiceImpl.class);

    // private static final Logger LOGGER =
    // LoggerFactory.getLogger(ApprovalLinkageServiceImpl.class);

    // String RDB_MOBILE_GATEWAY_BASE_URL = GlobalsProperties.getProperty(Globals.MG_BASE_URL);
    // String RDB_MOBILE_GATEWAY_BASE_URL = OfficeComStringUtil.RDB_MOBILE_GATEWAY_BASE_URL;

    /**
     * 개요 : 메일을 전송한다
     * 
     * @param ifParams 메일 정보
     */
    @Override
    public void sendApprovalEmailLinkage(Map<String, Object> ifParams, String mblGwUrl,
            Login Login) {
        /*
         * ifParams.put("MAIL_TY", Login.getMailTy()); ifParams.put("MAIL_IP", Login.getMailIp());
         * ifParams.put("MAIL_PORT", Login.getMailPort());
         */

        Map<String, Object> paramMap = new HashMap<String, Object>();
        // paramMap.put("url", OfficeComStringUtil.RDB_MOBILE_GATEWAY_BASE_URL +
        // "rdbm_send_email.do");
        paramMap.put("url", mblGwUrl + "rdbm_send_email.do");
        paramMap.put("ifParams", ifParams);

        try {
            // 결재 알림 메일 보내기
            // EmlManagementController emlManagementController = new EmlManagementController();
            Map<String, Object> result = null;// emlManagementController.callZimbraIf( paramMap );

            LOGGER.info("sendApprovalEmailLinkage() ]" + result.toString() + "[");

            // 결과 보내기
            if ("0000".equals(result.get("RESULT").toString())) {
                LOGGER.debug("==========Approval (1) Email Linkage Send Success");
            } else {
                LOGGER.debug("==========Approval (1) Email Linkage Send Error, Cause : "
                        + result.get("DESC").toString());
            }
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.error(e.getMessage());
            return; // 에러 페이지 출력안함
        }
    }

    /**
     * 개요 : 메일 내용을 구성한다
     * 
     * @param authToken Zimbra 인증 정보
     * @param subject 메일 제목
     * @param contents 메일 내용
     * @param emailAdresList 받을 메일 주소 목록
     * @throws Exception
     */
    @Override
    public void getEmailParamLinkage(String authToken, String subject, String contents,
            List<String> emailAdresList, String mblGwUrl, Login Login) throws JSONException {
        // 메일주소
        JSONArray jsonArray = new JSONArray();
        // parameters
        Map<String, Object> ifParams = new LinkedHashMap<>();

        try {
            // to 가 배열인 경우
            for (String emailAdres : emailAdresList) {
                if (StringUtils.isNotBlank(emailAdres)) {
                    JSONObject sObject = new JSONObject();// 배열 내에 들어갈 json
                    sObject.put("EMAIL_ID", emailAdres);
                    sObject.put("TYPE", "TO");
                    sObject.put("EMAIL_NAME", "");
                    jsonArray.put(sObject);
                }
            }

            ifParams.put("AUTH_TOKEN", authToken);
            ifParams.put("EMAIL_TOS", jsonArray.toString());
            ifParams.put("SUBJECT", subject);
            ifParams.put("CONTENTS", contents);


            // 일반전송
            // ifParams.put("ACTION",null);
        } catch (Exception e) {
            // e.printStackTrace();
            LOGGER.error(e.getMessage());
            return;// 에러 페이지 출력안함
        }

        // 메일 전송
        sendApprovalEmailLinkage(ifParams, mblGwUrl, Login);
    }
}
