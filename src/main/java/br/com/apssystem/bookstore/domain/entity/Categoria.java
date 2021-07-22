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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotBlank(message = "Campo nome é obrigátorio!")
	@Size(min = 3, max = 100, message = "O campo Nome deve ter 3 entre 100 caracters!")
	@Column(length = 100, nullable = false, unique = true)
	private String nome;

	@Column(length = 150)
	private String descricao;
	
	@NotNull
	@OneToMany(mappedBy = "categoria")
	private List<Livro> livros = new ArrayList<>();
}
