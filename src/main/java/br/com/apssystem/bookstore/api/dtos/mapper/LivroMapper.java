package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.LivroEntity;
import br.com.apssystem.bookstore.api.dtos.input.LivroInput;
import br.com.apssystem.bookstore.domain.entity.Categoria;
import br.com.apssystem.bookstore.domain.entity.Editora;
import br.com.apssystem.bookstore.domain.entity.Livro;
import br.com.apssystem.bookstore.domain.service.CategoriaService;
import br.com.apssystem.bookstore.domain.service.EditoraService;

@Component
public class LivroMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private EditoraService editoraService;

	public LivroEntity toEntity(Livro obj) {
		return modelMapper.map(obj, LivroEntity.class);
	}

	public List<LivroEntity> toCollectionEntity(List<Livro> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Livro toDomain(LivroInput input) {
		return modelMapper.map(input, Livro.class);
	}

	public void copyToDomainObject(LivroInput input, Livro obj) {
		Categoria cat = categoriaService.buscarPorId(obj.getCategoria().getId());
		obj.setCategoria(cat);
		Editora edi = editoraService.buscarPorId(obj.getEditora().getId());
		obj.setEditora(edi);
		modelMapper.map(input, obj);
	}

}
