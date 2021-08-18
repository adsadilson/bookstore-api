package br.com.apssystem.bookstore.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apssystem.bookstore.api.dtos.entity.LivroEntity;
import br.com.apssystem.bookstore.api.dtos.input.LivroInput;
import br.com.apssystem.bookstore.api.dtos.mapper.LivroMapper;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.service.LivroService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/livros")
@AllArgsConstructor
public class LivroResource {

	private LivroService livroService;
	private LivroMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<LivroEntity> buscarPorId(@PathVariable Long id) {
		Livro obj = livroService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}
	
	
	@GetMapping
	public ResponseEntity<List<LivroEntity>> listarTodosLivros(){
		List<LivroEntity> lists = mapper.toCollectionEntity(livroService.findAll());
		return ResponseEntity.ok(lists); 
	}

	/*
	 * @GetMapping("/{idCat}") public ResponseEntity<List<LivroEntity>>
	 * listarTodosLivrosPorCategoria(
	 * 
	 * @RequestParam(value = "categoria", defaultValue = "0") Long id) { List<Livro>
	 * list = livroService.listarTodosLivroPorCategoria(id); List<LivroEntity>
	 * listEntity = mapper.toCollectionEntity(list); return
	 * ResponseEntity.ok().body(listEntity); }
	 */
	
	/*	
	 * @GetMapping public ResponseEntity<List<LivroEntity>>
	 * listarTodosLivrosPorEditora(
	 * 
	 * @RequestParam(value = "editora", defaultValue = "0") Long id) { List<Livro>
	 * list = livroService.listarTodosLivroPorCategoria(id); List<LivroEntity>
	 * listEntity = mapper.toCollectionEntity(list); return
	 * ResponseEntity.ok().body(listEntity); }
	 */

	@PutMapping
	public ResponseEntity<LivroEntity> atualizar(@Valid @RequestBody LivroInput input) {
		Livro entity = livroService.buscarPorId(input.getId());
		mapper.copyToDomainObject(input, entity);
		livroService.autalizar(entity);
		return ResponseEntity.ok().body(mapper.toEntity(entity));
	}

	@PostMapping
	public ResponseEntity<LivroEntity> adicionar(@Valid @RequestBody LivroInput input) {
		Livro obj = livroService.adicionar(mapper.toDomain(input));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		livroService.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
