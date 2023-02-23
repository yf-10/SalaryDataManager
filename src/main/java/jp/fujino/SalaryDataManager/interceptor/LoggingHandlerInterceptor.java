package jp.fujino.SalaryDataManager.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.logging.Logger;

public class LoggingHandlerInterceptor implements HandlerInterceptor {

    Logger logger = Logger.getLogger(LoggingHandlerInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String message = String.format("[%s] %-4s %s",
                request.getLocalAddr(), request.getMethod(), request.getRequestURI());
        logger.info(message);
        return true;
    }

}
