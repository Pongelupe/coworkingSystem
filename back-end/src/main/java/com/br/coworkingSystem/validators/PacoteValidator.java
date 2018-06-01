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
		int quantidadeHoras = pacote.getQuantidadeHoras();
		double valor = pacote.getValor();

		if (nome == null || nome.isEmpty())
			errors.rejectValue("nome", "O nome do pacote não foi informado");
		
		if (quantidadeHoras <= 0)
			errors.rejectValue("quantidadeHoras", "A quantidade de horas do pacote deve ser maior que 0");
		
		if (valor < 0)
			errors.rejectValue("valor", "O valor do pacote não pode ser menor que 0");

	}

}
