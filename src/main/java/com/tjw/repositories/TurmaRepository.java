package com.tjw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
	List<Turma> findByNameContaining(String email);
}
