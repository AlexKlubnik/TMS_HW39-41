package by.klubnikov.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception exception) {
        log.info("We have got the exception, {}", exception.getMessage());

        String excURI = request.getRequestURI();
        String excURL = request.getRequestURL().toString();
        request.getSession().setAttribute("excMessage", "Something went wrong at");
        request.getSession().setAttribute("excURI", excURI);
        request.getSession().setAttribute("excURL", excURL);
        return "error.jsp";
    }
}
