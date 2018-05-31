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

		String cpf = cliente.getPessoa().getCpfCnpj();
		String nome = cliente.getPessoa().getNome();
		String email = cliente.getPessoa().getEmail();
		String telefonePrincipal = cliente.getPessoa().getTelefonePrincipal();

		
		if (cpf == null || cpf.isEmpty())
			erros.rejectValue("cpfCnpj", "O cpf/cnpj não pode ser vazio");

		if (nome == null || nome.isEmpty())
			erros.rejectValue("nome", "O nome não pode ser vazio");

		if (email == null || email.isEmpty())
			erros.rejectValue("email", "O e-mail não pode ser vazio");
		
		if (telefonePrincipal == null || telefonePrincipal.isEmpty())
			erros.rejectValue("telefonePrincipal", "O telefone principal não pode ser vazio");

	}

}
