package com.appdeveloperpractice.app.ws.ui.model.request;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



public class UserDetailsRequestModel {
	@NotNull(message="First name can't be null")
	@Size(min=2, message="First name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last name can't be null")
	@Size(min=2, message="Last name must not be less than 2 characters")
	private String lastName;
	
	@NotNull(message="Email can't be null")
	@Email
	private String email;
	
	@NotNull(message="Password can't be null")
	@Size(min=6, max=15, message="password length must be 6 to 15 characters")
	private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
