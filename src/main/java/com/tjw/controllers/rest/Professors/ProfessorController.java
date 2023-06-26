package com.tjw.controllers.rest.Professors;

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

import com.tjw.dtos.response.ProfessorDto;
import com.tjw.entities.Professor;
import com.tjw.servicesImpl.ProfessorServiceImpl;

@RestController
@RequestMapping(value = "/professors")
public class ProfessorController {
	@Autowired
	private ProfessorServiceImpl service;

	@GetMapping
	public List<Professor> index() {
		List<Professor> response = service.findAll();
		return response;
	}

	@GetMapping("/search")
	public List<Professor> searchByEmail(@RequestParam("email") String email) {
		return service.searchByEmail(email);
	}

	@PostMapping
	public ModelAndView store(Professor professor) {
		service.save(professor);
		return new ModelAndView("redirect:/professores");
	}

	@GetMapping(value = "/{id}")
	public ProfessorDto show(@PathVariable Long id) {
		ProfessorDto response = service.findByIdAndClasses(id);
		return response;
	}

	@PutMapping(value = "/{id}")
	public ModelAndView update(@PathVariable Long id, Professor professor) {
		service.update(id, professor);
		return new ModelAndView("redirect:/professores");
	}

	@DeleteMapping(value = "/{id}")
	public void destroy(@PathVariable Long id) {
		service.deleteById(id);
	}
}
