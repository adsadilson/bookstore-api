package br.com.apssystem.bookstore.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "locacao_cab")
@SequenceGenerator(name = "LOCACAO_CAB_ID", sequenceName = "LOCACAO_CAB_ID_SEQ")
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Getter
@Setter
public class LocacaoCab {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LOCACAO_CAB_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@OneToOne
	@JoinColumn(nullable = false)
	private Locatario locatario;

	@OneToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;

	private LocalDateTime hora;

	@Column(name = "data_saida")
	private LocalDate dataSaida;

	@Column(name = "data_entrega")
	private LocalDate dataEntrega;

	@OneToMany(mappedBy = "locacaoCab", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LocacaoItem> itens = new ArrayList<>();

}
