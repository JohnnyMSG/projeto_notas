package br.com.sonner.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.notas.modelo.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Fornecedor findByNome(String nome);
	
}
