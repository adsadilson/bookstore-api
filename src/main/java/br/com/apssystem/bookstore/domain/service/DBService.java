package br.com.apssystem.bookstore.domain.service;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.entity.Editora;
import br.com.apssystem.bookstore.domain.entity.Endereco;
import br.com.apssystem.bookstore.domain.entity.Funcionario;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.entity.Locatario;
import br.com.apssystem.bookstore.domain.entity.Perfil;
import br.com.apssystem.bookstore.domain.enums.Status;
import br.com.apssystem.bookstore.domain.repository.CategoriaRepository;
import br.com.apssystem.bookstore.domain.repository.EditoraRepository;
import br.com.apssystem.bookstore.domain.repository.FuncionarioRepository;
import br.com.apssystem.bookstore.domain.repository.LivroRepository;
import br.com.apssystem.bookstore.domain.repository.LocatarioRepository;
import br.com.apssystem.bookstore.domain.repository.PerfilRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private PerfilRepository perfilRepository;
	@Autowired
	private FuncionarioRepository funcRepository;
	@Autowired
	private EditoraRepository editoraRepository;
	@Autowired
	private LocatarioRepository locatarioRepository;

	// @formatter:off

	public void instanciaBaseDado() {

		populaTblPerfil();
		populaTblFuncionario();
		populaTblLocatario();
		populaEditoras();
	}
	
	private void populaEditoras() {
	
		Endereco end1 = new Endereco("Rua 2 de Junho", "713", "Centro", "Salvador", "BS");
		Editora edit1 = new Editora(null, "Rocco", "91856371000125", "71 99855-5001", end1, null);

		Endereco end2 = new Endereco("Av 7 de Setembro", "013", "Centro", "São Paulo", "SP");
		Editora edit2 = new Editora(null, "Arqueiro", "07997042000198", "11 98850-5500", end2, null);
		
		Endereco end3 = new Endereco("Rua Barrão do Rio Branco", "413", "Centro", "São Paulo", "SP");
		Editora edit3 = new Editora(null, "Editora Arqueiro", "83.336.724/0001-38", "11 90050-5599", end3, null);

		editoraRepository.saveAll(Arrays.asList(edit1, edit2, edit3));

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

	private void populaTblPerfil() {
		Perfil perfil1 = new Perfil();
		perfil1.setNome("ADIMINISTRADOR");
		perfil1.setDescricao("ACESSO SEM LIMITAÇÕES AO SISTEMA");
		perfil1.setStatus(Status.ATIVO);
		
		Perfil perfil2 = new Perfil();
		perfil2.setNome("OPERADOR DE CAIXA");
		perfil2.setDescricao("ACESSO A FRENTE DE LOJA [PDV]");
		perfil2.setStatus(Status.ATIVO);
		
		perfilRepository.saveAll(Arrays.asList(perfil1,perfil2));
	}
	
	private void populaTblFuncionario() {
		Perfil perfil1 = new Perfil();
		perfil1.setId(1L);
		
		Funcionario func = new Funcionario();
		func.setEmail("adilson@apss.com.br"); func.setNome("Adilson"); func.setLogin("ADMIN"); func.setPerfil(perfil1);
		funcRepository.save(func);
		
		Perfil perfil2 = new Perfil();
		perfil2.setId(2L);
		
		Funcionario func2 = new Funcionario();
		func2.setEmail("cleick@apss.com.br"); func2.setNome("CLEICK"); func2.setLogin("SUPORTE-1"); func2.setPerfil(perfil2);
		funcRepository.save(func2);
		
		Perfil perfil3 = new Perfil();
		perfil3.setId(1L);
		
		Funcionario func3 = new Funcionario();
		func3.setEmail("mr@apss.com.br"); func3.setNome("MARCELO"); func3.setLogin("MAR-N1"); func3.setPerfil(perfil3);
		funcRepository.save(func3);
	}
	
	
	private void populaTblLocatario() {
		Locatario loc1; Endereco end;
		
		end = new Endereco("RUA 7 DE SETEMBRO", "45", "CENTRO", "SALVADOR", "BA");
		loc1 = new Locatario();
		loc1.setNome("RONALDO DE OLIVEIRA"); loc1.setCpf("08823093074"); loc1.setFone("77-98859-0001"); loc1.setEndereco(end); 
		loc1.setNascimento(LocalDate.of(1982, 9, 11)); loc1.setRg("884569-11"); loc1.setProfissao("Motorista");
		locatarioRepository.save(loc1);

		end = new Endereco("AV SÃO PEDRO", "145", "PRIMAVERA", "SALVADOR", "BA");
		loc1 = new Locatario();
		loc1.setNome("CAMILA DA SILVA"); loc1.setCpf("91178976025"); loc1.setFone("71-98800-0051"); loc1.setEndereco(end); 
		loc1.setNascimento(LocalDate.of(1975, 10, 30)); loc1.setRg("77548054-11"); loc1.setProfissao("ADMINISTRADORA");
		locatarioRepository.save(loc1);

		end = new Endereco("RUA ELIEZER ANDRADE", "715", "CENTRO", "VITORIA DA CONQUISTA", "BA");
		loc1 = new Locatario();
		loc1.setNome("FERNANDA"); loc1.setCpf("97329757098"); loc1.setFone("77-343-2200"); loc1.setEndereco(end); 
		loc1.setNascimento(LocalDate.of(1989, 7, 23)); loc1.setRg("68688115-99"); loc1.setProfissao("CABELEREIRA");
		locatarioRepository.save(loc1);
		
	}
	
	// @formatter:on
}
