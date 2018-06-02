package com.br.coworkingSystem.controllers;

import java.util.List;

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

import com.br.coworkingSystem.model.Correspondencia;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.CorrespondenciaRepository;
import com.br.coworkingSystem.validators.CorrespondenciaValidator;

@Controller
public class CorrespondenciaController {

	@Autowired
	private CorrespondenciaRepository repository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new CorrespondenciaValidator());
	}

	@GetMapping("/correspondencias")
	public @ResponseBody ResponseEntity<Response<List<Correspondencia>>> getCorrespondencias() {
		Response<List<Correspondencia>> response = new Response<List<Correspondencia>>();

		List<Correspondencia> correspondencias = repository.findAll();
		response.setData(correspondencias);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/correspondencia")
	public @ResponseBody ResponseEntity<Response<Long>> cadastrarCorrespondencia(
			@RequestBody @Valid Correspondencia correspondencia, BindingResult result) {

		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			correspondencia = repository.save(correspondencia);
			response.setData(correspondencia.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/correspondencia")
	public @ResponseBody ResponseEntity<Response<Long>> updateCorrespondencia(
			@RequestBody @Valid Correspondencia correspondencia, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!repository.findById(correspondencia.getId()).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			repository.save(correspondencia);
			response.setData(correspondencia.getId());

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/correspondencia")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarCorrespondencia(
			@RequestParam Long idCorrespondencia) {
		Response<Boolean> response = new Response<Boolean>();

		if (!repository.findById(idCorrespondencia).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar esta Correspondencia");

			return ResponseEntity.badRequest().body(response);
		} else {
			repository.deleteById(idCorrespondencia);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}
}
