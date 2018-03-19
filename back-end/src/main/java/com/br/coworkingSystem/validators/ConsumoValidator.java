package com.br.coworkingSystem.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Consumo;

public class ConsumoValidator implements Validator {

	private Consumo consumo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Consumo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		consumo = (Consumo) target;

		Date dataInicial = consumo.getDataInicial();

		boolean dtInicialIsNull = dataInicial == null;
		if (dtInicialIsNull)
			erros.rejectValue("dataInicial", "A data inicial não pode ser null");

	}

}
