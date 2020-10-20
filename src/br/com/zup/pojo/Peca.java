package br.com.zup.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.zup.lojaSeuJoseEnums.Categoria;

@Entity
@Table(name="pecas")
public class Peca {

	@Id
	@Column(name="codigo_de_barras")
	private int codigoBarras;
	private String nome;
	@Column(name="modelo_do_carro")
	private String modeloCarro;
	private String fabricante;
	@Column(name="preco_de_custo")
	private double precoCusto;
	@Column(name="preco_de_venda")
	private double precoVenda;
	@Column(name="quantidade_em_estoque")
	private int quantidadeEstoque;
	private Categoria categoria;
	
	public Peca() {	
	}

	public int getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(int quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String mostraDados() {
		return String.format("Cod: %d - Nome: %s - Usada em: %s - Fab.: %s - Preço Custo: R$%.2f\n"
				+ "Preço Venda: R$%.2f - Estoque: %d peças - Categoria: %s\n",codigoBarras, nome, modeloCarro, fabricante, precoCusto, precoVenda,
				quantidadeEstoque, categoria);
	}
}

