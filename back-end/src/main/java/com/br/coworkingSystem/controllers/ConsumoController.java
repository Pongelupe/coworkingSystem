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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Consumo;
import com.br.coworkingSystem.model.ProdutoServico;
import com.br.coworkingSystem.model.Response;
import com.br.coworkingSystem.model.Sala;
import com.br.coworkingSystem.repositories.ClienteRepository;
import com.br.coworkingSystem.repositories.ConsumoRepository;
import com.br.coworkingSystem.repositories.ProdutoServicoRepository;
import com.br.coworkingSystem.repositories.SalaRepository;
import com.br.coworkingSystem.validators.ConsumoValidator;

@Controller
public class ConsumoController {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private ProdutoServicoRepository produtoServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private SalaRepository salaRepository;

	@InitBinder("consumo")
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new ConsumoValidator());
	}

	@GetMapping("/consumos")
	public @ResponseBody ResponseEntity<Response<List<Consumo>>> getConsumo() {
		Response<List<Consumo>> response = new Response<List<Consumo>>();
		List<Consumo> consumos = consumoRepository.findAllByFaturado(false);
		response.setData(consumos);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/consumo")
	public @ResponseBody ResponseEntity<Response<Long>> cadastraConsumo(@RequestParam Long idCliente,
			@RequestParam Long idSala, @RequestParam Long idProdutoServico, @RequestParam Long idSolicitante,
			@RequestBody @Valid Consumo consumo, BindingResult result) {

		Response<Long> response = new Response<Long>();
		Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
		Optional<Sala> salaOptional = salaRepository.findById(idSala);
		Optional<ProdutoServico> produtoServicoOptional = produtoServicoRepository.findById(idProdutoServico);

		if (result.hasErrors()) {
			response.setData(null);
			result.getAllErrors().forEach(error -> response.addError(error.getCode()));
			if (!clienteOptional.isPresent())
				response.addError("Cliente não encontrado");
					
			if (!salaOptional.isPresent() && !produtoServicoOptional.isPresent())
				response.addError("Não foi encontrado a sala nem produto que deseja consumir.");
			
			return ResponseEntity.badRequest().body(response);
		} else {
			consumo.setCliente(clienteOptional.get());
			
			if(salaOptional.isPresent())
				consumo.setSala(salaOptional.get());
			
			if(produtoServicoOptional.isPresent())
				consumo.setProdutoServico(produtoServicoOptional.get());
			
			consumo = consumoRepository.save(consumo);

			response.setData(consumo.getId());

			return ResponseEntity.ok(response);
		}
	}

	@PostMapping("/finalizarConsumo")
	public @ResponseBody ResponseEntity<Response<Long>> finalizarConsumo(@RequestParam Long idConsumo) {

		Response<Long> response = new Response<Long>();
		Optional<Consumo> optionalConsumo = consumoRepository.findById(idConsumo);

		if (optionalConsumo.isPresent()) {
			Consumo consumo = optionalConsumo.get();
			consumo.setDataFinal(new Date());
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