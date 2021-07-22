package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoriaInput {

	private Long id;

	@NotBlank(message = "Campo nome é obrigátorio!")
	@Size(min = 3, max = 100, message = "O campo Nome deve ter 3 entre 100 caracters!")
	private String nome;

	private String descricao;
	
}
