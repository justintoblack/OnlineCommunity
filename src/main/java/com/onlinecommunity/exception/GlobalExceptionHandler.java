package com.onlinecommunity.exception;

import com.onlinecommunity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@ControllerAdvice(basePackages = {"com.onlinecommunity.controller"})
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception exception){
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", exception);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("ErrorPage");
//        return mav;
//    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(RuntimeException e) {
        log.error("运行时异常：{}", e.getMessage());
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验异常：{}", e.getMessage());
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(BindException e) {
        log.error("BindException:{}", Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        return Result.failure(e.getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result exceptionHandler(IllegalArgumentException e) {
        log.error("IllegalArgumentException:{}", e.getMessage());
        return Result.failure(e.getMessage());
    }
}
