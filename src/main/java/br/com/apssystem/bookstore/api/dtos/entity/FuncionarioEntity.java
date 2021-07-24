package br.com.apssystem.bookstore.api.dtos.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class FuncionarioEntity {

	private Long id;
	private String nome;
	private String email;
	private String login;

	@JsonIgnoreProperties({ "descricao", "status" })
	private PerfilEntity perfil;
}
