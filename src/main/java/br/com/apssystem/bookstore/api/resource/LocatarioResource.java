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

import br.com.apssystem.bookstore.api.dtos.entity.LocatarioEntity;
import br.com.apssystem.bookstore.api.dtos.input.LocatarioInput;
import br.com.apssystem.bookstore.api.dtos.mapper.LocatarioMapper;
import br.com.apssystem.bookstore.domain.entity.Locatario;
import br.com.apssystem.bookstore.domain.service.LocatarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/locatarios")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LocatarioResource {

	private LocatarioService locatarioService;
	private LocatarioMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<LocatarioEntity> buscarPorId(@PathVariable Long id) {
		Locatario obj = locatarioService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}

	@GetMapping
	public ResponseEntity<List<LocatarioEntity>> listarTodos() {
		List<LocatarioEntity> lists = mapper.toCollectionEntity(locatarioService.listarTodos());
		return ResponseEntity.ok(lists);
	}

	@PostMapping
	public ResponseEntity<LocatarioEntity> adicionar(@Valid @RequestBody LocatarioInput obj) {
		Locatario objNovo = locatarioService.adicionar(mapper.toDomain(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(objNovo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<LocatarioEntity> atualizar(@RequestBody LocatarioInput input, @PathVariable Long id) {
		Locatario obj = locatarioService.buscarPorId(id);
		mapper.copyToDomainObject(input, obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		locatarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
