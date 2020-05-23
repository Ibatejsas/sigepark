package com.dim.sigepark.exception;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice // gestion de excepciones
public class SigueparkExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler({ HttpClientErrorException.class })
	public ResponseEntity<ErrorMessage> handlerClientError(HttpClientErrorException ex) {
		return ResponseEntity.status(ex.getStatusCode()).contentType(MediaType.APPLICATION_JSON)
				.body(new ErrorMessage(ex.getMessage()));
	}

	// anadir excepciones aqui para mejorar presentacion en front 

}
