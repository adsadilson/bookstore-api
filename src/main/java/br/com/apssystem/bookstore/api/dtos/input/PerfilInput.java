package br.com.apssystem.bookstore.api.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.apssystem.bookstore.domain.enums.Status;
import lombok.Data;

@Data
public class PerfilInput {

	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	private String descricao;
	private Status status;

}
