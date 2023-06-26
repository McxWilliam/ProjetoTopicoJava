package com.tjw.controllers.rest.Classes;

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

import com.tjw.dtos.response.TurmaDto;
import com.tjw.entities.Turma;
import com.tjw.servicesImpl.TurmaServiceImpl;

@RestController
@RequestMapping(value = "/classes")
public class ClasseController {
	@Autowired
	private TurmaServiceImpl service;


	@GetMapping("/search")
	public List<Turma> searchByName(@RequestParam("email") String name) {
		return service.searchByName(name);
	}

	
	@PostMapping
	public ModelAndView store(Turma classe) {
		service.save(classe);
		return new ModelAndView("redirect:/turmas");
	}


	@GetMapping
	public List<Turma> index() {
		List<Turma> response = service.findAll();
		return response;
	}

	@PutMapping(value = "/{id}")
	public ModelAndView update(@PathVariable Long id, Turma classe) {
		service.update(id, classe);
		return new ModelAndView("redirect:/turmas");
	}

	@GetMapping(value = "/{id}")
	public TurmaDto show(@PathVariable Long id) {
		TurmaDto response = service.findByIdAndStudents(id);
		return response;
	}

	@DeleteMapping(value = "/{id}")
	public void destroy(@PathVariable Long id) {
		service.deleteById(id);
	}
}
