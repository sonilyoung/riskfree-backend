package egovframework.com.infra.noty.service.impl;

import java.util.List;
import java.util.Map;
import egovframework.com.infra.noty.service.NotificationMessageVO;
import egovframework.com.infra.noty.service.NotificationUserVO;

public interface NotificationDAO {
    public List<NotificationUserVO> selectUserList(String strUserArray);

    public List<NotificationUserVO> selectUserListByDBSchema(Map<String, String> param);

    public int insertAlert(NotificationMessageVO messageVO);
}
