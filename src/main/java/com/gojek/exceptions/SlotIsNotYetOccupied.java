package com.gojek.exceptions;

public class SlotIsNotYetOccupied extends Exception {

	private static final long serialVersionUID = 1L;

	public SlotIsNotYetOccupied(String message) {
		super(message);
	}
}
