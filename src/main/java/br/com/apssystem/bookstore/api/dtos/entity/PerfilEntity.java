package br.com.apssystem.bookstore.api.dtos.entity;

import br.com.apssystem.bookstore.domain.enums.Status;
import lombok.Data;

@Data
public class PerfilEntity {

	private Long id;
	private String nome;
	private String descricao;
	private Status status;

}
