package br.com.apssystem.bookstore.api.dtos.entity;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LocatarioEntity {

	private Long id;
	private String nome;
	private String cpf;
	private LocalDate nascimento;
	private LocalDate cadastro;
	private String foto;
	private String fone;
	private String rg;
	private String serie;
	private String profissao;
	private String escola;

}
