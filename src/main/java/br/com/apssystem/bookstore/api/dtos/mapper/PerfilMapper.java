package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.PerfilEntity;
import br.com.apssystem.bookstore.api.dtos.input.PerfilInput;
import br.com.apssystem.bookstore.domain.entity.Perfil;

@Component
public class PerfilMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PerfilEntity toEntity(Perfil obj) {
		return modelMapper.map(obj, PerfilEntity.class);
	}

	public List<PerfilEntity> toCollectionEntity(List<Perfil> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Perfil toDomain(PerfilInput input) {
		return modelMapper.map(input, Perfil.class);
	}

	public void copyToDomainObject(PerfilInput input, Perfil obj) {
		modelMapper.map(input, obj);
	}

}
