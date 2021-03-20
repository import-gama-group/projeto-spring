package com.example.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	//Usuario findByEmail(String email);
	public Optional <Usuario> findByLogin(String login);
	public boolean existsByLogin(String login);
	
}
