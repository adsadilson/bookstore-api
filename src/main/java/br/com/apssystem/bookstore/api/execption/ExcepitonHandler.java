package br.com.apssystem.bookstore.api.execption;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ExcepitonHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<StanderError> objectNotFoundException(EntidadeNaoEncontradaException ex, ServletRequest request) {
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<StanderError> objectEmUsoException(EntidadeEmUsoException ex, ServletRequest request) {
		StanderError error = new StanderError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				ex.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos");
		for (FieldError e : ex.getBindingResult().getFieldErrors()) {
			error.addErrors(e.getField(), e.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

}
