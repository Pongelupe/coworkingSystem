package com.br.coworkingSystem.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.coworkingSystem.model.Contrato;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.ContratoRepository;
import com.br.coworkingSystem.validators.ContratoValidator;

@RestController
public class ContratoController {

	@Autowired
	private ContratoRepository repo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ContratoValidator());
	}

	@PostMapping("/contrato")
	public ResponseEntity<Response<Long>> cadastraContrato(@RequestBody @Valid Contrato contrato,
			BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			contrato = repo.save(contrato);
			response.setData(contrato.getId());
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("/contrato")
	public ResponseEntity<Response<Contrato>> getContrato(@RequestParam long idContrato) {
		Optional<Contrato> contratoOpitional = repo.findById(idContrato);
		Response<Contrato> response = new Response<Contrato>();

		if (contratoOpitional.isPresent())
			return ResponseEntity.ok(response.setData(contratoOpitional.get()));
		else
			return ResponseEntity.badRequest().body(response);
	}

	@PutMapping("/contrato")
	public ResponseEntity<Response<Long>> cadastraContrato(@RequestParam long idContrato,
			@RequestBody @Valid Contrato contrato, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors() || !repo.existsById(idContrato)) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			contrato.setId(idContrato);
			contrato = repo.save(contrato);
			response.setData(contrato.getId());
			return ResponseEntity.ok(response);
		}
	}
}
