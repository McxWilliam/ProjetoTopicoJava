package com.tjw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
	List<Professor> findByEmailContaining(String email);
}
