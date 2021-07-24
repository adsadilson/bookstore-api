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

import br.com.apssystem.bookstore.api.dtos.entity.FuncionarioEntity;
import br.com.apssystem.bookstore.api.dtos.input.FuncionarioInput;
import br.com.apssystem.bookstore.api.dtos.mapper.FuncionarioMapper;
import br.com.apssystem.bookstore.domain.entity.Funcionario;
import br.com.apssystem.bookstore.domain.service.FuncionarioService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/funcionarios")
@AllArgsConstructor
public class FuncionarioResource {

	private FuncionarioService funcionarioService;
	private FuncionarioMapper mapper;

	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioEntity> buscarPorId(@PathVariable Long id) {
		Funcionario obj = funcionarioService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toEntity(obj));
	}

	@GetMapping
	public ResponseEntity<List<FuncionarioEntity>> listarTodos() {
		List<FuncionarioEntity> lists = mapper.toCollectionEntity(funcionarioService.listarTodos());
		return ResponseEntity.ok(lists);
	}

	@PostMapping
	public ResponseEntity<FuncionarioEntity> adicionar(@Valid @RequestBody FuncionarioInput obj) {
		Funcionario objNovo = funcionarioService.adicionar(mapper.toDomain(obj));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objNovo.getId()).toUri();
		return ResponseEntity.created(uri).body(mapper.toEntity(objNovo));
	}

	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioEntity> atualizar(@RequestBody FuncionarioInput input, @PathVariable Long id) {
		Funcionario obj = funcionarioService.buscarPorId(id);
		mapper.copyToDomainObject(input, obj);
		return ResponseEntity.ok().body(mapper.toEntity(obj));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		funcionarioService.excluir(id);
		return ResponseEntity.noContent().build();
	}
}
