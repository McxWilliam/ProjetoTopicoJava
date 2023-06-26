package com.tjw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tjw.entities.Estudante;
import java.util.List;
import java.util.Optional;

public interface EstudanteRepository extends JpaRepository<Estudante, Long> {
	List<Estudante> findByEmailContaining(String email);

	Optional<Estudante> findByEmail(String email);
}
