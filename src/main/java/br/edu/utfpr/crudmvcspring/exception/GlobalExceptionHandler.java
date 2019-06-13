package br.edu.utfpr.crudmvcspring.exception;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//	@ExceptionHandler(EntityNotFoundException.class)
//	public ResponseEntity<?> handleException(EntityNotFoundException exception, HttpServletRequest request) {
//		System.err.print("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//
//		Response response = new Response();
//		response.addError("O dado solicitado não foi encontrado.");
//
//		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(EntityExistsException.class)
//	public ResponseEntity<?> handleException(EntityExistsException exception, HttpServletRequest request) {
//		log.error("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//
//		Response response = new Response();
//		response.addError(exception.getMessage());
//
//		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
//	}
//
//	@ExceptionHandler(DataIntegrityViolationException.class)
//	public ResponseEntity<?> handleException(DataIntegrityViolationException exception,
//			HttpServletRequest request) {
//		log.error("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//		Response response = new Response();
//		response.addError(exception.getMessage());
//
//		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
//	}
//
//	@ExceptionHandler(InvalidParamsException.class)
//	public ResponseEntity<?> handleException(InvalidParamsException exception, HttpServletRequest request) {
//		log.error("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//		Response response = new Response();
//		response.setErrors(exception.errors());
//
//		// return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//		return ResponseEntity.badRequest().body(response);
//	}
//
//	@ExceptionHandler(EmptyResultDataAccessException.class)
//	public ResponseEntity<?> handleException(EmptyResultDataAccessException exception,
//			HttpServletRequest request) {
//		log.error("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//		Response response = new Response();
//		response.addError(exception.getMessage());
//
//		return new ResponseEntity<>(response, HttpStatus.GONE);
//	}
//
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<?> handleException(ConstraintViolationException exception,
//			HttpServletRequest request) {
//		log.error("Error in process request: " + request.getRequestURL() + " cause by: "
//				+ exception.getClass().getSimpleName());
//		Response response = new Response();
//		response.addError(exception.getMessage());
//
//		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//
//
//	@ExceptionHandler(MaxUploadSizeExceededException.class)
//	public ResponseEntity<?> handleException(MaxUploadSizeExceededException exception) {
//		Response response = new Response();
//		response.addError("Tamanho da imagem excedido.");
//
//		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//	}

//	@ExceptionHandler(BadCredentialsException.class)
//	public ResponseEntity<?> handleException(BadCredentialsException exception) {
//		Response response = new Response();
//		response.addError("Por favor, insira os valores válidos de usuário/senha");
//
//		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
//	}
}
