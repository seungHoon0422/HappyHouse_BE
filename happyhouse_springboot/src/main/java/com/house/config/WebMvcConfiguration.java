
package com.house.config;

import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.house.interceptor.AuthCheckInterceptor;

@Configuration // 일반 자바가 아니라 환경설정 파일임을 알려주기  
@EnableAspectJAutoProxy // autoproxy 기능 
@MapperScan(basePackages= {"com.house.**.mapper"} )
@EnableWebMvc // interceptor 설정
public class WebMvcConfiguration implements WebMvcConfigurer{

	@Autowired
	AuthCheckInterceptor authCheckInterceptor;
	
	private final List<String> patterns = Arrays.asList("/interest/**");

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
//		registry.addInterceptor(authCheckInterceptor)
//		.addPathPatterns(patterns);
	}
}
