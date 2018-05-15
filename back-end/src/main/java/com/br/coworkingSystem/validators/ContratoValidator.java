package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Contrato;

public class ContratoValidator implements Validator {

	private Contrato contrato;

	@Override
	public boolean supports(Class<?> clazz) {
		return Contrato.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		this.contrato = (Contrato) target;

		if (contrato.getDataFim() != null)
			errors.rejectValue("dataFim", "A data fim não pode ser nula");
	}

}
