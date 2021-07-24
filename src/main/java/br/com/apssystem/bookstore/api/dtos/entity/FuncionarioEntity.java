package br.com.apssystem.bookstore.api.dtos.entity;


import lombok.Data;

@Data
public class FuncionarioEntity {

	private Long id;
	private String nome;
	private String email;
	private String login;

}
