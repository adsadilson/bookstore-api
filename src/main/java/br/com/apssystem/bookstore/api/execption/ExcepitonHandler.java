package br.com.apssystem.bookstore.api.execption;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ExcepitonHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StanderError> objectNotFoundException(ObjectNotFoundException ex, ServletRequest request) {
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(ObjectEmUsoException.class)
	public ResponseEntity<StanderError> objectEmUsoException(ObjectEmUsoException ex, ServletRequest request) {
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
