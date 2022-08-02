package egovframework.com.domain.law.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LawServiceImpl implements LawService {

}
