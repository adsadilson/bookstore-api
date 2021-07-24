package br.com.apssystem.bookstore.api.dtos.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.apssystem.bookstore.domain.enums.Status;
import lombok.Data;

@Data
public class PerfilFuncEntity {

	private Long id;
	private String nome;
	private String descricao;
	private Status status;
	
	@JsonIgnoreProperties({"email","login"})
	private List<FuncionarioEntity> funcionarios;

}
