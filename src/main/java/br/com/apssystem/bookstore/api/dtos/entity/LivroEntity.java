package br.com.apssystem.bookstore.api.dtos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class LivroEntity {

	private Long id;
	private String titulo;
	private String nome;
	private String texto;

	@JsonIgnoreProperties({ "descricao" })
	private CategoriaEntity categoria;

	@JsonIgnoreProperties({ "cnpj", "fone" })
	private EditoraEntity editora;
}
