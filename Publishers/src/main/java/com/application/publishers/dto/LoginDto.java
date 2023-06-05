package com.application.publishers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public class LoginDto {

	//@Email
	//@Pattern(regexp="[a-z0-9_.-]+@[a-z]+\\.[a-z]{2,}",message="Enter a valid Email")
	private String email;
	//@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$",message="Password must be min 8 characters")  
	private String password;
	
	public LoginDto() {
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
