package egovframework.com.domain.example.dao;

import java.util.List;

import egovframework.com.domain.example.domain.MemberVO;

public interface MemberDAO {
	
	public List<MemberVO> selectMember() throws Exception;
}
