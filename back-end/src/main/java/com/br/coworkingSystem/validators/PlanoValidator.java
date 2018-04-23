package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Plano;

public class PlanoValidator implements Validator {

	private Plano plano;

	@Override
	public boolean supports(Class<?> clazz) {
		return Plano.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		plano = (Plano) target;

		String nome = plano.getNome();

		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser inválido");
	}

}
