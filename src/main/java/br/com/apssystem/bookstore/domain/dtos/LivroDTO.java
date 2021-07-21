package br.com.apssystem.bookstore.domain.dtos;

import java.io.Serializable;

import br.com.apssystem.bookstore.domain.entity.Livro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String titulo;

	public LivroDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
	}

}
