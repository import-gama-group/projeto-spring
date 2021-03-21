package com.example.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Conta;
import com.example.app.model.Lancamento;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Integer> {
	
}
