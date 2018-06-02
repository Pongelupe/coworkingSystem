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
		String cpf = cliente.getCpfCnpj();
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		String telefonePrincipal = cliente.getTelefonePrincipal();

		if (dataCadastro == null)
			erros.rejectValue("dataCadastro", "A data de cadastro do cliente não foi informada");

		if (cpf == null || cpf.isEmpty())
			erros.rejectValue("cpfCnpj", "O cpf/cnpj não foi informado");

		if (nome == null || nome.isEmpty())
			erros.rejectValue("nome", "O nome não foi informado");

		if (email == null || email.isEmpty())
			erros.rejectValue("email", "O e-mail não foi informado");

		if (telefonePrincipal == null || telefonePrincipal.isEmpty())
			erros.rejectValue("telefonePrincipal", "O telefone principal não foi informado");

		new EnderecoValidator("endereco.").validate(cliente.getEndereco(), erros);

	}

}
