package br.com.apssystem.bookstore.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "editora")
@SequenceGenerator(name = "EDITORA_ID", sequenceName = "EDITORA_ID_SEQ")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "EDITORA_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@EqualsAndHashCode.Include
	private String cnpj;

	@Column(nullable = false, length = 100)
	private String nome;

	private String fone;

	@Embedded
	private Endereco endereco;

	@OneToMany(mappedBy = "editora")
	private List<Livro> livros = new ArrayList<>();

	public Editora(Long id, String nome, String cnpj, String fone, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.fone = fone;
		this.endereco = endereco;
	}

	public Editora() {
		super();
	}

}
