package br.com.apssystem.bookstore.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.repository.CategoriaRepository;
import br.com.apssystem.bookstore.domain.repository.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void instanciaBaseDado() {
		Categoria cat1 = new Categoria();
		cat1.setNome("Informática");
		cat1.setDescricao("Livros de Informática");

		Categoria cat2 = new Categoria();
		cat2.setNome("Ficção Cientifica");
		cat2.setDescricao("Ficção Cientifica");

		Categoria cat3 = new Categoria();
		cat3.setNome("Biografias");
		cat3.setDescricao("Livros de Biografias");

		Livro l1 = new Livro();
		l1.setTitulo("Clean Code");
		l1.setNomeAutor("Robert Martin");
		l1.setTexto("Lorem ipsum");
		l1.setCategoria(cat1);

		Livro l2 = new Livro();
		l2.setTitulo("Engenharia de Software");
		l2.setNomeAutor("Louis V. Gertner");
		l2.setTexto("Lorem ipsum");
		l2.setCategoria(cat1);

		Livro l3 = new Livro();
		l3.setTitulo("The Time Machine");
		l3.setNomeAutor("H.G. Wells");
		l3.setTexto("Lorem ipsum");
		l3.setCategoria(cat2);

		Livro l4 = new Livro();
		l4.setTitulo("I, Robot");
		l4.setNomeAutor("Isaac Asimov");
		l4.setTexto("Lorem ipsum");
		l4.setCategoria(cat2);

		Livro l5 = new Livro();
		l5.setTitulo("The War Of the Worlds");
		l5.setNomeAutor("H.G. Wells");
		l5.setTexto("Lorem ipsum");
		l5.setCategoria(cat2);

		cat1.getLivros().addAll(Arrays.asList(l1, l2));
		cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
}
