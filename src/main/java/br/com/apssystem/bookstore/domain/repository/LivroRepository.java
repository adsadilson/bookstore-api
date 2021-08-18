package br.com.apssystem.bookstore.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Livro;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("select obj from Livro obj where obj.categoria.id = :categoria_id order by titulo")
	List<Livro> findAllByCategoria(@Param(value = "categoria_id") Long id);

	@Query("select obj from Livro obj where obj.editora.id = :editora_id order by titulo")
	List<Livro> findAllByEditora(@Param(value = "editora_id") Long id);

}
