package br.com.apssystem.bookstore.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.apssystem.bookstore.domain.enums.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "perfil")
@SequenceGenerator(name = "PERFIL_ID", sequenceName = "PERFIL_ID_SEQ")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PERFIL_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, unique = true, length = 100)
	private String nome;

	@Column(length = 100)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany(mappedBy = "perfil")	
	private List<Funcionario> funcionarios = new ArrayList<>();

}
