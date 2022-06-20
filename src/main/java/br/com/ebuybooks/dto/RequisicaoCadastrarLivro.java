package br.com.ebuybooks.dto;

import java.math.BigDecimal;

import br.com.ebuybooks.model.Livro;

public class RequisicaoCadastrarLivro {
	
	private String titulo;
	private String autor;
	private String descricao;
	private String urlCapa;
	private Integer quantidadeEstoque;
	private String genero;
	private BigDecimal valor;
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getUrlCapa() {
		return urlCapa;
	}
	
	public void setUrlCapa(String urlCapa) {
		this.urlCapa = urlCapa;
	}
	
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Livro toLivro() {
		
		Livro livro = new Livro();
		livro.setAutor(autor);
		livro.setTitulo(titulo);
		livro.setDescricao(descricao);
		livro.setUrlCapa(urlCapa);
		livro.setValor(valor);
		livro.setGenero(genero);
		livro.setQuantidadeEstoque(quantidadeEstoque);
		
		return livro;
	}
	
	

}
