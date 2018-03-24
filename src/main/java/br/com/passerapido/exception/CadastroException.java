package br.com.passerapido.exception;

public class CadastroException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroException(String message, Throwable cause) {
		super(message, cause);
	}

	public CadastroException(String message) {
		super(message);
	}
	
	

}
