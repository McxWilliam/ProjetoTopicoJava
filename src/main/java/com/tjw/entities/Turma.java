package com.tjw.entities;

import java.util.Set;



import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Getter
@Setter
@Table(name = "tab_turmas")
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tab_turma_estudantes", joinColumns = @JoinColumn(name = "classe_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnore
	private Set<Estudante> students;

	@Deprecated
	public Turma() {
	}
}
