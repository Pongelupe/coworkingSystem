package com.br.coworkingSystem.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.coworkingSystem.model.ProdutoServico;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.ProdutoServicoRepository;
import com.br.coworkingSystem.validators.ProdutoServicoValidator;

@Controller
public class ProdutoServicoController {

	@Autowired
	private ProdutoServicoRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ProdutoServicoValidator());
	}

	@GetMapping("/produtosServicos")
	public @ResponseBody ResponseEntity<Response<List<ProdutoServico>>> getProdutoServicos() {
		Response<List<ProdutoServico>> response = new Response<List<ProdutoServico>>();

		List<ProdutoServico> produtosServicos = repository.findAll();
		response.setData(produtosServicos);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/produtoServico")
	public @ResponseBody ResponseEntity<Response<ProdutoServico>> getProdutoServico(
			@RequestParam Long idProdutoServico) {
		Response<ProdutoServico> response = new Response<ProdutoServico>();
		Optional<ProdutoServico> produtoServico = repository.findById(idProdutoServico);
		if (produtoServico.isPresent()) {
			response.setData(produtoServico.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("ProdutoServico não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/produtoServico")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraProdutoServico(
			@RequestBody @Valid ProdutoServico produtoServico, BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			produtoServico = repository.save(produtoServico);
			response.setData(produtoServico.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/produtoServico")
	public @ResponseBody ResponseEntity<Response<Long>> updateProdutoServico(@RequestParam Long idProdutoServico,
			@RequestBody @Valid ProdutoServico cliente, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(idProdutoServico).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			cliente.setId(idProdutoServico);
			repository.save(cliente);
			response.setData(idProdutoServico);

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/produtoServico")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarProdutoServico(
			@RequestParam Long idProdutoServico) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idProdutoServico).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar esta ProdutoServico");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idProdutoServico);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}

}
