package br.com.apssystem.bookstore.api.dtos.input;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.apssystem.bookstore.domain.entity.Endereco;
import lombok.Data;

@Data
public class EditoraInput {

	@CNPJ
	private String cnpj;

	@NotBlank
	private String nome;

	private String fone;

	@Embedded
	private Endereco endereco;

}
