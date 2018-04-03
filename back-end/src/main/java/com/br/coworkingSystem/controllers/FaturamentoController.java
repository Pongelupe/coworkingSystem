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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.coworkingSystem.model.Faturamento;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.FaturamentoRepository;
import com.br.coworkingSystem.validators.FaturamentoValidator;

@Controller
public class FaturamentoController {

	@Autowired
	private FaturamentoRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new FaturamentoValidator());
	}

	@GetMapping("/faturamentos")
	public @ResponseBody ResponseEntity<Response<List<Faturamento>>> getFaturamentos() {
		Response<List<Faturamento>> response = new Response<List<Faturamento>>();

		List<Faturamento> faturamentos = repository.findAll();
		response.setData(faturamentos);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/faturamento/{idFaturamento}")
	public @ResponseBody ResponseEntity<Response<Faturamento>> getFaturamento(@PathVariable Long idFaturamento) {
		Response<Faturamento> response = new Response<Faturamento>();
		Optional<Faturamento> faturamento = repository.findById(idFaturamento);
		if (faturamento.isPresent()) {
			response.setData(faturamento.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Faturamento não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/faturamento")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraFaturamento(@RequestBody @Valid Faturamento faturamento,
			BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			faturamento = repository.save(faturamento);
			response.setData(faturamento.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/faturamento/{idFaturamento}")
	public @ResponseBody ResponseEntity<Response<Long>> updateFaturamento(@PathVariable Long idFaturamento,
			@RequestBody @Valid Faturamento faturamento, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(idFaturamento).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este faturamento");

			return ResponseEntity.badRequest().body(response);
		} else {

			faturamento.setId(idFaturamento);
			repository.save(faturamento);
			response.setData(idFaturamento);

			return ResponseEntity.ok(response);
		}

	}

	@PostMapping("/aplicarDesconto")
	public @ResponseBody ResponseEntity<Response<Boolean>> aplicarDesconto(@RequestParam Long idFaturamento,
			@RequestBody Faturamento faturamento) {
		Response<Boolean> response = new Response<Boolean>();
		Optional<Faturamento> faturamentoOptional = repository.findById(idFaturamento);

		if (!faturamentoOptional.isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar este faturamento");

			return ResponseEntity.badRequest().body(response);
		} else {
			double desconto = faturamento.getDescontoFaturamento();
			faturamento = faturamentoOptional.get();
			faturamento.setDescontoFaturamento(desconto);
			repository.save(faturamento);

			response.setData(true);

			return ResponseEntity.ok(response);
		}
	}

	@DeleteMapping("/faturamento/{idFaturamento}")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarFaturamento(@PathVariable Long idFaturamento) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idFaturamento).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar este faturamento");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idFaturamento);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}
}
