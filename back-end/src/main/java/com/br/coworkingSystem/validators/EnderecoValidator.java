package com.br.coworkingSystem.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.br.coworkingSystem.model.Endereco;

public class EnderecoValidator implements Validator {

	private Endereco end;
	private String auxCampo;
	
	public EnderecoValidator() {
		setAuxCampo("");
	};
	
	public EnderecoValidator(String auxCampo) {
		setAuxCampo(auxCampo);
	}

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Endereco.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors erros) {
		end = (Endereco) target;

		String cep = end.getCep();
		String rua = end.getRua();
		int numero = end.getNumero();
		String bairro = end.getBairro();
		String cidade = end.getCidade();		
		String estado = end.getEstado();
		String pais = end.getPais();

		
		if (cep == null || cep.isEmpty())
			erros.rejectValue(auxCampo + "cep", "O cep não foi informado");

		if (rua == null || rua.isEmpty())
			erros.rejectValue(auxCampo + "rua", "A rua não foi informada");

		if (numero <= 0)
			erros.rejectValue(auxCampo + "numero", "O número não foi informado");
		
		if (bairro == null || bairro.isEmpty())
			erros.rejectValue(auxCampo + "bairro", "O bairro não foi informado");
		
		if (cidade == null || cidade.isEmpty())
			erros.rejectValue(auxCampo + "cidade", "A cidade não foi informada");
		
		if (estado == null || estado.isEmpty())
			erros.rejectValue(auxCampo + "estado", "O estado não foi informado");
		
		if (pais == null || pais.isEmpty())
			erros.rejectValue(auxCampo + "rua", "O país não foi informado");
	}
	
	public String getAuxCampo() {
		return auxCampo;
	}

	public void setAuxCampo(String auxCampo) {
		this.auxCampo = auxCampo;
	}


}
