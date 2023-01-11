package by.klubnikov.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;

@Slf4j
public class StudentsInterceptor implements HandlerInterceptor {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        int studentsQuant = (int) request.getSession().getAttribute("quantity");
        LocalDateTime dateTime = (LocalDateTime) request.getSession().getAttribute("time");
        log.info("GetAllStudents method was called at {}", dateTime);
        log.info("We have got {} students", studentsQuant);
    }
}
