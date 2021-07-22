package br.com.apssystem.bookstore.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.entity.Editora;
import br.com.apssystem.bookstore.domain.entity.Endereco;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.repository.CategoriaRepository;
import br.com.apssystem.bookstore.domain.repository.EditoraRepository;
import br.com.apssystem.bookstore.domain.repository.LivroRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private EditoraRepository editoraRepository;

	public void instanciaBaseDado() {
	
		Endereco end1 = new Endereco("Rua 2 de Junho", "713", "Centro","Salvador", "BS");
		Editora edit1 = new Editora(null, "Rocco", "91856371000125", "71 99855-5001", end1);

		Endereco end2 = new Endereco("Av 7 de Setembro", "013", "Centro","São Paulo", "SP");
		Editora edit2 = new Editora(null, "Arqueiro", "07997042000198", "11 98850-5500", end2);
		
		editoraRepository.saveAll(Arrays.asList(edit1,edit2));
		
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
		l1.setNome("Robert Martin");
		l1.setTexto("Lorem ipsum");
		l1.setCategoria(cat1);
		l1.setEditora(edit1);

		Livro l2 = new Livro();
		l2.setTitulo("Engenharia de Software");
		l2.setNome("Louis V. Gertner");
		l2.setTexto("Lorem ipsum");
		l2.setCategoria(cat1);
		l2.setEditora(edit2);

		Livro l3 = new Livro();
		l3.setTitulo("The Time Machine");
		l3.setNome("H.G. Wells");
		l3.setTexto("Lorem ipsum");
		l3.setCategoria(cat2);
		l3.setEditora(edit2);

		Livro l4 = new Livro();
		l4.setTitulo("I, Robot");
		l4.setNome("Isaac Asimov");
		l4.setTexto("Lorem ipsum");
		l4.setCategoria(cat2);
		l4.setEditora(edit1);

		Livro l5 = new Livro();
		l5.setTitulo("The War Of the Worlds");
		l5.setNome("H.G. Wells");
		l5.setTexto("Lorem ipsum");
		l5.setCategoria(cat2);
		l5.setEditora(edit2);

		cat1.getLivros().addAll(Arrays.asList(l1, l2));
		cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
}
