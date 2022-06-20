package br.com.ebuybooks.dto;


import java.math.BigDecimal;

import br.com.ebuybooks.model.Carrinho;


public class RequisicaoMontarCarrinho {
	
	private String urlCapa;
	private String titulo;
	private String autor;
	private BigDecimal valor;

	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getUrlCapa() {
		return urlCapa;
	}
	public void setUrlCapa(String urlCapa) {
		this.urlCapa = urlCapa;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	public Carrinho toCarrinho() {
		
		Carrinho carrinho = new Carrinho();
		carrinho.setAutorLivro(autor);
		carrinho.setTituloLivro(titulo);
		carrinho.setUrlImagem(urlCapa);
		carrinho.setQuantidade(1);
		carrinho.setValorTotal(valor);
		
		return carrinho;
	}

}
