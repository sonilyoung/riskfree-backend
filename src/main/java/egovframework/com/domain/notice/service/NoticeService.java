package egovframework.com.domain.notice.service;

import java.util.List;

import egovframework.com.domain.notice.domain.Notice;
import egovframework.com.domain.notice.parameter.NoticeParameter;
import egovframework.com.domain.notice.parameter.NoticeSearchParameter;

public interface NoticeService {

	List<Notice> getNoticeList(NoticeSearchParameter parameter);

	void insertNotice(NoticeParameter parameter);

	Notice getNotice(Long companyId, Long noticeId);

	void updateNotice(NoticeParameter parameter);

	void deleteNotice(Long companyId, Long noticeId);

	int updateViewCount(Notice notice);

}
