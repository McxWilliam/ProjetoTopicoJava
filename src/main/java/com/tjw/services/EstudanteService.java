package com.tjw.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.dtos.response.EstudanteDto;
import com.tjw.entities.Estudante;

public interface EstudanteService extends JpaRepository<Estudante, Long> {
	public Estudante findByIdVerify(Long id);

	public void update(Long id, Estudante student);

	public EstudanteDto findByIdAndClasses(Long id);

	List<Estudante> searchByEmail(String email);

	Estudante findOneByEmail(String email);
}
