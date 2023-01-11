package by.klubnikov.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Exception exception) {
        exception.printStackTrace();
        String exceptionMessage = exception.getMessage();
        String excURI = request.getRequestURI();
        String excURL = request.getRequestURL().toString();
        request.getSession().setAttribute("excMessage", exceptionMessage);
        request.getSession().setAttribute("excURI", excURI);
        request.getSession().setAttribute("excURL", excURL);
        return "error.jsp";
    }
}
