package com.fxzhj;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fxzhj.common.properties.DruidDataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by wan on 2017/1/17.
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableScheduling
@ComponentScan(basePackages = {"com.fxzhj.*"})
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("--- 启动服务成功！ ---");
	}
	
	@Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		System.out.println("--- 正在启动tomcat应用服务器！ ---");
        return application.sources(Application.class);  
    }

	private int count2 = 0;
	@Scheduled(cron = "0 0/1 * * * ?")
    public void reportCurrentTimeCron() throws InterruptedException {
        System.out.println(String.format("+++第%s次执行，当前时间为：%s", count2++, new SimpleDateFormat().format(new Date())));
    }
	
	@Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
	
}
