package br.com.apssystem.bookstore.api.dtos.input;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.apssystem.bookstore.domain.entity.Endereco;
import lombok.Data;

@Data
public class EditoraInput {
	
	@NotNull
	private Long id;

	@CNPJ
	@NotNull
	private String cnpj;

	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;

	private String fone;

	@Embedded
	private Endereco endereco;

}
