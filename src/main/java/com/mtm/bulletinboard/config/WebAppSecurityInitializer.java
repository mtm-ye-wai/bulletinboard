package com.mtm.bulletinboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class WebAppSecurityInitializer
		extends AbstractSecurityWebApplicationInitializer 
		{

	public WebAppSecurityInitializer() {
		super(SecurityConfig.class);
	}
}
