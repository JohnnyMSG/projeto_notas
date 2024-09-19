package br.com.sonner.notas.modelo;
import javax.persistence.*;

@Entity
public class Fornecedor {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "codigo_fornecedor")
	private int codigoFornecedor;
	private String nome;
	
	public Fornecedor() {
	}

	public Fornecedor(String nome, int codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
		this.nome = nome;
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
}
