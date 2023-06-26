package com.tjw.controllers.rest.Students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tjw.dtos.response.EstudanteDto;
import com.tjw.entities.Estudante;
import com.tjw.servicesImpl.EstudanteServiceImpl;

@RestController
@RequestMapping(value = "/students")
public class EstudanteController {
	@Autowired
	private EstudanteServiceImpl service;

	@GetMapping
	public List<Estudante> index() {
		List<Estudante> response = service.findAll();
		return response;
	}

	@GetMapping("/search")
	public List<Estudante> searchByEmail(@RequestParam("email") String email) {
		return service.searchByEmail(email);
	}

	@GetMapping("/first")
	public Estudante findOneByEmail(@RequestParam("email") String email) {
		return service.findOneByEmail(email);
	}

	@PostMapping
	public ModelAndView store(Estudante student) {
		service.save(student);
		return new ModelAndView("redirect:/alunos");
	}

	@GetMapping(value = "/{id}")
	public EstudanteDto show(@PathVariable Long id) {
		EstudanteDto response = service.findByIdAndClasses(id);
		return response;
	}

	@PutMapping(value = "/{id}")
	public ModelAndView update(@PathVariable Long id, Estudante student) {
		service.update(id, student);
		return new ModelAndView("redirect:/alunos");
	}

	@DeleteMapping(value = "/{id}")
	public void destroy(@PathVariable Long id) {
		service.deleteById(id);
	}
}
