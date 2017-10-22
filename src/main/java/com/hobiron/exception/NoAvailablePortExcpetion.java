package com.hobiron.exception;

public class NoAvailablePortExcpetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NoAvailablePortExcpetion(String msg) {
		super(msg);
	}

}
