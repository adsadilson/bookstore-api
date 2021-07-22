package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.apssystem.bookstore.api.dtos.input.ids.CategoriaIdInput;
import br.com.apssystem.bookstore.api.dtos.input.ids.EditoraIdInput;
import lombok.Data;

@Data
public class LivroInput {


	@NotBlank
	@Size(min = 4)
	private String titulo;
	
	@NotBlank
	@Size(min = 4)
	private String nome;
	
	private String texto;
		
	@NotNull
	@Valid
	private CategoriaIdInput categoria;
	
	@NotNull
	@Valid
	private EditoraIdInput editora;
}
