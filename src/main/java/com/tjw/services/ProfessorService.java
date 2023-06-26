package com.tjw.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.ProfessorDto;
import com.tjw.entities.Professor;

public interface ProfessorService extends JpaRepository<Professor, Long> {
	public Professor findByIdVerify(Long id);

	public void update(Long id, Professor professor);

	public ProfessorDto findByIdAndClasses(Long id);

	List<Professor> searchByEmail(String email);
}
