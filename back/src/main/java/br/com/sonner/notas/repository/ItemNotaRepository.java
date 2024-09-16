package br.com.sonner.notas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.sonner.notas.modelo.ItemNota;

public interface ItemNotaRepository extends JpaRepository<ItemNota, Long> {

	@Query("SELECT i FROM ItemNota i JOIN i.nota n WHERE n.id = :id")
	List<ItemNota> itensByNotaId(@Param("id") Long id);
}

