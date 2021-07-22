package br.com.apssystem.bookstore.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

	@Column(length = 100)
	private String logradouro;
	@Column(length = 20)
	private String numero;
	@Column(length = 100)
	private String bairro;
	@Column(length = 100)
	private String cidade;
	@Column(length = 2)
	private String uf;
}
