package br.com.apssystem.bookstore.domain.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "locacao_item")
@SequenceGenerator(name = "LOCACAO_ITEM_ID", sequenceName = "LOCACAO_ITEM_ID_SEQ")
@EqualsAndHashCode(onlyExplicitlyIncluded = false)
@Getter
@Setter
public class LocacaoItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LOCACAO_ITEM_SEQ_ID")
	@EqualsAndHashCode.Include
	private Long id;

	@ManyToOne
	@JoinColumn(nullable = false)
	private LocacaoCab locacaoCab;

	@ManyToOne
	@JoinColumn(name = "livro_id", nullable = false)
	private Livro livro;

	@Column(name = "data_devolucao")
	private LocalDate dataDevolucao;
}
