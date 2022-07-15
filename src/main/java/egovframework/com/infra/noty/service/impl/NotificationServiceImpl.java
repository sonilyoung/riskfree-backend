package egovframework.com.infra.noty.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.com.infra.noty.service.NotificationMessageVO;
import egovframework.com.infra.noty.service.NotificationService;
import egovframework.com.infra.noty.service.NotificationUserVO;

@Service("NotificationService")
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public List<NotificationUserVO> selectUserList(String strUserArray) {
        return notificationDAO.selectUserList(strUserArray);
    }

    @Override
    public void insertAlert(NotificationMessageVO messageVO) {
        notificationDAO.insertAlert(messageVO);
    }
}
