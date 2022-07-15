package egovframework.com.global.common.domain;

import java.io.Serializable;

/**
 * @Class Name : FileVO.java
 * @Description : 파일정보 처리를 위한 VO 클래스
 * @Modification Information
 *
 *               수정일 수정자 수정내용 ------- ------- ------------------- 2009. 3. 25. 이삼섭
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 25.
 * @version
 * @see
 *
 */
@SuppressWarnings("serial")
public class MonitorApvDueDateVO implements Serializable {
    private String docTitle;
    private String docDueDate;
    private String emailAddress;


    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDodDueDate() {
        return docDueDate;
    }

    public void setDodDueDate(String dodDueDate) {
        this.docDueDate = dodDueDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
