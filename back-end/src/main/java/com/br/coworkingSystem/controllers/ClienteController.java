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

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.ClienteRepository;
import com.br.coworkingSystem.validators.ClienteValidator;

@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ClienteValidator());
	}

	@GetMapping("/clientes")
	public @ResponseBody ResponseEntity<Response<List<Cliente>>> getClientes() {
		Response<List<Cliente>> response = new Response<List<Cliente>>();

		List<Cliente> clientes = repository.findAll();
		response.setData(clientes);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/cliente/{idCliente}")
	public @ResponseBody ResponseEntity<Response<Cliente>> getCliente(@PathVariable Long idCliente) {
		Response<Cliente> response = new Response<Cliente>();
		Optional<Cliente> cliente = repository.findById(idCliente);
		if (cliente.isPresent()) {
			response.setData(cliente.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Cliente não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/cliente")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraCliente(@RequestBody @Valid Cliente cliente,
			BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			cliente = repository.save(cliente);
			response.setData(cliente.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/cliente")
	public @ResponseBody ResponseEntity<Response<Long>> updateCliente(@RequestParam Long idCliente,
			@RequestBody @Valid Cliente cliente, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(idCliente).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			cliente.setId(idCliente);
			repository.save(cliente);
			response.setData(idCliente);

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/cliente")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarCliente(@RequestParam Long idCliente) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idCliente).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idCliente);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}
}
