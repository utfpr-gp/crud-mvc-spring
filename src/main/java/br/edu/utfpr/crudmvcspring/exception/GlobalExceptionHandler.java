package br.edu.utfpr.crudmvcspring.exception;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	/**
	 *
	 * Tratador genérico de exceptions.
	 * Trata de qualquer exceptions, caso não haja tratador mais específico.
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		log.error("[URL] : {}", req.getRequestURL(), e);

		//caso contrário, envia para a visão de erro
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", e.getMessage());
		mv.addObject("url", req.getRequestURL());
		mv.setViewName("error-handler");
		return mv;
	}

	/**
	 *
	 * Tratador de erro mais específico
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = EntityNotFoundException.class)
	public String entityNotFound(HttpServletRequest req, Exception e) throws Exception {

		log.error("[URL] : {}", req.getRequestURL(), e);
		return "not-found";
	}

	/**
	 *
	 * Trata de erros gerados pelo banco de dados.
	 * Note que @ExceptionHandler recebe duas classes como argumentos.
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = {DataIntegrityViolationException.class, ConstraintViolationException.class})
	public String handleDatabaseException(HttpServletRequest req, Exception e) {
		return "error-database";
	}
}
