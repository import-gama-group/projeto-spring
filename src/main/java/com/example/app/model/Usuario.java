package com.example.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Name não pode ser vazio")
	@NotNull(message = "Name não pode ser null")
	private String login;
	
	@NotEmpty(message = "Name não pode ser vazio")
	private String senha;
	
	@NotEmpty(message = "Name não pode ser vazio")
	private String nome;
	
	@NotEmpty(message = "CPF não pode ser vazio")
	private String cpf;
	
	@NotEmpty(message = "Name não pode ser vazio")
	private String email;
	
	public Usuario() {
		this(null, null, null, null, null, null);
	}
	
	// Constructor
	public Usuario(Integer id, String login, String senha, String nome, String cpf, String email) {
		super();
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getName() {
		return nome;
	}

	public void setName(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
