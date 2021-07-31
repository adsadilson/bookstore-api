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

import br.com.apssystem.bookstore.api.dtos.entity.EditoraEntity;
import br.com.apssystem.bookstore.api.dtos.input.EditoraInput;
import br.com.apssystem.bookstore.api.dtos.mapper.EditoraMapper;
import br.com.apssystem.bookstore.domain.entity.Editora;
import br.com.apssystem.bookstore.domain.service.EditoraService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/editoras")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EditoraResource {

	private EditoraService editoraService;
	private EditoraMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<EditoraEntity> buscarPorId(@PathVariable Long id) {
		Editora obj = editoraService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}

	@GetMapping
	public ResponseEntity<List<EditoraEntity>> listarTodos() {
		List<EditoraEntity> lists = mapper.toCollectionEntity(editoraService.listarTodos());
		return ResponseEntity.ok(lists);
	}

	@PostMapping
	public ResponseEntity<EditoraEntity> adicionar(@Valid @RequestBody EditoraInput obj) {
		Editora objSalva = editoraService.adicionar(mapper.toDomain(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objSalva.getId())
				.toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(objSalva));
	}

	@PutMapping
	public ResponseEntity<EditoraEntity> atualizar(@RequestBody EditoraInput input) {
		Editora obj = editoraService.buscarPorId(input.geti);
		mapper.copyToDomainObject(input, obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		editoraService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
