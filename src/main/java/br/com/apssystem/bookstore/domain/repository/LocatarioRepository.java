package br.com.apssystem.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Locatario;

@Repository
public interface LocatarioRepository extends JpaRepository<Locatario, Long> {

}
