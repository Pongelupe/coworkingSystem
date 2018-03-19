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

import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.model.Sala;
import com.br.coworkingSystem.repositories.SalaRepository;
import com.br.coworkingSystem.validators.SalaValidator;

@Controller
public class SalaController {

	@Autowired
	private SalaRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new SalaValidator());
	}

	@GetMapping("/salas")
	public @ResponseBody ResponseEntity<Response<List<Sala>>> getSalas() {
		Response<List<Sala>> response = new Response<List<Sala>>();

		List<Sala> salas = repository.findAll();
		response.setData(salas);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/sala/{idSala}")
	public @ResponseBody ResponseEntity<Response<Sala>> getSala(@PathVariable Integer idSala) {
		Response<Sala> response = new Response<Sala>();
		Optional<Sala> sala = repository.findById(idSala);
		if (sala.isPresent()) {
			response.setData(sala.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Sala não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/sala")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastraSala(@RequestBody @Valid Sala sala,
			BindingResult result) {

		Response<Integer> response = new Response<Integer>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			sala = repository.save(sala);
			response.setData(sala.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/sala")
	public @ResponseBody ResponseEntity<Response<Integer>> updateSala(@RequestParam Integer idSala,
			@RequestBody @Valid Sala cliente, BindingResult result) {
		Response<Integer> response = new Response<Integer>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(idSala).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			cliente.setId(idSala);
			repository.save(cliente);
			response.setData(idSala);

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/sala")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarSala(@RequestParam Integer idSala) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idSala).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar esta Sala");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idSala);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}

}
