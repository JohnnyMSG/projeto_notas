package br.com.sonner.notas.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.sonner.notas.modelo.Fornecedor;

public class FornecedorDto {

	private Long id;
	private int codigoFornecedor;
	private String nome;

	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.nome = fornecedor.getNome();
		this.codigoFornecedor = fornecedor.getCodigoFornecedor();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodigoFornecedor() {
		return codigoFornecedor;
	}

	public void setCodigoFornecedor(int codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<FornecedorDto> converter(List<Fornecedor> fornecedores) {
		return fornecedores.stream().map(FornecedorDto::new).collect(Collectors.toList());
	}
}
