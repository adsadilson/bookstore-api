package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.api.execption.NegocioException;
import br.com.apssystem.bookstore.domain.entity.Perfil;
import br.com.apssystem.bookstore.domain.repository.PerfilRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PerfilService {

	private PerfilRepository perfilRespository;

	public Perfil buscarPorId(Long id) {
		return perfilRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Perfil não encontrada para esse [ID: " + id + "]"));
	}

	public List<Perfil> listarTodos() {
		return perfilRespository.findAll();
	}

	@Transactional
	public Perfil adicionar(Perfil obj) {
		PerfilExistente(obj);
		return perfilRespository.save(obj);
	}

	@Transactional
	public Perfil atualizar(Perfil obj) {
		return adicionar(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			perfilRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Perfil não pode ser deletada! possui funcionários associados");
		}
	}

	public void PerfilExistente(Perfil obj) {
		boolean result = perfilRespository.findByNome(obj.getNome()).stream().anyMatch(cat -> !cat.equals(obj));
		if (result) {
			throw new NegocioException("Perfil já cadastrado para esse [NOME: " + obj.getNome() + "]");
		}
	}

	public Perfil buscarTodosFuncPorPerfil(Long obj) {
		buscarPorId(obj);
		return perfilRespository.buscarTodosOsFuncPorPerfil(obj)
				.orElseThrow(() -> new NegocioException("Não encontrando nenhum funcionário associado a esse perfil"));
	}

}
