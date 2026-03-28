package com.example.springbootdemo2.demos.web.exception;

// 自定义异常类，用于token异常
public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String message) {
        super(message);
    }
}
