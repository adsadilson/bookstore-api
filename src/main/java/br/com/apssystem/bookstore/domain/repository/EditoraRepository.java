package br.com.apssystem.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

	Optional<Editora> findByCnpj(String string);

}
