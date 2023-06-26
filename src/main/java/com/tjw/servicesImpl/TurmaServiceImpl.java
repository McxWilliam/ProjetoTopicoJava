package com.tjw.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.TurmaDto;
import com.tjw.dtos.response.ProfessorDto;
import com.tjw.dtos.response.EstudanteDto;
import com.tjw.entities.Turma;
import com.tjw.entities.Estudante;
import com.tjw.repositories.TurmaRepository;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.TurmaService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class TurmaServiceImpl extends SimpleJpaRepository<Turma, Long> implements TurmaService {
	@Autowired
	private TurmaRepository classeRepository;

	public TurmaServiceImpl(EntityManager entityManager) {
		super(Turma.class, entityManager);
	}

	@Override
	public Turma findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Turma classe) {
		Turma classeFind = this.findByIdVerify(id);

		classeFind.setName(classe.getName());
		if (classe.getProfessor() != null) {
			classeFind.setProfessor(classe.getProfessor());
		}

		this.save(classeFind);
	}

	@Override
	public TurmaDto findByIdAndStudents(Long id) {
		Turma classe = this.findByIdVerify(id);
		TurmaDto classeDto = new TurmaDto();
		classeDto.setId(classe.getId());
		classeDto.setName(classe.getName());

		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setId(classe.getProfessor().getId());
		professorDto.setName(classe.getProfessor().getName());
		professorDto.setEmail(classe.getProfessor().getEmail());

		classeDto.setProfessor(professorDto);

		Set<EstudanteDto> studentsDto = new HashSet<>();
		for (Estudante student : classe.getStudents()) {
			EstudanteDto studentDto = new EstudanteDto();
			studentDto.setId(student.getId());
			studentDto.setName(student.getName());
			studentDto.setEmail(student.getEmail());
			studentsDto.add(studentDto);
		}

		classeDto.setStudents(studentsDto);

		return classeDto;
	}

	@Override
	@Transactional
	public void enroll(Long id, Set<Estudante> students) {
		Turma classe = this.findByIdVerify(id);
		classe.getStudents().removeIf(student -> !students.contains(student));

		classe.getStudents().addAll(students);

		this.save(classe);
	}

	@Override
	public List<Turma> searchByName(String name) {
		return this.classeRepository.findByNameContaining(name);
	}
}
