package egovframework.com.domain.law.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.domain.law.service.LawService;
import io.swagger.annotations.Api;

/**
 * 관계법령에 따른 개선시정 명령조치 현황 API 컨트롤러
 * 
 * @author KimJuHwan
 *
 */
@RestController
@RequestMapping("/law/improvements")
@Api(tags = "Law Improvements Management API")
public class LawController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LawController.class);
	
	@Autowired
	private LawService lawService;
	
	
	
}
