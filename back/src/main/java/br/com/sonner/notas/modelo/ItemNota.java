package br.com.sonner.notas.modelo;

import java.math.BigDecimal;
import javax.persistence.*;

@Entity
public class ItemNota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantidade;
	@Column(name = "valor_total")
	private BigDecimal valorTotal;
	@ManyToOne
	private Nota nota;
	@ManyToOne
	private Produto produto;
	
	public ItemNota() {
	}

	public ItemNota(int quantidade, BigDecimal valorTotal, Nota nota, Produto produto) {
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.nota = nota;
		this.produto = produto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
