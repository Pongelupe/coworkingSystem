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

		double valor = faturamento.getValor();
		Date dtEmissao = faturamento.getDataEmissao();
		Date dtVencimento = faturamento.getDataVencimento();

		if (valor <= 0)
			erros.rejectValue("valor", "O valor do faturamento deve ser maior que 0");

		if (dtEmissao == null)
			erros.rejectValue("dtEmissao", "A data de emissão do faturamento não foi informada");

		if (dtVencimento == null)
			erros.rejectValue("dtVencimento", "A data de vencimento do faturamento não foi informada");

	}

}
