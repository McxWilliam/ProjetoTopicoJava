package com.tjw.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tab_professores")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@OneToMany(mappedBy = "professor")
	@JsonIgnore
	private List<Turma> classes;
	@Deprecated
	public Professor() {
	}
}
