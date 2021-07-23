package br.com.apssystem.bookstore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
