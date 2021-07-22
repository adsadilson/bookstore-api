package br.com.apssystem.bookstore.api.execption;

public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntidadeNaoEncontradaException(String message) {
		super(message);
	}

}
