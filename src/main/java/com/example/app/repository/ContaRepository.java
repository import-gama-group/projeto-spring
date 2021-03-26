package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{
	List<Conta> findByUsuarioId(Integer id);
	boolean existsByNumero(String numero);
}
