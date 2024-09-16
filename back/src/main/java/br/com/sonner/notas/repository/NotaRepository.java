package br.com.sonner.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.notas.modelo.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {

	Nota findByNumero(int numeroNota);
	
}
