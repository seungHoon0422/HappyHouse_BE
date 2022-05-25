package com.house.interceptor;

import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("@ Interceptor 진입");
        HttpSession session = request.getSession();
        if(session  != null) {
        	Object token = session.getAttribute("vuex");
        	System.out.println(token);
        	if(token != null) {
        		System.out.println("access token이 존재합니다.");
        		return true;
        	}
        }
        System.out.println("세션에 토큰이 존재하지 않습니다.");
        return false;
    }
}
