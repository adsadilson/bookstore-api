package br.com.apssystem.bookstore.api.dtos.input.ids;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditoraIdInput {

	@NotNull
	private Long id;
}
