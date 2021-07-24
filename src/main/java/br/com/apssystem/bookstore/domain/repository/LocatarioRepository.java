package br.com.apssystem.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Locatario;

@Repository
public interface LocatarioRepository extends JpaRepository<Locatario, Long> {

	Optional<Locatario> findByCpf(String cpf);

}
