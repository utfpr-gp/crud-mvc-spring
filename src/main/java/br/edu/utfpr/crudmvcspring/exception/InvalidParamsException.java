package br.edu.utfpr.crudmvcspring.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

//retorna uma resposta 404 se os parâmetros forem inválidos
//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Parâmetro(s) inválido(s).")
public class InvalidParamsException extends IllegalArgumentException {

	public InvalidParamsException(String message){
		super(message);
	}
}
