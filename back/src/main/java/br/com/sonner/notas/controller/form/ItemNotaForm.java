package br.com.sonner.notas.controller.form;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.NotFound;

import br.com.sonner.notas.modelo.ItemNota;
import br.com.sonner.notas.modelo.Nota;
import br.com.sonner.notas.modelo.Produto;
import br.com.sonner.notas.repository.ItemNotaRepository;
import br.com.sonner.notas.repository.NotaRepository;
import br.com.sonner.notas.repository.ProdutoRepository;

public class ItemNotaForm {

	@NotFound
	private int quantidade;
	@NotFound
	private int numeroNota;
	@NotFound
	private String nomeProduto;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(int numeroNota) {
		this.numeroNota = numeroNota;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public ItemNota converter(NotaRepository notaRepository, ProdutoRepository produtoRepository, ItemNotaRepository itemNotaRepository) {
	    Nota nota = notaRepository.findByNumero(numeroNota);
	    Produto produto = produtoRepository.findByNome(nomeProduto);

	    BigDecimal precoProduto = produto.getPreco();
	    BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(quantidade);
	    BigDecimal valor_total = precoProduto.multiply(quantidadeBigDecimal);

	    ItemNota novoItemNota = new ItemNota(quantidade, valor_total, nota, produto);
	    itemNotaRepository.save(novoItemNota);

	    List<ItemNota> itens = itemNotaRepository.itensByNotaId(nota.getId());

	    BigDecimal valor_nota_total = BigDecimal.ZERO;
	    for (ItemNota item : itens) {
	        valor_nota_total = valor_nota_total.add(item.getValorTotal());
	    }

	    nota.setValorTotal(valor_nota_total);
	    notaRepository.save(nota);

	    return novoItemNota;
	}


	public ItemNota atualizar(Long id, ItemNotaRepository itemNotaRepository, NotaRepository notaRepository, ProdutoRepository produtoRepository) {
		Produto produto = produtoRepository.findByNome(nomeProduto);
		Nota nota = notaRepository.findByNumero(numeroNota);
		ItemNota itemNota = itemNotaRepository.getReferenceById(id);
		
		BigDecimal precoProduto = produto.getPreco();
		BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(quantidade);
		BigDecimal valor_total = precoProduto.multiply(quantidadeBigDecimal);
		
		itemNota.setQuantidade(quantidade);
		itemNota.setValorTotal(valor_total);
		itemNota.setNota(nota);
		itemNota.setProduto(produto);

		return itemNota;
	}
}
