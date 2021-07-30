package br.com.apssystem.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

	Optional<Editora> findByCnpj(String obj);

	@Query("select obj from Editora obj where obj.nome = :nome and obj.cnpj= :cnpj order by obj.nome")
	Optional<Editora> findByCnpjAndNome(@Param(value = "nome") String nome, @Param(value = "cnpj") String cnpj);

}
