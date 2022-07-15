package egovframework.com.infra.noty.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import egovframework.com.infra.noty.service.NotificationMessageVO;
import egovframework.com.infra.noty.service.NotificationUserVO;

@Repository
public class NotificationDAOImpl implements NotificationDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String Namespace = "";

    public List<NotificationUserVO> selectUserList(String strUserArray) {
        return sqlSession.selectList(Namespace + "NotificationDAO.selectUserList", strUserArray);
    }

    public List<NotificationUserVO> selectUserListByDBSchema(Map<String, String> param) {
        return sqlSession.selectList(Namespace + "NotificationDAO.selectUserListByDBSchema", param);
    }

    public int insertAlert(NotificationMessageVO messageVO) {
        return sqlSession.insert(Namespace + "NotificationDAO.insertAlert", messageVO);
    }
}
