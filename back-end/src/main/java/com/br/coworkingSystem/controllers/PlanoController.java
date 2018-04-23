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

import com.br.coworkingSystem.model.Plano;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.PlanoRepository;
import com.br.coworkingSystem.validators.PlanoValidator;

@Controller
public class PlanoController {

	@Autowired
	private PlanoRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PlanoValidator());
	}

	@GetMapping("/planos")
	public @ResponseBody ResponseEntity<Response<List<Plano>>> getPlanos() {
		Response<List<Plano>> response = new Response<List<Plano>>();

		List<Plano> planos = repository.findAll();
		response.setData(planos);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/plano")
	public @ResponseBody ResponseEntity<Response<Plano>> getPlano(@RequestParam Long idPlano) {
		Response<Plano> response = new Response<Plano>();
		Optional<Plano> plano = repository.findById(idPlano);
		if (plano.isPresent()) {
			response.setData(plano.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Plano não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/plano")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraPlano(@RequestBody @Valid Plano plano,
			BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			plano = repository.save(plano);
			response.setData(plano.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/plano")
	public @ResponseBody ResponseEntity<Response<Long>> updatePlano(@RequestParam Long idPlano,
			@RequestBody @Valid Plano cliente, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(idPlano).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			cliente.setId(idPlano);
			repository.save(cliente);
			response.setData(idPlano);

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/plano")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarPlano(@RequestParam Long idPlano) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idPlano).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar esta Plano");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idPlano);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}
}
