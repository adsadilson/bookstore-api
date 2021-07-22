package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.repository.LivroRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroService {

	private LivroRepository repositoryLivro;
	private CategoriaService categoriaService;

	public Livro buscarPorId(Long id) {
		return repositoryLivro.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Livro não encontrada para esse [ID: " + id + "]"));
	}

	public List<Livro> findAll() {
		return repositoryLivro.findAll();
	}

	@Transactional
	public Livro adicionar(Livro obj) {
		Categoria cat = categoriaService.buscarPorId(obj.getCategoria().getId());
		obj.setId(null);
		obj.setCategoria(cat);
		return repositoryLivro.save(obj);
	}

	@Transactional
	public Livro autalizar(Livro obj) {
		return repositoryLivro.save(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			repositoryLivro.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Livro não pode ser deletada! possui livros associados");
		}
	}

	public List<Livro> listarTodosLivroPorCategoria(Long id) {
		categoriaService.buscarPorId(id);
		return repositoryLivro.findAllByCategorai(id);
	}

}
