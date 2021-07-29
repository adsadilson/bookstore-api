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

import br.com.apssystem.bookstore.api.dtos.entity.PerfilEntity;
import br.com.apssystem.bookstore.api.dtos.entity.PerfilFuncEntity;
import br.com.apssystem.bookstore.api.dtos.input.PerfilInput;
import br.com.apssystem.bookstore.api.dtos.mapper.PerfilMapper;
import br.com.apssystem.bookstore.domain.entity.Perfil;
import br.com.apssystem.bookstore.domain.service.PerfilService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/perfils")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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
	public ResponseEntity<PerfilEntity> adicionar(@Valid @RequestBody PerfilInput obj) {
		Perfil objNovo = perfilService.adicionar(mapper.toDomain(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objNovo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(objNovo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PerfilEntity> atualizar(@RequestBody PerfilInput input, @PathVariable Long id) {
		Perfil obj = perfilService.buscarPorId(id);
		mapper.copyToDomainObject(input, obj);
		perfilService.atualizar(obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		perfilService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/{funcionarios}")
	public ResponseEntity<PerfilFuncEntity> buscarTodosFuncPorPerfil(@PathVariable("id") Long id) {
		Perfil perfil = perfilService.buscarTodosFuncPorPerfil(id);
		return ResponseEntity.ok(mapper.toFuncEntity(perfil));
	}
}
