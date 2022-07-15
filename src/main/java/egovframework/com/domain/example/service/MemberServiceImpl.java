package egovframework.com.domain.example.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import egovframework.com.domain.example.dao.MemberDAO;
import egovframework.com.domain.example.domain.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public List<MemberVO> selectMember() throws Exception {

		return dao.selectMember();
	}

}
