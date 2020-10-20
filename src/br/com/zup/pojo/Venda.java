package br.com.zup.pojo;


public class Venda {
	
	private int codigoBarras;
	private String nomeProduto;
	private int qtdItensComprados;
	private double valorCompra;
	
	
	public Venda() {
	}

	public int getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	

	public int getQtdItensComprados() {
		return qtdItensComprados;
	}


	public void setQtdItensComprados(int qtdItensComprados) {
		this.qtdItensComprados = qtdItensComprados;
	}


	public double getValorCompra() {
		return valorCompra;
	}


	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}
	
	public String gravaVenda() {
		return String.format("%d     -   %s   - %d -    %.2f", codigoBarras, nomeProduto, qtdItensComprados, valorCompra);
	}
	
}
