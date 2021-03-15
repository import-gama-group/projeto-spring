package com.example.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Conta;
import com.example.app.model.Usuario;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	List<Conta> findByUsuarioId(Integer id);
}
