package egovframework.com.global.genid.impl;

import org.springframework.stereotype.Repository;
import egovframework.com.global.genid.GenIdDAO;

@Repository
public class GenIdDAOImpl implements GenIdDAO {

    public int selectNextId(String tableName) throws Exception {
        return 1;
    }
}
