package egovframework.com.infra.noty.service;

public class NotificationUserVO {
	public String userId = "";
	public String name = "";
	public String email = "";
	public String notificationType = "";
	public String mailTy = "";
	
	public String getMailTy() {
		return mailTy;
	}
	public void setMailTy(String mailTy) {
		this.mailTy = mailTy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}
}
