package com.gojek.exceptions;

public class NoFreeSlotsAvailable extends Exception {

	private static final long serialVersionUID = 1L;

	public NoFreeSlotsAvailable(String message) {
		super(message);
	}
}
