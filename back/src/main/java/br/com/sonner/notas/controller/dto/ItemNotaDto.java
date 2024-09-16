package br.com.sonner.notas.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.sonner.notas.modelo.ItemNota;

public class ItemNotaDto {

	private Long id;
	private int quantidade;
	private BigDecimal valor_total;
	private int numeroNota;
	private String nomeProduto;
	
	public ItemNotaDto(ItemNota itemNota) {
		this.id = itemNota.getId();
		this.quantidade = itemNota.getQuantidade();
		this.valor_total = itemNota.getValorTotal();
		this.numeroNota = itemNota.getNota().getNumero();
		this.nomeProduto = itemNota.getProduto().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor_total() {
		return valor_total;
	}

	public void setValor_total(BigDecimal valor_total) {
		this.valor_total = valor_total;
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

	public static List<ItemNotaDto> converter(List<ItemNota> itens) {
		return itens.stream().map(ItemNotaDto::new).collect(Collectors.toList());
	}
}


