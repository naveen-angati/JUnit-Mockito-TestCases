package com.test.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private int status;
	@Override
	
	public String getLocalizedMessage() {
		return this.getMessage();
	}

}
