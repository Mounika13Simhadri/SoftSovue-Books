package com.application.publishers.model;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="user_details")
@ToString
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	@Pattern(regexp="[a-z0-9_.-]+@[a-z]+\\.[a-z]{2,}",message="Enter a valid Email")
	@Column(unique=true)
	private String email;
	@Pattern(regexp="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$",message="Password must be min 8 characters") 
	private String password=createPassword();
	private String role;
	private Long publisher_id;

	public Long getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}

	public User(String name, String email, String password, String role, Long publisher_id) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.publisher_id = publisher_id;
	}

	public User() {
		
	}
	
	public User(@NotNull String name, @Email @NotNull String email, String password,String role) {
			this.name = name;
			this.email = email;
			this.password = password;
			this.role = role;
	}
	public User(@NotNull String name,@Email @NotNull String email, String role) {
		this.name = name;
			this.email = email;
			this.role = role;
			this.password =password;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getRole() {
	     return role;
	}
	public void setRole(String role) {
	     this.role = role;
	}
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	
	public static String createPassword() {
		
		  CharacterRule LCR = new CharacterRule(EnglishCharacterData.LowerCase);  
		  LCR.setNumberOfCharacters(2);  
		  
		  CharacterRule UCR = new CharacterRule(EnglishCharacterData.UpperCase);   
		  UCR.setNumberOfCharacters(2);  
		  
		  CharacterRule DR = new CharacterRule(EnglishCharacterData.Digit);     
		  DR.setNumberOfCharacters(2);  
	
		  CharacterRule SR = new CharacterRule(EnglishCharacterData.Special);      
		  SR.setNumberOfCharacters(2);  
		            
		  PasswordGenerator passGen = new PasswordGenerator();  
		 // String password =passwordEncoder.encode(passGen.generatePassword(8, SR, LCR, UCR, DR));    
		  String password =passGen.generatePassword(8, SR, LCR, UCR, DR);
		  return password;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", publisher_id=" + publisher_id + "]";
	}
	

}
