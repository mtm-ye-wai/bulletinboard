package com.mtm.bulletinboard.web.form;

import org.springframework.stereotype.Component;

import com.mtm.bulletinboard.common.enums.Status;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class PostForm {

	@NotEmpty(message = "Title can not be blank")
	@Size(max = 255, message = "255 characters is the maximum allowed")
	private String title;

	@NotEmpty(message = "Description can not be blank")
	private String description;

	private Status status;

}
