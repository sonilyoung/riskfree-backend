package egovframework.com.domain.notice.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import egovframework.com.domain.notice.domain.Notice;
import egovframework.com.domain.notice.parameter.NoticeParameter;
import egovframework.com.domain.notice.parameter.NoticeSearchParameter;

@Repository
public class NoticeDAOImpl implements NoticeDAO {
	
	@Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "egovframework.com.domain.notice.dao.NoticeDAO";

	@Override
	public List<Notice> getNoticeList(NoticeSearchParameter parameter) {
		return sqlSession.selectList(Namespace + ".getNoticeList", parameter);
	}

	@Override
	public void insertNotice(NoticeParameter parameter) {
		sqlSession.insert(Namespace + ".insertNotice", parameter);
	}

	@Override
	public Notice getNotice(Long companyId, Long noticeId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("noticeId", noticeId);
		return sqlSession.selectOne(Namespace + ".getNotice", map);
	}

	@Override
	public void updateNotice(NoticeParameter parameter) {
		sqlSession.update(Namespace + ".updateNotice", parameter);
	}

	@Override
	public void deleteNotice(Long companyId, Long noticeId) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("companyId", companyId);
		map.put("noticeId", noticeId);
		sqlSession.delete(Namespace + ".deleteNotice", map);
	}

	@Override
	public int updateViewCount(Notice notice) {
		return sqlSession.update(Namespace + ".updateViewCount", notice);
	}

}
