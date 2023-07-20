package com.mtm.bulletinboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;

@Configuration
public class ServerConfig
		implements WebApplicationInitializer, WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		System.out.println("web init");
		var ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		Dynamic servlet = servletContext.addServlet("dispatcherServlet",
				new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}
