<<<<<<< HEAD
package com.house.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // 일반 자바가 아니라 환경설정 파일임을 알려주기  
@EnableAspectJAutoProxy // autoproxy 기능 
@MapperScan(basePackages= {"com.house.**.mapper"} )
public class WebMvcConfiguration {

}
=======
package com.house.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // 일반 자바가 아니라 환경설정 파일임을 알려주기  
@EnableAspectJAutoProxy // autoproxy 기능 
@MapperScan(basePackages= {"com.house.**.mapper"} )
public class WebMvcConfiguration {

}
>>>>>>> branch 'master' of https://github.com/seungHoon0422/house.git
