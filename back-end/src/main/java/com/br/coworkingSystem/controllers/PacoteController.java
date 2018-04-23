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

import com.br.coworkingSystem.model.Pacote;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.model.Sala;
import com.br.coworkingSystem.repositories.PacoteRepository;
import com.br.coworkingSystem.repositories.SalaRepository;
import com.br.coworkingSystem.validators.PacoteValidator;

@Controller
public class PacoteController {

	@Autowired
	private PacoteRepository pacoteRepository;

	@Autowired
	private SalaRepository salaRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new PacoteValidator());
	}

	@GetMapping("/pacotes")
	public @ResponseBody ResponseEntity<Response<List<Pacote>>> getPacotes() {
		Response<List<Pacote>> response = new Response<List<Pacote>>();

		List<Pacote> pacotes = pacoteRepository.findAll();
		response.setData(pacotes);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/pacote")
	public @ResponseBody ResponseEntity<Response<Pacote>> getPacote(@RequestParam Long idPacote) {
		Response<Pacote> response = new Response<Pacote>();
		Optional<Pacote> pacote = pacoteRepository.findById(idPacote);
		if (pacote.isPresent()) {
			response.setData(pacote.get());
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Pacote não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/pacote")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraPacote(@RequestBody @Valid Pacote pacote,
			BindingResult result) {

		Response<Long> response = new Response<Long>();
		Optional<Sala> salaOptional = salaRepository.findById(pacote.getSala().getId());

		if (result.hasErrors() || !salaOptional.isPresent()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else {
			pacote.setSala(salaOptional.get());
			pacote = pacoteRepository.save(pacote);
			response.setData(pacote.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PutMapping("/pacote")
	public @ResponseBody ResponseEntity<Response<Long>> updatePacote(@RequestParam Long idPacote,
			@RequestBody @Valid Pacote cliente, BindingResult result) {
		Response<Long> response = new Response<Long>();

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));

			return ResponseEntity.badRequest().body(response);
		} else if (!pacoteRepository.findById(idPacote).isPresent()) {
			response.setData(null);
			response.addError("Não conseguimos encontrar este cliente");

			return ResponseEntity.badRequest().body(response);
		} else {

			cliente.setId(idPacote);
			pacoteRepository.save(cliente);
			response.setData(idPacote);

			return ResponseEntity.ok(response);
		}

	}

	@DeleteMapping("/pacote")
	public @ResponseBody ResponseEntity<Response<Boolean>> deletarPacote(@RequestParam Long idPacote) {
		Response<Boolean> response = new Response<Boolean>();

		if (!pacoteRepository.findById(idPacote).isPresent()) {
			response.setData(false);
			response.addError("Não conseguimos encontrar esta Pacote");

			return ResponseEntity.badRequest().body(response);
		} else {
			pacoteRepository.deleteById(idPacote);
			response.setData(true);

			return ResponseEntity.ok(response);
		}

	}

}
