package com.example.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	public List<Usuario> findAll();
	public Optional <Usuario> findByLogin(String login);
	boolean existsByLogin(String login);
	public Optional <Usuario> findByEmail(String email);
	
}
