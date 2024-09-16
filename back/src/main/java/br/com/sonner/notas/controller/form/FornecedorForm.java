package br.com.sonner.notas.controller.form;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;

import br.com.sonner.notas.modelo.Fornecedor;
import br.com.sonner.notas.repository.FornecedorRepository;

public class FornecedorForm {

	@NotFound
	@Length(min = 5)
	private String nome;
	@NotFound
	private int codigoFornecedor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigoFornecedor() {
		return codigoFornecedor;
	}

	public void setCodigoFornecedor(int codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}
	
	public Fornecedor converter() {
		return new Fornecedor(nome, codigoFornecedor);
	}

	public Fornecedor atualizar(Long id, FornecedorRepository fornecedoRepository) {
		Fornecedor fornecedor = fornecedoRepository.getReferenceById(id);
		fornecedor.setNome(nome);
		fornecedor.setCodigoFornecedor(codigoFornecedor);

		return fornecedor;
	}
}
