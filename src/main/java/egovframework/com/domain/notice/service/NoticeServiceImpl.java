package egovframework.com.domain.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.com.domain.notice.dao.NoticeDAO;
import egovframework.com.domain.notice.domain.Notice;
import egovframework.com.domain.notice.parameter.NoticeParameter;
import egovframework.com.domain.notice.parameter.NoticeSearchParameter;

@Service
@Transactional(readOnly = true)
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO repository;
	
	@Override
	public List<Notice> getNoticeList(NoticeSearchParameter parameter) {
		return repository.getNoticeList(parameter);
	}

	@Override
	public void insertNotice(NoticeParameter parameter) {
		repository.insertNotice(parameter);
	}

	@Override
	public Notice getNotice(Long companyId, Long noticeId) {
		return repository.getNotice(companyId, noticeId);
	}

	@Override
	public void updateNotice(NoticeParameter parameter) {
		repository.updateNotice(parameter);
	}

	@Override
	public void deleteNotice(Long companyId, Long noticeId) {
		repository.deleteNotice(companyId, noticeId);
	}

	@Override
	public int updateViewCount(Notice notice) {
		
		// 조회수 중복증가 막는 로직 필요
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		
		return repository.updateViewCount(notice);
	}

}
