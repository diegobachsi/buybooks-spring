package br.com.ebuybooks.controller;

import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.ebuybooks.model.Carrinho;
import br.com.ebuybooks.repository.CarrinhoRepository;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
	
	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	@GetMapping
	public String carrinho(Model model, Principal principal) {
		
		List<Carrinho> itensCarrinho = carrinhoRepository.findAllByUser(principal.getName());

		BigDecimal valorTotal = BigDecimal.ZERO;
		for (Carrinho item : itensCarrinho) {
			valorTotal = valorTotal.add(item.getValorTotal());
		}
		
		if (itensCarrinho.isEmpty()) {
			model.addAttribute("itensCarrinho", null);
		} else {
			model.addAttribute("itensCarrinho", itensCarrinho);
			model.addAttribute("valorTotal", valorTotal);
		}
		
		return "carrinho";
	}
	
	@GetMapping("{id}")
	public String deletar(@PathVariable("id") Long id) {
		
		Carrinho carrinho = carrinhoRepository.findCarrinhoById(id);
		
		Integer quantidade = carrinho.getQuantidade();
		BigDecimal quant = new BigDecimal(quantidade);
		BigDecimal valorTotal = carrinho.getValorTotal();
		BigDecimal valorItem = valorTotal.divide(quant);
		
		System.out.print(quantidade);
		
		if (quantidade > 1) {
			carrinho.setQuantidade(quantidade - 1);
			carrinho.setValorTotal(valorTotal.subtract(valorItem));
			carrinhoRepository.save(carrinho);
		} else {
			carrinhoRepository.deleteById(id);
		}
		
		return "redirect:/carrinho";
		
	}

}