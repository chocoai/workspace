/**
 * 启动类
 */
package com.yhcrt.tcpip;


import java.io.IOException;







import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

import com.yhcrt.tcpip.socket.WatchServer;


/**
 * @author 
 *
 */

@SpringBootApplication
public class ApplicationBoot extends SpringBootServletInitializer{

	 private static final Logger logger =Logger.getLogger(ApplicationBoot.class);  

	/**  
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		SpringApplication.run(ApplicationBoot.class, args);
		
	}
	
	
	@Override
	protected WebApplicationContext run(SpringApplication application) {
		// TODO Auto-generated method stub
//		application.addListeners(new
//				ApplicationStartup());
		return super.run(application);
	}
	
}
