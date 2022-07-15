package egovframework.com.domain.example.service;

import java.util.List;
import egovframework.com.domain.example.domain.MemberVO;

public interface MemberService {

    public List<MemberVO> selectMember() throws Exception;
}
