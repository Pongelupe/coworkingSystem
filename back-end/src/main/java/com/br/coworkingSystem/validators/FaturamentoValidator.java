package com.br.coworkingSystem.validators;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Faturamento;

public class FaturamentoValidator implements Validator {

	private Faturamento faturamento;

	@Override
	public boolean supports(Class<?> clazz) {
		return Faturamento.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		faturamento = (Faturamento) target;

		double valorFaturamento = faturamento.getValorFaturamento();
		Date dtEmissao = faturamento.getDtEmissao();
		Date dtVencimento = faturamento.getDtVencimento();

		if (valorFaturamento <= 0)
			erros.rejectValue("valorFaturamento", "O valorFaturamento não pode ser menor que 0");

		if (dtEmissao == null)
			erros.rejectValue("dtEmissao", "A data de faturamento não pode ser vazia");

		if (dtVencimento == null)
			erros.rejectValue("dtVencimento", "A data de faturamento não pode ser vazia");

	}

}
