package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.api.execption.NegocioException;
import br.com.apssystem.bookstore.domain.entity.Editora;
import br.com.apssystem.bookstore.domain.repository.EditoraRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditoraService {

	private EditoraRepository editoraRespository;

	public Editora buscarPorId(Long id) {
		return editoraRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Editora não encontrada para esse [ID: " + id + "]"));
	}

	public List<Editora> listarTodos() {
		return editoraRespository.findAll();
	}

	@Transactional
	public Editora adicionar(Editora obj) {
		obj.setId(null);
		editoraExistente(obj);
		return editoraRespository.save(obj);
	}

	@Transactional
	public Editora atualizar(Editora obj) {
		return adicionar(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			editoraRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Editora não pode ser deletada! possui livros associados");
		}
	}

	public void editoraExistente(Editora obj) {
		boolean result = editoraRespository.findById(obj.getId()).stream().anyMatch(cat -> !cat.equals(obj));
		if (result) {
			throw new NegocioException("Editora já cadastrada para esse [NOME E CNPJ ]");
		}
	}

}
