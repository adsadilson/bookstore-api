package br.com.apssystem.bookstore.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "funcionario")
@SequenceGenerator(name = "FUNCIONARIO_ID", sequenceName = "FUNCIONARIO_ID_SEQ")
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Getter
@Setter
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FUNCIONARIO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 50)
	@Size(min = 4, max = 50)
	private String nome;
	
	@Column(length = 50)
	@Size(min = 4, max = 50)
	private String login;
	
	@Column(length = 100)
	@Size(min = 6, max = 10)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "perfil_id")
	private Perfil perfil;
}
