package com.example.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.PlanoConta;

@Repository
public interface PlanoContaRepository extends JpaRepository<PlanoConta, Integer> {
	public Optional<PlanoConta> findById(Integer id);
	List<PlanoConta> findByUsuarioId(Integer id);
	public List<PlanoConta> findAllById(Integer id);
	
}
