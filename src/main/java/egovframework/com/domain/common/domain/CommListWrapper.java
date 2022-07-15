package egovframework.com.domain.common.domain;

import java.util.List;
import lombok.Data;

/**
 * 공통 List Wrapper
 * 
 * @fileName : CommListWrapper.java
 * @author : YeongJun Lee
 * @date : 2022.04.04
 */
@Data
public class CommListWrapper<T extends CommListVo> {
    private int totalCnt;
    private List<T> list;

    public CommListWrapper(List<T> list) {
        this.list = list;
        // 페이징된 데이터 건수가 아닌 전체 데이터 건수를 저장
        if (list != null && list.size() > 0) {
            this.totalCnt = list.size();
        }
    }
}
