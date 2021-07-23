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

	private PerfilRepository PerfilRespository;

	public Perfil buscarPorId(Long id) {
		return PerfilRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Perfil não encontrada para esse [ID: " + id + "]"));
	}

	public List<Perfil> listarTodos() {
		return PerfilRespository.findAll();
	}

	@Transactional
	public Perfil adicionar(Perfil obj) {
		obj.setId(null);
		PerfilExistente(obj);
		return PerfilRespository.save(obj);
	}

	@Transactional
	public Perfil atualizar(Perfil obj) {
		return adicionar(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			PerfilRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Perfil não pode ser deletada! possui funcionários associados");
		}
	}

	public void PerfilExistente(Perfil obj) {
		boolean result = PerfilRespository.findById(obj.getId()).stream().anyMatch(cat -> !cat.equals(obj));
		if (result) {
			throw new NegocioException("Perfil já cadastrada para esse [NOME]: "+obj.getNome());
		}
	}

}
