package io.springmvcrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="No user present with the given id")
public class NoUserPresent extends Exception {
	private static final long serialVersionUID = 620884532654265912L;

	public NoUserPresent(String string) {
		super(string);
	}
	
	public NoUserPresent(String string, Throwable cause) {
		super(string,cause);
	}
	

	
	
}
