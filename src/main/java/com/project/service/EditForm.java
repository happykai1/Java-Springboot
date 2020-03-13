package com.project.service;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class EditForm {
	@NotNull
	private Integer userId;
	@NotEmpty
	private String name;
	private String email;
	@NotEmpty
	private String password;
}
