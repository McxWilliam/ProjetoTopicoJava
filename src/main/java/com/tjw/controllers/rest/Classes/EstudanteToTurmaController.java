package com.tjw.controllers.rest.Classes;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjw.entities.Estudante;
import com.tjw.servicesImpl.TurmaServiceImpl;

@RestController
@RequestMapping(value = "/classes")
public class EstudanteToTurmaController {
	@Autowired
	private TurmaServiceImpl service;

	@PutMapping(value = "/students/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Set<Estudante> students) {
		service.enroll(id, students);
		String redirectUrl = "/turmas";
		return ResponseEntity.status(HttpStatus.OK).body("{\"redirect\": \"" + redirectUrl + "\"}");
	}
}
