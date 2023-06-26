package com.tjw.servicesImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import com.tjw.dtos.response.TurmaDto;
import com.tjw.dtos.response.EstudanteDto;
import com.tjw.entities.Turma;
import com.tjw.entities.Estudante;
import com.tjw.repositories.EstudanteRepository;
import com.tjw.serviceExceptions.EmailNotFoundException;
import com.tjw.serviceExceptions.NotFoundException;
import com.tjw.services.EstudanteService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class EstudanteServiceImpl extends SimpleJpaRepository<Estudante, Long> implements EstudanteService {
	@Autowired
	private EstudanteRepository studentRepository;

	public EstudanteServiceImpl(EntityManager entityManager) {
		super(Estudante.class, entityManager);
	}

	@Override
	public Estudante findByIdVerify(Long id) {
		return this.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	@Override
	@Transactional
	public void update(Long id, Estudante student) {
		Estudante studentFind = this.findByIdVerify(id);

		studentFind.setName(student.getName());
		studentFind.setEmail(student.getEmail());

		this.save(studentFind);
	}

	@Override
	public EstudanteDto findByIdAndClasses(Long id) {
		Estudante student = this.findByIdVerify(id);

		EstudanteDto studentDto = new EstudanteDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setEmail(student.getEmail());

		Set<TurmaDto> classesDto = new HashSet<>();
		for (Turma classe : student.getClasses()) {
			TurmaDto classeDto = new TurmaDto();
			classeDto.setId(classe.getId());
			classeDto.setName(classe.getName());
			classesDto.add(classeDto);
		}

		studentDto.setClasses(classesDto);

		return studentDto;
	}

	@Override
	public List<Estudante> searchByEmail(String email) {
		return this.studentRepository.findByEmailContaining(email);
	}

	@Override
	public Estudante findOneByEmail(String email) {
		return this.studentRepository.findByEmail(email).orElseThrow(() -> new EmailNotFoundException(email));
	}
}
