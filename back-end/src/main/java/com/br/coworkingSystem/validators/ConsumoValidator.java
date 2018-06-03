package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Consumo;;

public class ConsumoValidator implements Validator {

	private Consumo consumo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Consumo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		consumo = (Consumo) target;

		if (consumo.getDataInicial() == null)
			erros.rejectValue("dataInicial", "A data inicial não foi informada");
		
		if (consumo.getCliente() == null)
			erros.rejectValue("dataInicial", "O cliente não foi informado");
		
		if (consumo.getSala() == null && consumo.getProdutoServico() == null)
			erros.rejectValue("sala", "Não foi informado o item que deseja registrar o consumo. Informe a sala ou produto/serviço.");

	}

}
