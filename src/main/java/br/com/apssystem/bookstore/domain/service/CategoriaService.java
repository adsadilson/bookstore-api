package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.ObjectEmUsoException;
import br.com.apssystem.bookstore.api.execption.ObjectNotFoundException;
import br.com.apssystem.bookstore.domain.dtos.CategoriaDTO;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.repository.CategoriaRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoriaService {

	private CategoriaRepository repository;

	public Categoria findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada para esse [ID: " + id + "]"));
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	@Transactional
	public Categoria update(CategoriaDTO objDTO, Long id) {
		Categoria obj = findById(id);
		obj.setNome(objDTO.getNome());
		obj.setDescricao(objDTO.getDescricao());
		return repository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ObjectEmUsoException("Categoria não pode ser deletada! possui livros associados");
		}
	}

}
