package egovframework.com.global.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import egovframework.com.global.DynamicContextHolder;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
    protected Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
        if (request.getRequestURI().indexOf(".do") > 0) {
            log.info("================          START         ================");
            log.info(" Request URI   :  " + request.getRequestURI());
            log.info(" ContextHolder :  " + DynamicContextHolder.getDynamicType());
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (request.getRequestURI().indexOf(".do") > 0) {
            log.info("================           END          ================\n");
        }
    }
}
