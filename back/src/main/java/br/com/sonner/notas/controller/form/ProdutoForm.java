package br.com.sonner.notas.controller.form;

import java.math.BigDecimal;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;

import br.com.sonner.notas.modelo.Produto;
import br.com.sonner.notas.repository.ProdutoRepository;

public class ProdutoForm {

	@NotFound @Length(min = 5)
	private String nome;
	@NotFound @Length(min = 5)
	private String descricao;
	@NotFound
	private BigDecimal preco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Produto converter() {
		return new Produto(nome, descricao, preco);
	}

	public Produto atualizar(Long id, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.getReferenceById(id);
		produto.setDescricao(descricao);
		produto.setNome(nome);
		produto.setPreco(preco);
		
		return produto;		
	}
}





