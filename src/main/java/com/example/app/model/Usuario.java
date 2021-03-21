package com.example.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Name não pode ser vazio")
	@NotNull(message = "Name não pode ser null")
	private String login;
	
	
	private String password;
	
	@NotEmpty(message = "Name não pode ser vazio")
	private String name;
	
	
	private String cpf;
	
	
	private String email;
	
	public Usuario() {
		this(null, null, null, null, null, null);
	}
	
	// Constructor
	public Usuario(Integer id, String login, String password, String name, String cpf, String email) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
	}
	
	// Getters and Setters
	public Integer getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
