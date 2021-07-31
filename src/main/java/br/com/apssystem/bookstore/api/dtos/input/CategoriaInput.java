package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoriaInput {

	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;

	private String descricao;
	
}
