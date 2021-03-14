package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
