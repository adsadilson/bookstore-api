package br.com.apssystem.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
