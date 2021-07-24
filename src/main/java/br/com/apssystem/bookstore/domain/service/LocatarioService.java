package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.api.execption.NegocioException;
import br.com.apssystem.bookstore.domain.entity.Locatario;
import br.com.apssystem.bookstore.domain.repository.LocatarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocatarioService {

	private LocatarioRepository locatarioRespository;

	public Locatario buscarPorId(Long id) {
		return locatarioRespository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Locatario não encontrada para esse [ID: " + id + "]"));
	}

	public List<Locatario> listarTodos() {
		return locatarioRespository.findAll();
	}

	@Transactional
	public Locatario adicionar(Locatario obj) {
		locatarioExistente(obj);
		return locatarioRespository.save(obj);
	}

	@Transactional
	public Locatario atualizar(Locatario obj) {
		return adicionar(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			locatarioRespository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Locatario não pode ser deletada! possui locações associados");
		}
	}

	public void locatarioExistente(Locatario obj) {
		boolean result = locatarioRespository.findByCpf(obj.getCpf()).stream().anyMatch(loc -> !loc.equals(obj));
		if (result) {
			throw new NegocioException("Locatario já cadastrada para esse [CPF: "+obj.getCpf()+"]");
		}
	}

}
