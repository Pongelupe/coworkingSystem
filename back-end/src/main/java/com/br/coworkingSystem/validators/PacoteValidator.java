package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Pacote;

public class PacoteValidator implements Validator {

	private Pacote pacote;

	@Override
	public boolean supports(Class<?> clazz) {
		return Pacote.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		pacote = (Pacote) target;

		String nome = pacote.getNome();

		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome não pode ser inválido");

	}

}
