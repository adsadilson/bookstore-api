package br.com.apssystem.bookstore.api.dtos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.apssystem.bookstore.api.dtos.entity.FuncionarioEntity;
import br.com.apssystem.bookstore.api.dtos.input.FuncionarioInput;
import br.com.apssystem.bookstore.domain.entity.Funcionario;

@Component
public class FuncionarioMapper {

	@Autowired
	private ModelMapper modelMapper;

	public FuncionarioEntity toEntity(Funcionario obj) {
		return modelMapper.map(obj, FuncionarioEntity.class);
	}

	public List<FuncionarioEntity> toCollectionEntity(List<Funcionario> objs) {
		return objs.stream().map(obj -> toEntity(obj)).collect(Collectors.toList());
	}

	public Funcionario toDomain(FuncionarioInput input) {
		return modelMapper.map(input, Funcionario.class);
	}

	public void copyToDomainObject(FuncionarioInput input, Funcionario obj) {
		modelMapper.map(input, obj);
	}

}
