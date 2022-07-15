package egovframework.com.global.genid;

public interface GenIdDAO {

    public int selectNextId(String tableName) throws Exception;
}
