package com.mtm.bulletinboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.mtm.bulletinboard.bl.services.UserDetailsServiceImpl;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

//	private final UserDetailsServiceImpl userDetailsService;
//	
//	public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
//		this.userDetailsService = userDetailsService;
//	}

	@Bean
	public UserDetailsService userDetailsService(
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user")
				.password(bCryptPasswordEncoder.encode("userPass"))
				.roles("USER").build());
		manager.createUser(User.withUsername("admin")
				.password(bCryptPasswordEncoder.encode("adminPass"))
				.roles("USER", "ADMIN").build());
		return manager;
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain FilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/","/login").permitAll()
						.anyRequest().authenticated()
						)
//				.authorizeHttpRequests(
//						authorize -> authorize.requestMatchers("/posts/**")
//								.access(new WebExpressionAuthorizationManager(
//										"hasRole('USER')"))
//								.anyRequest().authenticated())
//				.authorizeHttpRequests(
//						authorize -> authorize.requestMatchers("/**")
//								.access(new WebExpressionAuthorizationManager(
//										"hasRole('ADMIN')"))
//								.anyRequest().authenticated())
				.formLogin(formLogin -> formLogin
						.loginPage("/login")
//						.loginProcessingUrl("/j_spring_security_check")
//						.defaultSuccessUrl("/posts")
//						.failureUrl("/login?error=true")
//						.usernameParameter("name")
//						.passwordParameter("password")
						);
//				.logout(logout -> logout.logoutUrl("/logout")
//						.logoutSuccessUrl("/posts"));

		return http.build();
	}

	@SuppressWarnings("removal")
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			UserDetailsService userDetailService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService(bCryptPasswordEncoder))
				.passwordEncoder(bCryptPasswordEncoder).and().build();
	}
	@Bean
	public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}
}
