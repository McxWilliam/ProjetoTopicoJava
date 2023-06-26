package com.tjw.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.TurmaDto;
import com.tjw.dtos.response.ProfessorDto;
import com.tjw.entities.Turma;
import com.tjw.entities.Professor;
import com.tjw.repositories.ProfessorRepository;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.ProfessorService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ProfessorServiceImpl extends SimpleJpaRepository<Professor, Long> implements ProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;

	public ProfessorServiceImpl(EntityManager entityManager) {
		super(Professor.class, entityManager);
	}

	@Override
	public Professor findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Professor professor) {
		Professor professorFind = this.findByIdVerify(id);

		professorFind.setName(professor.getName());
		professorFind.setEmail(professor.getEmail());

		this.save(professorFind);
	}

	@Override
	public ProfessorDto findByIdAndClasses(Long id) {
		Professor professor = this.findByIdVerify(id);

		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setId(professor.getId());
		professorDto.setName(professor.getName());
		professorDto.setEmail(professor.getEmail());

		List<TurmaDto> classesDto = new ArrayList<>();
		for (Turma classe : professor.getClasses()) {
			TurmaDto classeDto = new TurmaDto();
			classeDto.setId(classe.getId());
			classeDto.setName(classe.getName());
			classesDto.add(classeDto);
		}

		professorDto.setClasses(classesDto);

		return professorDto;
	}

	@Override
	public List<Professor> searchByEmail(String email) {
		return this.professorRepository.findByEmailContaining(email);
	}
}
