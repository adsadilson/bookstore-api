package br.com.apssystem.bookstore.api.execption;

public class ObjectEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectEmUsoException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectEmUsoException(String message) {
		super(message);
	}

}
