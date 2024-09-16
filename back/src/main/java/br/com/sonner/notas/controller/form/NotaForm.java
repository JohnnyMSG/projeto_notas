package br.com.sonner.notas.controller.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;

import br.com.sonner.notas.modelo.Fornecedor;
import br.com.sonner.notas.modelo.ItemNota;
import br.com.sonner.notas.modelo.Nota;
import br.com.sonner.notas.repository.FornecedorRepository;
import br.com.sonner.notas.repository.ItemNotaRepository;
import br.com.sonner.notas.repository.NotaRepository;

public class NotaForm {

	@NotFound
	private int numero;
	@NotFound
	@Length(min = 5)
	private String nomeFornecedor;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public Nota converter(FornecedorRepository fornecedoRepository, NotaRepository notaRepository,
			ItemNotaRepository itemNotaRepository) {
		Fornecedor fornecedor = fornecedoRepository.findByNome(nomeFornecedor);

		List<ItemNota> itensVazio = new ArrayList<>();

		Nota nota = new Nota(numero, fornecedor, BigDecimal.ZERO);
		nota = notaRepository.save(nota);

		List<ItemNota> itens = itemNotaRepository.itensByNotaId(nota.getId());

		BigDecimal valor_total = new BigDecimal("0");

		for (ItemNota item : itens) {
			valor_total = valor_total.add(item.getValorTotal());
		}

		nota.setValorTotal(valor_total);
		nota = notaRepository.save(nota);

		return nota;
	}

	public Nota atualizar(Long id, NotaRepository notaRepository, FornecedorRepository fornecedoRepository,
			ItemNotaRepository itemNotaRepository) {
		Fornecedor fornecedor = fornecedoRepository.findByNome(nomeFornecedor);
		Nota nota = notaRepository.getReferenceById(id);
		nota.setNumero(numero);
		nota.setFornecedor(fornecedor);

		List<ItemNota> itens = itemNotaRepository.itensByNotaId(nota.getId());

		BigDecimal valor_total = new BigDecimal("0");

		for (ItemNota item : itens) {
			valor_total = valor_total.add(item.getValorTotal());
		}

		nota.setValorTotal(valor_total);
		nota = notaRepository.save(nota);

		return nota;
	}
}
