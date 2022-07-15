package egovframework.com.global.common.service;

import java.util.List;
import java.util.Map;
import org.codehaus.jettison.json.JSONException;
import egovframework.com.domain.portal.logn.domain.Login;

public interface EmailMngService {

    /**
     * 개요 : 메일을 전송한다
     * 
     * @param ifParams 메일 정보
     */
    public void sendApprovalEmailLinkage(Map<String, Object> ifParams, String mblGwUrl,
            Login Login);

    /**
     * 개요 : 메일 내용을 구성한다
     * 
     * @param authToken Zimbra 인증 정보
     * @param subject 메일 제목
     * @param contents 메일 내용
     * @param emailAdresList 받을 메일 주소 목록
     * @param Login 메일 type/ip/port
     * @throws Exception
     */
    public void getEmailParamLinkage(String authToken, String subject, String contents,
            List<String> emailAdresList, String mblGwUrl, Login Login) throws JSONException;

}
