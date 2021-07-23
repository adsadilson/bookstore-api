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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apssystem.bookstore.api.dtos.entity.PerfilEntity;
import br.com.apssystem.bookstore.api.dtos.input.PerfilInput;
import br.com.apssystem.bookstore.api.dtos.mapper.PerfilMapper;
import br.com.apssystem.bookstore.domain.entity.Perfil;
import br.com.apssystem.bookstore.domain.service.PerfilService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/perfils")
@AllArgsConstructor
public class PerfilResource {

	private PerfilService perfilService;
	private PerfilMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<PerfilEntity> buscarPorId(@PathVariable Long id) {
		Perfil obj = perfilService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}

	@GetMapping
	public ResponseEntity<List<PerfilEntity>> listarTodos() {
		List<PerfilEntity> lists = mapper.toCollectionEntity(perfilService.listarTodos());
		return ResponseEntity.ok(lists);
	}

	@PostMapping
	public ResponseEntity<Perfil> adicionar(@Valid @RequestBody Perfil obj) {
		obj = perfilService.adicionar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PerfilEntity> atualizar(@RequestBody PerfilInput input, @PathVariable Long id) {
		Perfil obj = perfilService.buscarPorId(id);
		mapper.copyToDomainObject(input, obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		perfilService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
