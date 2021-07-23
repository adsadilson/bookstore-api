package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.EditoraEntity;
import br.com.apssystem.bookstore.api.dtos.input.EditoraInput;
import br.com.apssystem.bookstore.domain.entity.Editora;

@Component
public class EditoraMapper {

	@Autowired
	private ModelMapper modelMapper;

	public EditoraEntity toEntity(Editora obj) {
		return modelMapper.map(obj, EditoraEntity.class);
	}

	public List<EditoraEntity> toCollectionEntity(List<Editora> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Editora toDomain(EditoraInput input) {
		return modelMapper.map(input, Editora.class);
	}

	public void copyToDomainObject(EditoraInput input, Editora obj) {
		modelMapper.map(input, obj);
	}

}
