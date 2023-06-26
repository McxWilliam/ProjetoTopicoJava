package com.tjw.dtos.response;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudanteDto {
	private Long id;

	private String name;

	private String email;

	private Set<TurmaDto> classes;
}
