package com.gojek.exceptions;

public class InvalidCommand extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCommand(String message) {
		super(message);
	}
}
