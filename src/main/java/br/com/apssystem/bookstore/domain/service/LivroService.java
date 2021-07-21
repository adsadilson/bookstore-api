package br.com.apssystem.bookstore.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apssystem.bookstore.api.execption.ObjectEmUsoException;
import br.com.apssystem.bookstore.api.execption.ObjectNotFoundException;
import br.com.apssystem.bookstore.domain.dtos.LivroDTO;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.repository.LivroRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LivroService {

	private LivroRepository repository;
	private CategoriaService categoriaService;
	

	public Livro findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Livro não encontrada para esse [ID: " + id + "]"));
	}

	public List<Livro> findAll() {
		return repository.findAll();
	}

	@Transactional
	public Livro create(Livro obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	@Transactional
	public Livro update(LivroDTO objDTO, Long id) {
		Livro obj = findById(id);
		obj.setTitulo(objDTO.getTitulo());
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ObjectEmUsoException("Livro não pode ser deletada! possui livros associados");
		}
	}

	public List<Livro> findAll(Long id) {
		categoriaService.findById(id);
		return repository.findAllByCategorai(id);
	}

}
