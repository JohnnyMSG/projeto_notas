package br.com.sonner.notas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sonner.notas.modelo.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Produto findByNome(String nomeProduto);
	
}
