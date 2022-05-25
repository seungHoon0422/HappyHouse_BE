package com.house.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Map handlerException(Exception e){
        e.printStackTrace();
        Map result = new HashMap();
        result.put("code", "ERR");
        result.put("message", "어드바이스 에러 테스트");

        return result;
    }
}