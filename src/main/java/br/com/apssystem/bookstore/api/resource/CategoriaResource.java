package br.com.apssystem.bookstore.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apssystem.bookstore.api.dtos.entity.CategoriaEntity;
import br.com.apssystem.bookstore.api.dtos.input.CategoriaInput;
import br.com.apssystem.bookstore.api.dtos.mapper.CategoriaMapper;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.service.CategoriaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaResource {

	private CategoriaService categoriaService;
	private CategoriaMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaEntity> buscarPorId(@PathVariable Long id) {
		Categoria obj = categoriaService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}

	@GetMapping
	public ResponseEntity<List<CategoriaEntity>> listarTodos() {
		List<CategoriaEntity> lists = mapper.toCollectionEntity(categoriaService.listarTodos());
		return ResponseEntity.ok(lists);
	}

	@PostMapping
	public ResponseEntity<CategoriaEntity> adicionar(@Valid @RequestBody CategoriaInput obj) {
		Categoria objNovo = categoriaService.adicionar(mapper.toDomain(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(objNovo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaEntity> atualizar(@RequestBody CategoriaInput input, @PathVariable Long id) {
		Categoria obj = categoriaService.buscarPorId(id);
		mapper.copyToDomainObject(input, obj);
		categoriaService.atualizar(obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		categoriaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
