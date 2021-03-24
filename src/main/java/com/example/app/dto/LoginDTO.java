package com.example.app.dto;

public class LoginDTO {
	
	private String login;
	private String senha;
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public String getLogin() {
		return login;
	}

}
