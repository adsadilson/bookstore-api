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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apssystem.bookstore.domain.dtos.LivroDTO;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.service.LivroService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/livros")
@AllArgsConstructor
public class LivroResource {

	private LivroService livroService;

	@GetMapping("/{id}")
	public ResponseEntity<Livro> findById(@PathVariable Long id) {
		Livro obj = livroService.findById(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping
	public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Long id) {
		List<Livro> list = livroService.findAll(id);
		List<LivroDTO> livroDTOs = list.stream().map(obj -> new LivroDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(livroDTOs);
	}

	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> update(@RequestBody Livro obj, @PathVariable Long id) {
		Livro newObj = livroService.update(obj, id);
		return ResponseEntity.ok().body(new LivroDTO(newObj));
	}

	@PostMapping
	public ResponseEntity<Livro> create(@RequestBody Livro obj) {
		obj = livroService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		livroService.delete(id);
		return ResponseEntity.noContent().build();
	}

}