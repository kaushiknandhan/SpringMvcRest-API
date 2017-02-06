package io.springmvcrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = -6653549076222592258L;
	public UserAlreadyExists(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}
	public UserAlreadyExists(String string, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(string,cause);
	}
	

	

}
