package br.com.ebuybooks.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ebuybooks.model.Carrinho;
import br.com.ebuybooks.model.Venda;
import br.com.ebuybooks.repository.CarrinhoRepository;
import br.com.ebuybooks.repository.VendasRepository;

@Controller
@RequestMapping
public class ComprasController {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@Autowired
	private VendasRepository vendaRepository;
	
	@GetMapping("/finalizarCompra")
	public String finalizar(Model model, Principal principal) {
		
		List<Carrinho> itensCarrinho = carrinhoRepository.findAllByUser(principal.getName());
		
		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Carrinho item : itensCarrinho) {
			valorTotal = valorTotal.add(item.getValorTotal());
		}
		
		model.addAttribute("itensCarrinho", itensCarrinho);
		model.addAttribute("valorTotal", valorTotal);
		
		Date data = new Date();
		
		for (Carrinho item : itensCarrinho) {
			Venda venda = new Venda();
			venda.setAutorLivro(item.getAutorLivro());
			venda.setTituloLivro(item.getTituloLivro());
			venda.setQuantidade(item.getQuantidade());
			venda.setUrlImagem(item.getUrlImagem());
			venda.setValorTotal(item.getValorTotal());
			venda.setCriadoEm(data);
			venda.setUser(item.getUser());
			vendaRepository.save(venda);
		}
		
		carrinhoRepository.deleteAll(itensCarrinho);
		 
		return "finalizarCompra";
		
	}
	
	@GetMapping("/comprasFinalizadas")
	public String finalizadas(Model model, Principal principal) {
		
		List<Venda> vendas = vendaRepository.findAllByUser(principal.getName());
		
		if (vendas.isEmpty()) {
			model.addAttribute("vendas", null);
		} else {
			model.addAttribute("vendas", vendas);
		}
		
		return "comprasFinalizadas";
		
	}

}
