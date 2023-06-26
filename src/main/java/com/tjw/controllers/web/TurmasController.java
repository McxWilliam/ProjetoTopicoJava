package com.tjw.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjw.dtos.response.TurmaDto;
import com.tjw.entities.Professor;
import com.tjw.servicesImpl.TurmaServiceImpl;
import com.tjw.servicesImpl.ProfessorServiceImpl;

@Controller
@RequestMapping("/turmas")
public class TurmasController {
	@Autowired
	private TurmaServiceImpl service;
	@Autowired
	private ProfessorServiceImpl professorService;

	@GetMapping("")
	public String turmas(Model model) {
		model.addAttribute("currentPage", "turmas");
		return "turmas";
	}

	@GetMapping("/cadastrar")
	public String cadastrarTurmas(Model model) {
		List<Professor> professors = professorService.findAll();
		model.addAttribute("currentPage", "turmas");
		model.addAttribute("professors", professors);
		return "cadastro-turma";
	}

	@GetMapping("/{id}")
	public String editarTurmas(@PathVariable Long id, Model model) {
		TurmaDto response = service.findByIdAndStudents(id);
		List<Professor> professors = professorService.findAll();
		model.addAttribute("classe", response);
		model.addAttribute("professors", professors);
		model.addAttribute("currentPage", "turmas");
		return "editar-turma";
	}

	@GetMapping("/matricula/{id}")
	public String matricularAlunos(@PathVariable Long id, Model model) {
		TurmaDto response = service.findByIdAndStudents(id);
		model.addAttribute("classe", response);
		model.addAttribute("currentPage", "turmas");
		return "enroll-students";
	}
}
