package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Sala;
import com.br.coworkingSystem.model.TipoSala;

public class SalaValidator implements Validator {

	private Sala sala;

	@Override
	public boolean supports(Class<?> clazz) {
		return Sala.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		sala = (Sala) target;

		String nome = sala.getNome();
		int quantidadeEstacoes = sala.getQuantidadeEstacoes();
		double valorHora = sala.getValorHora();
		TipoSala tipoSala = sala.getTipoSala();

		if (nome == null || nome.isEmpty())
			erros.rejectValue("nome", "O nome não pode ser vazio");

		if (tipoSala == null || tipoSala.name().isEmpty())
			erros.rejectValue("tipoSala", "O tipoSala não pode ser vazio ou está inválido");

		if (quantidadeEstacoes < 0)
			erros.rejectValue("quantidadeEstacoes", "A quantidade de estações não pode ser menor que 0");

		if (valorHora <= 0)
			erros.rejectValue("valorHora", "O valorHora não pode ser menor que 0");

	}

}
