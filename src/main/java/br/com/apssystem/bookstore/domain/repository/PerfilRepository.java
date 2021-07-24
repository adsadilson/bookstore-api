package br.com.apssystem.bookstore.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apssystem.bookstore.domain.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	Optional<Perfil> findByNome(String nome);
	
	@Query("SELECT bean FROM Perfil bean JOIN bean.funcionarios WHERE bean.id = :id")
	Optional<Perfil> buscarTodosOsFuncPorPerfil(@Param("id") Long id);

}
