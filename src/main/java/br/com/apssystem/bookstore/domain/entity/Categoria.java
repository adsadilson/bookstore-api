package br.com.apssystem.bookstore.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "categoria")
@SequenceGenerator(name = "CATEGORIA_ID", sequenceName = "CATEGORIA_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "CATEGORIA_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 100, nullable = false, unique = true)
	private String nome;

	@Column(length = 255)
	private String descricao;
	
	@NotNull
	@OneToMany(mappedBy = "categoria")
	private List<Livro> livros = new ArrayList<>();
}
