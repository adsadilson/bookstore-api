package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.EntidadeEmUsoException;
import br.com.apssystem.bookstore.api.execption.EntidadeNaoEncontradaException;
import br.com.apssystem.bookstore.api.execption.NegocioException;
import br.com.apssystem.bookstore.domain.entity.Funcionario;
import br.com.apssystem.bookstore.domain.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FuncionarioService {

	private FuncionarioRepository funcionarioRepository;

	public Funcionario buscarPorId(Long id) {
		Funcionario obj = funcionarioRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException("Funcionário não encontrada para esse [ID: " + id + "]"));
		return obj;
	}

	public List<Funcionario> listarTodos() {
		return funcionarioRepository.findAll();
	}

	public void funcionarioExistente(Funcionario obj) {
		boolean result = funcionarioRepository.findByEmail(obj.getEmail()).stream().anyMatch(func -> !func.equals(obj));
		if (result)
			throw new NegocioException("Funcionário já cadastrado para esse EMAIL: " + obj.getEmail());
	}

	@Transactional
	public Funcionario adicionar(Funcionario obj) {
		funcionarioExistente(obj);
		return funcionarioRepository.save(obj);
	}

	public Funcionario atualizar(Funcionario obj) {
		return adicionar(obj);
	}

	public void excluir(Long id) {
		buscarPorId(id);
		try {
			funcionarioRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException("Funcionário não pode ser deletada! possui locações associados");
		}
	}
}
