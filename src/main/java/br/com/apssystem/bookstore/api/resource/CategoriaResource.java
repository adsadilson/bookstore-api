package br.com.apssystem.bookstore.api.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apssystem.bookstore.domain.dtos.CategoriaDTO;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.service.CategoriaService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaResource {

	private CategoriaService service;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<CategoriaDTO> list = service.findAll().stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok(list);
	}


	@PostMapping
	public ResponseEntity<Categoria> create(@RequestBody Categoria obj) {
		obj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoriaDTO> update(@RequestBody CategoriaDTO objDTO, @PathVariable Long id) {
		Categoria newObj = service.update(objDTO, id);
		return ResponseEntity.ok().body(new CategoriaDTO(newObj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
