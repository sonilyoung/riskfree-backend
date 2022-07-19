package egovframework.com.domain.notice.dao;

import java.util.List;

import egovframework.com.domain.notice.domain.Notice;
import egovframework.com.domain.notice.parameter.NoticeParameter;
import egovframework.com.domain.notice.parameter.NoticeSearchParameter;

public interface NoticeDAO {

	List<Notice> getNoticeList(NoticeSearchParameter parameter);

	void insertNotice(NoticeParameter parameter);

	Notice getNotice(Long companyId, Long noticeId);

	void updateNotice(NoticeParameter parameter);

	void deleteNotice(Long companyId, Long noticeId);

	int updateViewCount(Notice notice);

}
