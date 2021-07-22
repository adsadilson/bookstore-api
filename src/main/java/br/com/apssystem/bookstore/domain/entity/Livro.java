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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "livro")
@SequenceGenerator(name = "LIVRO_ID", sequenceName = "LIVRO_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LIVRO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(length = 100, nullable = false)
	private String titulo;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "texto")
	private String texto;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "editora_id", nullable = false)
	private Editora editora;
}
