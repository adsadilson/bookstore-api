package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.apssystem.bookstore.api.dtos.input.ids.PerfilIdInput;
import lombok.Data;

@Data
public class FuncionarioInput {

	@Size(min = 4, max = 50)
	private String nome;

	@Email
	private String email;

	@Size(min = 4, max = 50)
	private String login;

	@Size(min = 6, max = 10)
	private String senha;

	@NotNull
	private PerfilIdInput perfil;
}
