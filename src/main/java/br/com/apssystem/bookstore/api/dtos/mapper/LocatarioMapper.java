package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.LocatarioEntity;
import br.com.apssystem.bookstore.api.dtos.input.LocatarioInput;
import br.com.apssystem.bookstore.domain.entity.Locatario;

@Component
public class LocatarioMapper {

	@Autowired
	private ModelMapper modelMapper;

	public LocatarioEntity toEntity(Locatario obj) {
		return modelMapper.map(obj, LocatarioEntity.class);
	}

	public List<LocatarioEntity> toCollectionEntity(List<Locatario> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Locatario toDomain(LocatarioInput input) {
		return modelMapper.map(input, Locatario.class);
	}

	public void copyToDomainObject(LocatarioInput input, Locatario obj) {
		modelMapper.map(input, obj);
	}

}
