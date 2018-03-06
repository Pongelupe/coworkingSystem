package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Cliente;

public class ClienteValidator implements Validator {

	private Cliente cliente;

	@Override
	public boolean supports(Class<?> clazz) {
		return Cliente.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		cliente = (Cliente) target;

		String cpf = cliente.getCpfCnpj();
		
		
		if (cpf == null || cpf.isEmpty())
			erros.rejectValue("cpfCnpj", "o cpf/cnpj não pode ser vazio");

	}

}
