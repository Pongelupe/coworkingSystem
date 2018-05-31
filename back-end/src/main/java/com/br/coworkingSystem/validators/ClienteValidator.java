package com.br.coworkingSystem.validators;

import java.util.Date;

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

		Date dataCadastro = cliente.getDataCadastro();
		
		if (dataCadastro == null)
			erros.rejectValue("dataCadastro", "A data de cadastro do cliente não foi informada");
				
		new PessoaValidator("pessoa.").validate(cliente.getPessoa(), erros);

	}

}
