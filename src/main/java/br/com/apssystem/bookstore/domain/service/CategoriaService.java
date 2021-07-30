package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.api.execption.NegocioException;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {

	private CategoriaRepository categoriaRespository;

	public Categoria buscarPorId(Long id) {
		return categoriaRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Categoria não encontrada para esse [ID: " + id + "]"));
	}

	public List<Categoria> listarTodos() {
		return categoriaRespository.findAll();
	}

	@Transactional
	public Categoria adicionar(Categoria obj) {
		categoriaExistente(obj);
		return categoriaRespository.save(obj);
	}

	@Transactional
	public Categoria atualizar(Categoria obj) {
		return categoriaRespository.save(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			categoriaRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Categoria não pode ser deletada! possui livros associados");
		}
	}

	public void categoriaExistente(Categoria obj) {
		boolean result = categoriaRespository.findByNome(obj.getNome()).stream()
				.anyMatch(cat -> !cat.equals(obj));
		if (result) {
			throw new NegocioException("Categoria já cadastrada para esse [ID: " + obj.getNome() + "]");
		}
	}

}
