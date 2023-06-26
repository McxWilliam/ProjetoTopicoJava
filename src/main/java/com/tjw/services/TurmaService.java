package com.tjw.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.TurmaDto;
import com.tjw.entities.Turma;
import com.tjw.entities.Estudante;

public interface TurmaService extends JpaRepository<Turma, Long> {
	public Turma findByIdVerify(Long id);

	public void update(Long id, Turma classe);

	public TurmaDto findByIdAndStudents(Long id);

	public void enroll(Long id, Set<Estudante> students);

	List<Turma> searchByName(String name);
}
