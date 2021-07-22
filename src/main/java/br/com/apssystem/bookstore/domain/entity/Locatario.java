package br.com.apssystem.bookstore.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "locatario")
@SequenceGenerator(name = "LOCATARIO_ID", sequenceName = "LOCATARIO_ID_SEQ")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
public class Locatario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LOCATARIO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 50)
	private String cpf;

	private LocalDate nascimento;

	private LocalDate cadastro;

	@Column(length = 100)
	private String foto;

	@Column(length = 20)
	private String fone;

	@Column(length = 50)
	private String rg;

	@Column(length = 100)
	private String serie;

	@Column(length = 100)
	private String profissao;

	@Column(length = 100)
	private String escola;
	
	@Embedded
	private Endereco endereco;

	public Locatario(Long id, String nome, String cpf, LocalDate nascimento, String foto, String fone, String rg,
			String serie, String profissao, String escola) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cadastro = LocalDate.now();
		this.foto = foto;
		this.fone = fone;
		this.rg = rg;
		this.serie = serie;
		this.profissao = profissao;
		this.escola = escola;
	}

	public Locatario() {
		super();
		this.cadastro = LocalDate.now();
	}

}
