package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.ProdutoServico;

public class ProdutoServicoValidator implements Validator {

	private ProdutoServico produto;

	@Override
	public boolean supports(Class<?> clazz) {
		return ProdutoServico.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		this.produto = (ProdutoServico) target;

		double valor = produto.getValor();
		if (valor <= 0)
			errors.rejectValue("valor", "O valor não pode ser menor que 0");
	}

}
