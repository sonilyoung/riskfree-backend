package egovframework.com.domain.org.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import egovframework.com.domain.org.service.OrgnztManageService;
import egovframework.com.domain.org.service.UserManageService;

/**
 * 조직 관리를 위한 컨트롤러 클래스
 * 
 * @author paul
 * @since 2016.02.04
 * @version 1.0
 * @see
 *
 *      <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일               수정자           수정내용
 *  -------       --------    ---------------------------
 *  2016.02.04    paul        최초 생성
 *  2017.04.05    suji.h      변수명 정리
 *      </pre>
 */
@Controller
public class OrgnztManageController {

    @Resource(name = "OrgnztManageService")
    private OrgnztManageService orgnztService;

    @Autowired
    private UserManageService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrgnztManageController.class);



}
