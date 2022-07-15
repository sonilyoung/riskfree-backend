package egovframework.com.infra.noty.service;

import java.util.List;

public interface NotificationService {
	public List<NotificationUserVO> selectUserList(String strUserArray);
	public void insertAlert(NotificationMessageVO messageItem);
}
