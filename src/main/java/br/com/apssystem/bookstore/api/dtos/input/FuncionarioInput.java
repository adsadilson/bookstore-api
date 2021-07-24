package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.apssystem.bookstore.api.dtos.input.ids.PerfilIdInput;
import lombok.Data;

@Data
public class FuncionarioInput {

	@NotBlank
	@Size(min = 4, max = 50)
	private String nome;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(min = 4, max = 50)
	private String login;

	@Size(min = 6, max = 10)
	private String senha;

	@NotNull
	@Valid
	private PerfilIdInput perfil;
}
