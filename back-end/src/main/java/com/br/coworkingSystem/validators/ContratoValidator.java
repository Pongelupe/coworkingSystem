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

		if (contrato.getDiaVencimento() <= 0)
			errors.rejectValue("diaVencimento", "O dia do vencimento do contrato não foi informado");
		
		if (contrato.getMesesDuracaoContrato() <= 0)
			errors.rejectValue("mesesDuracaoContrato", "A duração do contrato não foi informada");
		
		if (contrato.getDataInicio() == null)
			errors.rejectValue("dataInicio", "A data de início do contrato não foi informada");
		
		
	}

}
