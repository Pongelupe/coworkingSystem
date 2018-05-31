package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Pessoa;

public class PessoaValidator implements Validator {

	private Pessoa pessoa;
	private String auxCampo;
	
	public PessoaValidator() {
		setAuxCampo("");
	};
	
	public PessoaValidator(String auxCampo) {
		setAuxCampo(auxCampo);
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Pessoa.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		pessoa = (Pessoa) target;

		String cpf = pessoa.getCpfCnpj();
		String nome = pessoa.getNome();
		String email = pessoa.getEmail();
		String telefonePrincipal = pessoa.getTelefonePrincipal();

		if (cpf == null || cpf.isEmpty())
			erros.rejectValue(auxCampo + "cpfCnpj", "O cpf/cnpj não foi informado");

		if (nome == null || nome.isEmpty())
			erros.rejectValue(auxCampo + "nome", "O nome não foi informado");

		if (email == null || email.isEmpty())
			erros.rejectValue(auxCampo + "email", "O e-mail não foi informado");
		
		if (telefonePrincipal == null || telefonePrincipal.isEmpty())
			erros.rejectValue(auxCampo + "telefonePrincipal", "O telefone principal não foi informado");


		new EnderecoValidator(auxCampo + "endereco.").validate(pessoa.getEndereco(), erros);
		
	}

	public String getAuxCampo() {
		return auxCampo;
	}

	public void setAuxCampo(String auxCampo) {
		this.auxCampo = auxCampo;
	}

}
