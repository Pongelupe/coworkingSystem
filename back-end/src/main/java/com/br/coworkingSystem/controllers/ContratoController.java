package com.br.coworkingSystem.controllers;

import java.util.List;
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

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Contrato;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.ClienteRepository;
import com.br.coworkingSystem.repositories.ContratoRepository;
import com.br.coworkingSystem.validators.ContratoValidator;

@RestController
public class ContratoController {

	@Autowired
	private ContratoRepository repo;

	@Autowired
	private ClienteRepository repoCliente;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ContratoValidator());
	}

	@PostMapping("/contrato")
	public ResponseEntity<Response<Long>> cadastrarContrato(@RequestBody @Valid Contrato contrato,
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
	public ResponseEntity<Response<List<Contrato>>> getContratoByCliente(@RequestParam long idCliente) {

		Optional<Cliente> clienteOptional = repoCliente.findById(idCliente);
		Response<List<Contrato>> response = new Response<List<Contrato>>();

		if (clienteOptional.isPresent())
			return ResponseEntity.ok(response.setData(repo.findAllByCliente(clienteOptional)));
		else
			return ResponseEntity.badRequest().body(response);
	}

	@GetMapping("/contratos")
	public ResponseEntity<Response<List<Contrato>>> getContratos() {

		Response<List<Contrato>> response = new Response<List<Contrato>>();
		return ResponseEntity.ok(response.setData(repo.findAll()));

	}

	@PutMapping("/contrato")
	public ResponseEntity<Response<Long>> upadteContrato(@RequestBody @Valid Contrato contrato, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors() || !repo.existsById(contrato.getId())) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			contrato = repo.save(contrato);
			response.setData(contrato.getId());
			return ResponseEntity.ok(response);
		}
	}
}
