package br.com.apssystem.bookstore.api.dtos.input.ids;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LivroIdInput {

	@NotNull
	private Long id;
}
