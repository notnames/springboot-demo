package com.example.springbootdemo2.demos.web.exception;

import com.example.springbootdemo2.demos.web.entity.Result;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    public Result unAuthorizedException(UnauthorizedException e) {
        return Result.error(401, e.getMessage());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
//        // 【关键步骤】不要直接 ex.getMessage()！
//        // 而是从 BindingResult 中获取第一个错误的默认消息
//        String errorMessage = ex.getBindingResult()
//                .getFieldError() // 获取第一个字段错误
//                .getDefaultMessage(); // 获取我们在注解里写的 message
//
//
//        return Result.error(errorMessage);
//        // 现在返回的 message 只有："用户名长度必须在 2-20 之间"
//    }
    // 全局异常
    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        String message = e.getMessage();
        System.out.println("ERR:" + e.getMessage());
        return Result.error(StringUtils.hasLength(message)? message : "操作失败！");
    }

//    // 头像内容非url异常
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Result handleConstraintViolation(ConstraintViolationException e) {
//        // 提取错误信息（比如取第一条）
//        String message = e.getConstraintViolations().iterator().next().getMessage();
//        // 返回统一的错误结果
//        return Result.error(message);
//    }
//
//    // 头像内容非url异常
//    @ExceptionHandler(EntityNotFoundException.class)
//    public Result handleConstraintViolation(EntityNotFoundException e) {
//        // 提取错误信息（比如取第一条）
//        String message = e.getMessage();
//        // 返回统一的错误结果
//        return Result.error(message);
//    }

}
