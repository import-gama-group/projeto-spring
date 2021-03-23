package com.example.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 3, max = 20, message = "O Login deve ter entre 3 e 20 caracteres")
	@NotEmpty(message = "Login não pode ser vazio/null")
	private String login;

	@NotEmpty(message = "Senha não pode ser vazio/null")
	private String senha;

	@NotEmpty(message = "Nome não pode ser vazio/null")
	private String nome;
	
	@NotEmpty(message = "CPF não pode ser vazio/null")
	private String cpf;
	
	@NotEmpty(message = "Email não pode ser vazio/null")
	private String email;
	
	public Usuario() {
		
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

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
}
