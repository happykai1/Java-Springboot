package com.project.service;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class AddForm {
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;
}