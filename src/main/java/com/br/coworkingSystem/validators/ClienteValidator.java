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
		String nome = cliente.getNome();
		String email = cliente.getEmail();

		if (cpf == null || cpf.isEmpty())
			erros.rejectValue("cpfCnpj", "O cpf/cnpj não pode ser vazio");

		if (nome == null || nome.isEmpty())
			erros.rejectValue("nome", "O nome não pode ser vazio");

		if (email == null || email.isEmpty())
			erros.rejectValue("email", "O email não pode ser vazio");

	}

}
