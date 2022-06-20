package br.com.ebuybooks.dto;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.ebuybooks.model.User;

public class RequisicaoCadastrarUser {
	
	private String username;
	private String password;
	private String repeatPassword;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public User toUser() {
		
		User user = new User();
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		
		return user;
	}
}
