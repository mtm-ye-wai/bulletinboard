package com.mtm.bulletinboard.web.form;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class LoginForm {

	@Email(message = "Email format is invalid")
	@NotEmpty(message = "Email can not be blank")
	private String email;
	
	@NotEmpty(message = "Password can not be blank")
	private String password;
	
	private String rememberMe;
}
