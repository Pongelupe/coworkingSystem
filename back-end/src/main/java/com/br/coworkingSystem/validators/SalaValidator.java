package com.br.coworkingSystem.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Sala;
import com.br.coworkingSystem.model.enuns.TipoSala;

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
		Date horarioInicial = sala.getHorarioInicial();
		Date horarioFinal = sala.getHorarioFinal();
		int quantidadeEstacoes = sala.getQuantidadeEstacoes();
		double valorHora = sala.getValorHora();
		TipoSala tipo = sala.getTipo();

		if (nome == null || nome.isEmpty())
			erros.rejectValue("nome", "O nome não foi informado");
		
		if (horarioInicial == null)
			erros.rejectValue("horarioInicial", "O horário inicial não foi informado");
		
		if (horarioFinal == null)
			erros.rejectValue("horarioFinal", "O horário final não foi informado");

		if (tipo == null || tipo.name().isEmpty())
			erros.rejectValue("tipo", "O tipo da sala não foi informado ou é inválido");

		if (quantidadeEstacoes < 0)
			erros.rejectValue("quantidadeEstacoes", "A quantidade de estações não pode ser menor que 0");

		if (valorHora <= 0)
			erros.rejectValue("valorHora", "O valor da hora da sala não pode ser menor que 0");

	}

}
