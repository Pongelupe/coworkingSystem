package com.br.coworkingSystem.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Consumo;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.repositories.ClienteRepository;
import com.br.coworkingSystem.repositories.ConsumoRepository;
import com.br.coworkingSystem.repositories.SalaRepository;
import com.br.coworkingSystem.validators.ConsumoValidator;

@Controller
public class ConsumoController {

	@Autowired
	private ConsumoRepository consumoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private SalaRepository salaRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ConsumoValidator());
	}

	@GetMapping("/consumos/{idCliente}")
	public @ResponseBody ResponseEntity<Response<List<Consumo>>> getConsumo(@PathVariable Long idCliente) {
		Response<List<Consumo>> response = new Response<List<Consumo>>();
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if (cliente.isPresent()) {
			List<Consumo> consumos = consumoRepository.findAllBySolicitante(cliente.get());
			response.setData(consumos);
			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Cliente não encontrado não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/consumo/{idCliente}/{idSala}")
	public @ResponseBody ResponseEntity<Response<Integer>> cadastraConsumo(@RequestBody @Valid Consumo consumo,
			BindingResult result, @PathVariable Long idCliente, @PathVariable int idSala) {

		Response<Integer> response = new Response<Integer>();

		if (result.hasErrors() || salaRepository.existsById(idSala) || clienteRepository.existsById(idCliente)) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));
			response.addError("Cliente/Sala podem estar errados!");

			return ResponseEntity.badRequest().body(response);
		} else {
			consumo.setSala(salaRepository.getOne(idSala));
			consumo.setSolicitante(clienteRepository.getOne(idCliente));
			consumo = consumoRepository.save(consumo);
			response.setData(consumo.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PostMapping("/finalizarConsumo/{idConsumo}")
	public @ResponseBody ResponseEntity<Response<Integer>> finalizarConsumo(@PathVariable Long idConsumo,
			@RequestBody Date dataFinal) {

		Response<Integer> response = new Response<Integer>();
		Optional<Consumo> optionalConsumo = consumoRepository.findById(idConsumo);

		if (optionalConsumo.isPresent() && dataFinal != null) {
			Consumo consumo = optionalConsumo.get();
			consumo.setDataFinal(dataFinal);
			consumo.setFaturado(true);
			consumoRepository.save(consumo);

			response.setData(consumo.getId());

			return ResponseEntity.ok(response);
		} else {
			response.setData(null);
			response.addError("Consumo não encontrado");

			return ResponseEntity.badRequest().body(response);
		}

	}

}