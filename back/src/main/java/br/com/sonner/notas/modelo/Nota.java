package br.com.sonner.notas.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "notas")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private LocalDate data = LocalDate.now();
	@ManyToOne
	private Fornecedor fornecedor;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	@OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ItemNota> itens = new ArrayList<>();

	public Nota() {

	}

	public Nota(int numero, Fornecedor fornecedor, BigDecimal valorTotal) {
		this.numero = numero;
		this.fornecedor = fornecedor;
		this.valorTotal = valorTotal;
	}

	public void adicionarItem(ItemNota item) {
		item.setNota(this);
		this.itens.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemNota> getItens() {
		return itens;
	}

	public void setItens(List<ItemNota> itens) {
		this.itens = itens;
	}
}
