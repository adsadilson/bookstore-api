package br.com.apssystem.bookstore.api.dtos.input;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import br.com.apssystem.bookstore.domain.entity.Endereco;
import lombok.Data;

@Data
public class LocatarioInput {
	
	private Long id;

	@NotBlank
	@Size(min = 4, max = 100)
	private String nome;

	@NotBlank
	@CPF
	private String cpf;

	private LocalDate nascimento;
	private String foto;
	private String fone;
	private String rg;
	private String serie;
	private String profissao;
	private String escola;
	private Endereco endereco;

}
