package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Correspondencia;

public class CorrespondenciaValidator implements Validator {

	private Correspondencia correspondencia;

	@Override
	public boolean supports(Class<?> clazz) {
		return Correspondencia.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		correspondencia = (Correspondencia) target;

		if (correspondencia.getRemetente() == null || correspondencia.getRemetente().isEmpty())
			errors.rejectValue("remetente", "O remetente não foi informado");
		
		if (correspondencia.getDataRecebimento() == null )
			errors.rejectValue("dataRecebimento", "A data de recebimento não foi informada");
	}

}
