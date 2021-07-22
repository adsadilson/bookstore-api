package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.CategoriaEntity;
import br.com.apssystem.bookstore.api.dtos.input.CategoriaInput;
import br.com.apssystem.bookstore.domain.entity.Categoria;

@Component
public class CategoriaMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CategoriaEntity toEntity(Categoria obj) {
		return modelMapper.map(obj, CategoriaEntity.class);
	}

	public List<CategoriaEntity> toCollectionEntity(List<Categoria> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Categoria toDomain(CategoriaInput input) {
		return modelMapper.map(input, Categoria.class);
	}

	public void copyToDomainObject(CategoriaInput input, Categoria obj) {
		modelMapper.map(input, obj);
	}

}
