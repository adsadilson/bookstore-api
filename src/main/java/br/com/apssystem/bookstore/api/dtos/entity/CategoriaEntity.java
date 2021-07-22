package br.com.apssystem.bookstore.api.dtos.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoriaEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;

}
